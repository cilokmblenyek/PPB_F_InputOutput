package com.example.ppb_f_inputoutput;

import android.content.Intent;
import android.os.Bundle;
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

        // Set event listener pada tombol Input Output
        buttonInputOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke InputOutputActivity
                Intent intent = new Intent(MainActivity.this, InputOutputActivity.class);
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
    }
}