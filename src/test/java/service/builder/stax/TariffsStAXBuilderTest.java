package service.builder.stax;

import org.junit.Test;

import static org.junit.Assert.*;

public class TariffsStAXBuilderTest {

    @Test
    public void buildSetEntities() {
        TariffsStAXBuilder stAxBuilder = new TariffsStAXBuilder();
        stAxBuilder.buildSetEntities("src/test/resources/device_files/Tariffs.xml");
    }
}