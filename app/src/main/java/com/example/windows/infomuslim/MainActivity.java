package com.example.windows.infomuslim;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.infomuslim.Model.ModelDataHeader;
import com.example.windows.infomuslim.api.ApiClient;
import com.example.windows.infomuslim.api.ApiInterface;
import com.example.windows.infomuslim.ui.DaftarMasjidActivity;
import com.example.windows.infomuslim.ui.DaftarPenceramahActivity;
import com.example.windows.infomuslim.ui.daftarActivity.DaftarPantiActivity;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cardView_masjid)
    CardView cardViewMasjid;
    @BindView(R.id.cardView_penceramah)
    CardView cardViewPenceramah;
    @BindView(R.id.cardView_kritikSaran)
    CardView cardViewKritikSaran;

    FlipperLayout flipperLayout;
    ProgressDialog loading;
    private ArrayList<ModelDataHeader> modelDataHeaderArrayList;

    String gambar;
    CarouselView carouselView;
    List<String> imageUrls;
    ImageListener imageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        flipperLayout = findViewById(R.id.flipper_layout);
//        imageUrls = new ArrayList<String>();
        cardViewMasjid.setBackgroundResource(R.drawable.bg_gradient);
        cardViewPenceramah.setBackgroundResource(R.drawable.bg_gradient);
        cardViewKritikSaran.setBackgroundResource(R.drawable.bg_gradient);


        getData();
//        imageUrls = Arrays.asList("http://muslim-info.xakti.tech/img/slide_header/"+ gambar.split(","));
//        carouselView = (CarouselView) findViewById(R.id.carouselView);

    }

    public int getRandomColorCode(){

        Random random = new Random();

        return Color.argb(255, random.nextInt(256), random.nextInt(256),     random.nextInt(256));

    }

    void getData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<ModelDataHeader>> call = apiService.getDataHeader();


        loading = ProgressDialog.show(this, null, "Please wait...", true, false);
        call.enqueue(new Callback<ArrayList<ModelDataHeader>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelDataHeader>> call, Response<ArrayList<ModelDataHeader>> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    modelDataHeaderArrayList = response.body();

                    for (int i = 0; i < modelDataHeaderArrayList.size(); i++) {
                        FlipperView view = new FlipperView(getBaseContext());
                        gambar = modelDataHeaderArrayList.get(i).getGambar();
//                        imageUrls = Arrays.asList("http://muslim-info.xakti.tech/img/slide_header/" + gambar.split(","));
//                        Log.d("tag", "onResponse: " + imageUrls);
                        final String caption = modelDataHeaderArrayList.get(i).getCaption();
                        view.setImageUrl("http://muslim-info.xakti.tech/img/slide_header/" + gambar)
                                .setImageScaleType(ImageView.ScaleType.FIT_XY); //You can use any ScaleType
//                                .setDescription(caption);
//                        textViewCaption.setText(caption);
//                        flipperLayout.setScrollTimeInSec(10);
                        flipperLayout.addFlipperView(view);
                        view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                            @Override
                            public void onFlipperClick(FlipperView flipperView) {
                                /*Toast.makeText(MainActivity.this
                                        , caption + (flipperLayout.getCurrentPagePosition() + 1)
                                        , Toast.LENGTH_SHORT).show();*/

                                FancyToast.makeText(MainActivity.this,caption,
                                        FancyToast.LENGTH_SHORT,FancyToast.DEFAULT, false).show();
                            }
                        });

                    }


                } else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelDataHeader>> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Gagal connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.cardView_masjid, R.id.cardView_penceramah, R.id.cardView_kritikSaran})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardView_masjid:
                Intent intent = new Intent(MainActivity.this, DaftarMasjidActivity.class);
                startActivity(intent);
//                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                break;
            case R.id.cardView_penceramah:
                Intent intent1 = new Intent(MainActivity.this, DaftarPenceramahActivity.class);
                startActivity(intent1);
//                overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
                break;
            case R.id.cardView_kritikSaran:
                Intent intent2 = new Intent(MainActivity.this, DaftarPantiActivity.class);
                startActivity(intent2);
                /*Intent intent2 = new Intent(MainActivity.this, DaftarPantiActivity.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);*/
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
