package pl.xcodesoftware.nbp.exception.enums;

public enum CurrencyRequestValidationMessage {
    REQUEST_AUTHOR_IS_NULL_OR_EMPTY_OR_BLANK("Request author is null or empty or blank"),
    CURRENCY_CODE_IS_NULL_OR_EMPTY_OR_BLANK("Currency code is null or empty or blank"),
    CURRENCY_CODE_BAD_LENGTH("Currency code length is less or greater than 3 chars");

    private String message;

    CurrencyRequestValidationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
