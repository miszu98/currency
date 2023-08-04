package pl.xcodesoftware.nbp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyValueRequest {

    private String currency;

    private String requestAuthor;

}
