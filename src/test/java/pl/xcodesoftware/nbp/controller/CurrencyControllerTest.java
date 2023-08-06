package pl.xcodesoftware.nbp.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import pl.xcodesoftware.nbp.configuration.IntegrationTestConfiguration;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;
import pl.xcodesoftware.nbp.repository.CurrencyRequestInfoRepository;
import pl.xcodesoftware.nbp.service.NbpService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.*;

public class CurrencyControllerTest extends IntegrationTestConfiguration {

    @Autowired
    private CurrencyRequestInfoRepository currencyRequestInfoRepository;

    @MockBean
    private NbpService nbpService;

    @BeforeEach
    void setUp() {
        CurrencyRequestInfoEntity currencyRequestInfoEntity = getMockCurrencyInfoRequestEntity();
        currencyRequestInfoRepository.save(currencyRequestInfoEntity);
    }

    @AfterEach
    void tearDown() {
        currencyRequestInfoRepository.deleteAll();
    }

    @Test
    void shouldSaveCurrencyRequestHistoricRecord() throws Exception {
        final CurrencyValueRequestDTO currencyValueRequestDTO = getMockCurrencyValueRequestExample();
        final String currencyValueRequestJson = objectMapper.writeValueAsString(currencyValueRequestDTO);
        final ExchangeRateResponseDTO ExchangeRateResponseDTO = getMockExchangeRateResponse();
        final String currencyCode = "PLN";

        when(nbpService.getExchangeRateByCurrencyCode(currencyCode)).thenReturn(ExchangeRateResponseDTO);

        mockMvc.perform(post("/api/v1/currencies/get-current-currency-value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(currencyValueRequestJson))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.value").value("4.2"));

        assertEquals(2, currencyRequestInfoRepository.findAll().size());
    }

    @Test
    void shouldReturnOneCurrencyRequestHistoricRecord() throws Exception {
        final PageRequest pageRequest = getMockPageRequest();

        mockMvc.perform(get("/api/v1/currencies/requests")
                .queryParam("page", String.valueOf(pageRequest.getPageNumber()))
                .queryParam("size", String.valueOf(pageRequest.getPageSize())))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.content").isNotEmpty(),
                        jsonPath("$.content[0].value").value("4.2"),
                        jsonPath("$.content[0].currency").value("PLN"),
                        jsonPath("$.content[0].requestAuthor").value("Michał Małek"));
    }
}
