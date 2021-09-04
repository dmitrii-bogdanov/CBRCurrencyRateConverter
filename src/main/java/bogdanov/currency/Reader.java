package bogdanov.currency;

import java.io.IOException;

public abstract class Reader<T> {

    public abstract T getRates() throws IOException;
}
