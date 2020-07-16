package org.eldorado.simpleweather.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import org.eldorado.simpleweather.R;
import org.eldorado.simpleweather.remote.RemoteWeatherDataSource;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class ViewCityFragment extends Fragment {

    public static final String BUNDLE_CITY_NAME = "cityName";
    public static final String BUNDLE_CITY_TEMPERATURE = "cityTemperature";
    public static final String BUNDLE_CITY_HUMIDITY = "cityHumidity";
    public static final String BUNDLE_CITY_WIND_SPEED = "cityWindSpeed";

    private Context mContext;

    private RemoteWeatherDataSource weatherDataSource;
    private CompositeDisposable disposables = new CompositeDisposable();

    @BindView(R.id.txtCityName) TextView txtCityName;
    @BindView(R.id.txtCityTemperature) TextView txtCityTemperature;
    @BindView(R.id.txtCityHumidity) TextView txtCityHumidity;
    @BindView(R.id.txtCityWindSpeed) TextView txtCityWindSpeed;

    private String name = "";
    private String temperature = "0.0";
    private int humidity = 0;
    private String windSpeed = "0.0";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            name = bundle.getString(BUNDLE_CITY_NAME, "");
            temperature = bundle.getString(BUNDLE_CITY_TEMPERATURE, "");
            humidity = bundle.getInt(BUNDLE_CITY_HUMIDITY, 0);
            windSpeed = bundle.getString(BUNDLE_CITY_WIND_SPEED, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_city, container, false);
        ButterKnife.bind(this, view);

        txtCityName.setText(name);
        txtCityTemperature.setText(getString(R.string.temperature_text, temperature));
        txtCityHumidity.setText(getString(R.string.humidity, humidity));
        txtCityWindSpeed.setText(getString(R.string.windSpeed, windSpeed));

        return view;
    }

    @Override
    public void onDestroyView() {
        disposables.clear();
        super.onDestroyView();
    }
}
