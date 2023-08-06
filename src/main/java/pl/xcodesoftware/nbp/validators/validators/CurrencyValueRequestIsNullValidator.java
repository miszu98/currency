package pl.xcodesoftware.nbp.validators.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;

import static java.util.Objects.isNull;

public class CurrencyValueRequestIsNullValidator extends CurrencyCodeLengthValidator {
    @Override
    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        if (isNull(currencyValueRequestDTO)) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.CURRENCY_REQUEST_IS_NULL);
        }
        validateNext(currencyValueRequestDTO);
    }
}
