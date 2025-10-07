public class Pensioner extends Person {
    private static final double ADDITIONAL_DISCOUNT = 5; // 5%

    public Pensioner(String name, double money, int age) {
        super(name, money, age);
        if (age < 65) {
            throw new IllegalArgumentException("Пенсионер должен быть старше 65 лет");
        }
    }

    @Override
    public void buy(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть пустым");
        }

        if (!(product instanceof DiscountProduct)) {
            System.out.println(this.getName() + " (пенсионер) отказывается от покупки обычного товара: " + product.getName());
            return;
        }

        double finalCost = product.getCost() * (1 - ADDITIONAL_DISCOUNT / 100.0);

        if (this.getMoney() >= finalCost) {
            this.setMoney(this.getMoney() - finalCost);
            this.getBag().add(product);
            System.out.println(this.getName() + " купил(а) " + product.getName() + " со скидкой за " + finalCost);
        } else {
            System.out.println(this.getName() + " не может купить " + product.getName() + " даже со скидкой.");
        }
    }
}