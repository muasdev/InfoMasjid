package com.example.windows.infomuslim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Toast;

import com.example.windows.infomuslim.Adapter.FlipperAdapter;
import com.example.windows.infomuslim.Model.HeroesItem;
import com.example.windows.infomuslim.Model.ModelSlider;
import com.example.windows.infomuslim.api.ApiInterface;
import com.example.windows.infomuslim.ui.DaftarMasjidActivity;
import com.example.windows.infomuslim.ui.DaftarPenceramahActivity;
import com.example.windows.infomuslim.ui.SaranActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cardView_masjid)
    CardView cardViewMasjid;
    @BindView(R.id.cardView_penceramah)
    CardView cardViewPenceramah;
    @BindView(R.id.cardView_jadwalPenceramah)
    CardView cardViewJadwalPenceramah;
    @BindView(R.id.cardView_kritikSaran)
    CardView cardViewKritikSaran;

    //the base url
    public static final String BASE_URL = "https://www.simplifiedcoding.net/demos/view-flipper/";
    @BindView(R.id.adapterViewFlipper)
    AdapterViewFlipper adapterViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getImageSlider();

    }

    void getImageSlider() {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        ApiInterface service = retrofit.create(ApiInterface.class);

        //creating an api call
        Call<ModelSlider> call =  service.getHeroes();

        //making the call
        call.enqueue(new Callback<ModelSlider>() {
            @Override
            public void onResponse(Call<ModelSlider> call, Response<ModelSlider> response) {

                //getting list of heroes
                ArrayList<HeroesItem> heros = response.body().getHeroes();

                //creating adapter object
                FlipperAdapter adapter = new FlipperAdapter(getApplicationContext(), heros);

                //adding it to adapterview flipper
                adapterViewFlipper.setAdapter(adapter);
                adapterViewFlipper.setFlipInterval(1000);
                adapterViewFlipper.startFlipping();
            }

            @Override
            public void onFailure(Call<ModelSlider> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick({R.id.cardView_masjid, R.id.cardView_penceramah, R.id.cardView_jadwalPenceramah, R.id.cardView_kritikSaran})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardView_masjid:
                Intent intent = new Intent(MainActivity.this, DaftarMasjidActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
            case R.id.cardView_penceramah:
                Intent intent1 = new Intent(MainActivity.this, DaftarPenceramahActivity.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
                break;
            case R.id.cardView_jadwalPenceramah:
                break;
            case R.id.cardView_kritikSaran:
                Intent intent2 = new Intent(MainActivity.this, SaranActivity.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
