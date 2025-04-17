package com.example.ppb_f_inputoutput;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MahasiswaAdapter extends ArrayAdapter<mahasiswa> {

    // ViewHolder pattern: caches view lookups
    private static class ViewHolder {
        TextView txtNama;
        TextView txtNrp;
        Button buttonEdit;
        Button buttonDelete;
    }

    // Constructor
    public MahasiswaAdapter(Context context, ArrayList<mahasiswa> listMahasiswa) {
        super(context, 0, listMahasiswa);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mahasiswa currentMahasiswa = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_mahasiswa, parent, false);

            holder.txtNama = convertView.findViewById(R.id.textViewNama);
            holder.txtNrp = convertView.findViewById(R.id.textViewNrp);
            holder.buttonEdit = convertView.findViewById(R.id.buttonEdit);
            holder.buttonDelete = convertView.findViewById(R.id.buttonDelete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (currentMahasiswa != null) {
            holder.txtNama.setText(currentMahasiswa.getNama());
            holder.txtNrp.setText(currentMahasiswa.getNrp());

            // Handle Edit button click
            holder.buttonEdit.setOnClickListener(v -> {
                // Call a method to show the update dialog
                ((InputOutputActivity) getContext()).showUpdateDialog(position);
            });

            // Handle Delete button click
            holder.buttonDelete.setOnClickListener(v -> {
                // Remove the item and notify the adapter
                ((InputOutputActivity) getContext()).deleteMahasiswa(position);
            });
        }

        return convertView;
    }
}