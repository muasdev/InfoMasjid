package com.example.windows.infomuslim.ui;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.infomuslim.Adapter.DaftarPenceramahAdapter;
import com.example.windows.infomuslim.Model.ResponseDataPenceramah;
import com.example.windows.infomuslim.R;
import com.example.windows.infomuslim.api.ApiClient;
import com.example.windows.infomuslim.api.ApiInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarPenceramahActivity extends AppCompatActivity {

    @BindView(R.id.rv_daftar_penceramah)
    RecyclerView rvDaftarPenceramah;

    DaftarPenceramahAdapter daftarPenceramahAdapter;
    private ArrayList<ResponseDataPenceramah> dataPenceramahArrayList;

    private SearchView searchView;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_penceramah);
        ButterKnife.bind(this);

        getData();
        dataPenceramahArrayList = new ArrayList<>();

        rvDaftarPenceramah = (RecyclerView) findViewById(R.id.rv_daftar_penceramah);

        rvDaftarPenceramah.setHasFixedSize(true);
        rvDaftarPenceramah.setLayoutManager(new GridLayoutManager(this, 2));
//        rvDaftarPenceramah.setLayoutManager(new LinearLayoutManager(this));
        daftarPenceramahAdapter = new DaftarPenceramahAdapter();
        daftarPenceramahAdapter.setPenceramahList(getApplicationContext(),dataPenceramahArrayList);
        rvDaftarPenceramah.setAdapter(daftarPenceramahAdapter);
    }

    void getData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<ResponseDataPenceramah>> call = apiService.getPenceramah();

        loading = ProgressDialog.show(this, null, "Please wait...", true, false);
        call.enqueue(new Callback<ArrayList<ResponseDataPenceramah>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseDataPenceramah>> call, Response<ArrayList<ResponseDataPenceramah>> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    dataPenceramahArrayList = response.body();
                    daftarPenceramahAdapter.setPenceramahList(getApplicationContext(), dataPenceramahArrayList);
                    daftarPenceramahAdapter.notifyDataSetChanged();
                } else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseDataPenceramah>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Gagal connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                daftarPenceramahAdapter.getFilter().filter(query);
                Log.d("tag", "onQueryTextSubmit: ini query" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                daftarPenceramahAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
