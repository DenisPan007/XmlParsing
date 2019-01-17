package service.entity_builder;

import entity.device.Device;
import service.entity_builder.dom.DeviceDOMBuilder;
import service.entity_builder.sax.DeviceSAXBuilder;
import service.entity_builder.stax.DeviceStAXBuilder;


public class DeviceBuilderFactory  {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractEntitiesBuilder<Device> createDeviceBuilder(String typeParser) {
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
