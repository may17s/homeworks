package org.example.repository;

import org.example.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {

    private final String filePath;

    public CarsRepositoryImpl(String inputFilePath) {
        this.filePath = inputFilePath;
    }

    @Override
    public List<Car> getAllCars() {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(this::parseCar)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, exception);
        }
    }

    private Car parseCar(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) return null;
        return new Car(
                parts[0],
                parts[1],
                parts[2],
                Long.parseLong(parts[3]),
                Long.parseLong(parts[4])
        );
    }
}