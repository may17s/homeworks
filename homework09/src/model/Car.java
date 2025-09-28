package model;

import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private int year;
    private int power;
    private int acceleration;
    private int suspension;
    private int durability;

    public Car() {}

    public Car(String brand, String model, int year, int power, int acceleration, int suspension, int durability) {
        this.setBrand(brand);
        this.setModel(model);
        this.setYear(year);
        this.setPower(power);
        this.setAcceleration(acceleration);
        this.setSuspension(suspension);
        this.setDurability(durability);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return String.format("model.Car {brand='%s', model='%s', year=%d, power=%d, acceleration=%d, suspension=%d, durability=%d}",
                brand, model, year, power, acceleration, suspension, durability);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Car car)) return false;

        return year == car.year &&
                power == car.power &&
                acceleration == car.acceleration &&
                suspension == car.suspension &&
                durability == car.durability &&
                brand.equals(car.brand) &&
                model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, power, acceleration, suspension, durability);
    }
}
