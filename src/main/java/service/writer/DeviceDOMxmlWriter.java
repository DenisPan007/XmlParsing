package service.writer;

import entity.device.Component;
import entity.device.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Set;


public class DeviceDOMxmlWriter {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    public DeviceDOMxmlWriter() throws WriterException{
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
            throw new WriterException("Can't write to  XML File",e);
        }
    }
    public void writeToXML(Set<Device> deviceSet, String fileName) throws WriterException{
        try{
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("devices");
        rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xmlns","http://www.example.com/devices");
        rootElement.setAttribute("xsi:schemaLocation","http://www.example.com/devices DevicesXSD.xsd");
        doc.appendChild(rootElement);
        for (Device device : deviceSet) {
            rootElement.appendChild(getDevice(doc, device));
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult file = new StreamResult(new File(fileName));
        transformer.transform(source, file);
        LOGGER.info("Создание XML файла закончено");
    }
        catch (TransformerException exc){
        LOGGER.error(exc);
        throw new WriterException("Can't write to  XML File",exc);
    }
    }

    private Node getDevice(Document doc, Device device) {
        Element device1 = doc.createElement("device");
        device1.setAttribute("ID", device.getID());
        for(Component component : device.getComponents()) {
            device1.appendChild(getDeviceElements(doc, device1, "component", component));
        }
        return device1;
    }
    private Node getDeviceElements(Document doc,Element element, String name, Component component) {
        Element node = doc.createElement(name);
        node.appendChild(getComponentElements(doc,node,"name",component.getName()));
        node.appendChild(getComponentElements(doc,node,"country",component.getCountry()));
        node.appendChild(getComponentElements(doc,node,"price",String.valueOf(component.getPrice())));
        node.appendChild(getComponentElements(doc,node,"peripheral",String.valueOf(component.isPeripheral())));
        node.appendChild(getComponentElements(doc,node,"power_Consumption_Watt",String.valueOf(component.getPowerConsumptionWatt())));
        node.appendChild(getComponentElements(doc,node,"port", String.valueOf(component.getPort())));
        node.appendChild(getComponentElements(doc,node,"critical", String.valueOf(component.isCritical())));
        return node;
    }
    private Node getComponentElements(Document doc,Element element, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
