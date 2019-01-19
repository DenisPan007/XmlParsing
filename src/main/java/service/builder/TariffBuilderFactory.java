package service.builder;

import entity.tariff.Tariff;
import service.builder.AbstractEntitiesBuilder;
import service.builder.dom.TariffsDOMBuilder;
import service.builder.sax.BuilderException;
import service.builder.sax.tariff.TariffsSAXBuilder;
import service.builder.stax.TariffsStAxBuilder;

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