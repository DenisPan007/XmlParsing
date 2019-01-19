package service.builder.stax;

import org.junit.Test;

public class TariffsStAxBuilderTest {

    @Test
    public void buildSetEntities() {
        TariffsStAxBuilder stAxBuilder = new TariffsStAxBuilder();
        stAxBuilder.buildSetEntities("Tariffs.xml");
        System.out.println(stAxBuilder.getEntities());
    }
}