package org.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String Name;
    private LocalDate birthDate;
    private long phoneNumber;
    private char gender;
    private int age;

    public Person(String Name, LocalDate birthDate, long phoneNumber, char gender, int age) {
        this.Name = Name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s%s %d%c",
                Name, birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                phoneNumber, gender);
    }
}