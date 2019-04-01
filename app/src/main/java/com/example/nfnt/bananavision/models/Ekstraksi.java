package com.example.nfnt.bananavision.models;

/**
 * Created by NFNT on 3/28/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ekstraksi {
    @SerializedName("H")
    @Expose
    private Double h;
    @SerializedName("S")
    @Expose
    private Double s;
    @SerializedName("I")
    @Expose
    private Double i;
    @SerializedName("contras")
    @Expose
    private Double contras;
    @SerializedName("energy")
    @Expose
    private Double energy;
    @SerializedName("homogenity")
    @Expose
    private Double homogenity;
    @SerializedName("entropy")
    @Expose
    private Double entropy;
    @SerializedName("corelation")
    @Expose
    private Double corelation;

    public Double getH() {
        return h;
    }

    public void setH(Double h) {
        this.h = h;
    }

    public Double getS() {
        return s;
    }

    public void setS(Double s) {
        this.s = s;
    }

    public Double getI() {
        return i;
    }

    public void setI(Double i) {
        this.i = i;
    }

    public Double getContras() {
        return contras;
    }

    public void setContras(Double contras) {
        this.contras = contras;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Double getHomogenity() {
        return homogenity;
    }

    public void setHomogenity(Double homogenity) {
        this.homogenity = homogenity;
    }

    public Double getEntropy() {
        return entropy;
    }

    public void setEntropy(Double entropy) {
        this.entropy = entropy;
    }

    public Double getCorelation() {
        return corelation;
    }

    public void setCorelation(Double corelation) {
        this.corelation = corelation;
    }

}