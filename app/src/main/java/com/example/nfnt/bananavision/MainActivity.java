package com.example.nfnt.bananavision;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nfnt.bananavision.generator.ServiceGenerator;
import com.example.nfnt.bananavision.models.ApiData;
import com.example.nfnt.bananavision.models.Klasifikasi;
import com.example.nfnt.bananavision.sevices.ApiServices;

import java.io.ByteArrayOutputStream;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiServices apiServices;
    TextView txtHasil,txtDetail;
    CircularProgressButton circular;
    Bitmap imageHasil,imgPost;
    ImageView imgViewHasil;
    String encoded_string,path,image_name;
    Button detailButon;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiServices = ServiceGenerator.createService(ApiServices.class);
        txtHasil = (TextView) findViewById(R.id.textViewHasil);
        txtDetail = (TextView) findViewById(R.id.textViewDetail);
        imgViewHasil = (ImageView) findViewById(R.id.imageViewHasil);
        /////////////////
        Bundle b = getIntent().getExtras();
        path = b.getCharSequence("img").toString();
        image_name = b.getCharSequence("imgName").toString();
        imageHasil = BitmapFactory.decodeFile(path);
        imgViewHasil.setImageBitmap(imageHasil);
        circular = (CircularProgressButton) findViewById(R.id.circularBtn);
        circular.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.custom_button));
        ///////////////////////dialogdetail
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_layout);
        //dialog.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
        /**
         * Mengeset komponen dari custom dialog
         */
        final TextView txtDialog = (TextView) dialog.findViewById(R.id.txtDetailDialog);
        ImageView imageDialog = (ImageView) dialog.findViewById(R.id.imageViewDialog);
        imageDialog.setImageBitmap(imageHasil);
        detailButon = (Button) findViewById(R.id.detailButon);
        detailButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        ////////////////endofdialog
        if (b.getCharSequence("id")=="folder")
        {
            imgViewHasil.setRotation(90);
        }

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////encode
                imgPost = BitmapFactory.decodeFile(path);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imgPost.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] array = stream.toByteArray();
                encoded_string = Base64.encodeToString(array, 0);
                ////////////////API
                ApiData data = new ApiData(image_name, encoded_string);
                apiServices = ServiceGenerator.createService(ApiServices.class);
                Call<ApiData> call = apiServices.postData(data);
                circular.startAnimation();
                call.enqueue(new Callback<ApiData>() {
                    @Override
                    public void onResponse(Call<ApiData> call, Response<ApiData> response) {

                        Toast.makeText(getApplicationContext(), response.body().getFilename(), Toast.LENGTH_SHORT).show();
                        Call<Klasifikasi> ekstrax = apiServices.getKlasifikasi(response.body().getFilename(),"android");
                        ekstrax.enqueue(new Callback<Klasifikasi>() {
                            @Override
                            public void onResponse(Call<Klasifikasi> call, Response<Klasifikasi> response) {
                                String text,kematangan = null,datas;
                                
                                datas = response.body().getKeterangan().toString();
                                if (datas.equals("matang")){
                                    kematangan = "Matang";
                                }
                                else if(datas.equals("setMatang")){
                                    kematangan = "Setengah Matang";
                                }
                                else if(datas.equals("mentah")){
                                    kematangan = "Mentah";
                                }

                                int prediksi = Integer.parseInt(response.body().getPrediksi().toString());
                                if(prediksi<0) {
                                    text = "Buah Akan Matang dalam "+(prediksi*-1) + " Hari";
                                }
                                else if(prediksi==0){
                                    text = "Buah Matang dalam " + prediksi + " Hari";
                                }
                                else{
                                    text = "Buah telah Matang pada " + prediksi + " Hari yang lalu";
                                }
                                txtDetail.setText(text);
                                txtHasil.setText(kematangan);
                                circular.revertAnimation();
                                txtDialog.setText("Identifikasi : "+kematangan+"\nPrediksi :"+text+"\n\nHSI\nHue :"
                                                +response.body().getHue()+"\nSaturation :"+response.body().getSaturation()
                                                +"\nIntensity :"+response.body().getIntensity()+"\n\nGLCM\nContras : "+response.body().getContras()
                                                +"\nEntropy : "+response.body().getEntropy()+"\nHomogenity :"+response.body().getHomogenity()
                                                +"\nEnergy :"+response.body().getEnergy()
                                                );
                            }

                            @Override
                            public void onFailure(Call<Klasifikasi> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
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
