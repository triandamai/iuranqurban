package com.tdn.qurban.admin.ui.pengajuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.domain.model.TarikDanaModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemTarikdanaBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterPenarikanDana extends RecyclerView.Adapter<AdapterPenarikanDana.MyViewHolder> {
    private List<TarikDanaModel> tarikDanaModels = new ArrayList<>();
    private AdapterClicked adapterClicked;
    private Context context;

    public AdapterPenarikanDana(AdapterClicked adapterClicked, Context context) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPenarikanDana.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTarikdanaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_tarikdana, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenarikanDana.MyViewHolder holder, int position) {
        holder.binding.setPosisi(position);
        holder.binding.setAction(adapterClicked);
        holder.binding.tvNama.setText(tarikDanaModels.get(position).getAtas_nama());

        holder.binding.tvStatus.setText("Status");
    }

    public void setData(List<TarikDanaModel> NotifikasiModels) {
        if (this.tarikDanaModels == null) {
            this.tarikDanaModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterPenarikanDana.this.tarikDanaModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterPenarikanDana.this.tarikDanaModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    TarikDanaModel lama = AdapterPenarikanDana.this.tarikDanaModels.get(oldItemPosition);
                    TarikDanaModel baru = NotifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.tarikDanaModels = NotifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return tarikDanaModels == null ? 0 : tarikDanaModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemTarikdanaBinding binding;

        public MyViewHolder(@NonNull ItemTarikdanaBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
