package com.example.nfnt.bananavision.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiData {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("string_encoded")
    @Expose
    private String string_encoded;

    public ApiData(String name, String string_encoded)
    {
        this.name = name;
        this.string_encoded = string_encoded;
    }

    public String getStatus() {
        return image;
    }

    public void setStatus(String status) {
        this.image = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getString_encoded() {
        return string_encoded;
    }

    public void setString_encoded(String string_encoded) {
        this.string_encoded = string_encoded;
    }
}