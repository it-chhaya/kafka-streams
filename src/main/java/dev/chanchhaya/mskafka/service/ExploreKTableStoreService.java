package dev.chanchhaya.mskafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExploreKTableStoreService {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    public ReadOnlyKeyValueStore<String, String> productsStore() {
        return streamsBuilderFactoryBean
                .getKafkaStreams()
                .store(
                        StoreQueryParameters.fromNameAndType(
                                "words-store",
                                QueryableStoreTypes.keyValueStore()
                        )
                );
    }

}
