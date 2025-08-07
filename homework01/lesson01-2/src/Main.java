import java.util.concurrent.ThreadLocalRandom;
public class Main {
    public static void main(String[] args) {
        int min = 0;
        int max = 0;
        for (int i = 1; i < 5; i++) {
            int a = ThreadLocalRandom.current().nextInt(min, max + 1);
            int b = ThreadLocalRandom.current().nextInt(min, max + 1);
            // https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D0%BC%D0%B5%D0%BD%D1%8C,_%D0%BD%D0%BE%D0%B6%D0%BD%D0%B8%D1%86%D1%8B,_%D0%B1%D1%83%D0%BC%D0%B0%D0%B3%D0%B0
            System.out.println(a + " " + b + " " + (a-b % 2));

            // 0 ножницы
            // 1 бумага
            // 2 камень


        }
    }
}