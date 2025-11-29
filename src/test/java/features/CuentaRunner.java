package features;

import com.intuit.karate.junit5.Karate;

public class CuentaRunner {
    @Karate.Test
    Karate testClientes() {
        return Karate.run("cuenta").relativeTo(getClass());
    }
}

