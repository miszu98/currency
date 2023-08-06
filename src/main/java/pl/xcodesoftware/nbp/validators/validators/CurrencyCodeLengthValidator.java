package pl.xcodesoftware.nbp.validators.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidator;

public class CurrencyCodeLengthValidator extends CurrencyRequestValidator {
    @Override
    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        final String currencyCode = currencyValueRequestDTO.getCurrency();

        boolean isNotCorrectLength = currencyCode.length() != 3;
        if (isNotCorrectLength) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.CURRENCY_CODE_BAD_LENGTH);
        }
        validateNext(currencyValueRequestDTO);
    }
}
