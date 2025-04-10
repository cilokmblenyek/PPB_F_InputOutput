package com.example.ppb_f_inputoutput;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MahasiswaAdapter extends ArrayAdapter<mahasiswa> {

    // ViewHolder pattern: caches view lookups
    private static class ViewHolder {
        TextView txtNama;
        TextView txtNrp;
    }

    // Constructor
    public MahasiswaAdapter(Context context, ArrayList<mahasiswa> listMahasiswa) {
        super(context, 0, listMahasiswa);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        mahasiswa currentMahasiswa = getItem(position);

        ViewHolder holder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_mahasiswa, parent, false);

            holder.txtNama = convertView.findViewById(R.id.textViewNama);
            holder.txtNrp = convertView.findViewById(R.id.textViewNrp);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view
        if (currentMahasiswa != null) {
            holder.txtNama.setText(currentMahasiswa.getNama());
            holder.txtNrp.setText(currentMahasiswa.getNrp());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}