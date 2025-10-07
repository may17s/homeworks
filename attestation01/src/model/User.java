package model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

public class User {
    private String id;
    private LocalDateTime created;
    private String login;
    private String password;
    private String confirmPassword;
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer age;
    private boolean isWorker;

    public User() {
        this.created = LocalDateTime.now();
        this.isWorker = false;
    }

    public User(String id, LocalDateTime created, String login, String password, String confirmPassword,
                String lastName, String firstName, String middleName, Integer age, boolean isWorker) {
        this.id = id;
        this.created = created == null ? LocalDateTime.now() : created;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.isWorker = isWorker;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    public String getLogin() { return login; }
    public void setLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("Login не может быть пустым");
        } else if (login.length() >= 20) {
            throw new IllegalArgumentException("Login больше 20 символов");
        } else if (!login.matches("^[a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("Login не соответствует буквенно-цифровому и знаку подчеркивания");
        } else if (login.matches("^\\d+$")) {
            throw new IllegalArgumentException("Login не должен содержать только цифры");
        }
        this.login = login;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || !lastName.matches("^[а-яА-Яa-zA-Z]+$")) {
            throw new IllegalArgumentException("Некорректная фамилия");
        }
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || !firstName.matches("^[а-яА-Яa-zA-Z]+$")) {
            throw new IllegalArgumentException("Некорректное имя");
        }
        this.firstName = firstName;
    }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) {
        if (middleName != null && !middleName.matches("^[а-яА-Яa-zA-Z]*$")) {
            throw new IllegalArgumentException("Некорректное отчество");
        }
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        if (age != null && age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = age;
    }

    public boolean isWorker() { return isWorker; }
    public void setWorker(boolean worker) { isWorker = worker; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;

        return isWorker == user.isWorker &&
                Objects.equals(id, user.id) &&
                Objects.equals(created, user.created) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(confirmPassword, user.confirmPassword) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, login, password, confirmPassword, lastName, firstName, middleName, age, isWorker);
    }

    @Override
    public String toString() {
        return id + "|" + created + "|" + login + "|" + password + "|" + confirmPassword + "|" +
                lastName + "|" + firstName + "|" + middleName + "|" + age + "|" + isWorker;
    }

    public static final Function<String, User> stringToUser = line -> {
        String[] parts = line.split("\\|");
        if (parts.length < 10) {
            throw new IllegalArgumentException("Некорректный формат строки: " + line);
        }

        User user = new User();
        user.setId(parts[0]);
        user.setCreated(LocalDateTime.parse(parts[1]));
        user.setLogin(parts[2]);
        user.setPassword(parts[3]);
        user.setConfirmPassword(parts[4]);
        user.setLastName(parts[5]);
        user.setFirstName(parts[6]);
        user.setMiddleName(parts[7].isEmpty() ? null : parts[7]);
        user.setAge(parts[8].isEmpty() ? null : Integer.valueOf(parts[8]));
        user.setWorker(Boolean.parseBoolean(parts[9]));

        return user;
    };

    public static final Function<User, String> userToString = user ->
            user.getId() + "|" +
                    user.getCreated() + "|" +
                    user.getLogin() + "|" +
                    user.getPassword() + "|" +
                    user.getConfirmPassword() + "|" +
                    user.getLastName() + "|" +
                    user.getFirstName() + "|" +
                    (user.getMiddleName() != null ? user.getMiddleName() : "") + "|" +
                    (user.getAge() != null ? user.getAge() : "") + "|" +
                    user.isWorker();

}
