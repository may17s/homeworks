import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String inputArrow = scanner.nextLine();

            if (inputArrow.length() >= 106 || inputArrow.contains(" ")) {
                System.out.println("Ошибка: строка должна содержать до 106 символов и не иметь пробелов.");
                return;
            }

            int arrowCount = 0;
            int n = inputArrow.length();

            for (int i = 0; i <= n - 5; i++) {
                if (inputArrow.startsWith(">>-->", i) || inputArrow.startsWith("<--<<", i)) {
                    arrowCount++;
                }
            }

            System.out.println(arrowCount);
        }
    }
}