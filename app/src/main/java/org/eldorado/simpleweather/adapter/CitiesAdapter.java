package org.eldorado.simpleweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.eldorado.simpleweather.R;
import org.eldorado.simpleweather.model.Weather;
import org.eldorado.simpleweather.remote.RemoteContract;
import org.eldorado.simpleweather.remote.RemoteModule;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Weather> mCitiesWeather;

    public CitiesAdapter(Context context, List<Weather> citiesWeather) {
        mContext = context;
        mCitiesWeather = citiesWeather;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void updateData(List<Weather> citiesWeather) {
        mCitiesWeather = citiesWeather;
        this.notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCity) TextView txtCity;
        @BindView(R.id.txtTemperature) TextView txtTemperature;

        public MyViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener((View v) -> {
                if (listener != null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION)
                        listener.onItemClick(position);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);

        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Weather weather = mCitiesWeather.get(position);

        holder.txtCity.setText(weather.getName());
        holder.txtTemperature.setText(mContext.getString(R.string.temperature, String.valueOf(weather.getMain().getTemp())));
    }

    @Override
    public int getItemCount() {
        return mCitiesWeather.size();
    }

}
