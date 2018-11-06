package com.example.windows.infomuslim.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseDataPenceramah {

    private String kontak;

    private String nama;

    private String foto;

    private String alamat;

    public ResponseDataPenceramah(String kontak, String nama, String foto, String alamat) {
        this.kontak = kontak;
        this.nama = nama;
        this.foto = foto;
        this.alamat = alamat;
    }

   /* public ResponseDataPenceramah(JSONObject object) {

        try {

            String foto = object.getString("foto");
            String nama = object.getString("nama");
            String alamat = object.getString("alamat");
            String kontak = object.getString("kontak");

            this.nama = nama;
            this.foto = foto;
            this.alamat = alamat;
            this.kontak = kontak;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getKontak() {
        return kontak;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }
}