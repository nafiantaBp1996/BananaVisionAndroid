package com.example.nfnt.bananavision.sevices;

import com.example.nfnt.bananavision.models.ApiData;
import com.example.nfnt.bananavision.models.Klasifikasi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by NFNT on 3/7/2019.
 */

public interface ApiServices {
    @GET("dataset")
    Call<ApiData> getData();

    @POST("datauji")
    Call<ApiData> postData(@Body ApiData apiData);

    @GET("ekstraksi")
    Call<Klasifikasi> getKlasifikasi(@Query("image") String path, @Query("id") String id);

}
