package pl.xcodesoftware.nbp.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
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
    public ExchangeRateResponseDTO getExchangeRateByCodeAndSave(CurrencyValueRequestDTO currencyValueRequestDTO) throws JsonProcessingException {
        currencyRequestValidationProcessor.validate(currencyValueRequestDTO);
        ExchangeRateResponseDTO exchangeRate = nbpService.getExchangeRateByCurrencyCode(currencyValueRequestDTO.getCurrency());
        currencyRequestInfoService.saveRequest(currencyValueRequestDTO, exchangeRate.getValue());
        return exchangeRate;
    }

}
