package model;

import java.util.Arrays;
import java.util.Objects;

public class PerformanceCar extends Car {
    private String[] addOns;

    public PerformanceCar() {
        super();
        this.addOns = new String[] {};
    }

    public PerformanceCar(String brand, String model, int year, int power, int acceleration, int suspension, int durability) {
        super(brand, model, year, (int)(power * 1.5), acceleration, (int)(suspension * 0.75), durability);
        this.addOns = new String[] {};
    }

    public String[] getAddOns() { return addOns; }
    public void setAddOns(String[] addOns) { this.addOns = addOns; }

    @Override
    public String toString() {
        return String.format("%s, addOns=%s", super.toString(), Arrays.toString(addOns));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PerformanceCar performanceCar)) return false;

        return super.equals(object) &&
                Arrays.equals(this.addOns, performanceCar.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(addOns));
    }
}
