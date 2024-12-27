//package dev.chanchhaya.mskafka.topology;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.kstream.Consumed;
//import org.apache.kafka.streams.kstream.Printed;
//import org.apache.kafka.streams.kstream.Produced;
//
//public class GreetingTopology {
//
//    //public final static String GREETINGS = "greetings";
//    public final static String GREETINGS = "source.public.marketing_stream";
//    public final static String GREETINGS_UPPERCASE = "greetings_uppercase";
//
//    public static Topology buildTopology() {
//
//        // Building pipeline
//        StreamsBuilder streamsBuilder = new StreamsBuilder();
//
//        // Reading from theKafka topic
//        var greetingsStream = streamsBuilder
//                .stream(GREETINGS,
//                        Consumed.with(Serdes.String(), Serdes.String()));
//
//        greetingsStream
//                .print(Printed.<String, String>toSysOut().withLabel("greetingStream"));
//
//        // Performing the data
////        var modifiedStream = greetingsStream
////                .filter((key, value) -> value.length() > 5)
////                .map((key, value) -> KeyValue.pair(key.toUpperCase(), value.toUpperCase()))
////                .mapValues((readOnlyKey, value) -> value.toUpperCase());
//
//        /*var modifiedStream = greetingsStream
//                .map((key, value) -> KeyValue.pair(key.toUpperCase(), value.toUpperCase()));
//
//        // Writing data back to Kafka topic using to operator
//        modifiedStream
//                .to(GREETINGS_UPPERCASE,
//                        Produced.with(Serdes.String(), Serdes.String()));
//
//        modifiedStream
//                .print(Printed.<String, String>toSysOut().withLabel("modifiedStream"));*/
//
//        return streamsBuilder.build();
//    }
//
//}
