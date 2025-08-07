import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Исходная строка: ");
            String input = scanner.nextLine();
            System.out.print("Сколько раз вывести строку? ");
            int count = scanner.nextInt();

            String result = repeatString(input, count);
            System.out.printf("После повторения %d раз: %s\n", count, result);
        }
    }

    public static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}