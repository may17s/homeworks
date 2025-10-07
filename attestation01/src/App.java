import model.User;
import repositories.UsersRepositoryFileImpl;

import java.util.List;

public class App {

    private static final String GUID_TEST_ID = "004c16a2-64b9-4c18-806f-87f3385c788f";

    public static void main(String[] args) {
        UsersRepositoryFileImpl repo = new UsersRepositoryFileImpl();

        User testUser = new User();
        testUser.setId(GUID_TEST_ID);
        testUser.setLogin("pimenov_s");
        testUser.setPassword("q89q7a");
        testUser.setConfirmPassword("q89q7a");
        testUser.setLastName("Пименов");
        testUser.setFirstName("Святогор");
        testUser.setMiddleName("Павлович");
        testUser.setAge(44);
        testUser.setWorker(true);

        System.out.println("1. Создаем пользователя");
        repo.create(testUser);

        System.out.println("2. Поиск пользователя в файле по идентификатору " + GUID_TEST_ID);
        User foundUser = repo.findById(GUID_TEST_ID);
        System.out.println(foundUser);

        System.out.println("2. Выгрузка всех пользователей из файла");
        List<User> all = repo.findAll();
        all.forEach(System.out::println);

        System.out.println("3. Обновление полей существующего в файле пользователя");
        foundUser.setAge(29);
        repo.update(foundUser);

        System.out.println("4. Поиск по возрасту 29");
        List<User> byAge = repo.findByAge(29);
        byAge.forEach(System.out::println);

        System.out.println("5. Все работники");
        List<User> workers = repo.findByIsWorker(true);
        workers.forEach(System.out::println);

        System.out.println("6. удаление пользователя по идентификатору");
        repo.deleteById(GUID_TEST_ID);
    }
}