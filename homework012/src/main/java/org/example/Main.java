package org.example;

import org.example.exceptions.*;
import org.example.model.Person;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Иванов Семен Семенович 10.07.2003 89678768678 m 37
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите 7 полей в произвольном порядке: ФИО дд.мм.гггг номер пол(m/f) возраст");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");

            try {
                if (parts.length != 7) {
                    throw new InvalidDataCountException(
                            parts.length < 7 ? "Слишком мало данных" : "Слишком много данных"
                    );
                }

                List<String> words = new ArrayList<>();
                String dateStr = null;
                String phoneStr = null;
                String genderStr = null;
                String ageStr = null;

                for (String part : parts) {
                    if (part.isEmpty()) continue;

                    if (genderStr == null && (part.equals("m") || part.equals("f"))) {
                        genderStr = part;
                        continue;
                    } else if (dateStr == null && isValidDate(part)) {
                        dateStr = part;
                        continue;
                    } else if (phoneStr == null && part.matches("\\d+") && !part.startsWith("-") && !part.equals("+")) {
                        phoneStr = part;
                        continue;
                    } else if (ageStr == null && part.matches("\\d+") && Integer.parseInt(part) >= 0 && Integer.parseInt(part) <= 200) {
                        ageStr = part;
                        continue;
                    } else if (part.matches("[a-zA-Zа-яА-Я]+")) {
                        words.add(part);
                        continue;
                    }

                    throw new IllegalArgumentException("Невозможно идентифицировать слово: " + part);
                }

                if (genderStr == null) throw new InvalidGenderException("Не указан пол");
                if (dateStr == null) throw new InvalidDateFormatException("Не найдена дата рождения");
                if (ageStr == null) throw new InvalidAgeException("Не указан возраст");
                if (phoneStr == null) throw new InvalidPhoneException("Не найден номер телефона");
                if (words.size() != 3) {
                    throw new InvalidDataCountException("ФИО должно содержать три слова (Фамилия, Имя, Отчество)");
                }

                LocalDate birthDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                long phone = Long.parseLong(phoneStr);
                int age = Integer.parseInt(ageStr);

                Person person = new Person(words.get(0), words.get(1), words.get(2), birthDate, phone, genderStr.charAt(0), age);

                String filename = person.getLastName() + ".txt";
                try (FileWriter writer = new FileWriter(filename, true)) {
                    writer.write(person + "\n");
                    System.out.println("Данные успешно записаны в файл " + filename);
                }

            } catch (InvalidDataCountException | InvalidDateFormatException |
                     InvalidPhoneException | InvalidGenderException | InvalidAgeException e) {
                System.err.println("Ошибка валидации: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Ошибка записи в файл: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}