package pl.xcodesoftware.nbp.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.dto.ExchangeRateResponseDTO;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;

import java.math.BigDecimal;
import java.util.List;

@UtilityClass
public class MockDataGeneratorUtil {

    public static CurrencyValueRequestDTO getMockCurrencyValueRequestExample() {
        return CurrencyValueRequestDTO.builder()
                .currency("PLN")
                .requestAuthor("Michał Małek")
                .build();
    }

    public static BigDecimal getMockExchangeRate() {
        return new BigDecimal("4.2");
    }

    public static PageRequest getMockPageRequest() {
        return PageRequest.of(0, 10);
    }

    public static List<CurrencyRequestInfoEntity> getMockCurrencyRequestInfoEntities() {
        return List.of(
                getMockCurrencyInfoRequestEntity()
        );
    }

    public static CurrencyRequestInfoDTO getMockCurrencyRequestInfoDTO() {
        return CurrencyRequestInfoDTO.builder()
                .currency("PLN")
                .requestAuthor("Michał Małek")
                .value(getMockExchangeRate()).build();
    }

    public static CurrencyRequestInfoEntity getMockCurrencyInfoRequestEntity() {
        return CurrencyRequestInfoEntity.builder()
                .currency("PLN")
                .requestAuthor("Michał Małek")
                .value(getMockExchangeRate()).build();
    }

    public static ExchangeRateResponseDTO getMockExchangeRateResponse() {
        return ExchangeRateResponseDTO.builder()
                .value(getMockExchangeRate())
                .build();
    }

}
