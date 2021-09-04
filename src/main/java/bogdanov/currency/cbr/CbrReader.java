package bogdanov.currency.cbr;

import bogdanov.currency.Reader;
import bogdanov.currency.cbr.dto.RubleRates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@RequiredArgsConstructor
public class CbrReader extends Reader<RubleRates> {

    private final URL url;

    public RubleRates getRates() throws IOException{
        return convert(read(url.openConnection().getInputStream()));
    }

    private RubleRates convert(String xml) throws JsonProcessingException {
        return (new XmlMapper()).readValue(xml, RubleRates.class);
    }

    private String read(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
