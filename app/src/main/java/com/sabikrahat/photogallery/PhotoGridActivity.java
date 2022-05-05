package com.sabikrahat.photogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class PhotoGridActivity extends AppCompatActivity {

    private GridView gridView;

    private String URL = "https://muthosoft.com/univ/photos/";
    private ArrayList<PhotoModel> photoModels;
    private CustomPhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_grid);

        findViewById(R.id.exitBtn).setOnClickListener(v -> finish());

        gridView = findViewById(R.id.gridViewId);

        httpRequest();

        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            PhotoModel photo = photoModels.get(i);
            Intent intent = new Intent(PhotoGridActivity.this, ShowDescriptionActivity.class);
            intent.putExtra("url", photo.getUrl());
            intent.putExtra("description", photo.getDescription());
            startActivity(intent);
        });
    }

    private void httpRequest() {
        photoModels = new ArrayList<>();
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "GET", null);
                    System.out.println("Rahat OutSide For Loop: " + data);
                    if (data != null) {
                        String[] pModels = data.split(",");
                        for(int i = 0; i < pModels.length; i++) {
                            photoModels.add(new PhotoModel(URL + pModels[i].split(":")[0], pModels[i].split(":")[1]));
                        }
                        System.out.println("Rahat Inside For Loop: " + photoModels.size());
                    }
                    return data;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                if (data != null) {
                    try {
                        adapter = new CustomPhotoAdapter(PhotoGridActivity.this, photoModels);
                        gridView.setAdapter(adapter);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }.execute();
    }
}