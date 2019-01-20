package service.writer;

import controller.DeviceParser;
import controller.XmlParserException;
import entity.device.Component;
import entity.device.Device;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class DeviceDOMxmlWriterTest {
    private Set<Device> deviceSet = new LinkedHashSet<>();
    @Before
    public void fillSet() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        deviceSet = parser.parse("src/test/resources/device_files/Devices.xml","src/test/resources/device_files/DevicesXsd.xsd","SAX");

    }
    @Test
    public void write() throws WriterException, IOException, SAXException,ParserConfigurationException {
        DeviceDOMxmlWriter deviceDOMxmlWriter = new DeviceDOMxmlWriter();
        deviceDOMxmlWriter.writeToXML(deviceSet,"src/test/resources/device_files/Write.xml");
        String xmlInitial = "src/test/resources/device_files/Devices.xml";
        String xmlCreated = "src/test/resources/device_files/Write.xml";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setCoalescing(true);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc1 = db.parse(new File(xmlInitial));
        doc1.normalizeDocument();

        Document doc2 = db.parse(new File(xmlCreated));
        doc2.normalizeDocument();

        Assert.assertTrue(doc1.isEqualNode(doc2));
    }
}
