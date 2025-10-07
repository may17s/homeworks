import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Тестовые данные №1
        Person[] persons = new Person[] {
            new Person("Павел Андреевич", 10000),
            new Person("Анна Петровна", 2000),
            new Person("Борис", 10)
        };

        // Тестовые данные №2
        /*
        Person[] persons = new Person[] {
                new Person("Женя", 0)
        };
        */
        // Тестовые данные №3
        /*
        Person[] persons = new Person[] {
                new Person("Света", -3)
        };
        */

        Product[] products = new Product[] {
            new Product("Хлеб", 40),
            new Product("Молоко", 60),
            new Product("Торт", 1000),
            new Product("Кофе растворимый", 879),
            new Product("Масло", 150),
            new Product("Мороженое", 200),
            new Product("Макароны", 800)
        };

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите имя покупателя и название продукта через запятую (или END для завершения):");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("END")) {
                    break;
                }

                String[] parts = input.split(",", 2);
                if (parts.length != 2) {
                    System.out.println("Неверный формат ввода.");
                    continue;
                }

                String personName = parts[0];
                String productName = parts[1];

                int index = -1;
                for (int i = 0; i < persons.length; i++) {
                    if (persons[i].getName().equals(personName)) {
                        index = i;
                        break;
                    }
                }

                if(index == -1) {
                    System.out.println("Неизвестный покупатель: " + personName);
                    continue;
                }

                Person person = persons[index];

                index = -1;
                for (int i = 0; i < products.length; i++) {
                    if (products[i].getName().equals(productName)) {
                        index = i;
                        break;
                    }
                }

                if(index == -1) {
                    System.out.println("Неизвестный продукт: " + productName);
                    continue;
                }

                person.buy(products[index]);
            }

            for(Person person: persons) {
                System.out.println(person);
            }
        }

        // Создаём сущности
        Program newsProgram = new Program("Новости", 8.5, 1000000);
        Program movieProgram = new Program("Очень интересный фильм", 9.2, 500000);

        Channel channel1 = new Channel("Первый канал", 1, newsProgram);
        Channel channel2 = new Channel("Россия-1", 2, movieProgram);

        Channel[] channels = {channel1, channel2};
        TelevisionSet tv = new TelevisionSet(55, TelevisionSet.DisplayTechnology.OLED, "Samsung", "QLED 4K", true, channels);

        tv.setTvSwitch(); // Включим телевизор
        System.out.println(tv); // Вывод информации о телевизоре

        tv.changeChannel(2); // Переключимся на второй канал
        System.out.println(tv.getCurrentChannel()); // Вывод текущего канала
        tv.setTvSwitch(); // Выключим телевизор
    }
}