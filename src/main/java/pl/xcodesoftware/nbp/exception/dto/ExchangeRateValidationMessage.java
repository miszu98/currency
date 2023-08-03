package pl.xcodesoftware.nbp.exception.dto;

public enum ExchangeRateValidationMessage {

    EXCHANGE_RATE_IS_NULL("Exchange rate is null");

    private String message;

    ExchangeRateValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
