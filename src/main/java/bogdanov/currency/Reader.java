package bogdanov.currency;

import java.io.IOException;

public interface Reader<T> {

    T getRates() throws IOException;
}
