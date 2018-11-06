package com.example.windows.infomuslim.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.windows.infomuslim.R;
import com.example.windows.infomuslim.map.MapsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMasjidActivity extends AppCompatActivity {

    @BindView(R.id.iv_detailGambarFilm)
    ImageView ivDetailGambarFilm;
    @BindView(R.id.tv_detailJudulFilm)
    TextView tvDetailJudulFilm;
    @BindView(R.id.tv_detailDeskFilm)
    TextView tvDetailDeskFilm;
    @BindView(R.id.tv_detailRilisFilm)
    TextView tvDetailRilisFilm;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    String iniID;
    String judulnya;
    String desknya;
    String rilisnya;
    String imgPath;
    String lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masjid);


        ivDetailGambarFilm = (ImageView) findViewById(R.id.iv_detailGambarFilm);
        tvDetailJudulFilm = (TextView) findViewById(R.id.tv_detailJudulFilm);
        tvDetailDeskFilm = (TextView) findViewById(R.id.tv_detailDeskFilm);
        tvDetailRilisFilm = (TextView) findViewById(R.id.tv_detailRilisFilm);

        floatingActionButton = findViewById(R.id.fab);



        /*memanggil metode getIntentData()*/
        getIntentData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                judulnya = tvDetailJudulFilm.getText().toString();
                intent.putExtra("nm_masjid", judulnya);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);
                startActivity(intent);
            }
        });

        ButterKnife.bind(this);

    }

    /*metode ini digunakan untuk menerima data yang dikirim dari MainActivity
    * berdasarkan RecyclerView yang diklik*/
    private void getIntentData(){
        imgPath = "http://prova.xakti.tech/masjid/img/masjid/"+getIntent().getStringExtra("gambar");
        Glide.with(this)
                .load(imgPath)
                .into(ivDetailGambarFilm);
        tvDetailJudulFilm.setText(getIntent().getStringExtra("nm_masjid"));
        tvDetailDeskFilm.setText(getIntent().getStringExtra("alamat"));
        iniID = getIntent().getStringExtra("_ID");
        lat = getIntent().getStringExtra("lat");
        lng = getIntent().getStringExtra("lng");
    }

}
