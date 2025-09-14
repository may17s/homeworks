import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();;
        List<Product> products = new ArrayList<>();;

        try {
            Path filePath = Paths.get("src/input.txt");
            List<String> lines = Files.readAllLines(filePath);

            int lineidx = 0;
            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.equals("END")) {
                    break;
                }
                if (lineidx == 0) {
                    persons = parsePersons(line);
                }
                else if( lineidx == 1) {
                    products = parseProducts(line);
                } else {
                    Person person = findPersonByName(persons, line);
                    if (person == null) {
                        System.out.println("Покупатель в строке не найден: " + line);
                        continue;
                    }
                    String productName = line.replace(person.getName(), "").trim();
                    Product product = findProductByName(products, productName);

                    if (product == null) {
                        System.out.println("Продукт в строке не найден: " + line);
                        continue;
                    }

                    person.buy(product);
                }

                lineidx++;
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }

        for(Person person: persons) {
            System.out.println(person);
        }
    }

    private static List<Person> parsePersons(String line) {
        List<Person> persons = new ArrayList<>();
        String[] data = line.split("\\r?\\n|;|,|\\r");
        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split("=", 2);
            if (parts.length != 2) {
                System.out.println("Неверный формат части строки: " + data[i]);
                continue;
            }

            String personName = parts[0].trim();
            double money = Double.parseDouble(parts[1].trim());

            if (findPersonByName(persons, personName) == null) {
                persons.add(new Person(personName, money));
            }
        }

        return persons;
    }

    private static List<Product> parseProducts(String line) {
        List<Product> products = new ArrayList<>();
        String[] data = line.split("\\r?\\n|;|,|\\r");
        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split("=", 2);
            if (parts.length != 2) {
                System.out.println("Неверный формат части строки: " + data[i]);
                continue;
            }

            String productName = parts[0].trim();
            double money = Double.parseDouble(parts[1].trim());

            if (findProductByName(products, productName) == null) {
                products.add(new Product(productName, money));
            }
        }

        return products;
    }

    private static Person findPersonByName(List<Person> persons, String name) {
        for (Person person : persons) {
            if(name.startsWith(person.getName())) {
                return person;
            }
        }
        return null;
    }


    private static Product findProductByName(List<Product> products, String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

}
