import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();

            String[] words = input.split(" ");

            for (int i = 0; i < words.length; i++) {
                char[] chars = words[i].toLowerCase().toCharArray();
                Arrays.sort(chars);
                words[i] = new String(chars);
            }

            System.out.println(String.join(" ", words));
        }
    }
}