package com.sabikrahat.photogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShowDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_description);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String description = intent.getStringExtra("description");

        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        ImageView imageView = findViewById(R.id.imageViewId);
        TextView textView = findViewById(R.id.textViewId);

        try {
            Glide.with(ShowDescriptionActivity.this).load(url).into(imageView);
        } catch (Exception e) {
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
        textView.setText(description);
    }
}