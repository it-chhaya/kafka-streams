package dev.chanchhaya.mskafka.config;

import dev.chanchhaya.mskafka.topology.AggregateOperatorsTopology;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicProductStockIn() {
        return TopicBuilder
                .name(AggregateOperatorsTopology.PRODUCT_STOCK_IN_TOPIC)
                .partitions(3)
                .replicas(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic ordersEventTopicIn() {
        return TopicBuilder
                .name("order-events-topic-in")
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic ordersEventTopicOut() {
        return TopicBuilder
                .name("order-events-topic-out")
                .partitions(1)
                .compact()
                .build();
    }


    @Bean
    public NewTopic wordsEventTopicIn() {
        return TopicBuilder
                .name("words")
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic outWordsEventTopicIn() {
        return TopicBuilder
                .name("out-words")
                .partitions(1)
                .compact()
                .build();
    }

}
