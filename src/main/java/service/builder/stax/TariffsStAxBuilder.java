package service.builder.stax;

import entity.tariff.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.builder.AbstractEntitiesBuilder;
import entity.tariff.TariffEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TariffsStAxBuilder extends AbstractEntitiesBuilder<Tariff>{
    private static final Logger LOGGER = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public TariffsStAxBuilder() {
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
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.TARIFF) {
                        Tariff device = buildTariff(reader, name);
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

    private Tariff buildTariff(XMLStreamReader reader, String bankType) throws XMLStreamException /*ParsingException */ {
        Tariff tariff = new Tariff();
        tariff.setId(reader.getAttributeValue(null, TariffEnum.ID.getValue()));
        tariff.setOldTariff(Boolean.valueOf(reader.getAttributeValue(null, TariffEnum.OLD_TARIFF.getValue())));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            tariff.setName(getXMLText(reader));
                            break;
                        case OPERATOR_NAME:
                            tariff.setOperatorName(getXMLText(reader));
                            break;
                        case PAYROLL:
                            tariff.setPayroll(Double.valueOf(getXMLText(reader)));
                            break;
                        case CALL_PRICE_INSIDE_NET:
                            tariff.setCallPriceInsideNet(Double.valueOf(getXMLText(reader)));
                            break;
                        case CALL_PRICE_OUTSIDE_NET:
                            tariff.setCallPriceOutsideNet(Double.valueOf(getXMLText(reader)));
                            break;
                        case CALL_PRICE_TO_STATIC_PHONES:
                            tariff.setCallPriceToStaticPhones(Double.valueOf(getXMLText(reader)));
                            break;
                        case SMS_PRICE:
                            tariff.setSmsPrice(Integer.valueOf(getXMLText(reader)));
                            break;
                        case FAVORITE_NUMBER_AMOUNT:
                            tariff.setFavoriteNumbersAmount(Integer.valueOf(getXMLText(reader)));
                            break;
                        case PRICE_FOR_GETTING_TARIFF:
                            tariff.setPriceForGettingTariff(Double.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffEnum.valueOf(name.toUpperCase()) == TariffEnum.TARIFF) {
                        return tariff;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Tariff");
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

