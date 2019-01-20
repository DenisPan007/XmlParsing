package service.builder.dom;

import org.junit.Test;
import service.builder.sax.BuilderException;

public class DeviceDOMBuilderTest {
    @Test
    public void buildSetEntities() throws BuilderException {
        DeviceDOMBuilder domBuilder = new DeviceDOMBuilder();
        domBuilder.buildSetEntities("src/test/resources/device_files/Devices.xml");
    }
}
