package com.example.nfnt.bananavision;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nfnt.bananavision.sevices.ApiServices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {
    private static final int CAMERA_PERMISSION=1;
    private static final int READ_PERMISSION=1;
    private File file;
    ApiServices apiServices;
    private Uri file_uri;
    private String image_name,encoded_string,pathImage;
    Bitmap bitmap;
    //ImageView imgView_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradient);
        Button cameraBtn = (Button) findViewById(R.id.btnCamerass);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermis();
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        image_name = timeStamp+".jpg";
        pathImage= Environment.getExternalStorageDirectory().getAbsolutePath()+ "/example";

        File dir = new File(pathImage);
        dir.mkdirs();

        File file = new File(dir,timeStamp+".jpg");

        try {
            new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }
    private void getPermis(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERA_PERMISSION);
            Log.d("permision", "Fail");
        }
        else{
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (i.resolveActivity(getPackageManager()) != null)
            {
                File imgFile=null;
                try {

                    imgFile = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (imgFile!=null ){
                    file_uri = FileProvider.getUriForFile(this,"com.example.nfnt.bananavision.provider",imgFile);
                    i.putExtra(MediaStore.EXTRA_OUTPUT,file_uri);
                    startActivityForResult(i,CAMERA_PERMISSION);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case CAMERA_PERMISSION:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    getPermis();
                }
                else
                {
                    Log.e("status", "Fail" );
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PERMISSION && resultCode == RESULT_OK){
            //bitmap = (Bitmap) data.getExtras().get("data");
            bitmap = BitmapFactory.decodeFile("/storage/emulated/0"+file_uri.getPath());
            //imgView_data.setImageBitmap(bitmap);
            new HomePage.Encode_image().execute();
            Intent results = new Intent(this,MainActivity.class);
            Bundle b = new Bundle();
            b.putString("img","/storage/emulated/0" + file_uri.getPath());
            b.putString("imgName",image_name);
            results.putExtras(b);
            startActivity(results);
            //txtData.setText(encoded_string);
        }
    }

    private class Encode_image extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            bitmap = BitmapFactory.decodeFile("/storage/emulated/0" + file_uri.getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] array = stream.toByteArray();
            encoded_string = Base64.encodeToString(array, 0);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            ApiData data = new ApiData(image_name, encoded_string);
//            apiServices = ServiceGenerator.createService(ApiServices.class);
//            Call<ApiData> call = apiServices.postData(data);
//            call.enqueue(new Callback<ApiData>() {
//                @Override
//                public void onResponse(Call<ApiData> call, Response<ApiData> response) {
//                    Toast.makeText(getApplicationContext(), "Sukses", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<ApiData> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}

