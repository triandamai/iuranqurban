package com.tdn.qurban.nasabah.ui.notifikasi;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.qurban.nasabah.ui.home.AdapterMyTabungan;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterMyTabungan.MyViewHolder> {
    @NonNull
    @Override
    public AdapterMyTabungan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMyTabungan.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
