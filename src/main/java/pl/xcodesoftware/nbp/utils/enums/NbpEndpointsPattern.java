package pl.xcodesoftware.nbp.utils.enums;

public enum NbpEndpointsPattern {

    CURRENT_CURRENCY_VALUE("/exchangerates/rates/A/%s/today"),
    CURRENCIES_EXCHANGE_RATES("/exchangerates/tables/A?format=json");

    private String value;

    NbpEndpointsPattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}