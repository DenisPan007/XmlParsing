package service.entity_builder_factory;

import entity.tariff.Tariff;
import service.entity_builder.AbstractEntitiesBuilder;
import service.entity_builder.dom.TariffsDOMBuilder;
import service.entity_builder.sax.BuilderException;
import service.entity_builder.sax.tariff.TariffsSAXBuilder;
import service.entity_builder.stax.TariffsStAxBuilder;

public class TariffBuilderFactory {
        private enum TypeParser {
            SAX, STAX, DOM
        }
        public AbstractEntitiesBuilder<Tariff> createTariffBuilder(String typeParser) throws BuilderException {
            TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
            switch (type) {
                case DOM:
                    return new TariffsDOMBuilder();
                case STAX:
                    return new TariffsStAxBuilder();
                case SAX:
                    return new TariffsSAXBuilder();
                default:
                    throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
            }
        }
    }