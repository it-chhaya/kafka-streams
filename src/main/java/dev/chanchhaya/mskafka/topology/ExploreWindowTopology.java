//package dev.chanchhaya.mskafka.topology;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.kstream.*;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//@Slf4j
//public class ExploreWindowTopology {
//
//    public static final String WINDOW_WORDS = "window-words";
//
//    public static Topology build() {
//        StreamsBuilder builder = new StreamsBuilder();
//
//        KStream<String, String> wordsStream = builder
//                .stream(WINDOW_WORDS,
//                        Consumed.with(Serdes.String(), Serdes.String()));
//
//        tumblingWindows(wordsStream);
//
//        return builder.build();
//    }
//
//
//    private static void tumblingWindows(KStream<String, String> wordsStream) {
//
//        Duration windowSize = Duration.ofMinutes(5);
//
//        TimeWindows timeWindows = TimeWindows.ofSizeWithNoGrace(windowSize);
//
//        KTable<Windowed<String>, Long> windowedTable = wordsStream
//                .groupByKey()
//                .windowedBy(timeWindows)
//                .count();
//
//        windowedTable
//                .toStream()
//                .peek((key, value) -> {
//                    log.info("tumblingWindows: key : {}, value : {}", key, value);
//                    printLocationDateTimes(key, value);
//                })
//                .print(Printed.<Windowed<String>, Long>toSysOut());
//    }
//
//
//    private static void printLocationDateTimes(Windowed<String> key, Long value) {
//        Instant startTime = key.window().startTime();
//        Instant endTime = key.window().endTime();
//
//        LocalDateTime startLdt = LocalDateTime.ofInstant(startTime, ZoneId.of(ZoneId.SHORT_IDS.get("VST")));
//        LocalDateTime endLdt = LocalDateTime.ofInstant(endTime, ZoneId.of(ZoneId.SHORT_IDS.get("VST")));
//
//        log.info("startLdt: {}, endLdt: {}, count: {}", startLdt, endLdt, value);
//    }
//
//}
