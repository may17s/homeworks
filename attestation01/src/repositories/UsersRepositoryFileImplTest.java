package repositories;

import exception.UserNotFoundException;
import model.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersRepositoryFileImplTest {

    private static UsersRepositoryFileImpl repo;
    private static final String GUID_TEST_ID = "guid-testid-1";

    @BeforeAll
    static void BeforeAll() {
        repo = new UsersRepositoryFileImpl();
    }

    public User setUser() {
        User user = new User();
        user.setId(GUID_TEST_ID);
        user.setLogin("super_user");
        user.setPassword("password1234");
        user.setConfirmPassword("password1234");
        user.setLastName("Сизиков");
        user.setFirstName("Даниил");
        user.setAge(33);
        return user;
    }

    @Test
    @Order(1)
    void testCreateUser() {
        assertDoesNotThrow(() -> repo.create(setUser()));
    }

    @Test
    @Order(2)
    @DisplayName("Тест на логин ≥ 20 символов, будет исключение")
    void testCreateUserWithMore20Symbols() {
        User user = setUser();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> user.setLogin("super_mega_big_login"),
                "Слишком длинный логин"
        );
    }

    @Test
    @Order(3)
    @DisplayName("Тест на логин только из цифр, будет исключение")
    void testCreateUserWithOnlyNumbers() {
        User user = setUser();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> user.setLogin("234"),
                "Логин не должен содержать только цифры"
        );
    }

    @Test
    @Order(20)
    void findById() {
        User found = repo.findById(GUID_TEST_ID);
        assertNotNull(found);
        assertEquals(GUID_TEST_ID, found.getId());
    }

    @Test
    @Order(24)
    void update() {
        User user = repo.findById(GUID_TEST_ID);
        user.setAge(32);
        assertDoesNotThrow(() -> repo.update(user));

        User updatedUser = repo.findById(GUID_TEST_ID);
        assertEquals(32, updatedUser.getAge());
    }

    @Test
    @Order(25)
    void deleteById() {
        assertDoesNotThrow(() -> repo.deleteById(GUID_TEST_ID));
        assertThrows(UserNotFoundException.class, () -> repo.findById(GUID_TEST_ID));
    }

    @Test
    @Order(28)
    @Disabled("Not implemented yet")
    void findByIsWorker() {
    }

    @AfterAll
    static void AfterAll() {
    }
}