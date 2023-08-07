package pl.xcodesoftware.nbp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.exception.ExchangeRateValidationException;
import pl.xcodesoftware.nbp.service.impl.NbpServiceImpl;
import pl.xcodesoftware.nbp.utils.CurrencyApiUrlUtils;
import pl.xcodesoftware.nbp.utils.enums.NbpEndpointsPattern;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.xcodesoftware.nbp.exception.enums.ExchangeRateValidationMessage.CURRENCY_NOT_FOUND;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.getMockCurrenciesData;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.getMockJsonNode;

@ExtendWith(MockitoExtension.class)
public class NbpServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClientMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @InjectMocks
    private NbpServiceImpl underTest;

    @ParameterizedTest
    @ValueSource(strings = {"213", "ABC", " ", ""})
    public void shouldThrowExceptionWhenNotFoundCurrencyByProvidedCode(String testCurrencyCode) throws JsonProcessingException {
        when(objectMapper.readTree(any(String.class))).thenReturn(getMockJsonNode());
        when(webClientBuilder.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(CurrencyApiUrlUtils.getEndpoint(NbpEndpointsPattern.CURRENCIES_EXCHANGE_RATES)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(String.class)).thenReturn(Flux.just(getMockCurrenciesData()));

        ExchangeRateValidationException exception = assertThrows(ExchangeRateValidationException.class,
                () -> underTest.getExchangeRateByCurrencyCode(testCurrencyCode));

        assertEquals(CURRENCY_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    public void shouldNotThrowExceptionWhenFoundCurrencyByProvidedCode() throws JsonProcessingException {
        final String currencyCode = "USD";

        when(objectMapper.readTree(any(String.class))).thenReturn(getMockJsonNode());
        when(webClientBuilder.build()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(CurrencyApiUrlUtils.getEndpoint(NbpEndpointsPattern.CURRENCIES_EXCHANGE_RATES)))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(String.class)).thenReturn(Flux.just(getMockCurrenciesData()));

        ExchangeRateResponseDTO exchangeRateResponseDTO = assertDoesNotThrow(() -> underTest.getExchangeRateByCurrencyCode(currencyCode));

        assertEquals(new BigDecimal("4.062"), exchangeRateResponseDTO.getValue());
    }

}
