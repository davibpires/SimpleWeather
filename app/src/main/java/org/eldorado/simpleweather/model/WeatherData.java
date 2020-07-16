package org.eldorado.simpleweather.model;

public class WeatherData {

    private double temp;
    private double tempMin;
    private int humidity;
    private int pressure;
    private double tempMax;

    public WeatherData() {
        this.temp = 0.0;
        this.tempMin = 0.0;
        this.humidity = 0;
        this.pressure = 0;
        this.tempMax = 0.0;
    }

    public WeatherData(double temp, double tempMin, int humidity, int pressure, double tempMax) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.pressure = pressure;
        this.tempMax = tempMax;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", tempMin=" + tempMin +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", tempMax=" + tempMax +
                '}';
    }

}
