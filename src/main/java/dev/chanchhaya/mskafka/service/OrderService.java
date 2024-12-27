package dev.chanchhaya.mskafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final KTableJsonStoreService kTableJsonStoreService;

    public List<Object> getOrders() {

        ReadOnlyKeyValueStore<String, Object> ordersStore =
                kTableJsonStoreService.ordersStore();

        List<Object> orderRest = new ArrayList<>();

        try (KeyValueIterator<String, Object> orders = ordersStore.all()) {
            while(orders.hasNext()) {
                KeyValue<String, Object> order  = orders.next();
                orderRest.add(order.value);
            }
        }

        return orderRest;
    }

}
