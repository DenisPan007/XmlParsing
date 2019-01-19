package service.builder.dom;

import entity.tariff.Tariff;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.builder.AbstractEntitiesBuilder;
import entity.tariff.TariffEnum;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TariffsDOMBuilder extends AbstractEntitiesBuilder<Tariff> {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public TariffsDOMBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    @Override
    public void buildSetEntities(String fileName) {
        Document doc;
        LOGGER.log(Level.DEBUG, "Start parsing...");
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            entities.clear();
            NodeList tariffsList = root.getElementsByTagName(TariffEnum.TARIFF.getValue());
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element element = (Element) tariffsList.item(i);
                Tariff tariff = buildTariff(element);
                entities.add(tariff);
            }
        } catch (IOException | SAXException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private Tariff buildTariff(Element tariffElement) {
        Tariff tariff = new Tariff();
        tariff.setId(tariffElement.getAttribute(TariffEnum.ID.getValue()));
        tariff.setName(getElementTextContent(tariffElement, TariffEnum.NAME.getValue()));
        tariff.setOperatorName(getElementTextContent(tariffElement, TariffEnum.OPERATOR_NAME.getValue()));
        tariff.setPayroll(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.PAYROLL.getValue())));
        tariff.setCallPriceInsideNet(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.CALL_PRICE_INSIDE_NET.getValue())));
        tariff.setCallPriceOutsideNet(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.CALL_PRICE_OUTSIDE_NET.getValue())));
        tariff.setCallPriceToStaticPhones(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.CALL_PRICE_TO_STATIC_PHONES.getValue())));
        tariff.setSmsPrice(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.SMS_PRICE.getValue())));
        tariff.setFavoriteNumbersAmount(Integer.parseInt(getElementTextContent(tariffElement, TariffEnum.FAVORITE_NUMBER_AMOUNT.getValue())));
        tariff.setPriceForGettingTariff(Double.parseDouble(getElementTextContent(tariffElement, TariffEnum.PRICE_FOR_GETTING_TARIFF.getValue())));
        return tariff;
    }
}