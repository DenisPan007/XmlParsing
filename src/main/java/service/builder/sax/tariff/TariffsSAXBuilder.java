package service.builder.sax.tariff;

import entity.tariff.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import service.builder.AbstractEntitiesBuilder;
import service.builder.sax.BuilderException;

import java.io.IOException;

public  class TariffsSAXBuilder extends AbstractEntitiesBuilder<Tariff> {
    private static final Logger LOGGER = LogManager.getLogger();
    private TariffsHandler tariffsHandler = new TariffsHandler();
    private XMLReader reader;

    public TariffsSAXBuilder() throws BuilderException {
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tariffsHandler);
        }
        catch (SAXException e){
            LOGGER.error(e);
            throw new BuilderException("Can't create XMLReader",e);
        }
    }

    @Override
    public void buildSetEntities(String fileName) throws BuilderException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error(e);
            throw new BuilderException(e);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new BuilderException("Can't read file",e);
        }
        entities = tariffsHandler.getTariffs();
    }
}
