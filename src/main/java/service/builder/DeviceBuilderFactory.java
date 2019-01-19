package service.builder;

import entity.device.Device;
import service.builder.AbstractEntitiesBuilder;
import service.builder.dom.DeviceDOMBuilder;
import service.builder.sax.BuilderException;
import service.builder.sax.device.DeviceSAXBuilder;
import service.builder.stax.DeviceStAXBuilder;


public class DeviceBuilderFactory  {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractEntitiesBuilder<Device> createDeviceBuilder(String typeParser) throws BuilderException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DeviceDOMBuilder();
            case STAX:
                return new DeviceStAXBuilder();
            case SAX:
                return new DeviceSAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
