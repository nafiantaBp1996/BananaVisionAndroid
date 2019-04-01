package com.example.nfnt.bananavision.models;

/**
 * Created by NFNT on 3/28/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Klasifikasi {
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("prediksi")
    @Expose
    private String prediksi;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getPrediksi() {
        return prediksi;
    }

    public void setPrediksi(String prediksi) {
        this.prediksi = prediksi;
    }

}