package service.builder.sax;

import org.junit.Test;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.builder.sax.device.DeviceHandler;


public class DeviceHandlerTest {
    @Test
    public void parse() throws Exception{
        XMLReader reader = XMLReaderFactory.createXMLReader();
        DeviceHandler handler = new DeviceHandler();
        reader.setContentHandler(handler);
        reader.parse("src/test/resources/device_files/Devices.xml");
    }
}
