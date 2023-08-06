package pl.xcodesoftware.nbp.validators.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;

public class CurrencyCodeContainsOnlyLettersValidator extends CurrencyCodeLengthValidator {
    @Override
    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        final String currencyCode = currencyValueRequestDTO.getCurrency();

        boolean currencyCodeNotContainsOnlyLetters = !currencyCode.chars().allMatch(Character::isLetter);

        if (currencyCodeNotContainsOnlyLetters) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.CURRENCY_CODE_NOT_CONTAINS_ONLY_LETTERS);
        }

        validateNext(currencyValueRequestDTO);
    }
}
