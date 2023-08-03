package pl.xcodesoftware.nbp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CurrencyRequestInfoDTO {

    private String currency;

    private String requestAuthor;

    private LocalDateTime date;

    private BigDecimal value;

}
