package com.example.windows.infomuslim.Utils;


import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.windows.infomuslim.Model.MasjidModel;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LoaderDaftarMasjid extends AsyncTaskLoader<ArrayList<MasjidModel>> {

    private ArrayList<MasjidModel> mData;
    private boolean mHasResult = false;

    public LoaderDaftarMasjid(Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    public ArrayList<MasjidModel> loadInBackground() {
        SyncHttpClient syncHttpClient = new SyncHttpClient();

        final ArrayList<MasjidModel> movieItemsArrayList = new ArrayList<>();
        String url = "http://muslim-info.xakti.tech/api/masjid";
        syncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    /*String result = new String(responseBody);
                    *//*JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");*//*

                    JSONArray list = new JSONArray(result);

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        MasjidModel movieItems1 = new MasjidModel(movie);
                        movieItems1.getId();
                        movieItems1.getGambar();
                        movieItems1.getNm_masjid();
                        movieItems1.getAlamat();
                        movieItems1.getLat();
                        movieItems1.getLng();
                        movieItemsArrayList.add(movieItems1);
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemsArrayList;
    }

    @Override
    public void deliverResult(ArrayList<MasjidModel> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            /*Metode forceLoad() akan dijalankan ketika data belum tersedia*/
            forceLoad();
        else if (mHasResult)
            /*Metode deliverResult() digunakan untuk menampilkan result data yang sudah ada.
                Metode ini akan dijalankan juga ketika terjadi reset pada loader.*/
            deliverResult(mData);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    protected void onReleaseResources(ArrayList<MasjidModel> data) {
        //nothing to do.
    }
}
