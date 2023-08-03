package pl.xcodesoftware.nbp.utils.enums;

public enum EndpointsPattern {

    CURRENT_CURRENCY_VALUE("/exchangerates/rates/A/%s/today");

    private String value;

    EndpointsPattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}