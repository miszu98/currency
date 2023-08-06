package pl.xcodesoftware.nbp.exception.enums;

public enum ExchangeRateValidationMessage {

    CURRENCY_NOT_FOUND("Currency not found");

    private String message;

    ExchangeRateValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
