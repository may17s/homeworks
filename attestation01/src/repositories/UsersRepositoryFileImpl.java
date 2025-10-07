package repositories;

import exception.UserNotFoundException;
import model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static model.User.stringToUser;
import static model.User.userToString;

public class UsersRepositoryFileImpl implements UsersRepository {

    private static final String USERS_FILENAME = "users.txt";

    private ArrayList<User> load() {
        File file = new File(USERS_FILENAME);
        if (!file.exists()) return new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.lines()
                    .map(stringToUser)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения из файла", e);
        }
    }

    private void save(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILENAME))) {
            users.stream()
                    .map(userToString)
                    .forEach(line -> {
                        try {
                            bw.write(line);
                            bw.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }

    @Override
    public void create(User user) {
        checkUser(user);
        List<User> users = load();
        users.add(user);
        save(users);
    }

    @Override
    public User findById(String id) {
        return load().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Пользователя с заданным идентификатором не существует"));
    }

    @Override
    public ArrayList<User> findAll() {
        return load();
    }

    @Override
    public void update(User user) {
        checkUser(user);
        ArrayList<User> users = load();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Пользователь не найден, создаем нового...");
            create(user);
            return;
        }
        save(users);
    }

    @Override
    public void deleteById(String id) {
        ArrayList<User> users = load();
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (!removed) {
            throw new UserNotFoundException("Пользователя с заданным идентификатором не существует");
        }
        save(users);
    }

    @Override
    public void deleteAll() {
        try {
            new PrintWriter(USERS_FILENAME).close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка очистки файла: " + USERS_FILENAME, e);
        }
    }

    @Override
    public ArrayList<User> findByAge(int age) {
        return load().stream()
                .filter(u -> u.getAge() != null && u.getAge() == age)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<User> findByIsWorker(boolean isWorker) {
        return load().stream()
                .filter(u -> u.isWorker() == isWorker)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void checkUser(User user) {
        if (user == null) throw new IllegalArgumentException("User не может быть null");

        // проверка password и confirmPassword
        if (user.getPassword() == null || user.getConfirmPassword() == null ||
                !user.getPassword().equals(user.getConfirmPassword()) ||
                user.getPassword().length() >= 20 ||
                !user.getPassword().matches("^[a-zA-Z0-9_]*$") ||
                user.getPassword().matches("^[a-zA-Z]*$")) {
            throw new IllegalArgumentException("Некорректный password");
        }
    }
}
