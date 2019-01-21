package service.builder.stax;

import org.junit.Test;

public class TariffsStAxBuilderTest {

    @Test
    public void buildSetEntities() {
        TariffsStAXBuilder stAxBuilder = new TariffsStAXBuilder();
        stAxBuilder.buildSetEntities("src/test/resources/device_files/Tariffs.xml");
    }
}