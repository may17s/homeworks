import java.text.DecimalFormat;
import java.util.Objects;

public class Product {
    private String name;
    private double cost;
    private boolean isAvailableForChildren;

    public Product(String name, double cost, boolean isAvailableForChildren) {
        this.setName(name);
        this.setCost(cost);
        this.setAvailableForChildren(isAvailableForChildren);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public boolean isAvailableForChildren() {
        return isAvailableForChildren;
    }

    public void setName(String name) {
        // валидация
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Недопустимое имя продукта! Не может быть пустым!");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Недопустимое имя продукта! Должно быть больше 3 символов!");
        }
        if (name.matches("\\d+")) {
            throw new IllegalArgumentException("Недопустимое имя продукта! Не может содержать только цифры!");
        }
        this.name = name;
    }

    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта!");
        }
        this.cost = cost;
    }

    public void setAvailableForChildren(boolean availableForChildren) {
        this.isAvailableForChildren = availableForChildren;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.##");
        return name + " = " + df.format(cost) + (isAvailableForChildren ? " (для детей)" : " (не для детей)");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Double.compare(product.cost, cost) == 0
                && isAvailableForChildren == product.isAvailableForChildren
                && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, isAvailableForChildren);
    }
}