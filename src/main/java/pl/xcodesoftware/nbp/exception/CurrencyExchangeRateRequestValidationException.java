package pl.xcodesoftware.nbp.exception;

import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;

public class CurrencyExchangeRateRequestValidationException extends RuntimeException {
    public CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage currencyRequestValidationMessage) {
        super(currencyRequestValidationMessage.getMessage());
    }
}
