package com.example.ppb_f_inputoutput;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi elemen UI
        Button buttonInputOutput = findViewById(R.id.buttonInputOutput);
        Button buttonToast = findViewById(R.id.buttonToast);
        Button buttonIntent = findViewById(R.id.buttonIntent);
        Button buttonMaps = findViewById(R.id.buttonMaps);

        // Set event listener pada tombol Input Output
        buttonInputOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke activity InputOutput
                Intent intent = new Intent(MainActivity.this, InputOutputActivity.class);
                startActivity(intent);
            }
        });

        // Set event listener pada tombol Maps
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke activity Maps
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Set event listener pada tombol Toast
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tampilkan Toast
                Toast.makeText(MainActivity.this, "Halo Senopati!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set event listener for Intent button
        buttonIntent.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "captured_image_" + System.currentTimeMillis() + ".jpg");
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");

            Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (imageUri != null) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                try {
                    startActivity(cameraIntent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Camera app not found!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Failed to create image file!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}