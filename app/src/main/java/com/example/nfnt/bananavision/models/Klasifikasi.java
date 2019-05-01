package com.example.nfnt.bananavision.models;

/**
 * Created by NFNT on 3/28/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Klasifikasi {
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("prediksi")
    @Expose
    private String prediksi;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("hue")
    @Expose
    private String hue;
    @SerializedName("saturation")
    @Expose
    private String saturation;
    @SerializedName("intensity")
    @Expose
    private String intensity;
    @SerializedName("contras")
    @Expose
    private String contras;
    @SerializedName("energy")
    @Expose
    private String energy;
    @SerializedName("homogenity")
    @Expose
    private String homogenity;
    @SerializedName("entropy")
    @Expose
    private String entropy;

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getPrediksi() {
        return prediksi;
    }

    public void setPrediksi(String prediksi) {
        this.prediksi = prediksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHue() {
        return hue;
    }

    public void setHue(String hue) {
        this.hue = hue;
    }

    public String getSaturation() {
        return saturation;
    }

    public void setSaturation(String saturation) {
        this.saturation = saturation;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getContras() {
        return contras;
    }

    public void setContras(String contras) {
        this.contras = contras;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getHomogenity() {
        return homogenity;
    }

    public void setHomogenity(String homogenity) {
        this.homogenity = homogenity;
    }

    public String getEntropy() {
        return entropy;
    }

    public void setEntropy(String entropy) {
        this.entropy = entropy;
    }

}