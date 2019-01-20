package service.builder.sax;

import org.junit.Test;
import service.builder.sax.device.DeviceSAXBuilder;

public class DeviceSAXBuilderTest {
    @Test
    public void buildSetEntities() throws BuilderException {
        DeviceSAXBuilder builder = new DeviceSAXBuilder();
        builder.buildSetEntities("src/test/resources/device_files/Devices.xml");

    }
}
