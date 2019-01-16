package service.deviceParsing;


import entity.Component;
import entity.Device;

import entity.Port;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.entity_builder.AbstractEntitiesBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DeviceStAXBuilder extends AbstractEntitiesBuilder<Device> {
    private static final Logger LOGGER = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public DeviceStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetEntities(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DeviceEnum.valueOf(name.toUpperCase()) == DeviceEnum.DEVICE) {
                        Device device = buildDevice(reader, name);
                        entities.add(device);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }

    private Device buildDevice(XMLStreamReader reader, String bankType) throws XMLStreamException /*ParsingException */ {
        Device device = new Device();
        device.setID(reader.getAttributeValue(null, DeviceEnum.ID.getValue()));
        return fill(reader, device);
    }

    private Device fill(XMLStreamReader reader, Device device) throws XMLStreamException/*, ParsingException*/ {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DeviceEnum.valueOf(name.toUpperCase())) {
                        case COMPONENT:
                            device.getComponents().add(getXMLComponent(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DeviceEnum.valueOf(name.toUpperCase()) == DeviceEnum.DEVICE) {
                        return device;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Device");
    }

    private Component getXMLComponent(XMLStreamReader reader) throws XMLStreamException {
        Component component = new Component();
        int type;
        String tag;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    tag = reader.getLocalName();
                    switch (DeviceEnum.valueOf(tag.toUpperCase())) {
                        case NAME:
                            component.setName(getXMLText(reader));
                            break;
                        case COUNTRY:
                            component.setCountry(getXMLText(reader));
                            break;
                        case PRICE:
                            component.setPrice(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PERIPHERAL:
                            component.setPeripheral(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case POWER_CONSUMPTION_WATT:
                            component.setPowerConsumptionWatt(Integer.parseInt(getXMLText(reader)));
                            break;
                        case PORT:
                            component.setPort(Port.valueOf(getXMLText(reader)));
                            break;
                        case CRITICAL:
                            component.setCritical(Boolean.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    tag = reader.getLocalName();
                    if (DeviceEnum.valueOf(tag.toUpperCase()) == DeviceEnum.COMPONENT) {
                        return component;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Component");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
