import model.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Car car = new Car("Renault", "Megane 2", 2008, 113, 10, 80, 100);
        PerformanceCar pCar = new PerformanceCar("Ford", "Focus", 2009, 115, 3, 100, 80);
        ShowCar sCar = new ShowCar("Opel", "Astra", 2008, 120, 2, 90, 70);
        sCar.setStars(5);

        DragRace drag = new DragRace(400, "Экстра гонка", 10000);
        drag.addCar(pCar);
        drag.addCar(sCar);

        Garage garage = new Garage(new Car[] {car, pCar, sCar});
        System.out.println(garage);

        System.out.println("Drag Race: " + drag);
        System.out.println("PerformanceCar: " + pCar);
        System.out.println("ShowCar: " + sCar);

        System.out.println("\nДополнительное задание:\n");
        ExtraHomework();
        }

    // дополнительное задание
    public static void ExtraHomework() {
        List<Car> cars = new ArrayList<>();
        List<Race> races = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";", -1);
                String className = "model." + parts[0];

                try {
                    Class<?> clazz = Class.forName(className);

                    if (Car.class.isAssignableFrom(clazz)) {
                        Constructor<?> constructor = getConstructor(clazz);
                        Car car = (Car) constructor.newInstance(getArgs(constructor, parts));

                        if (clazz.getSimpleName().equals("PerformanceCar")) {
                            String[] addOns = parts.length > 8 && !parts[8].isEmpty() ?
                                    parts[8].split(",") : new String[] {};
                            car.getClass().getMethod("setAddOns", String[].class).invoke(car, (Object) addOns);
                        } else if (clazz.getSimpleName().equals("ShowCar")) {
                            int stars = parts.length > 8 ? Integer.parseInt(parts[8]) : 0;
                            car.getClass().getMethod("setStars", int.class).invoke(car, stars);
                        }

                        cars.add(car);

                    } else if (Race.class.isAssignableFrom(clazz)) {
                        Constructor<?> constructor = getConstructor(clazz);
                        Race race = (Race) constructor.newInstance(getArgs(constructor, parts));
                        races.add(race);

                    } else {
                        System.err.println("Класс не является Car или Race: " + className);
                    }

                } catch (Exception e) {
                    System.err.println("Ошибка при создании объекта из строки: " + line);
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка чтения input.txt: " + e.getMessage());
            return;
        }

        Garage garage = new Garage(cars.toArray(new Car[0]));

        System.out.println(garage);
        System.out.println();
        races.forEach(System.out::println);

        final String filenameOutput = "output.txt";

        try (var writer = Files.newBufferedWriter(Paths.get(filenameOutput))) {
            writer.write(garage.toString());
            writer.write("\n\n");
            for (Race race : races) {
                writer.write(race.toString() + "\n");
            }
            System.out.println("\nРезультат сохранён в " + filenameOutput);
        } catch (IOException e) {
            System.err.println("Ошибка записи " + filenameOutput + ": " + e.getMessage());
        }
    }

    public static Constructor<?> getConstructor(Class<?> cr) {
        for (Constructor<?> constructor : cr.getDeclaredConstructors()   ) {
            if (constructor.getParameterCount() > 0)
                return constructor;
        }
        return null;
    }

    public static Object[] getArgs(Constructor<?> cr, String[] parts) {
        Object[] args = new Object[cr.getParameterCount()];

        for (int i = 0; i < cr.getParameterCount(); i++) {
            String value = parts[i + 1];
            Class<?> type = cr.getParameters()[i].getType();
            if (type == String.class) {
                args[i] = value;
            } else if (type == int.class || type == Integer.class) {
                args[i] = Integer.parseInt(value);
            }
        }
        return args;
    }
}