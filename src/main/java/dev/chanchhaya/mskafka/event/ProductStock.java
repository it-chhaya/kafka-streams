package dev.chanchhaya.mskafka.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductStock {

    private String code;
    private int quantity;
    private double price;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate;
}
