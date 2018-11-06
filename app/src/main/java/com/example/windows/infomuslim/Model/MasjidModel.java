package com.example.windows.infomuslim.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MasjidModel implements Parcelable {

    private int id;
    private String nm_masjid;
    private String gambar;
    private String alamat;
    private String lat;
    private String lng;

    /*public MasjidModel(JSONObject object) {

        try {

            int id = object.getInt("id");
            String gambar = object.getString("gambar");
            String nm_masjid = object.getString("nm_masjid");
            String alamat = object.getString("alamat");
            String lat = object.getString("lat");
            String lng = object.getString("lng");

            this.id = id;
            this.nm_masjid = nm_masjid;
            this.gambar = gambar;
            this.alamat = alamat;
            this.lat = lat;
            this.lng = lng;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    protected MasjidModel(Parcel in) {
        id = in.readInt();
        nm_masjid = in.readString();
        gambar = in.readString();
        alamat = in.readString();
        lat = in.readString();
        lng = in.readString();
    }

    public MasjidModel(int id, String nm_masjid, String gambar, String alamat, String lat, String lng) {
        this.id = id;
        this.nm_masjid = nm_masjid;
        this.gambar = gambar;
        this.alamat = alamat;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nm_masjid);
        dest.writeString(gambar);
        dest.writeString(alamat);
        dest.writeString(lat);
        dest.writeString(lng);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MasjidModel> CREATOR = new Creator<MasjidModel>() {
        @Override
        public MasjidModel createFromParcel(Parcel in) {
            return new MasjidModel(in);
        }

        @Override
        public MasjidModel[] newArray(int size) {
            return new MasjidModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNm_masjid() {
        return nm_masjid;
    }

    public void setNm_masjid(String nm_masjid) {
        this.nm_masjid = nm_masjid;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
