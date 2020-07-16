package org.eldorado.simpleweather.remote;

import org.eldorado.simpleweather.model.Weather;
import org.eldorado.simpleweather.model.WeatherGroup;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET(RemoteContract.GET_WEATHER)
    Observable<Weather> getCityWeather(
            @Query(RemoteContract.CITY_KEY) String cityName,
            @Query(RemoteContract.UNIT_KEY) String units,
            @Query(RemoteContract.ACCESS_KEY) String accessKey
    );

    @GET(RemoteContract.GET_GROUP)
    Observable<WeatherGroup> getCitiesWeather(
            @Query(RemoteContract.ID_KEY) String ids,
            @Query(RemoteContract.UNIT_KEY) String units,
            @Query(RemoteContract.ACCESS_KEY) String accessKey
    );

}
