package pl.xcodesoftware.nbp.validators.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidator;

public class CurrencyCodeLengthValidator extends CurrencyRequestValidator {
    @Override
    public void validate(CurrencyValueRequest currencyValueRequest) {
        final String currencyCode = currencyValueRequest.getCurrency();

        boolean isNotCorrectLength = currencyCode.length() != 3;
        if (isNotCorrectLength) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.CURRENCY_CODE_BAD_LENGTH);
        }
        validateNext(currencyValueRequest);
    }
}
