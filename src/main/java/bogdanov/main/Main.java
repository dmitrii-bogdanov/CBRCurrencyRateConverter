package bogdanov.main;

import bogdanov.currency.Converter;
import bogdanov.currency.Links;
import bogdanov.currency.cbr.CbrConverter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Links.add("CBR", "http://www.cbr.ru/scripts/XML_daily.asp");

        try {
            String targetCurrency = "HUF";
            String baseCurrency = "NOK";
            Converter converter = new CbrConverter();

            System.out.printf("1 %s / 1 %s = %.4f",
                    targetCurrency,
                    baseCurrency,
                    converter.update().getRate(targetCurrency, baseCurrency)
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
