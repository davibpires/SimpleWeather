package org.eldorado.simpleweather.model;

public class WeatherInfo {

    private String icon;
    private String description;
    private String main;
    private int id;

    public WeatherInfo() {
        this.icon = "";
        this.description = "";
        this.main = "";
        this.id = 0;
    }

    public WeatherInfo(String icon, String description, String main, int id) {
        this.icon = icon;
        this.description = description;
        this.main = main;
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", main='" + main + '\'' +
                ", id=" + id +
                '}';
    }

}
