package pl.xcodesoftware.nbp.validators.validators;

import org.apache.commons.lang3.StringUtils;
import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidator;

public class RequestAuthorIsNullOrEmptyOrBlankValidator extends CurrencyRequestValidator {
    @Override
    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        final String requestAuthor = currencyValueRequestDTO.getRequestAuthor();

        if (requestAuthorIsEmptyOrBlankOrNull(requestAuthor)) {
            throw new CurrencyExchangeRateRequestValidationException(CurrencyRequestValidationMessage.REQUEST_AUTHOR_IS_NULL_OR_EMPTY_OR_BLANK);
        }
        validateNext(currencyValueRequestDTO);
    }

    private boolean requestAuthorIsEmptyOrBlankOrNull(String requestAuthor) {
        return requestAuthor == null || StringUtils.isEmpty(requestAuthor) || StringUtils.isBlank(requestAuthor);
    }
}
