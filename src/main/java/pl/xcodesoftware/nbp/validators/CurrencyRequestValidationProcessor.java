package pl.xcodesoftware.nbp.validators;

import org.springframework.stereotype.Component;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.validators.validators.CurrencyCodeIsNullOrEmptyOrBlankValidator;
import pl.xcodesoftware.nbp.validators.validators.CurrencyCodeLengthValidator;
import pl.xcodesoftware.nbp.validators.validators.RequestAuthorIsNullOrEmptyOrBlankValidator;

@Component
public class CurrencyRequestValidationProcessor {
    private CurrencyRequestValidator currencyRequestValidator;

    public CurrencyRequestValidationProcessor() {
        this.currencyRequestValidator = CurrencyRequestValidator.link(
                new RequestAuthorIsNullOrEmptyOrBlankValidator(),
                new CurrencyCodeIsNullOrEmptyOrBlankValidator(),
                new CurrencyCodeLengthValidator()
        );
    }

    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        this.currencyRequestValidator.validate(currencyValueRequestDTO);
    }
}
