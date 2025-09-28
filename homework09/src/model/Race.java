package model;

import java.util.List;
import java.util.ArrayList;

public class Race {
    private int length;
    private String route;
    private int prize;
    private List<Car> cars;

    public Race() {
        this.cars = new ArrayList<>();
    }

    public Race(int length, String route, int prize) {
        this.length = length;
        this.route = route;
        this.prize = prize;
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) { this.cars = cars; }
    public void addCar(Car car) { cars.add(car); }

    @Override
    public String toString() {
        return String.format("Race {length=%d, route='%s', prize=%d, cars=%s}",
                length, route, prize, cars.toString());
    }
}
