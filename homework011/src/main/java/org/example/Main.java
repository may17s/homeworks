package org.example;

import org.example.model.Car;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String inputFileName = "input.txt";

        String colorToFind = "Black";
        long mileageToFind = 0L;
        long minPrice = 700000L;
        long maxPrice = 900000L;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        List<Car> cars;

        try (Stream<String> stream = Files.lines(Paths.get(inputFileName), StandardCharsets.UTF_8)) {
            cars = stream.map(Main::parseCar)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            throw new RuntimeException("Ошибка чтения файла: " + inputFileName, exception);
        }

        System.out.println("Автомобили в базе:");
        System.out.println("Number Model Color Mileage Cost");
        cars.forEach(car -> System.out.println(car.toString()));

        // 1. Номера по цвету или пробегу
        List<String> numbersByColorOrMileage = cars.stream()
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
        System.out.println("Номера автомобилей по цвету или пробегу: " + String.join(" ", numbersByColorOrMileage));

        // 2. Уникальные модели в ценовом диапазоне
        long uniqueModelsCount = cars.stream()
                .filter(car -> car.getCost() >= minPrice && car.getCost() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

        // 3. Цвет автомобиля с минимальной стоимостью
        String minCostColor = cars.stream()
                .min(Comparator.comparing(Car::getCost))
                .map(Car::getColor)
                .orElse("");
        System.out.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

        // 4. Средняя стоимость модели
        printAverageCostByModel(cars, modelToFind1);
        printAverageCostByModel(cars, modelToFind2);
    }

    private static Car parseCar(String line) {
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

    private static void printAverageCostByModel(List<Car> cars, String model) {
        Double avgCost = cars.stream()
                .filter(car -> car.getModel().equals(model))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
        System.out.printf("Средняя стоимость модели %s: %.2f\n", model, avgCost);
    }
}