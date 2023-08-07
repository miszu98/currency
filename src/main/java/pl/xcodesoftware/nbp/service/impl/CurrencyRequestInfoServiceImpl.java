package pl.xcodesoftware.nbp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;
import pl.xcodesoftware.nbp.mapper.CurrencyRequestInfoMapper;
import pl.xcodesoftware.nbp.repository.CurrencyRequestInfoRepository;
import pl.xcodesoftware.nbp.service.CurrencyRequestInfoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyRequestInfoServiceImpl implements CurrencyRequestInfoService {

    private final CurrencyRequestInfoRepository currencyRequestInfoRepository;
    private final CurrencyRequestInfoMapper currencyRequestInfoMapper;

    @Override
    @Transactional
    public void saveRequest(CurrencyValueRequestDTO currencyValueRequestDTO, BigDecimal responseExchangeRate) {
        log.info("Saving request for {}", currencyValueRequestDTO.getCurrency());
        CurrencyRequestInfoEntity currencyRequestInfoEntity = buildFullCurrencyRequestInfo(currencyValueRequestDTO,
                responseExchangeRate);
        currencyRequestInfoRepository.save(currencyRequestInfoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CurrencyRequestInfoDTO> getCurrencyRequestHistory(Pageable pageable) {
        log.info("Looking for currencies requests");
        Page<CurrencyRequestInfoEntity> currencyRequestInfoEntityPage = currencyRequestInfoRepository.findAll(pageable);
        List<CurrencyRequestInfoEntity> pageContent = currencyRequestInfoEntityPage.getContent();
        List<CurrencyRequestInfoDTO> mappedPageContent = transformCurrencyRequestInfoEntitiesToDTO(pageContent);
        return new PageImpl<>(mappedPageContent, pageable, currencyRequestInfoEntityPage.getTotalElements());
    }

    private List<CurrencyRequestInfoDTO> transformCurrencyRequestInfoEntitiesToDTO(List<CurrencyRequestInfoEntity> currencyRequestInfoEntities) {
        return currencyRequestInfoEntities.stream()
                .map(currencyRequestInfoMapper::currencyRequestInfoEntityToDto)
                .collect(Collectors.toList());
    }

    private CurrencyRequestInfoEntity buildFullCurrencyRequestInfo(CurrencyValueRequestDTO currencyValueRequestDTO,
                                                                   BigDecimal responseExchangeRate) {
        return CurrencyRequestInfoEntity.builder()
                .currency(currencyValueRequestDTO.getCurrency())
                .requestAuthor(currencyValueRequestDTO.getRequestAuthor())
                .value(responseExchangeRate)
                .build();
    }

}
