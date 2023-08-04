package pl.xcodesoftware.nbp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CurrencyRequestInfoDTO {

    private String currency;

    private String requestAuthor;

    private LocalDateTime date;

    private BigDecimal value;

}
