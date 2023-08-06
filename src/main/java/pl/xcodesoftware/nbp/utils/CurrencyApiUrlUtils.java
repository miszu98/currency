package pl.xcodesoftware.nbp.utils;

import lombok.experimental.UtilityClass;
import pl.xcodesoftware.nbp.utils.enums.NbpEndpointsPattern;


@UtilityClass
public class CurrencyApiUrlUtils {

    private static final String BASE_URL = "http://api.nbp.pl/api";

    public static String getEndpoint(NbpEndpointsPattern nbpEndpointsPattern, Object... values) {
        return BASE_URL + nbpEndpointsPattern.getValue().formatted(values);
    }

}
