package pl.xcodesoftware.nbp.utils;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

    public static String getMockCurrenciesData() {
        return "[{\"table\":\"A\",\"no\":\"150/A/NBP/2023\",\"effectiveDate\":\"2023-08-04\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1168},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":4.062}]}]";
    }

    public static ArrayNode getMockJsonNode() {
        ObjectNode objectNode = new ObjectNode(JsonNodeFactory.instance);
        objectNode.put("table", "A");
        objectNode.put("no", "150/A/NBP/2023");
        objectNode.put("effectiveDate", "2023-08-04");

        ArrayNode ratesArray = new ArrayNode(JsonNodeFactory.instance);

        ObjectNode rate1 = new ObjectNode(JsonNodeFactory.instance);
        rate1.put("currency", "bat (Tajlandia)");
        rate1.put("code", "THB");
        rate1.put("mid", 0.1168);
        ratesArray.add(rate1);

        ObjectNode rate2 = new ObjectNode(JsonNodeFactory.instance);
        rate2.put("currency", "dolar amerykański");
        rate2.put("code", "USD");
        rate2.put("mid", 4.0620);
        ratesArray.add(rate2);

        objectNode.set("rates", ratesArray);

        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
        arrayNode.add(objectNode);

        return arrayNode;
    }

}
