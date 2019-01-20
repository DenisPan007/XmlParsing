package service.builder.sax.device;

import entity.device.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.builder.AbstractEntitiesBuilder;
import service.builder.sax.BuilderException;

import java.io.IOException;

public class DeviceSAXBuilder extends AbstractEntitiesBuilder<Device> {
    private static final Logger LOGGER = LogManager.getLogger();
    private DeviceHandler deviceHandler;
    private XMLReader reader;

    public DeviceSAXBuilder() throws BuilderException {
        deviceHandler = new DeviceHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(deviceHandler);
        } catch (SAXException e) {
            LOGGER.error(e);
            throw new BuilderException("Can't create XMLReader",e);
        }
    }

    @Override
    public void buildSetEntities(String fileName) throws BuilderException {
        try {
            entities.clear();
            reader.parse(fileName);
        } catch (IOException | org.xml.sax.SAXException e) {
            LOGGER.error(e);
            throw  new BuilderException("Can't read file",e);
        }
        entities = deviceHandler.getDevices();
    }
}
