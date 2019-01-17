package service.entity_builder.sax;

import entity.Tariff;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;
import service.entity_builder.sax.tariffs_parse.TariffsHandler;

public class SaxEntitiesBuilderTest {

    @Test
    public void buildSetEntities() throws BuilderException {
        DefaultHandler tariffsHandler = new TariffsHandler();
        SaxEntitiesBuilder<Tariff> builder = new SaxEntitiesBuilder<Tariff>(tariffsHandler);
        builder.buildSetEntities("Tariffs.xml");

    }
}