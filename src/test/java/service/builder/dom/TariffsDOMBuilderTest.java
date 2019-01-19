package service.builder.dom;

import org.junit.Test;
import service.builder.sax.BuilderException;

public class TariffsDOMBuilderTest {

    @Test
    public void buildSetEntities() throws BuilderException {
        TariffsDOMBuilder domBuilder = new TariffsDOMBuilder();
        domBuilder.buildSetEntities("src/test/resources/tariffs_files/Tariffs.xml");
    }
}