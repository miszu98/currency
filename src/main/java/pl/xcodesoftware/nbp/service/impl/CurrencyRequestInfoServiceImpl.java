package pl.xcodesoftware.nbp.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
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
    public void saveRequest(CurrencyValueRequest currencyValueRequest, BigDecimal responseExchangeRate) {
        log.info("Saving request for {}", currencyValueRequest.getRequestAuthor());
        CurrencyRequestInfoEntity currencyRequestInfoEntity = buildFullCurrencyRequestInfo(currencyValueRequest, responseExchangeRate);
        currencyRequestInfoRepository.save(currencyRequestInfoEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CurrencyRequestInfoDTO> getCurrencyRequestHistory(Pageable pageable) {
        log.info("Get ");
        Page<CurrencyRequestInfoEntity> currencyRequestInfoEntityPage = currencyRequestInfoRepository.findAll(pageable);
        List<CurrencyRequestInfoEntity> pageContent = currencyRequestInfoEntityPage.getContent();
        List<CurrencyRequestInfoDTO> mappedPageContent = pageContent.stream()
                .map(currencyRequestInfoMapper::currencyRequestInfoEntityToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(mappedPageContent, pageable, mappedPageContent.size());
    }

    private CurrencyRequestInfoEntity buildFullCurrencyRequestInfo(CurrencyValueRequest currencyValueRequest,
                                                                   BigDecimal responseExchangeRate) {
        return CurrencyRequestInfoEntity.builder()
                .currency(currencyValueRequest.getCurrency())
                .requestAuthor(currencyValueRequest.getRequestAuthor())
                .value(responseExchangeRate)
                .build();
    }

}
