package dev.chanchhaya.mskafka.event;

import java.util.Set;

public record ProductStockAggregate(
        String key,
        Set<ProductStock> valueList,
        int runningCount
)  {

}
