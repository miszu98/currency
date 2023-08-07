package pl.xcodesoftware.nbp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.exception.ExchangeRateValidationException;
import pl.xcodesoftware.nbp.exception.enums.ExchangeRateValidationMessage;
import pl.xcodesoftware.nbp.service.NbpService;
import pl.xcodesoftware.nbp.utils.CurrencyApiUrlUtils;
import pl.xcodesoftware.nbp.utils.enums.NbpEndpointsPattern;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;
import static pl.xcodesoftware.nbp.exception.enums.ExchangeRateValidationMessage.EXCHANGE_RATE_IS_NULL;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbpServiceImpl implements NbpService {

    private final ObjectMapper objectMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public ExchangeRateResponseDTO getExchangeRateByCurrencyCode(String currencyCode) throws JsonProcessingException {
        log.info("Trying connect to NBP API and get currencies exchange rates");
        return ExchangeRateResponseDTO.builder()
                .value(findExchangeRateByCurrencyCode(currencyCode)).build();
    }

    private BigDecimal findExchangeRateByCurrencyCode(String currencyCode) throws JsonProcessingException {
        String currenciesData = getCurrenciesData();
        JsonNode rootNode = objectMapper.readTree(currenciesData);
        JsonNode exchangeRatesNode = rootNode.get(0).get("rates");
        JsonNode foundCurrencyExchangeRate = filterJsonNodesByCurrencyCode(currencyCode, exchangeRatesNode);
        if (isNull(foundCurrencyExchangeRate)) {
            throw new ExchangeRateValidationException(EXCHANGE_RATE_IS_NULL);
        }
        return foundCurrencyExchangeRate.decimalValue();
    }

    private JsonNode filterJsonNodesByCurrencyCode(String currencyCode, JsonNode exchangeRatesNode) {
        return StreamSupport.stream(exchangeRatesNode.spliterator(), false)
                .filter(currency -> currency.get("code").asText().equals(currencyCode))
                .findFirst().orElseThrow(
                        () -> new ExchangeRateValidationException(ExchangeRateValidationMessage.CURRENCY_NOT_FOUND)
                ).get("mid");
    }

    private String getCurrenciesData() {
        final String apiUrl = CurrencyApiUrlUtils.getEndpoint(NbpEndpointsPattern.CURRENCIES_EXCHANGE_RATES);
        return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(String.class)
                .blockFirst();
    }
}
