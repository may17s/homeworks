import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            TelevisionSet[] tvsetArray = new TelevisionSet[10];
            Random random = new Random();
            int[] screenSizes = {24, 25, 27, 32};
            String[] models = {"X1000", "UltraHD", "OLED55", "QLED4K", "SmartView"};

            for (int i = 0; i < tvsetArray.length; i++) {
                System.out.println("\n");
                System.out.println("Введите данные для телевизора " + (i + 1) + ":");
                System.out.print("ТВ№" + (i + 1) + ", Марка: ");
                String brandName = scanner.nextLine();
                System.out.print("ТВ№" + (i + 1) + ", Поддерживает Smart TV? (true/false): ");
                boolean hasSmartTV = scanner.nextBoolean();
                System.out.print("ТВ№" + (i + 1) + ", Номер текущего канала: ");
                int currentChannel = scanner.nextInt();
                System.out.print("ТВ№" + (i + 1) + ", Громкость звука (от 0 до 100): ");
                int volume = scanner.nextInt();
                System.out.print("ТВ№" + (i + 1) + ", Телевизор включен? (true/false): ");
                boolean tvSwitch = scanner.nextBoolean();

                TelevisionSet tvSet = new TelevisionSet(
                        screenSizes[random.nextInt(screenSizes.length)],
                        TelevisionSet.DisplayTechnology.values()[random.nextInt(TelevisionSet.DisplayTechnology.values().length)],
                        brandName, models[random.nextInt(models.length)], random.nextBoolean());
                tvSet.setCurrentChannel(currentChannel);
                tvSet.setVolume(volume);
                if (tvSwitch) tvSet.setTvSwitch();
                tvsetArray[i] = tvSet;
                System.out.println(tvSet);
            }
        }
    }
}