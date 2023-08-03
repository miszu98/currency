package pl.xcodesoftware.nbp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;
import pl.xcodesoftware.nbp.service.NbpService;
import pl.xcodesoftware.nbp.utils.CurrencyApiUrlUtils;
import pl.xcodesoftware.nbp.utils.enums.EndpointsPattern;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbpServiceImpl implements NbpService {

    @Override
    public ExchangeRateResponse getExchangeRate(String currencyCode) {
        log.info("Trying connect to NBP API and get currency value for {}", currencyCode);
        final String apiUrl = CurrencyApiUrlUtils.getEndpoint(EndpointsPattern.CURRENT_CURRENCY_VALUE, currencyCode);
        Flux<ExchangeRateResponse> exchangeRateResponseFlux = WebClient
                .create()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToFlux(ExchangeRateResponse.class);
        return exchangeRateResponseFlux.blockFirst();
    }

}
