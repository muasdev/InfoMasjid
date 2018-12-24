package com.example.windows.infomuslim.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.windows.infomuslim.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMasjidActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    String iniID;
    String judulnya;
    String desknya;
    String rilisnya;
    String imgPath;
    String lat, lng;

    @BindView(R.id.tv_detailNamaMasjid)
    TextView tvDetailNamaMasjid;
    @BindView(R.id.tv_detailAlamatMasjid)
    TextView tvDetailAlamatMasjid;
    @BindView(R.id.tv_detailKecamatanMasjid)
    TextView tvDetailKecamatanMasjid;
    @BindView(R.id.tv_detailImamMasjid)
    TextView tvDetailImamMasjid;
   /* @BindView(R.id.tv_detailthnberdiriMasjid)
    TextView tvDetailthnberdiriMasjid;*/
    @BindView(R.id.iv_detailGambarMasjid)
    ImageView ivDetailGambarMasjid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_masjid);

        getSupportActionBar().setTitle(getIntent().getStringExtra("nm_masjid"));


        ivDetailGambarMasjid = (ImageView) findViewById(R.id.iv_detailGambarMasjid);
        tvDetailNamaMasjid = (TextView) findViewById(R.id.tv_detailNamaMasjid);
        tvDetailAlamatMasjid = (TextView) findViewById(R.id.tv_detailAlamatMasjid);
        tvDetailKecamatanMasjid = (TextView) findViewById(R.id.tv_detailKecamatanMasjid);
        tvDetailImamMasjid = (TextView) findViewById(R.id.tv_detailImamMasjid);
//        tvDetailthnberdiriMasjid = (TextView) findViewById(R.id.tv_detailthnberdiriMasjid);

        floatingActionButton = findViewById(R.id.fab);



        /*memanggil metode getIntentData()*/
        getIntentData();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                judulnya = tvDetailJudulFilm.getText().toString();
                intent.putExtra("nm_masjid", judulnya);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);
                startActivity(intent);*/
                Toast.makeText(DetailMasjidActivity.this, "Maaf masih pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

        ButterKnife.bind(this);

    }

    /*metode ini digunakan untuk menerima data yang dikirim dari MainActivity
     * berdasarkan RecyclerView yang diklik*/
    private void getIntentData() {
        imgPath = "http://muslim-info.xakti.tech/img/masjid/" + getIntent().getStringExtra("gambar");
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.mosque_icon_128)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(this)
                .load(imgPath)
                .apply(options)
                .into(ivDetailGambarMasjid);
        tvDetailNamaMasjid.setText(getIntent().getStringExtra("nm_masjid"));
        tvDetailAlamatMasjid.setText(getIntent().getStringExtra("alamat"));
        tvDetailKecamatanMasjid.setText(getIntent().getStringExtra("camat"));
//        tvDetailthnberdiriMasjid.setText(getIntent().getStringExtra("thn_berdiri"));
        tvDetailImamMasjid.setText(getIntent().getStringExtra("imam"));
        iniID = getIntent().getStringExtra("_ID");
        lat = getIntent().getStringExtra("lat");
        lng = getIntent().getStringExtra("lng");
    }

}
