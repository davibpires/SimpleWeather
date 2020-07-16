package org.eldorado.simpleweather.model;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private int dt;
    private Coordinates coord;
    private int visibility;
    private List<WeatherInfo> weather;
    private String name;
    private int cod;
    private WeatherData main;
    private Clouds clouds;
    private int id;
    private Sys sys;
    private String base;
    private Wind wind;

    public Weather() {
        this.dt = 0;
        this.coord = new Coordinates();
        this.visibility = 0;
        this.weather = new ArrayList<>();
        this.name = "";
        this.cod = 0;
        this.main = new WeatherData();
        this.clouds = new Clouds();
        this.id = 0;
        this.sys = new Sys();
        this.base = "";
        this.wind = new Wind();
    }

    public Weather(int dt, Coordinates coord, int visibility, List<WeatherInfo> weather, String name, int cod, WeatherData main, Clouds clouds, int id, Sys sys, String base, Wind wind) {
        this.dt = dt;
        this.coord = coord;
        this.visibility = visibility;
        this.weather = weather;
        this.name = name;
        this.cod = cod;
        this.main = main;
        this.clouds = clouds;
        this.id = id;
        this.sys = sys;
        this.base = base;
        this.wind = wind;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public WeatherData getMain() {
        return main;
    }

    public void setMain(WeatherData main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dt=" + dt +
                ", coord=" + coord +
                ", visibility=" + visibility +
                ", weather=" + weather +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", main=" + main +
                ", clouds=" + clouds +
                ", id=" + id +
                ", sys=" + sys +
                ", base='" + base + '\'' +
                ", wind=" + wind +
                '}';
    }

}
