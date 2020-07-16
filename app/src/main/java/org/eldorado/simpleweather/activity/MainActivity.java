package org.eldorado.simpleweather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import org.eldorado.simpleweather.R;
import org.eldorado.simpleweather.fragment.ListCitiesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.contentView) FrameLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new ListCitiesFragment())
                .commit();
    }

    public void addFragment(FragmentManager fragmentManager, Fragment fragment, String tag) {
        fragmentManager.beginTransaction()
                .replace(R.id.contentView, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

}