public class TelevisionSet {

    public enum DisplayTechnology  { NONE, CRT, PLASMA, LED, OLED, QLED };

    private int screenSize; // Размер экрана (дюймы)
    private DisplayTechnology displayTechnology; // Тип матрицы
    private String brandName; // Марка
    private String modelName; // Модель
    private boolean hasSmartTV; // Имеет ли Smart TV

    public TelevisionSet() {
        this.screenSize = 0;
        this.displayTechnology = DisplayTechnology.NONE;
        this.brandName = "";
        this.modelName = "";
        this.hasSmartTV = false;
    }

    public TelevisionSet(int screenSize, DisplayTechnology displayTechnology, String brandName, String modelName, boolean hasSmartTV) {
        this.screenSize = screenSize;
        this.displayTechnology = displayTechnology;
        this.brandName = brandName;
        this.modelName = modelName;
        this.hasSmartTV = hasSmartTV;
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

    public void setDisplayTechnology(DisplayTechnology displayTechnology) {
        this.displayTechnology = displayTechnology;
    }

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

    @Override
    public String toString() {
        return String.format("Характеристики телевизора следующие: " +
            "{ Диагональ: %d дюймов, Тип матрицы: %s, Марка: %s, Модель: %s, Smart TV: %s }",
                screenSize, displayTechnology.toString(), brandName, modelName, (hasSmartTV ? "Да" : "Нет"));
    }
}
