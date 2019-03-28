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
    @SerializedName("R")
    @Expose
    private Double r;
    @SerializedName("G")
    @Expose
    private Double g;
    @SerializedName("B")
    @Expose
    private Double b;
    @SerializedName("pixel")
    @Expose
    private Integer pixel;

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

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getG() {
        return g;
    }

    public void setG(Double g) {
        this.g = g;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Integer getPixel() {
        return pixel;
    }

    public void setPixel(Integer pixel) {
        this.pixel = pixel;
    }

}


