import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random ran = new Random();

        for (int i = 1; i < 5; i++) {
            int x = ran.nextInt(10) + 5;
            System.out.print(i == 1 ? x : "   " + x);
        }
    }
}