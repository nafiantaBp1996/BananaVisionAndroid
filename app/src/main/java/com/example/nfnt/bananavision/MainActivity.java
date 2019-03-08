package com.example.nfnt.bananavision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nfnt.bananavision.generator.ServiceGenerator;
import com.example.nfnt.bananavision.models.ApiData;
import com.example.nfnt.bananavision.sevices.ApiServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiServices apiServices;
    TextView statusTxt;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiServices = ServiceGenerator.createService(ApiServices.class);
        statusTxt = (TextView) findViewById(R.id.textView_status);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
            }
        });

        Call<ApiData> call = apiServices.getData();
        call.enqueue(new Callback<ApiData>() {
            @Override
            public void onResponse(Call<ApiData> call, Response<ApiData> response) {
                statusTxt.setText(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<ApiData> call, Throwable t) {
                statusTxt.setText(t.getMessage());
            }
        });
    }
}
