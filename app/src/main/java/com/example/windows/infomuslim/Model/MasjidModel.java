package com.example.windows.infomuslim.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MasjidModel implements Parcelable {

    private int id;
    private String nm_masjid;
    private String kecamatan;
    private String alamat;
    private String thn_berdiri;
    private String imam;
    private String lat;
    private String lng;
    private String gambar;

    public MasjidModel(int id, String nm_masjid, String kecamatan, String alamat, String thn_berdiri, String imam, String lat, String lng, String gambar) {
        this.id = id;
        this.nm_masjid = nm_masjid;
        this.kecamatan = kecamatan;
        this.alamat = alamat;
        this.thn_berdiri = thn_berdiri;
        this.imam = imam;
        this.lat = lat;
        this.lng = lng;
        this.gambar = gambar;
    }

    protected MasjidModel(Parcel in) {
        id = in.readInt();
        nm_masjid = in.readString();
        kecamatan = in.readString();
        alamat = in.readString();
        thn_berdiri = in.readString();
        imam = in.readString();
        lat = in.readString();
        lng = in.readString();
        gambar = in.readString();
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

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getThn_berdiri() {
        return thn_berdiri;
    }

    public void setThn_berdiri(String thn_berdiri) {
        this.thn_berdiri = thn_berdiri;
    }

    public String getImam() {
        return imam;
    }

    public void setImam(String imam) {
        this.imam = imam;
    }



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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nm_masjid);
        parcel.writeString(kecamatan);
        parcel.writeString(alamat);
        parcel.writeString(thn_berdiri);
        parcel.writeString(imam);
        parcel.writeString(lat);
        parcel.writeString(lng);
        parcel.writeString(gambar);
    }
}
