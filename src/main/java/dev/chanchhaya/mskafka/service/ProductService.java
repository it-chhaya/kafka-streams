package dev.chanchhaya.mskafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ExploreKTableStoreService exploreKTableStoreService;

    public List<KeyValue<String, String>> getData() {

        var wordsStore = getWords();
        var words = wordsStore.all();

        var spliterator = Spliterators.spliteratorUnknownSize(words, 0);

        return StreamSupport.stream(spliterator, false)
                .toList();
    }

    private ReadOnlyKeyValueStore<String, String> getWords() {
        return exploreKTableStoreService.productsStore();
    }

}
