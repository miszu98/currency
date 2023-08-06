package pl.xcodesoftware.nbp.validators.validators;

import org.apache.commons.lang3.StringUtils;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidator;

public class CurrencyCodeIsNullOrEmptyOrBlankValidator extends CurrencyRequestValidator {

    @Override
    public void validate(CurrencyValueRequest currencyValueRequest) {
        final String currencyCode = currencyValueRequest.getCurrency();

        if (currencyIsEmptyOrBlankOrNull(currencyCode)) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.CURRENCY_CODE_IS_NULL_OR_EMPTY_OR_BLANK);
        }
        validateNext(currencyValueRequest);
    }

    private boolean currencyIsEmptyOrBlankOrNull(String currency) {
        return currency == null || StringUtils.isEmpty(currency) || StringUtils.isBlank(currency);
    }
}
