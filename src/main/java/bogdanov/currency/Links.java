package bogdanov.currency;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Links {

    private static Map<String, URL> links= new HashMap<>();

    public static void add(String name, String urlAddress) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(urlAddress)) {
            throw new RuntimeException("Name or url value is missing");
        }
        name = name.toUpperCase(Locale.ROOT);
        urlAddress = urlAddress.toLowerCase(Locale.ROOT);
        try {
            links.put(name, new URL(urlAddress));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url : " + urlAddress);
        }
    }

    public static URL get(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("Name value is missing");
        }
        URL url;
        name = name.toUpperCase(Locale.ROOT);
        if ((url = links.get(name)) != null) {
            return url;
        } else {
            throw new RuntimeException("Link with name : " + name + " not found");
        }
    }

    public static Map<String, URL> getAll() {
        return links;
    }
}
