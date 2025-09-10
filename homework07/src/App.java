import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите название продукта и через = сумму, через запятую скидку (или END для завершения):");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("END")) {
                    break;
                }

                String[] parts = input.split("=", 2);
                if (parts.length != 2) {
                    System.out.println("Неверный формат ввода.");
                    continue;
                }

                String productName = parts[0].trim();
                String cost = parts[1].trim();
                try {
                    if (!input.contains(",")) {
                        // обычный продукт
                        products.add(new Product(productName, Double.parseDouble(cost)));
                    } else {
                        String[] costParts = cost.split(",", 2);
                        if (costParts.length != 2) {
                            System.out.println("Неверный формат ввода цены.");
                            continue;
                        }
                        cost = costParts[0].trim();
                        String percent = costParts[1].replace("%", "").trim();
                        products.add(new DiscountProduct(productName,
                                Double.parseDouble(cost),
                                Double.parseDouble(percent),
                                LocalDate.now().plusMonths(1)
                        ));

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        if (!products.isEmpty()) {
            System.out.print("Обычные продукты: ");
            boolean hasRegular = false;
            for (Product p : products) {
                if (!(p instanceof DiscountProduct)) {
                    if (hasRegular) System.out.print(", ");
                    System.out.print(p.getName());
                    hasRegular = true;
                }
            }
            if (!hasRegular) System.out.print("Нет");
            System.out.println();

            System.out.print("Акционные продукты: ");
            boolean hasDiscount = false;
            for (Product p : products) {
                if (p instanceof DiscountProduct) {
                    if (hasDiscount) System.out.print(", ");
                    System.out.print(p.getName());
                    hasDiscount = true;
                }
            }
            if (!hasDiscount) System.out.print("Нет");
            System.out.println();
        }
    }
}