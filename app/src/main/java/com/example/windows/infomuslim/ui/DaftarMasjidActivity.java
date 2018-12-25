package com.example.windows.infomuslim.ui;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.windows.infomuslim.Adapter.MasjidPareAdapter;
import com.example.windows.infomuslim.Model.MasjidModel;
import com.example.windows.infomuslim.R;
import com.example.windows.infomuslim.api.ApiClient;
import com.example.windows.infomuslim.api.ApiInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarMasjidActivity extends AppCompatActivity {

    @BindView(R.id.rv_daftar_masjid)
    RecyclerView rvDaftarMasjid;

    private SearchView searchView;
    ProgressDialog loading;

    private MasjidPareAdapter masjidPareAdapter;
    private ArrayList<MasjidModel> masjidModelArrayList;

    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_masjid);
        ButterKnife.bind(this);

        // Getting SwipeContainerLayout
        swipeLayout = findViewById(R.id.swipe_container);
        // Adding Listener
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        // Remember to CLEAR OUT old items before appending in the new ones
                        masjidPareAdapter.clear();
                        // ...the data has come back, add new items to your masjidPareAdapter...
                        getData();
                        // Now we call setRefreshing(false) to signal refresh has finished
                        swipeLayout.setRefreshing(false);

                    }
                }, 4000); // Delay in millis
            }
        });

        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        getData();

        masjidModelArrayList = new ArrayList<>();

        rvDaftarMasjid = (RecyclerView) findViewById(R.id.rv_daftar_masjid);

        rvDaftarMasjid.setHasFixedSize(true);
        rvDaftarMasjid.setLayoutManager(new GridLayoutManager(this, 2));
//        rvDaftarMasjid.setLayoutManager(new LinearLayoutManager(this));
        masjidPareAdapter = new MasjidPareAdapter();
//        masjidPareAdapter.setmData(masjidModelArrayList);
        masjidPareAdapter.setMasjidList(getApplicationContext(), masjidModelArrayList);
        rvDaftarMasjid.setAdapter(masjidPareAdapter);



    }

    void getData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<MasjidModel>> call = apiService.getMovies();


        loading = ProgressDialog.show(this, null, "Please wait...", true, false);
        call.enqueue(new Callback<ArrayList<MasjidModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MasjidModel>> call, Response<ArrayList<MasjidModel>> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    masjidModelArrayList = response.body();
                    masjidPareAdapter.setMasjidList(getApplicationContext(), masjidModelArrayList);
                    masjidPareAdapter.notifyDataSetChanged();


                } else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal ambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MasjidModel>> call, Throwable t) {
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
                masjidPareAdapter.getFilter().filter(query);
                Log.d("tag", "onQueryTextSubmit: ini query" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                masjidPareAdapter.getFilter().filter(query);
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
