package pl.xcodesoftware.nbp.exception;

import pl.xcodesoftware.nbp.exception.enums.ExchangeRateValidationMessage;

public class ExchangeRateValidationException extends RuntimeException {
    public ExchangeRateValidationException(ExchangeRateValidationMessage exchangeRateValidationMessage) {
        super(exchangeRateValidationMessage.getMessage());
    }
}
