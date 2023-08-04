package pl.xcodesoftware.nbp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;
import pl.xcodesoftware.nbp.mapper.CurrencyRequestInfoMapper;
import pl.xcodesoftware.nbp.repository.CurrencyRequestInfoRepository;
import pl.xcodesoftware.nbp.service.impl.CurrencyRequestInfoServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.xcodesoftware.nbp.utils.MockDataGeneratorUtil.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyRequestInfoServiceTest {

    @Mock
    private CurrencyRequestInfoRepository currencyRequestInfoRepository;

    @Mock
    private CurrencyRequestInfoMapper currencyRequestInfoMapper;

    @Captor
    private ArgumentCaptor<CurrencyRequestInfoEntity> argumentCaptor;

    @InjectMocks
    private CurrencyRequestInfoServiceImpl underTest;

    @Test
    void shouldSaveIncomingCurrencyRequestToDatabase() {
        final CurrencyValueRequest currencyValueRequest = getMockCurrencyValueRequestExample();
        final BigDecimal apiResponseExchangeRate = getMockExchangeRate();
        final CurrencyRequestInfoEntity currencyRequestInfoEntity = getMockCurrencyInfoRequestEntity();

        underTest.saveRequest(currencyValueRequest, apiResponseExchangeRate);

        verify(currencyRequestInfoRepository).save(argumentCaptor.capture());

        CurrencyRequestInfoEntity capturedCurrencyRequestInfoEntity = argumentCaptor.getValue();

        assertEquals(capturedCurrencyRequestInfoEntity.getCurrency(), currencyRequestInfoEntity.getCurrency());
        assertEquals(capturedCurrencyRequestInfoEntity.getRequestAuthor(), currencyRequestInfoEntity.getRequestAuthor());
        assertEquals(capturedCurrencyRequestInfoEntity.getValue(), currencyRequestInfoEntity.getValue());
    }

    @Test
    void shouldFoundCurrencyRequestHistoricRecord() {
        final PageRequest pageRequest = getMockPageRequest();
        final List<CurrencyRequestInfoEntity> currencyRequestInfoEntities = getMockCurrencyRequestInfoEntities();
        final CurrencyRequestInfoDTO currencyRequestInfoDTO = getMockCurrencyRequestInfoDTO();

        when(currencyRequestInfoRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(currencyRequestInfoEntities));
        when(currencyRequestInfoMapper.currencyRequestInfoEntityToDto(currencyRequestInfoEntities.get(0)))
                .thenReturn(currencyRequestInfoDTO);

        Page<CurrencyRequestInfoDTO> currencyRequestHistoryPage = underTest.getCurrencyRequestHistory(pageRequest);

        List<CurrencyRequestInfoDTO> pageContent = currencyRequestHistoryPage.getContent();

        assertEquals(1, pageContent.size());
        assertEquals("PLN", pageContent.get(0).getCurrency());
        assertEquals("Michał Małek", pageContent.get(0).getRequestAuthor());
        assertEquals(new BigDecimal("4.2"), pageContent.get(0).getValue());
    }

    @Test
    void shouldThrowExceptionWhenNotProvidePageableForGettingCurrencyRequestHistoricRecords() {
        final PageRequest pageRequest = null;
        final List<CurrencyRequestInfoEntity> currencyRequestInfoEntities = getMockCurrencyRequestInfoEntities();
        final CurrencyRequestInfoDTO currencyRequestInfoDTO = getMockCurrencyRequestInfoDTO();

        when(currencyRequestInfoRepository.findAll(pageRequest))
                .thenReturn(new PageImpl<>(currencyRequestInfoEntities));
        when(currencyRequestInfoMapper.currencyRequestInfoEntityToDto(currencyRequestInfoEntities.get(0)))
                .thenReturn(currencyRequestInfoDTO);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> underTest.getCurrencyRequestHistory(pageRequest));

        assertEquals("Pageable must not be null", illegalArgumentException.getMessage());
    }
}
