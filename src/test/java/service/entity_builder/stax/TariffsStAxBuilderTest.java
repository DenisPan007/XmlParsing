package service.entity_builder.stax;

import org.junit.Test;

import static org.junit.Assert.*;

public class TariffsStAxBuilderTest {

    @Test
    public void buildSetEntities() {
        TariffsStAxBuilder stAxBuilder = new TariffsStAxBuilder();
        stAxBuilder.buildSetEntities("Tariffs.xml");
        System.out.println(stAxBuilder.getEntities());
    }
}