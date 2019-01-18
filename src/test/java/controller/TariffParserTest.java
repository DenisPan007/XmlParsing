package controller;

import entity.tariff.Tariff;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TariffParserTest {
    private Set<Tariff> expectedSet = new LinkedHashSet<>();
    @Before
    public void fillSet(){
        Tariff t1 = new Tariff("Ellissa","mts",171,5,10,9,30,2,201);
        Tariff t2 = new Tariff("Albert","mts",131,4,6,19,23,2,235);
        Tariff t3 = new Tariff("Kendall","velcome",171,1,5,13,20,1,369);
        Tariff t4 = new Tariff("Dill","mts",138,3,8,8,21,1,496);
        Tariff t5 = new Tariff("Bonny","life",108,4,4,17,30,2,383);
        Tariff t6 = new Tariff("Karia","mts",114,4,7,13,25,5,361);
        Tariff t7 = new Tariff("Mabelle","life",113,1,7,17,21,1,345);
        Tariff t8 = new Tariff("Normie","velcome",102,1,10,10,25,3,257);
        Tariff t9 = new Tariff("Kermit","mts",117,5,10,17,28,4,305);
        Tariff t10 = new Tariff("Lyon","life",157,2,4,13,22,5,360);
        Tariff t11 = new Tariff("Bianka","velcome",112,2,5,10,22,3,423);
        Tariff t12 = new Tariff("Phillida","mts",153,3,6,14,27,3,450);
        Tariff t13 = new Tariff("Nico","life",128,1,5,13,22,5,488);
        Tariff t14 = new Tariff("Haley","mts",114,1,4,8,22,1,420);
        Tariff t15 = new Tariff("Cristionna","velcome",175,3,5,15,29,4,486);
        Tariff t16 = new Tariff("Berkly","mts",175,5,9,10,24,3,250);

        expectedSet.addAll(Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16));
    }

    @Test
    public void validParseSax() throws XmlParserException {
        TariffParser parser = new TariffParser();
        Set<Tariff> actualSet = parser.parse("src/test/resources/tariffs_files/Tariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","SAX");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseSax() throws XmlParserException {
        TariffParser parser = new TariffParser();
        parser.parse("src/test/resources/tariffs_files/InvalidTariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","SAX");
    }
    @Test
    public void parseDom() throws XmlParserException {
        TariffParser parser = new TariffParser();
        Set<Tariff> actualSet = parser.parse("src/test/resources/tariffs_files/Tariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","DOM");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseDom() throws XmlParserException {
        TariffParser parser = new TariffParser();
        parser.parse("src/test/resources/tariffs_files/InvalidTariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","DOM");
    }
    @Test
    public void parseStAx() throws XmlParserException {
        TariffParser parser = new TariffParser();
        Set<Tariff> actualSet = parser.parse("src/test/resources/tariffs_files/Tariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","STAX");
        assertEquals(actualSet,expectedSet);
    }
    @Test(expected = XmlParserException.class)
    public void invalidParseStAx() throws XmlParserException {
        TariffParser parser = new TariffParser();
        parser.parse("src/test/resources/tariffs_files/InvalidTariffs.xml","src/test/resources/tariffs_files/TariffsXsd.xsd","STAX");
    }
}