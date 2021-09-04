package bogdanov.currency;

import java.io.IOException;
import java.util.Collection;

public interface Converter {

    double getRate(String targetCurrencyCharCode, String baseCurrencyCharCode);

    Converter update() throws IOException;

    Collection<String> getCurrenciesList();
}
