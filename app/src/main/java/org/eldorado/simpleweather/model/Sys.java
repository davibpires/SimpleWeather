package org.eldorado.simpleweather.model;

public class Sys {

    private String country;
    private int sunrise;
    private int sunset;
    private int id;
    private int type;
    private double message;

    public Sys() {
        this.country = "";
        this.sunrise = 0;
        this.sunset = 0;
        this.id = 0;
        this.type = 0;
        this.message = 0.0;
    }

    public Sys(String country, int sunrise, int sunset, int id, int type, double message) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.id = id;
        this.type = type;
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", id=" + id +
                ", type=" + type +
                ", message=" + message +
                '}';
    }

}
