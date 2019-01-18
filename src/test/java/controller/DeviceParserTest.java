package controller;

import entity.device.Component;
import entity.device.Device;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static entity.device.PortEnum.COM;
import static entity.device.PortEnum.LPT;
import static entity.device.PortEnum.USB;
import static org.junit.Assert.*;

public class DeviceParserTest {
    private Set<Device> expectedSet = new LinkedHashSet<>();
    @Before
    public void fillSet(){
        Component c1 = new Component("Block","Russia",2,true,2,COM,true);
        Component c2 = new Component("Monitor","Russia",10,true,3,COM,true);
        Component c3 = new Component("Display","Russia",2,true,10,USB,true);
        Component c4 = new Component("Block","Russia",2,true,2,LPT,true);
        Component c5 = new Component("Mouse","USA",3,true,2,LPT,true);
        Component c6 = new Component("Printer","USA",10,true,2,LPT,true);
        Device d1 = new Device();
        Device d2 = new Device();
        Device d3 = new Device();
        d1.setID("ID_1");
        d2.setID("ID_2");
        d3.setID("ID_3");
        d1.setComponents(Arrays.asList(c1,c2));
        d2.setComponents(Arrays.asList(c3));
        d3.setComponents(Arrays.asList(c4,c5,c6));
        expectedSet.addAll(Arrays.asList(d1,d2,d3));

    }

    @Test
    public void parseSax() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        Set<Device> actualSet = parser.parse("src/test/resources/device_files/Devices.xml","src/test/resources/device_files/DevicesXsd.xsd","SAX");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseSax() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        parser.parse("src/test/resources/device_files/InvalidDevice.xml","src/test/resources/device_files/DevicesXsd.xsd","SAX");
    }
    @Test
    public void parseDom() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        Set<Device> actualSet = parser.parse("src/test/resources/device_files/Devices.xml","src/test/resources/device_files/DevicesXsd.xsd","DOM");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseDom() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        parser.parse("src/test/resources/device_files/InvalidDevice.xml","src/test/resources/device_files/DevicesXsd.xsd","DOM");
    }
    @Test
    public void parseStAx() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        Set<Device> actualSet = parser.parse("src/test/resources/device_files/Devices.xml","src/test/resources/device_files/DevicesXsd.xsd","STAX");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseStAx() throws XmlParserException {
        DeviceParser parser = new DeviceParser();
        parser.parse("src/test/resources/device_files/InvalidDevice.xml","src/test/resources/device_files/DevicesXsd.xsd","STAX");
    }
}