import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите степень в градусах Фаренгейта: ");
            double fahrenheit = scanner.nextDouble();
            double celsius = (fahrenheit - 32) * 5 / 9;
            System.out.println(fahrenheit + " градусов по Фаренгейту равна " + celsius + " по Цельсию");
        }
    }
}