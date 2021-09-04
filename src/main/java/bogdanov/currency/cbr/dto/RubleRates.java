package bogdanov.currency.cbr.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "ValCurs")
public class RubleRates {
    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    private LocalDate date;
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;
    @JacksonXmlElementWrapper(localName = "Valute", useWrapping = false)
    @JacksonXmlProperty(localName = "Valute")
    private List<CurrencyToRubleRate> currency;

    public void setDate(String date) {
        this.date = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );
    }

}
