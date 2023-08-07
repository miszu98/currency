package pl.xcodesoftware.nbp.validators.validators;

import pl.xcodesoftware.nbp.dto.CurrencyValueRequestDTO;
import pl.xcodesoftware.nbp.exception.CurrencyExchangeRateRequestValidationException;
import pl.xcodesoftware.nbp.validators.CurrencyRequestValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pl.xcodesoftware.nbp.exception.enums.CurrencyRequestValidationMessage.REQUEST_AUTHOR_FIRST_NAME_AND_LAST_NAME_NOT_CORRECT;

// Przy tym walidatorze można by zrobić walidatory w ramach krajów w których byłaby uruchamiana aplikacja
public class RequestAuthorFirstNameAndLastNameValidator extends CurrencyRequestValidator {

    @Override
    public void validate(CurrencyValueRequestDTO currencyValueRequestDTO) {
        final String regex = "^[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż][a-zA-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]*\\s[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż][a-zA-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]*$";
        final String requestAuthor = currencyValueRequestDTO.getRequestAuthor();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(requestAuthor);

        boolean firstNameAndLastNameIsNotCorrect = !matcher.matches();

        if (firstNameAndLastNameIsNotCorrect) {
            throw new CurrencyExchangeRateRequestValidationException(REQUEST_AUTHOR_FIRST_NAME_AND_LAST_NAME_NOT_CORRECT);
        }
        validateNext(currencyValueRequestDTO);
    }

}
