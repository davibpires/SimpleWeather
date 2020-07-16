package org.eldorado.simpleweather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.eldorado.simpleweather.R;
import org.eldorado.simpleweather.activity.MainActivity;
import org.eldorado.simpleweather.adapter.CitiesAdapter;
import org.eldorado.simpleweather.model.Weather;
import org.eldorado.simpleweather.model.WeatherGroup;
import org.eldorado.simpleweather.remote.OpenWeatherService;
import org.eldorado.simpleweather.remote.RemoteModule;
import org.eldorado.simpleweather.remote.RemoteWeatherDataSource;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ListCitiesFragment extends Fragment {

    private Context mContext;

    private RemoteWeatherDataSource weatherDataSource;
    private CompositeDisposable disposables = new CompositeDisposable();

    private CitiesAdapter adapter;

    private List<String> citiesIds;
    private List<Weather> citiesWeathers;

    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);
        ButterKnife.bind(this, view);

        citiesWeathers = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        adapter = new CitiesAdapter(mContext, citiesWeathers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        adapter.setOnItemClickListener((int position) -> {
            Weather weather = citiesWeathers.get(position);
            Timber.d("Click on %s - %s", weather.getName(), weather.toString());

            if (getActivity() != null && getFragmentManager() != null && getActivity() instanceof MainActivity) {
                Bundle bundle = new Bundle();
                bundle.putString(ViewCityFragment.BUNDLE_CITY_NAME, weather.getName());
                bundle.putString(ViewCityFragment.BUNDLE_CITY_TEMPERATURE, String.valueOf(weather.getMain().getTemp()));
                bundle.putInt(ViewCityFragment.BUNDLE_CITY_HUMIDITY, weather.getMain().getHumidity());
                bundle.putString(ViewCityFragment.BUNDLE_CITY_WIND_SPEED, String.valueOf(weather.getWind().getSpeed()));

                Fragment fragment = new ViewCityFragment();
                fragment.setArguments(bundle);

                ((MainActivity) getActivity()).addFragment(getFragmentManager(), fragment, "viewCity");
            }
            else
                Snackbar.make(coordinatorLayout, R.string.error, Snackbar.LENGTH_SHORT).show();
        });

        OpenWeatherService openWeatherService = new RemoteModule().provideOpenWeatherService();
        weatherDataSource = new RemoteWeatherDataSource(openWeatherService);

        citiesIds = new ArrayList<>();

        try {
            JSONArray cities = new JSONArray(loadCitiesJSONFromAsset());

            for (int i = 0; i < cities.length(); i++)
                citiesIds.add(cities.getJSONObject(i).getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    public String loadCitiesJSONFromAsset() {
        String json = null;

        try {
            InputStream is = mContext.getAssets().open("cities.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    public void getCitiesWeather(String citiesIds) {
        Observable<WeatherGroup> observableWeatherGroup = weatherDataSource.getCitiesWeather(citiesIds);

        disposables.add(observableWeatherGroup
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherGroup -> {
                            citiesWeathers.addAll(weatherGroup.getList());
                            Collections.sort(citiesWeathers, (Weather weather1, Weather weather2) -> weather1.getName().compareTo(weather2.getName()));
                            adapter.updateData(citiesWeathers);
                        },
                        throwable -> {
                            Snackbar.make(
                                    coordinatorLayout,
                                    String.format("Failed to get weather information: %s", throwable.getMessage()),
                                    Snackbar.LENGTH_INDEFINITE
                            ).show();
                            disposables.clear();
                        }
                ));
    }

    @OnClick(R.id.fabRefresh)
    public void refreshCitiesWeather() {
        Snackbar.make(coordinatorLayout, R.string.updating, Snackbar.LENGTH_SHORT).show();
        citiesWeathers = new ArrayList<>();

        int total = citiesIds.size();
        int batchSize = 15;
        int i = 0;
        int nextBatch;

        boolean moreLines = true;
        while (moreLines) {
            nextBatch = i + batchSize;

            if (nextBatch > total) {
                moreLines = false;
                nextBatch = total;
            }

            List<String> batch = citiesIds.subList(i, nextBatch);
            getCitiesWeather(TextUtils.join(",", batch));

            i += batchSize;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshCitiesWeather();
    }

    @Override
    public void onDestroyView() {
        disposables.clear();
        super.onDestroyView();
    }

}
