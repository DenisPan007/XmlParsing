package service.builder.stax;

import org.junit.Test;
import service.builder.sax.BuilderException;


public class DeviceStAXBuilderTest {
    @Test
    public void buildSetEntities() throws BuilderException {
        DeviceStAXBuilder stAxBuilder = new DeviceStAXBuilder();
        stAxBuilder.buildSetEntities("src/test/resources/device_files/Devices.xml");
        System.out.println(stAxBuilder.getEntities());
    }
}
