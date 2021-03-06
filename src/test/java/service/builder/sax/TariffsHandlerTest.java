package service.builder.sax;

import org.junit.Test;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.builder.sax.tariff.TariffsHandler;

public class TariffsHandlerTest {
    @Test
    public void parse() throws Exception{
        XMLReader reader = XMLReaderFactory.createXMLReader();
        TariffsHandler handler = new TariffsHandler();
        reader.setContentHandler(handler);
        reader.parse("src/test/resources/tariffs_files/Tariffs.xml");
    }


}