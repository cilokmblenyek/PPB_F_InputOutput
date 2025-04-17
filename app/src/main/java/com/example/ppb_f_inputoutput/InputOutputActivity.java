package com.example.ppb_f_inputoutput;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class InputOutputActivity extends AppCompatActivity {

    private Button buttonAddMahasiswa, buttonUpdate, buttonDelete;
    private EditText editTextSearch;
    private ListView listViewHasil;
    private MahasiswaAdapter adapter;
    private ArrayList<mahasiswa> mahasiswaList;
    private ArrayList<mahasiswa> filteredList;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_output);

        // Initialize UI components
        editTextSearch = findViewById(R.id.editTextSearch);
        buttonAddMahasiswa = findViewById(R.id.buttonAddMahasiswa);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        listViewHasil = findViewById(R.id.listViewHasil);

        // Initialize data lists
        mahasiswaList = new ArrayList<>();
        filteredList = new ArrayList<>(mahasiswaList);

        // Initialize and set the adapter
        adapter = new MahasiswaAdapter(this, filteredList);
        listViewHasil.setAdapter(adapter);

        // Add Mahasiswa Button
        buttonAddMahasiswa.setOnClickListener(v -> showAddDialog());

        // Search functionality
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // ListView item click listener
        listViewHasil.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            buttonUpdate.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);
            buttonAddMahasiswa.setVisibility(View.GONE);
        });

        // Update Button
        buttonUpdate.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                showUpdateDialog(selectedIndex);
            }
        });

        // Delete Button
        buttonDelete.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                mahasiswaList.remove(selectedIndex);
                filterList(editTextSearch.getText().toString());
                resetForm();
            }
        });

        // Long click to delete
        listViewHasil.setOnItemLongClickListener((parent, view, position, id) -> {
            mahasiswaList.remove(position);
            filterList(editTextSearch.getText().toString());
            resetForm();
            return true;
        });
    }

    private void showAddDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_mahasiswa, null);
        EditText dialogInputNama = dialogView.findViewById(R.id.dialogInputNama);
        EditText dialogInputNim = dialogView.findViewById(R.id.dialogInputNim);

        new AlertDialog.Builder(this)
                .setTitle("Tambah Mahasiswa")
                .setView(dialogView)
                .setPositiveButton("Tambah", (dialog, which) -> {
                    String nama = dialogInputNama.getText().toString().trim();
                    String nim = dialogInputNim.getText().toString().trim();

                    if (!nama.isEmpty() && !nim.isEmpty()) {
                        mahasiswaList.add(new mahasiswa(nama, nim));
                        filterList(editTextSearch.getText().toString());
                        Toast.makeText(this, "Mahasiswa berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nama dan NIM harus diisi!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    public void showUpdateDialog(int position) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_mahasiswa, null);
        EditText dialogInputNama = dialogView.findViewById(R.id.dialogInputNama);
        EditText dialogInputNim = dialogView.findViewById(R.id.dialogInputNim);

        mahasiswa selectedMahasiswa = mahasiswaList.get(position); // Correct usage
        dialogInputNama.setText(selectedMahasiswa.getNama());
        dialogInputNim.setText(selectedMahasiswa.getNrp());

        new AlertDialog.Builder(this)
                .setTitle("Update Mahasiswa")
                .setView(dialogView)
                .setPositiveButton("Update", (dialog, which) -> {
                    String newNama = dialogInputNama.getText().toString().trim();
                    String newNim = dialogInputNim.getText().toString().trim();

                    if (!newNama.isEmpty() && !newNim.isEmpty()) {
                        selectedMahasiswa.setNama(newNama);
                        selectedMahasiswa.setNrp(newNim);
                        filterList(editTextSearch.getText().toString());
                        Toast.makeText(this, "Mahasiswa berhasil diperbarui!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nama dan NIM harus diisi!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void filterList(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(mahasiswaList);
        } else {
            for (mahasiswa m : mahasiswaList) {
                if (m.getNama().toLowerCase().contains(query.toLowerCase()) ||
                        m.getNrp().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(m);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void resetForm() {
        selectedIndex = -1;
        buttonUpdate.setVisibility(View.GONE);
        buttonDelete.setVisibility(View.GONE);
        buttonAddMahasiswa.setVisibility(View.VISIBLE);
    }

    public void deleteMahasiswa(int position) {
        mahasiswaList.remove(position);
        filterList(editTextSearch.getText().toString());
        Toast.makeText(this, "Mahasiswa berhasil dihapus!", Toast.LENGTH_SHORT).show();
    }
}