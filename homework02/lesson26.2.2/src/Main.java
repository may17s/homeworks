import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите 1-е целое число: ");
            int num1 = scanner.nextInt();
            System.out.print("Введите второе целое число: ");
            int num2 = scanner.nextInt();

            int sum = num1 + num2;
            int difference = num1 - num2;
            int product = num1 * num2;
            double average = (double) (num1 + num2) / 2;
            int distance = Math.abs(num1 - num2);
            int max = Math.max(num1, num2);
            int min = Math.min(num1, num2);

            System.out.printf("Сумма двух целых чисел: %d\n", sum);
            System.out.printf("Разница двух целых чисел: %d\n", difference);
            System.out.printf("Произведение из двух целых чисел: %d\n", product);
            System.out.printf("Среднее из двух целых чисел: %.2f\n", average);
            System.out.printf("Расстояние двух целых чисел: %d\n", distance);
            System.out.printf("Максимальное целое число: %d\n", max);
            System.out.printf("Минимальное целое число: %d\n", min);
        }
    }
}