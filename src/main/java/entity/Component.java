package entity;

import java.util.Objects;

public class Component {
    private String name;
    private String country;
    private int price;
    private boolean peripheral;
    private int  powerConsumptionWatt;
    private Port port;
    private  boolean critical;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public int getPowerConsumptionWatt() {
        return powerConsumptionWatt;
    }

    public void setPowerConsumptionWatt(int powerConsumptionWatt) {
        this.powerConsumptionWatt = powerConsumptionWatt;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return price == component.price &&
                peripheral == component.peripheral &&
                powerConsumptionWatt == component.powerConsumptionWatt &&
                critical == component.critical &&
                Objects.equals(name, component.name) &&
                Objects.equals(country, component.country) &&
                port == component.port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, price, peripheral, powerConsumptionWatt, port, critical);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Component{");
        sb.append("name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", price=").append(price);
        sb.append(", peripheral=").append(peripheral);
        sb.append(", powerConsumptionWatt=").append(powerConsumptionWatt);
        sb.append(", port=").append(port);
        sb.append(", critical=").append(critical);
        sb.append('}');
        return sb.toString();
    }
}
enum Port{
    COM,USB,LPT
}
