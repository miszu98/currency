package pl.xcodesoftware.nbp.service;

import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;

public interface NbpService {

    ExchangeRateResponse getExchangeRate(String currencyCode);

}
