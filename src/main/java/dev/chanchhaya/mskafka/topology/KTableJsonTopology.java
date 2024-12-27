package dev.chanchhaya.mskafka.topology;

import dev.chanchhaya.mskafka.event.OrderEvent;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class KTableJsonTopology {

    final String sourceTopic = "order-events-topic-in";
    final String store = "order-events-topic-store";
    final String targetTopic = "order-events-topic-out";




    @Autowired
    public Topology buildKTableJsonTopology(StreamsBuilder streamsBuilder) {


        KTable<String, OrderEvent> orders = streamsBuilder
                .table(
                        sourceTopic,
                        Materialized.as(store)
                );

        orders
                .toStream()
                .print(Printed.<String, OrderEvent>toSysOut().withLabel("order"));

        KTable<String, OrderEvent> processedOrders = orders
                .filter((key, value) -> value.getOrderPrice() > 100);

        processedOrders
                .toStream()
                .print(Printed.<String, OrderEvent>toSysOut().withLabel("processedOrders"));

        processedOrders
                .toStream()
                .to(
                        targetTopic
                );

        return streamsBuilder.build();
    }

}
