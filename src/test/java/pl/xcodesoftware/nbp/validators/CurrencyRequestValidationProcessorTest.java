package pl.xcodesoftware.nbp.validators;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyRequestValidationProcessorTest {

    @InjectMocks
    private CurrencyRequestValidationProcessor underTest;

    @Test
    void shouldPassAllValidators() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EUR")
                .requestAuthor("Example User").build();

        assertDoesNotThrow(() -> underTest.validate(currencyValueRequestDTO));
    }

    @Test
    void shouldThrowExceptionWhenRequestAuthorIsEmpty() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EUR")
                .requestAuthor("").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.REQUEST_AUTHOR_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRequestAuthorIsBlank() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EUR")
                .requestAuthor(" ").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.REQUEST_AUTHOR_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRequestAuthorIsNull() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EUR")
                .requestAuthor(null).build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.REQUEST_AUTHOR_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsEmpty() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("")
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsBlank() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency(" ")
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsNull() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency(null)
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_IS_NULL_OR_EMPTY_OR_BLANK.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyCodeLengthIsLessThan3() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EU")
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_BAD_LENGTH.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyCodeLengthIsGreaterThan3() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EURD")
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_BAD_LENGTH.getMessage(), exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCurrencyRequestIsNull() {
        final CurrencyValueRequestDTO currencyValueRequestDTO = null;

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_REQUEST_IS_NULL.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"E11", "WD2", "123"})
    void shouldThrowExceptionWhenCurrencyCodeContainsNotOnlyLetters(String testCurrencyCode) {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency(testCurrencyCode)
                .requestAuthor("Example user").build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.CURRENCY_CODE_NOT_CONTAINS_ONLY_LETTERS.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Michał", "Michał ", "romek", "romek ", "Rom123 Nowak", "Romek Now123"})
    void shouldThrowExceptionWhenRequestAuthorHasBadName(String testRequestAuthor) {
        final CurrencyValueRequestDTO currencyValueRequestDTO = CurrencyValueRequestDTO.builder()
                .currency("EUR")
                .requestAuthor(testRequestAuthor).build();

        CurrencyExchangeRateRequestValidationException exception = assertThrows(CurrencyExchangeRateRequestValidationException.class,
                () -> underTest.validate(currencyValueRequestDTO));

        assertEquals(CurrencyRequestValidationMessage.REQUEST_AUTHOR_FIRST_NAME_AND_LAST_NAME_NOT_CORRECT.getMessage(), exception.getMessage());
    }
}

