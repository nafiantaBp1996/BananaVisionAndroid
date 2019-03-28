package com.example.nfnt.bananavision.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("filename")
    @Expose
    private String filename;
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
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
