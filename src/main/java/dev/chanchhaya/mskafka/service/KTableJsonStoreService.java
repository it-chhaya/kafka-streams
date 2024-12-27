package dev.chanchhaya.mskafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KTableJsonStoreService {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    public ReadOnlyKeyValueStore<String, Object> ordersStore() {
        return streamsBuilderFactoryBean
                .getKafkaStreams()
                .store(
                        StoreQueryParameters.fromNameAndType(
                                "order-events-topic-store",
                                QueryableStoreTypes.keyValueStore()
                        )
                );
    }

}
