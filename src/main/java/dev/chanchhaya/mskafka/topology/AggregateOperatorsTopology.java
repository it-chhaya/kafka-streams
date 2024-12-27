package dev.chanchhaya.mskafka.topology;

import dev.chanchhaya.mskafka.event.ProductStock;
import dev.chanchhaya.mskafka.event.ProductStockAggregate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j
@Component
public class AggregateOperatorsTopology {

    public static final String PRODUCT_STOCK_IN_TOPIC = "product-stock-in";

    @Autowired
    public Topology buildTopology(StreamsBuilder streamsBuilder) {

        KStream<String, ProductStock> productStockIn = streamsBuilder
                .stream(PRODUCT_STOCK_IN_TOPIC,
                        Consumed.with(Serdes.String(), new JsonSerde<>(ProductStock.class)));

        productStockIn
                .print(Printed.<String, ProductStock>toSysOut().withLabel(PRODUCT_STOCK_IN_TOPIC));

        KGroupedStream<String, ProductStock> groupProductStock = productStockIn
                .groupByKey();
        //.groupBy((key, value) -> value.getCode());


        operatorCount(groupProductStock);

        operatorReduce(groupProductStock);

        operatorAggregate(groupProductStock);

        return streamsBuilder.build();
    }

    private void operatorAggregate(KGroupedStream<String, ProductStock> groupProductStock) {

        Initializer<ProductStockAggregate> aggregateInitializer = () -> new ProductStockAggregate(
                "",
                new HashSet<>(),
                0
        );

        Aggregator<String, ProductStock, ProductStockAggregate> aggregator =
                (key, value, aggregate) -> {
                    aggregate.valueList().add(value);
                    return new ProductStockAggregate(
                            key,
                            aggregate.valueList(),
                            aggregate.runningCount() + 1
                    );
                };

        KTable<String, ProductStockAggregate> groupProductStockAggregate = groupProductStock
                .aggregate(
                        aggregateInitializer,
                        aggregator,
                        Materialized.<String, ProductStockAggregate, KeyValueStore<Bytes, byte[]>>as("product-stock-aggregated-store")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JsonSerde<>(ProductStockAggregate.class))
                );

        groupProductStockAggregate
                .toStream()
                .print(Printed.<String, ProductStockAggregate>toSysOut().withLabel(PRODUCT_STOCK_IN_TOPIC));

    }

    private void operatorReduce(KGroupedStream<String, ProductStock> groupProductStock) {

        KTable<String, ProductStock> reducedProductStock = groupProductStock
                .reduce((value1, value2) -> ProductStock
                                .builder()
                                .code(value2.getCode())
                                .price(value2.getPrice())
                                .quantity(value1.getQuantity() + value2.getQuantity())
                                .stockDate(value2.getStockDate())
                                .build(),
                        Materialized.<String, ProductStock, KeyValueStore<Bytes, byte[]>>as("reduced-product-stock")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JsonSerde<>(ProductStock.class))
                );

        reducedProductStock
                .toStream()
                .print(Printed.<String, ProductStock>toSysOut().withLabel("reduced-product-stock"));
    }

    private void operatorCount(KGroupedStream<String, ProductStock> groupProductStock) {
        KTable<String, Long> countProductStock = groupProductStock
                .count(Named.as("counted-product-stock"),
                        Materialized.as("counted-product-stock"));
        countProductStock
                .toStream()
                .print(Printed.<String, Long>toSysOut().withLabel("counted-product-stock"));
    }

}
