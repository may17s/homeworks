import java.text.DecimalFormat;
import java.time.LocalDate;

public class DiscountProduct extends Product {
    private double discountPercentage; // Размер (процент) скидки
    private LocalDate expirationDate;  // Срок действия скидки

    public DiscountProduct(String name, double originalCost, double discountPercentage, LocalDate expirationDate, boolean isAvailableForChildren) {
        super(name, originalCost, isAvailableForChildren);

        this.setDiscountPercentage(discountPercentage);
        this.setExpirationDate(expirationDate);
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в диапазоне от 0% до 100%");
        }
        this.discountPercentage = discountPercentage;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Дата окончания скидки не может быть пустой");
        }
        this.expirationDate = expirationDate;
    }

    @Override
    public double getCost() {
        if (LocalDate.now().isAfter(expirationDate)) {
            // Срок действия скидки истек
            return super.getCost();
        } else {
            // С учетом скидки
            return super.getCost() * (1 - discountPercentage / 100.0);
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.##");
        if (LocalDate.now().isAfter(expirationDate)) {
            return super.toString() + " (Скидка истекла)";
        } else {
            return super.getName() + " = " + df.format(this.getCost()) + ", " + df.format(discountPercentage) + "%";
        }
    }
}