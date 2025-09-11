public class Adult extends Person {
    public Adult(String name, double money, int age) {
        super(name, money, age);
        if (age > 18 && age < 65) {
            throw new IllegalArgumentException("Возраст взрослого должен быть от 18 до 65 лет");
        }
        this.setCanTakeCredit(true);
    }
}