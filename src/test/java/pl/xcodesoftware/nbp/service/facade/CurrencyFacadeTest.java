package pl.xcodesoftware.nbp.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.service.CurrencyRequestInfoService;
import pl.xcodesoftware.nbp.service.NbpService;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidationProcessor;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.getMockCurrencyValueRequestExample;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.getMockExchangeRateResponse;

@ExtendWith(MockitoExtension.class)
public class CurrencyFacadeTest {

    @Mock
    private NbpService nbpService;

    @Mock
    private CurrencyRequestInfoService currencyRequestInfoService;

    @Mock
    private CurrencyRequestValidationProcessor currencyRequestValidationProcessor;

    @InjectMocks
    private CurrencyFacade underTest;

    @Test
    void shouldValidateCurrencyRequestBeforeCommunicateWithNbpAPI() throws JsonProcessingException {
        final CurrencyValueRequestDTO currencyValueRequestDTO = getMockCurrencyValueRequestExample();
        final ExchangeRateResponseDTO exchangeRateResponseDTO = getMockExchangeRateResponse();

        when(nbpService.getExchangeRateByCurrencyCode("PLN")).thenReturn(exchangeRateResponseDTO);

        underTest.getExchangeRateByCodeAndSave(currencyValueRequestDTO);

        verify(currencyRequestValidationProcessor).validate(currencyValueRequestDTO);
    }

    @Test
    void shouldCommunicateWithNbpAPI() throws JsonProcessingException {
        final CurrencyValueRequestDTO currencyValueRequestDTO = getMockCurrencyValueRequestExample();
        final ExchangeRateResponseDTO exchangeRateResponseDTO = getMockExchangeRateResponse();

        when(nbpService.getExchangeRateByCurrencyCode("PLN")).thenReturn(exchangeRateResponseDTO);

        underTest.getExchangeRateByCodeAndSave(currencyValueRequestDTO);

        verify(nbpService).getExchangeRateByCurrencyCode("PLN");
    }

    @Test
    void shouldSaveCurrencyRequest() throws JsonProcessingException {
        final CurrencyValueRequestDTO currencyValueRequestDTO = getMockCurrencyValueRequestExample();
        final ExchangeRateResponseDTO exchangeRateResponseDTO = getMockExchangeRateResponse();

        when(nbpService.getExchangeRateByCurrencyCode("PLN")).thenReturn(exchangeRateResponseDTO);

        underTest.getExchangeRateByCodeAndSave(currencyValueRequestDTO);

        verify(currencyRequestInfoService).saveRequest(currencyValueRequestDTO, new BigDecimal("4.2"));
    }
}
