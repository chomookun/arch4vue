package org.chomookun.arch4vue.example;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.*;

@Builder
@Getter
public class Example {

    private String exampleId;

    private String name;

    private String icon;

    private Integer number;

    private BigDecimal decimalNumber;

    private LocalDateTime dateTime;

    private Instant instantDateTime;

    private ZonedDateTime zonedDateTime;

    private LocalDate date;

    private LocalTime time;

    private boolean enabled;

    private String type;

    private String text;

}
