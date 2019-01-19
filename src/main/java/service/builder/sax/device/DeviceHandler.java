package service.builder.sax.device;

import entity.device.Component;
import entity.device.Device;
import entity.device.DeviceEnum;
import entity.device.PortEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private Set<Device> deviceSet;
    private Device currentDevice;
    private DeviceEnum currentEnum;
    private EnumSet<DeviceEnum> withText;

    public DeviceHandler() {
        deviceSet = new LinkedHashSet<>();
        withText = EnumSet.range(DeviceEnum.COMPONENT, DeviceEnum.CRITICAL);
    }

    public Set<Device> getDevices() {
        return deviceSet;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (DeviceEnum.valueOf(localName.toUpperCase())) {
            case DEVICE:
                currentDevice = new Device();
                currentDevice.setID(attrs.getValue(0));
                break;
            default:
                DeviceEnum temp = DeviceEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if (DeviceEnum.DEVICE.getValue().equals(localName)) {
            deviceSet.add(currentDevice);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();

        if (currentEnum != null) {
            switch (currentEnum) {
                case COMPONENT:
                    currentDevice.getComponents().add(new Component());
                    break;
                case NAME:
                    currentDevice.getComponents().get(currentDevice.getComponents().size() - 1).setName(s);
                case COUNTRY:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setCountry(s);
                    break;
                case PRICE:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setPrice(Integer.parseInt(s));
                    break;
                case PERIPHERAL:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setPeripheral(Boolean.valueOf(s));
                    break;
                case POWER_CONSUMPTION_WATT:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setPowerConsumptionWatt(Integer.parseInt(s));
                    break;
                case PORT:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setPort(PortEnum.valueOf(s));
                    break;
                case CRITICAL:
                    currentDevice.getComponents().get(currentDevice.getComponents().size()-1).setCritical(Boolean.valueOf(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}