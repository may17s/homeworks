import java.util.Objects;

class Channel {
    private String name;
    private int number;
    private Program prg;

    public Channel(String name, int number, Program prg) {
        this.name = name;
        this.number = number;
        this.prg = prg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Program getProgram() {
        return prg;
    }

    public void setProgram(Program program) {
        this.prg = program;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Channel channel = (Channel) object;
        return number == channel.number &&
                Objects.equals(name, channel.name) &&
                Objects.equals(prg, channel.prg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, prg);
    }

    @Override
    public String toString() {
        return String.format("Канал: Название: %s, Номер: %d, %s", name, number, prg);
    }
}
