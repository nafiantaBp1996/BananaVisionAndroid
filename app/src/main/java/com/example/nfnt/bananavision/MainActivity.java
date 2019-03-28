package com.example.nfnt.bananavision;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nfnt.bananavision.generator.ServiceGenerator;
import com.example.nfnt.bananavision.models.ApiData;
import com.example.nfnt.bananavision.models.Ekstraksi;
import com.example.nfnt.bananavision.sevices.ApiServices;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiServices apiServices;
    TextView txtHasil;
    Button btnEkstraksi;
    Bitmap imageHasil,imgPost;
    ImageView imgViewHasil;
    String encoded_string,path,image_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiServices = ServiceGenerator.createService(ApiServices.class);
        txtHasil = (TextView) findViewById(R.id.textViewHasil);
        btnEkstraksi = (Button) findViewById(R.id.btnEkstraksi);
        imgViewHasil = (ImageView) findViewById(R.id.imageViewHasil);

        Bundle b = getIntent().getExtras();
        path = b.getCharSequence("img").toString();
        image_name = b.getCharSequence("imgName").toString();
        imageHasil = BitmapFactory.decodeFile(path);
        imgViewHasil.setImageBitmap(imageHasil);

        btnEkstraksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgPost = BitmapFactory.decodeFile(path);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imgPost.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] array = stream.toByteArray();
                encoded_string = Base64.encodeToString(array, 0);
                ApiData data = new ApiData(image_name, encoded_string);
                apiServices = ServiceGenerator.createService(ApiServices.class);
                Call<ApiData> call = apiServices.postData(data);
                call.enqueue(new Callback<ApiData>() {
                    @Override
                    public void onResponse(Call<ApiData> call, Response<ApiData> response) {

                        Toast.makeText(getApplicationContext(), response.body().getFilename(), Toast.LENGTH_SHORT).show();
                        Call<Ekstraksi> ekstrax = apiServices.getEkstract(response.body().getFilename());
                        ekstrax.enqueue(new Callback<Ekstraksi>() {
                            @Override
                            public void onResponse(Call<Ekstraksi> call, Response<Ekstraksi> response) {
                                txtHasil.setText(response.body().getPixel().toString());
                            }

                            @Override
                            public void onFailure(Call<Ekstraksi> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<ApiData> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
