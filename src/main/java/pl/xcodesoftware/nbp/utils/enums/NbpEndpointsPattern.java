package pl.xcodesoftware.nbp.utils.enums;

public enum NbpEndpointsPattern {

    CURRENCIES_EXCHANGE_RATES("/exchangerates/tables/A?format=json");

    private String value;

    NbpEndpointsPattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}