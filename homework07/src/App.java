import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Person[] persons = new Person[] {
                new Adult("Василий Викторович", 10000, 45),
                new Adult("Клара Петровна", 2000, 30),
                new Child("Иннокентий", 10, 10),
                new Child("Лёва", 5, 4),
                new Pensioner("Венедикта Александровна", 500, 70)
        };

        Product[] products = new Product[] {
                new Product("Хлеб", 40, true),
                new Product("Молоко", 60, true),
                new Product("Торт", 1000, true),
                new DiscountProduct("Торт", 1000, 15, LocalDate.of(2025, 12, 31), true),
                new DiscountProduct("Кофе растворимый", 879, 50, LocalDate.of(2025, 12, 31), false),
                new Product("Пиво", 150, false),
                new Product("Мороженое", 200, true),
                new Product("Макароны", 800, true)
        };
        System.out.println("Василий Викторович (взрослый) покупает 'Хлеб' (обычный, доступный для детей):");
        persons[0].buy(products[0]);

        System.out.println("Клара Петровна (взрослый) покупает 'Торт' (скидочный, 15%):");
        persons[1].buy(products[3]);

        System.out.println("Иннокентий (ребенок, 10 лет) покупает 'Мороженое' (доступный для детей, но дорогое):");
        persons[2].buy(products[6]);

        System.out.println("Иннокентий (ребенок, 10 лет) пытается купить 'Пиво' (не доступный для детей):");
        persons[2].buy(products[5]);

        System.out.println("Лёва (ребенок, 4 года) пытается купить 'Хлеб' (обычный, доступный для детей):");
        persons[3].buy(products[0]);

        System.out.println("Венедикта Александровна (пенсионер) покупает 'Кофе растворимый' (50% скидка):");
        persons[4].buy(products[4]);

        System.out.println("Венедикта Александровна (пенсионер) пытается купить 'Макароны' (обычный):");
        persons[4].buy(products[7]);

        System.out.println();
        System.out.println("Итого: ");

        for (Person person : persons) {
            System.out.println(person);
        }

    }
}