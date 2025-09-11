public class Child extends Person {
    public Child(String name, double money, int age) {
        super(name, money, age);
        if (age < 0 || age > 17) {
            throw new IllegalArgumentException("Возраст ребенка должен быть от 0 до 17 лет");
        }
    }

    @Override
    public void buy(Product product) {
        if (this.getAge() < 6) {
            System.out.println(this.getName() + " (ребенок младше 6 лет) не может покупать товары.");
            return;
        }
        super.buy(product);
    }
}