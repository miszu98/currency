package pl.xcodesoftware.nbp.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;

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

    public abstract void validate(CurrencyValueRequestDTO currencyValueRequestDTO);

    protected void validateNext(CurrencyValueRequestDTO currencyValueRequestDTO) {
        if (nonNull(nextValidator)) {
            nextValidator.validate(currencyValueRequestDTO);
        }
    }
}
