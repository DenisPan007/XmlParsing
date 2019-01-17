package service.entity_builder.sax;

import org.junit.Test;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.entity_builder.sax.tariffs_parse.TariffsHandler;

public class TariffsHandlerTest {
    @Test
    public void parse() throws Exception{
        XMLReader reader = XMLReaderFactory.createXMLReader();
        TariffsHandler handler = new TariffsHandler();
        reader.setContentHandler(handler);
        reader.parse("Tariffs.xml");
    }


}