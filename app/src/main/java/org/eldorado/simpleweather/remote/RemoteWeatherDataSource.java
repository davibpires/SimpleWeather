package org.eldorado.simpleweather.remote;

import org.eldorado.simpleweather.model.Weather;
import org.eldorado.simpleweather.model.WeatherGroup;

import java.util.List;

import io.reactivex.Observable;

public class RemoteWeatherDataSource {

    private OpenWeatherService openWeatherService;

    public RemoteWeatherDataSource(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    public Observable<Weather> getCityWeather(String cityName) {
        return openWeatherService.getCityWeather(
                cityName,
                RemoteContract.UNIT_METRIC,
                RemoteContract.ACCESS_KEY_API_LAYER
        );
    }

    public Observable<WeatherGroup> getCitiesWeather(String ids) {
        return openWeatherService.getCitiesWeather(
                ids,
                RemoteContract.UNIT_METRIC,
                RemoteContract.ACCESS_KEY_API_LAYER
        );
    }

}
