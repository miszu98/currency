package pl.xcodesoftware.nbp.validators;

import org.springframework.stereotype.Component;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.validators.validators.*;

@Component
public class CurrencyRequestValidationProcessor {
    private CurrencyRequestValidator currencyRequestValidator;

    public CurrencyRequestValidationProcessor() {
        this.currencyRequestValidator = CurrencyRequestValidator.link(
                new CurrencyValueRequestIsNullValidator(),
                new RequestAuthorIsNullOrEmptyOrBlankValidator(),
                new CurrencyCodeIsNullOrEmptyOrBlankValidator(),
                new CurrencyCodeLengthValidator(),
                new CurrencyCodeContainsOnlyLettersValidator()
        );
    }

    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        this.currencyRequestValidator.validate(currencyValueRequestDTO);
    }
}
