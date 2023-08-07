package pl.xcodesoftware.nbp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.service.CurrencyRequestInfoService;
import pl.xcodesoftware.nbp.service.facade.CurrencyFacade;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/currencies")
@CrossOrigin(origins = "http://localhost:63342") // for testing purpose
public class CurrencyController {

    private final CurrencyFacade currencyFacade;
    private final CurrencyRequestInfoService currencyRequestInfoService;

    @PostMapping("/get-current-currency-value")
    @Cacheable(value = "currency", key = "#currencyValueRequestDTO")
    public ResponseEntity<ExchangeRateResponseDTO> getCurrentCurrencyValue(@RequestBody CurrencyValueRequestDTO currencyValueRequestDTO)
            throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(currencyFacade.getExchangeRateByCodeAndSave(currencyValueRequestDTO));
    }

    @GetMapping("/requests")
    public ResponseEntity<Page<CurrencyRequestInfoDTO>> getCurrencyRequestHistory(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRequestInfoService.getCurrencyRequestHistory(pageable));
    }

}
