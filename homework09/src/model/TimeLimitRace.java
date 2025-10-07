package model;

import java.util.Objects;

public class TimeLimitRace extends Race {
    private int goldTime;

    public TimeLimitRace() { super(); }

    public TimeLimitRace(int length, String route, int prize, int goldTime) {
        super(length, route, prize);
        this.setGoldTime(goldTime);
    }

    public int getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    @Override
    public String toString() {
        return String.format("TimeLimitRace {%s, goldTime=%d}", super.toString(), goldTime);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TimeLimitRace that)) return false;
        if (!super.equals(object)) return false;
        return super.equals(object) &&
                goldTime == that.goldTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goldTime);
    }
}
