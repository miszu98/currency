package pl.xcodesoftware.nbp.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequest;

import static java.util.Objects.nonNull;

public abstract class CurrencyRequestValidator {
    public CurrencyRequestValidator nextValidator;

    public static CurrencyRequestValidator link(CurrencyRequestValidator first,
                                                CurrencyRequestValidator... chain) {
        CurrencyRequestValidator head = first;
        for (CurrencyRequestValidator nextInChain : chain) {
            head.nextValidator = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract void validate(CurrencyValueRequest currencyValueRequest);

    protected void validateNext(CurrencyValueRequest currencyValueRequest) {
        if (nonNull(nextValidator)) {
            nextValidator.validate(currencyValueRequest);
        }
    }
}
