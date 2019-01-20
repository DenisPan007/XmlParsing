package service.writer;

import entity.tariff.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Set;

public class TariffDOMxmlWriter {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    public TariffDOMxmlWriter() throws WriterException{
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
            throw new WriterException("Can't write to  XML File",e);
        }
    }
    public void writeToXML(Set<Tariff> tariffSet, String fileName) throws WriterException{
        try{
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("tariffs");
            rootElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns","http://www.example.com/tariffs");
            rootElement.setAttribute("xsi:schemaLocation","http://www.example.com/tariffs TariffsXsd.xsd");
            doc.appendChild(rootElement);
            for (Tariff tariff : tariffSet) {
                rootElement.appendChild(getTariff(doc, tariff));
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

    private Node getTariff(Document doc, Tariff tariff) {
        Element tariff1 = doc.createElement("tariff");
        tariff1.setAttribute("ID", tariff.getId());
        if (tariff.isOldTariff()) {
            tariff1.setAttribute("old_tariff", "true");
        }
        tariff1.appendChild(getComponentElement(doc,"name",tariff.getName()));
        tariff1.appendChild(getComponentElement(doc,"operator_name",tariff.getOperatorName()));
        tariff1.appendChild(getComponentElement(doc,"payroll",String.valueOf(tariff.getPayroll())));
        tariff1.appendChild(getComponentElement(doc,"call_price_inside_net",String.valueOf(tariff.getCallPriceInsideNet())));
        tariff1.appendChild(getComponentElement(doc,"call_price_outside_net",String.valueOf(tariff.getCallPriceOutsideNet())));
        tariff1.appendChild(getComponentElement(doc,"call_price_to_static_phones",String.valueOf(tariff.getCallPriceToStaticPhones())));
        tariff1.appendChild(getComponentElement(doc,"sms_price",String.valueOf(tariff.getSmsPrice())));
        tariff1.appendChild(getComponentElement(doc,"favorite_number_amount",String.valueOf(tariff.getFavoriteNumbersAmount())));
        tariff1.appendChild(getComponentElement(doc,"price_for_getting_tariff",String.valueOf(tariff.getPriceForGettingTariff())));
        return tariff1;
    }

    private Node getComponentElement(Document doc, String name, String value){
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
