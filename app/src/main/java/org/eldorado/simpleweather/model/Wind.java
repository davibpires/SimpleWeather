package org.eldorado.simpleweather.model;

public class Wind {

    private int deg;
    private double speed;


    public Wind() {
        this.deg = 0;
        this.speed = 0.0;
    }

    public Wind(int deg, double speed) {
        this.deg = deg;
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "deg=" + deg +
                ", speed=" + speed +
                '}';
    }

}
