package pl.xcodesoftware.nbp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponse;
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
    public ResponseEntity<ExchangeRateResponse> getCurrentCurrencyValue(@RequestBody CurrencyValueRequest currencyValueRequest) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(currencyFacade.getExchangeRateByCodeAndSave(currencyValueRequest));
    }

    @GetMapping("/requests")
    public ResponseEntity<Page<CurrencyRequestInfoDTO>> getCurrencyRequestHistory(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRequestInfoService.getCurrencyRequestHistory(pageable));
    }

}
