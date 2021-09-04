package bogdanov.currency.cbr;

import bogdanov.currency.Converter;
import bogdanov.currency.Links;
import bogdanov.currency.Reader;
import bogdanov.currency.cbr.dto.CurrencyToRubleRate;
import bogdanov.currency.cbr.dto.RubleRates;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class CbrConverter implements Converter {

    private final String name = "CBR";

    private final Reader<RubleRates> cbrReader = new CbrReader(Links.get(name));

    private final Map<String, CurrencyToRubleRate> currencies = new HashMap<>();

    @Override
    public Converter update() throws IOException {
        currencies.clear();
        cbrReader.getRates().getCurrency().forEach(c -> currencies.put(c.getCharCode(), c));
        return this;
    }

    @Override
    public double getRate(String targetCurrencyCharCode, String baseCurrencyCharCode) {
        if (targetCurrencyCharCode == null || baseCurrencyCharCode == null) {
            throw new RuntimeException("One or both of the currencies codes are missing");
        }
        targetCurrencyCharCode = targetCurrencyCharCode.toUpperCase(Locale.ROOT);
        baseCurrencyCharCode = baseCurrencyCharCode.toUpperCase(Locale.ROOT);
        CurrencyToRubleRate target, base;
        StringBuilder message = new StringBuilder();
        if ((target = currencies.get(targetCurrencyCharCode)) == null) {
            message.append("No currency with code : " + targetCurrencyCharCode + " was found;");
        }
        if ((base = currencies.get(baseCurrencyCharCode)) == null) {
            message.append("No currency with code : " + baseCurrencyCharCode + " was found;");
        }
        if (message.length() > 0) {
            throw new RuntimeException(message.toString());
        }
        return (target.getValue() / target.getNominal()) / (base.getValue() / base.getNominal());
    }

    @Override
    public Collection<String> getCurrenciesList() {
        return currencies.keySet();
    }
}
