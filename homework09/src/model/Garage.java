package model;

import java.util.Arrays;

public class Garage {
    private Car[] parkedCars;

    public Garage() {
        this.parkedCars = new Car[] {};
    }

    public Garage(Car[] parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Car[] getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(Car[] parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Car getCar(int index) {
        if (index >= 0 && index < parkedCars.length) {
            return (Car)parkedCars[index];
        }
        return null;
    }

    public void setCar(int index, Car car) {
        if (index >= 0 && index < parkedCars.length) {
            parkedCars[index] = car;
        } else {
            throw new IllegalArgumentException("index указан не верно");
        }
    }

    @Override
    public String toString() {
        return String.format("Garage {parkedCars=%s}", Arrays.toString(parkedCars));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Garage garage)) return false;

        return Arrays.equals(parkedCars, garage.parkedCars);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(parkedCars);
    }
}
