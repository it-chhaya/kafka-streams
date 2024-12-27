package dev.chanchhaya.mskafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableKafkaStreams
@Slf4j
public class MsKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsKafkaApplication.class, args);

        //exploreWindowTopology();
    }

//    private static void exploreWindowTopology() {
//
//        Topology topology = ExploreWindowTopology.build();
//
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "greetings-app");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10000);
//
//        // Create topic firstly
//        // createTopics(properties, List.of(ExploreWindowTopology.WINDOW_WORDS));
//
//        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
//
//        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
//
//        log.info("Starting Windowed streams");
//        kafkaStreams.start();
//    }
//
//    private static void greetingTopology() {
//        Properties properties = new Properties();
//        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "greetings-app");
//        //properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "34.87.93.243:9093");
//        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
//        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
//
//        // Create topic firstly
//        createTopics(properties, List.of("source.public.products"));
//
//        var greetingsTopology = GreetingTopology.buildTopology();
//
//        var kafkaStreams = new KafkaStreams(greetingsTopology, properties);
//
//        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
//
//        kafkaStreams.start();
//    }
//
//    private static void createTopics(Properties properties, List<String> greetings) {
//
//        AdminClient adminClient = AdminClient.create(properties);
//        int partitions = 1;
//        short replication = 1;
//
//        List<NewTopic> newTopics = greetings
//                .stream()
//                .map(topic -> new NewTopic(topic, partitions, replication))
//                .collect(Collectors.toList());
//
//        CreateTopicsResult createdTopicsResult = adminClient.createTopics(newTopics);
//
//        try {
//            createdTopicsResult
//                    .all()
//                    .get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

}
