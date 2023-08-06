package pl.xcodesoftware.nbp.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;
import pl.xcodesoftware.nbp.service.CurrencyRequestInfoService;
import pl.xcodesoftware.nbp.service.NbpService;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidationProcessor;


@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final NbpService nbpService;

    private final CurrencyRequestInfoService currencyRequestInfoService;

    private final CurrencyRequestValidationProcessor currencyRequestValidationProcessor;

    @Transactional
    public ExchangeRateResponse getExchangeRateByCodeAndSave(CurrencyValueRequest currencyValueRequest) throws JsonProcessingException {
        currencyRequestValidationProcessor.validate(currencyValueRequest);
        ExchangeRateResponse exchangeRate = nbpService.getExchangeRateByCurrencyCode(currencyValueRequest.getCurrency());
        currencyRequestInfoService.saveRequest(currencyValueRequest, exchangeRate.getValue());
        return exchangeRate;
    }

}
