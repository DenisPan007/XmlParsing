package controller;

import entity.device.Device;
import service.builder.AbstractEntitiesBuilder;
import service.builder.sax.BuilderException;
import service.builder.DeviceBuilderFactory;
import service.validate.ValidatorException;
import service.validate.ValidatorSaxXsd;

import java.util.Set;

public class DeviceParser {
    private ValidatorSaxXsd validator = new ValidatorSaxXsd();
    private DeviceBuilderFactory factory = new DeviceBuilderFactory();

    public Set<Device> parse(String fileName, String schemaXsd, String typeOfParser)throws XmlParserException{
        try {
            validator.validate(fileName, schemaXsd);
            AbstractEntitiesBuilder<Device> builder = factory.createDeviceBuilder(typeOfParser);
            builder.buildSetEntities(fileName);
            return builder.getEntities();
        }
        catch (ValidatorException | BuilderException e){
            throw new XmlParserException(e);
        }
    }
}
