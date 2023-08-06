package pl.xcodesoftware.nbp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;


public interface NbpService {

    ExchangeRateResponse getExchangeRateByCurrencyCode(String currencyCode) throws JsonProcessingException;

}
