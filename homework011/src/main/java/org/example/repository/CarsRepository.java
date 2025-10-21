package org.example.repository;

import org.example.model.Car;
import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
}