package service.deviceParsing;

public enum DeviceEnum {
    DEVICES("devices"),
    DEVICE("device"),
    ID("ID"),
    COMPONENT("component"),
    NAME("name"),
    COUNTRY("country"),
    PRICE("price"),
    PERIPHERAL("peripheral"),
    POWER_CONSUMPTION_WATT("power_Consumption_Watt"),
    PORT("port"),
    CRITICAL("critical");
    private String value;

    DeviceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
