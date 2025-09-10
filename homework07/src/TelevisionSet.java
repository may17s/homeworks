import java.util.Arrays;
import java.util.Objects;

public class TelevisionSet {

    public enum DisplayTechnology  { NONE, CRT, PLASMA, LED, OLED, QLED };

    private int screenSize; // Размер экрана (дюймы)
    private DisplayTechnology displayTechnology; // Тип матрицы
    private String brandName; // Марка
    private String modelName; // Модель
    private boolean hasSmartTV; // Имеет ли Smart TV
    private int currentChannelIndex; // текущий канал
    private Channel[] channels; // список каналов
    private int volume; // Громкость (0..100)
    private boolean tvSwitch; // Признак включенного телевизора

    public TelevisionSet() {
        this.screenSize = 0;
        this.displayTechnology = DisplayTechnology.NONE;
        this.brandName = "";
        this.modelName = "";
        this.hasSmartTV = false;
        this.currentChannelIndex = 0;
        this.volume = 25;
        this.tvSwitch = false;
    }

    public TelevisionSet(int screenSize, DisplayTechnology displayTechnology, String brandName, String modelName, boolean hasSmartTV, Channel[] channels) {
        this.screenSize = screenSize;
        this.displayTechnology = displayTechnology;
        this.brandName = brandName;
        this.modelName = modelName;
        this.hasSmartTV = hasSmartTV;
        this.currentChannelIndex = 0;
        this.volume = 25;
        this.tvSwitch = false;
        this.channels = channels;
    }

    public int getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(int screenSize) {
        if (screenSize > 0) {
            this.screenSize = screenSize;
        } else {
            System.out.println("Размер экрана указан не верно! Должен быть больше нуля.");
        }
    }

    public DisplayTechnology getDisplayTechnology() {
        return displayTechnology;
    }

    public void setDisplayTechnology(DisplayTechnology displayTechnology) { this.displayTechnology = displayTechnology; }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setHasSmartTV(boolean hasSmartTV) {
        this.hasSmartTV = hasSmartTV;
    }

    public boolean isHasSmartTV() {
        return hasSmartTV;
    }


    public int getVolume() { return volume; }

    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
        } else {
            System.out.println("Громкость должна быть в диапазоне от 0 до 100 (по умолчанию 25)");
        }
    }

    public boolean getTvSwitch() { return tvSwitch; }

    public void setTvSwitch() { this.tvSwitch = !this.tvSwitch; }

    public void changeChannel(int channelNumber) {
        if (!tvSwitch) {
            System.out.println("Телевизор выключен. Включение...");
            setTvSwitch();
        }
        for (int i = 0; i < channels.length; i++) {
            if (channels[i].getNumber() == channelNumber) {
                currentChannelIndex = i;
                System.out.printf("Переключено на канал: %s%n", channels[i].getName());
                return;
            }
        }
        System.out.println("Канал не найден.");
    }

    public void setChannels(Channel[] channels) {
        this.channels = channels;
    }

    public Channel getCurrentChannel() {
        if (channels.length > 0 && currentChannelIndex < channels.length) {
            return channels[currentChannelIndex];
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        TelevisionSet televisionSet = (TelevisionSet) object;
        return screenSize == televisionSet.screenSize
                && hasSmartTV == televisionSet.hasSmartTV
                && currentChannelIndex == televisionSet.currentChannelIndex
                && volume == televisionSet.volume
                && tvSwitch == televisionSet.tvSwitch
                && Objects.equals(brandName, televisionSet.brandName)
                && Objects.equals(modelName, televisionSet.modelName)
                && Objects.deepEquals(channels, televisionSet.channels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, modelName, screenSize, hasSmartTV, currentChannelIndex, volume, tvSwitch, Arrays.deepHashCode(channels));
    }

    @Override
    public String toString() {
        return String.format("Класс: Телевизор: " +
            "{Диагональ: %d дюймов, Тип матрицы: %s, Марка: %s, Модель: %s, Smart TV: %s, Текущий канал= %s, Громкость=%d, Включен=%s}",
                screenSize, displayTechnology.toString(), brandName, modelName, (hasSmartTV ? "Да" : "Нет"), getCurrentChannel(), volume, (tvSwitch ? "Да" : "Нет"));
    }
}
