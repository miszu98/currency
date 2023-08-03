package pl.xcodesoftware.nbp.service.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;
import pl.xcodesoftware.nbp.exception.ExchangeRateValidationException;
import pl.xcodesoftware.nbp.service.CurrencyRequestInfoService;
import pl.xcodesoftware.nbp.service.NbpService;

import static java.util.Objects.isNull;
import static pl.xcodesoftware.nbp.exception.dto.ExchangeRateValidationMessage.EXCHANGE_RATE_IS_NULL;

@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final NbpService nbpService;

    private final CurrencyRequestInfoService currencyRequestInfoService;

    @Transactional
    public ExchangeRateResponse getExchangeRateByCodeAndSave(CurrencyValueRequest currencyValueRequest) {
        ExchangeRateResponse exchangeRate = nbpService.getExchangeRate(currencyValueRequest.getCurrency());
        if (isNull(exchangeRate.getValue())) {
            throw new ExchangeRateValidationException(EXCHANGE_RATE_IS_NULL);
        }
        currencyRequestInfoService.saveRequest(currencyValueRequest, exchangeRate.getValue());
        return exchangeRate;
    }

}
