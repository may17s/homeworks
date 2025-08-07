import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите число строк и столбцов сетки: ");
            int xy = scanner.nextInt();
            System.out.print("Введите повторяемый элемент сетки: ");
            String value = scanner.next();

            for (int i = 0; i < xy; i++) {
                for (int j = 0; j < xy; j++) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }
    }
}