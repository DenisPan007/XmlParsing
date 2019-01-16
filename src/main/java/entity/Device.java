package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Device {
    private List<Component> components;

    public Device() {
        components = new ArrayList<>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(components, device.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Device{");
        sb.append("components=").append(components);
        sb.append('}');
        return sb.toString();
    }
}
