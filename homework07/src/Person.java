import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private List<Product> bag;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.bag = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя покупателя не может быть пустым");
        }
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getBag() {
        return new ArrayList<>(bag);
    }

    public void buy(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть пустым");
        }
        if (this.getMoney() >= product.getCost()) {
            this.setMoney(this.getMoney() - product.getCost());
            this.bag.add(product);
            System.out.println(this.name + " купил(а) " + product.getName());
        } else {
            System.out.println(this.name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public String toString() {
        if (bag.isEmpty())
            return name + " - Ничего не куплено";

        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" - ");
        for (Product product : bag) {
            sb.append(product.getName());
            sb.append(", ");
        }
        sb.setLength(sb.length() - ", ".length());
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Person person = (Person) object;
        return Double.compare(person.money, money) == 0
                && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money);
    }
}