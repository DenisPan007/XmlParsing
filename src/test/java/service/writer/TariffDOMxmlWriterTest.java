package service.writer;

import controller.TariffParser;
import controller.XmlParserException;
import entity.tariff.Tariff;
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

import static org.junit.Assert.*;

public class TariffDOMxmlWriterTest {
    private Set<Tariff> tariffSet = new LinkedHashSet<>();
    @Before
    public void fillSet() throws XmlParserException {
        TariffParser parser = new TariffParser();
        tariffSet = parser.parse("src/test/resources/tariffs_files/Tariffs.xml", "src/test/resources/tariffs_files/TariffsXsd.xsd", "SAX");
    }
    @Test
    public void writeToXML() throws WriterException,XmlParserException {
        TariffParser parser = new TariffParser();
        TariffDOMxmlWriter tariffDOMxmlWriter = new TariffDOMxmlWriter();
        tariffDOMxmlWriter.writeToXML(tariffSet,"src/test/resources/tariffs_files/Write.xml");
        Set<Tariff> tariffSetExpected = parser.parse("src/test/resources/tariffs_files/Tariffs.xml", "src/test/resources/tariffs_files/TariffsXsd.xsd", "SAX");
        Set<Tariff> tariffSetActual = parser.parse("src/test/resources/tariffs_files/Write.xml", "src/test/resources/tariffs_files/TariffsXsd.xsd", "SAX");
        assertEquals(tariffSetExpected, tariffSetActual);
    }
}