package pl.xcodesoftware.nbp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;

import java.math.BigDecimal;

public interface CurrencyRequestInfoService {

    void saveRequest(CurrencyValueRequestDTO currencyValueRequestDTO, BigDecimal responseExchangeRate);

    Page<CurrencyRequestInfoDTO> getCurrencyRequestHistory(Pageable pageable);

}
