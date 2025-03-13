package com.example.ppb_f_inputoutput;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi elemen UI
        EditText inputNama = findViewById(R.id.inputNama);
        EditText inputNim = findViewById(R.id.inputNim);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        TextView textHasil = findViewById(R.id.textHasil);

        // Set event listener pada tombol
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil input dari EditText
                String nama = inputNama.getText().toString().trim();
                String nim = inputNim.getText().toString().trim();

                // Periksa apakah input kosong
                if (nama.isEmpty() || nim.isEmpty()) {
                    textHasil.setText("Mohon isi Nama dan NIM!");
                } else {
                    // Tampilkan hasil di TextView
                    textHasil.setText("Nama: " + nama + "\nNIM: " + nim);
                }
            }
        });
    }
}
