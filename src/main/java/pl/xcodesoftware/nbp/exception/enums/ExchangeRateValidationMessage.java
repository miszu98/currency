package pl.xcodesoftware.nbp.exception.enums;

public enum ExchangeRateValidationMessage {

    CURRENCY_NOT_FOUND("Currency not found"),
    EXCHANGE_RATE_IS_NULL("External NBP API returns exchange rate as null");

    private String message;

    ExchangeRateValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
