package controller;

import entity.tariff.Tariff;
import service.entity_builder.AbstractEntitiesBuilder;
import service.entity_builder.sax.BuilderException;
import service.entity_builder_factory.TariffBuilderFactory;
import service.validate.ValidatorException;
import service.validate.ValidatorSaxXsd;

import java.util.Set;

public  class TariffParser {
    private ValidatorSaxXsd validator = new ValidatorSaxXsd();
    private TariffBuilderFactory factory = new TariffBuilderFactory();

    public Set<Tariff> parse(String fileName,String schemaXsd,String typeOfParser)throws XmlParserException{
        try {
            validator.validate(fileName, schemaXsd);
            AbstractEntitiesBuilder<Tariff> builder = factory.createTariffBuilder(typeOfParser);
            builder.buildSetEntities(fileName);
            return builder.getEntities();
        }
        catch (ValidatorException |BuilderException e){
        throw new XmlParserException(e);
        }
}
}
