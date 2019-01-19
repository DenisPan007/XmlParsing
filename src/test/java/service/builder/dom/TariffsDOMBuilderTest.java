package service.builder.dom;

import org.junit.Test;

public class TariffsDOMBuilderTest {

    @Test
    public void buildSetEntities() {
        TariffsDOMBuilder domBuilder = new TariffsDOMBuilder();
        domBuilder.buildSetEntities("Tariffs.xml");
    }
}