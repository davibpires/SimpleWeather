package org.eldorado.simpleweather.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherGroup {

    private int cnt;
    private List<Weather> list;

    public WeatherGroup() {
        this.cnt = 0;
        this.list = new ArrayList<>();
    }

    public WeatherGroup(int cnt, List<Weather> list) {
        this.cnt = cnt;
        this.list = list;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "WeatherGroup{" +
                "cnt=" + cnt +
                ", list=" + list +
                '}';
    }

}
