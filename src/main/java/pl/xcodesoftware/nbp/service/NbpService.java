package pl.xcodesoftware.nbp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;


public interface NbpService {

    ExchangeRateResponseDTO getExchangeRateByCurrencyCode(String currencyCode) throws JsonProcessingException;

}
