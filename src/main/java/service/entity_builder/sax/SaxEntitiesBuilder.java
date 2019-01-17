package service.entity_builder.sax;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import service.entity_builder.AbstractEntitiesBuilder;

import java.io.IOException;

public  class SaxEntitiesBuilder<T> extends AbstractEntitiesBuilder<T> {
    private XMLReader reader;

    public SaxEntitiesBuilder(DefaultHandler handler) throws BuilderException {
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        }
        catch (SAXException e){
            throw new BuilderException("Can't create XMLReader",e);
        }
    }

    @Override
    public void buildSetEntities(String fileName) throws BuilderException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new BuilderException(e);
        } catch (IOException e) {
            throw new BuilderException("Can't read file",e);
        }
        entities = getEntities();
    }
}
