package org.example.test;

import org.example.model.Car;
import org.example.repository.CarsRepository;
import org.example.repository.CarsRepositoryImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String path = "src/main/java/org/example/data/";
        String inputFileName = path + "cars.txt";
        String outputFileName = path + "output.txt";

        CarsRepository repository = new CarsRepositoryImpl(inputFileName);
        List<Car> cars = repository.getAllCars();

        String colorToFind = "Black";
        long mileageToFind = 0L;
        long minPrice = 700000L;
        long maxPrice = 900000L;
        String modelToFind1 = "Toyota";
        String modelToFind2 = "Volvo";

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println("Автомобили в базе:");
            writer.println("Number Model Color Mileage Cost");
            cars.forEach(car -> writer.println(car.toString()));

            // 1. Номера по цвету или пробегу
            List<String> numbersByColorOrMileage = cars.stream()
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .collect(Collectors.toList());
            writer.println("Номера автомобилей по цвету или пробегу: " + String.join(" ", numbersByColorOrMileage));

            // 2. Уникальные модели в ценовом диапазоне
            long uniqueModelsCount = cars.stream()
                .filter(car -> car.getCost() >= minPrice && car.getCost() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();
            writer.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

            // 3. Цвет автомобиля с минимальной стоимостью
            String minCostColor = cars.stream()
                .min(Comparator.comparing(Car::getCost))
                .map(Car::getColor)
                .orElse("");
            writer.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

            // 4. Средняя стоимость модели
            printAverageCostByModel(writer, cars, modelToFind1);
            printAverageCostByModel(writer, cars, modelToFind2);
            // Также выводим в консоль
            System.out.println("Результаты записаны в файл: " + outputFileName);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private static void printAverageCostByModel(PrintWriter writer, List<Car> cars, String model) {
        Double avgCost = cars.stream()
                .filter(car -> car.getModel().equals(model))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
            writer.printf("Средняя стоимость модели %s: %.2f\n", model, avgCost);
    }
}