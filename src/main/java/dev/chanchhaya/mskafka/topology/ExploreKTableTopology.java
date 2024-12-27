package dev.chanchhaya.mskafka.topology;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExploreKTableTopology {

    public static String WORDS = "words";

    @Autowired
    public Topology build(StreamsBuilder streamsBuilder) {

        KTable<String, String> wordsTable = streamsBuilder
                .table(WORDS,
                        Consumed.with(Serdes.String(), Serdes.String()),
                        Materialized.as("words-store")
                );

        wordsTable
                .toStream()
                .print(Printed.<String, String>toSysOut().withLabel("Word Table"));

        KTable<String, String> processedWords = wordsTable
                .filter((key, value) -> value.length() > 2);

        processedWords
                .toStream()
                .print(Printed.<String, String>toSysOut().withLabel("Processed Word Table"));

        processedWords
                .toStream()
                .to(
                        "out-words",
                        Produced.with(Serdes.String(), Serdes.String())
                );

        return streamsBuilder.build();
    }

}
