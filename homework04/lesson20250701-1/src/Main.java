import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String keyboardLetters = "qwertyuiopasdfghjklzxcvbnm";

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите букву (одну маленькую букву английского алфавита): ");
            String inputLetter = scanner.next();

            if (inputLetter.length() != 1 || !keyboardLetters.contains(inputLetter)) {
                System.out.println("Ошибка: введите одну маленькую букву английского алфавита.");
                return;
            }

            char letter = inputLetter.charAt(0);
            int index = keyboardLetters.indexOf(letter);
            int leftIndex = (index - 1 + keyboardLetters.length()) % keyboardLetters.length();
            System.out.println("Слева стоящая буква: " + keyboardLetters.charAt(leftIndex));
        }
    }
}