package service.entity_builder.sax.tariffs_parse;

import entity.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffsHandler extends DefaultHandler {
    private Set<Tariff> tariffs;
    private Tariff currentTariff = null;
    private TariffEnum currentEnum = null;
    private EnumSet<TariffEnum> withText;

    public TariffsHandler() {
        tariffs = new HashSet<Tariff>();
        withText = EnumSet.range(TariffEnum.NAME,TariffEnum.PRICE_FOR_GETTING_TARIFF);
    }

    @Override
    public void startDocument() {
        System.out.println("Parsing started");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("tariff".equals(localName)) {
            currentTariff = new Tariff();
            currentTariff.setId(Long.valueOf(attrs.getValue(0)));
        } else {
            TariffEnum temp = TariffEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }
    @Override
    public void characters(char[ ] ch, int start, int length) {
        System.out.print("CHARACTERS!  ");
        System.out.println(new String(ch, start, length));
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println(localName);
        System.out.println("end element!");
    }
    @Override
    public void endDocument() {
        System.out.println("\nParsing ended");
    }
}

