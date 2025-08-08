import java.util.Random;

public class App {
    public static void main(String[] args) {

        TelevisionSet tvSet1 = new TelevisionSet();
        System.out.println(tvSet1);

        TelevisionSet tvSet2 = new TelevisionSet(27, TelevisionSet.DisplayTechnology.LED, "Philips", "273s", false);
        System.out.println(tvSet2);

        Random random = new Random();
        int[] screenSizes = {24, 25, 27, 32};
        String[] brands = {"Samsung", "LG", "Sony", "Philips", "Panasonic"};
        String[] models = {"X1000", "UltraHD", "OLED55", "QLED4K", "SmartView"};

        TelevisionSet tvSet3 = new TelevisionSet(
                screenSizes[random.nextInt(screenSizes.length)],
                TelevisionSet.DisplayTechnology.values()[random.nextInt(TelevisionSet.DisplayTechnology.values().length)],
                brands[random.nextInt(brands.length)], models[random.nextInt(models.length)], random.nextBoolean());
        System.out.println(tvSet3);
    }
}