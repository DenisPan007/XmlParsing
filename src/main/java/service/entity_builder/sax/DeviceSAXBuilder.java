package service.entity_builder.sax;

import entity.device.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.entity_builder.AbstractEntitiesBuilder;

import java.io.IOException;

public class DeviceSAXBuilder extends AbstractEntitiesBuilder<Device> {
    private static final Logger LOGGER = LogManager.getLogger();
    private DeviceHandler deviceHandler;
    private XMLReader reader;

    public DeviceSAXBuilder() {
        deviceHandler = new DeviceHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(deviceHandler);
        } catch (SAXException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void buildSetEntities(String fileName) {
        try {
            entities.clear();
            reader.parse(fileName);
        } catch (IOException | org.xml.sax.SAXException e) {
            LOGGER.error(e);
        }
        entities = deviceHandler.getDevices();
    }
}
