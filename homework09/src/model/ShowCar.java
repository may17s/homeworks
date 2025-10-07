package model;

import java.util.Arrays;
import java.util.Objects;

public class ShowCar extends Car {
    private int stars;

    public ShowCar() {
        super();
        this.stars = 0;
    }

    public ShowCar(String brand, String model, int year, int power, int acceleration, int suspension, int durability) {
        super(brand, model, year, power, acceleration, suspension, durability);
        this.stars = 0;
    }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    @Override
    public String toString() {
        return String.format("%s, stars=%d", super.toString(), stars);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ShowCar showCar)) return false;

        return super.equals(object) &&
                this.stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }
}
