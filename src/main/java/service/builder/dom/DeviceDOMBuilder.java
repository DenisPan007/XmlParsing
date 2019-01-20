package service.builder.dom;

import entity.device.Component;
import entity.device.Device;
import entity.device.DeviceEnum;
import entity.device.PortEnum;
import service.builder.AbstractEntitiesBuilder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.builder.sax.BuilderException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
public class DeviceDOMBuilder extends AbstractEntitiesBuilder<Device> {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public DeviceDOMBuilder() throws BuilderException {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new BuilderException("Can't create XMLReader",e);
        }
    }

    @Override
    public void buildSetEntities(String fileName) throws BuilderException {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            entities.clear();
            NodeList deviceList = root.getElementsByTagName(DeviceEnum.DEVICE.getValue());
            for (int i = 0; i < deviceList.getLength(); i++) {
                Element element = (Element) deviceList.item(i);
                Device device = buildDevice(element);
                entities.add(device);
            }
        } catch (IOException | SAXException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new BuilderException(e);
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private Device buildDevice(Element deviceElement) {
        Device device = new Device();
        device.setID(deviceElement.getAttribute(DeviceEnum.ID.getValue()));
        for (int i = 0; i < deviceElement.getElementsByTagName(DeviceEnum.COMPONENT.getValue()).getLength(); i++) {
            Element componentElement = (Element) deviceElement.getElementsByTagName(DeviceEnum.COMPONENT.getValue()).item(i);
            device.getComponents().add(fill(componentElement));
        }
        return device;
    }

    private Component fill(Element componentElement) {
        Component currentComponent = new Component();
        currentComponent.setName(getElementTextContent(componentElement, DeviceEnum.NAME.getValue()));
        currentComponent.setCountry(getElementTextContent(componentElement, DeviceEnum.COUNTRY.getValue()));
        currentComponent.setPrice(Integer.parseInt(getElementTextContent(componentElement, DeviceEnum.PRICE.getValue())));
        currentComponent.setPeripheral(Boolean.valueOf(getElementTextContent(componentElement, DeviceEnum.PERIPHERAL.getValue())));
        currentComponent.setPowerConsumptionWatt(Integer.parseInt(getElementTextContent(componentElement, DeviceEnum.POWER_CONSUMPTION_WATT.getValue())));
        currentComponent.setPort(PortEnum.valueOf(getElementTextContent(componentElement, DeviceEnum.PORT.getValue())));
        currentComponent.setCritical(Boolean.valueOf(getElementTextContent(componentElement, DeviceEnum.CRITICAL.getValue())));
        return currentComponent;
    }
}
