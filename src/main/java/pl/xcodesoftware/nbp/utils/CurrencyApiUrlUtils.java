package pl.xcodesoftware.nbp.utils;

import lombok.experimental.UtilityClass;
import pl.xcodesoftware.nbp.utils.enums.EndpointsPattern;


@UtilityClass
public class CurrencyApiUrlUtils {

    private static final String BASE_URL = "http://api.nbp.pl/api";

    public static String getEndpoint(EndpointsPattern endpointsPattern, Object... values) {
        return BASE_URL + endpointsPattern.getValue().formatted(values);
    }

}
