package com.example.nfnt.bananavision;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nfnt.bananavision.sevices.ApiServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {
    private static final int CAMERA_PERMISSION=1;
    private static final int SELECT_PICTURE=2;
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
                getPermis(1);
            }
        });
        Button fileBtn = (Button) findViewById(R.id.btnFolder);
        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermis(0);

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
    private void getPermis(int value){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERA_PERMISSION);
            Log.d("permision", "Fail");
        }
        else if(value==1){
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
        else{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == CAMERA_PERMISSION){
                Intent results = new Intent(this,MainActivity.class);
                Bundle b = new Bundle();
                b.putString("id","camera");
                b.putString("img","/storage/emulated/0" + file_uri.getPath());
                b.putString("imgName",image_name);
                results.putExtras(b);
                startActivity(results);
            }
            else if(requestCode == SELECT_PICTURE){
                file_uri=data.getData();
                Intent results = new Intent(this,MainActivity.class);
                Bundle b = new Bundle();
                b.putString("id","folder");
                b.putString("img",getRealPathFromURI_API19(this,file_uri));
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                image_name = timeStamp+".jpg";
                b.putString("imgName",image_name);
                results.putExtras(b);
                startActivity(results);
            }
        }

    }

    public static String getRealPathFromURI_API19(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

}

