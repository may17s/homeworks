package model;

import java.util.Objects;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace() { super(); }
    public CircuitRace(int length, String route, int prize) {
        super(length, route, prize);
        this.setLaps(laps);
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @Override
    public String toString() {
        return String.format("CircuitRace {%s, laps=%d}", super.toString(), laps);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CircuitRace that)) return false;
        if (!super.equals(object)) return false;
        return super.equals(object) &&
                laps == that.laps;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), laps);
    }
}
