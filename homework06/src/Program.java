import java.util.Objects;

class Program {
    private String name;
    private double rating;
    private int viewers;

    public Program(String name, double rating, int viewers) {
        this.name = name;
        this.rating = rating;
        this.viewers = viewers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("Рейтинг должен быть в диапазоне от 0 до 10.");
        }
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        if (viewers >= 0) {
            this.viewers = viewers;
        } else {
            System.out.println("Число зрителей не может быть отрицательным.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return Double.compare(program.rating, rating) == 0 &&
                viewers == program.viewers &&
                Objects.equals(name, program.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, viewers);
    }

    @Override
    public String toString() {
        return String.format("Программа: {Название: %s, Рейтинг: %.2f, Зрители: %d}", name, rating, viewers);
    }
}