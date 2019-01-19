package service.builder.sax;

import org.junit.Test;
import service.builder.sax.tariff.TariffsSAXBuilder;

public class TariffsSAXBuilderTest {

    @Test
    public void buildSetEntities() throws BuilderException {
        TariffsSAXBuilder builder = new TariffsSAXBuilder();
        builder.buildSetEntities("src/test/resources/tariffs_files/Tariffs.xml");

    }
}