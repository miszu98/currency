package pl.xcodesoftware.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class ExchangeRateResponse {

    private BigDecimal value;

    @JsonProperty("rates")
    private void unpackExchangeRateResponse(List<Map<String, String>> exchangeRateResponses) {
        Map<String, String> exchangeRateResponse = exchangeRateResponses.get(0);
        this.value = new BigDecimal(exchangeRateResponse.get("mid"));
    }

}
