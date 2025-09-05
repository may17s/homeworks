import java.util.Objects;

public class TelevisionSet {

    public enum DisplayTechnology  { NONE, CRT, PLASMA, LED, OLED, QLED };

    private int screenSize; // Размер экрана (дюймы)
    private DisplayTechnology displayTechnology; // Тип матрицы
    private String brandName; // Марка
    private String modelName; // Модель
    private boolean hasSmartTV; // Имеет ли Smart TV
    private int currentChannel; // текущий канал
    private int volume; // Громкость (0..100)
    private boolean tvSwitch; // Признак включенного телевизора

    public TelevisionSet() {
        this.screenSize = 0;
        this.displayTechnology = DisplayTechnology.NONE;
        this.brandName = "";
        this.modelName = "";
        this.hasSmartTV = false;
        this.currentChannel = 1;
        this.volume = 25;
        this.tvSwitch = false;
    }

    public TelevisionSet(int screenSize, DisplayTechnology displayTechnology, String brandName, String modelName, boolean hasSmartTV) {
        this.screenSize = screenSize;
        this.displayTechnology = displayTechnology;
        this.brandName = brandName;
        this.modelName = modelName;
        this.hasSmartTV = hasSmartTV;
        this.currentChannel = 1;
        this.volume = 25;
        this.tvSwitch = false;
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

    public int getCurrentChannel() { return currentChannel; }

    public void setCurrentChannel(int currentChannel) {
        this.currentChannel = currentChannel;
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

    public void setTvSwitch() { this.tvSwitch = true; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        TelevisionSet televisionSet = (TelevisionSet) object;
        return screenSize == televisionSet.screenSize
                && hasSmartTV == televisionSet.hasSmartTV
                && currentChannel == televisionSet.currentChannel
                && volume == televisionSet.volume
                && tvSwitch == televisionSet.tvSwitch
                && Objects.equals(brandName, televisionSet.brandName)
                && Objects.equals(modelName, televisionSet.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName, modelName, screenSize, hasSmartTV, currentChannel, volume, tvSwitch);
    }

    @Override
    public String toString() {
        return String.format("Класс: Телевизор: " +
            "{Диагональ: %d дюймов, Тип матрицы: %s, Марка: %s, Модель: %s, Smart TV: %s, Текущий канал= %d, Громкость=%d, Включен=%s}",
                screenSize, displayTechnology.toString(), brandName, modelName, (hasSmartTV ? "Да" : "Нет"), currentChannel, volume, (tvSwitch ? "Да" : "Нет"));
    }
}
