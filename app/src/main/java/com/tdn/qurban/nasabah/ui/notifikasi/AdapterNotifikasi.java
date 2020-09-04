package com.tdn.qurban.nasabah.ui.notifikasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tdn.data.Const;
import com.tdn.domain.model.NotifikasiModel;
import com.tdn.qurban.R;
import com.tdn.qurban.core.AdapterClicked;
import com.tdn.qurban.databinding.ItemNotifikasiBinding;
import com.tdn.qurban.nasabah.ui.home.AdapterMyTabungan;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterNotifikasi.MyViewHolder> {
    private List<NotifikasiModel> NotifikasiModels = new ArrayList<>();
    private AdapterClicked adapterClicked;
    private Context context;

    public AdapterNotifikasi(Context context, AdapterClicked adapterClicked) {
        this.adapterClicked = adapterClicked;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNotifikasi.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotifikasiBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_notifikasi, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifikasi.MyViewHolder holder, int position) {
        if (NotifikasiModels.get(position).getTipe().equalsIgnoreCase(Const.TIPE_NOTIF_AKTIVASI)) {
            if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_AKTIVASI_MENUNGGU)) {
                // holder.binding.tvIsiNotifikasi.setText("Aktivasi Akun Anda telah disetujui Oleh Admin");
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_AKTIVASI_DITERIMA)) {
                holder.binding.tvIsiNotifikasi.setText("Aktivasi Akun Anda telah disetujui Oleh Admin");
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_AKTIVASI_DITOLAK)) {
                holder.binding.tvIsiNotifikasi.setText("Aktivasi Akun Anda telah ditolak dengan alasan : " + NotifikasiModels.get(position).getBody());
            }
            holder.binding.btnAksi.setVisibility(View.GONE);
        } else if (NotifikasiModels.get(position).getTipe().equalsIgnoreCase(Const.TIPE_NOTIF_TAMBAHSALDO)) {
            if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA)) {
                holder.binding.tvIsiNotifikasi.setText("Penambahan Saldo Diterima Oleh Admin");
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITOLAK)) {
                holder.binding.tvIsiNotifikasi.setText("Penambahan Saldo Ditolak alasan : " + NotifikasiModels.get(position).getStatus());
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_MENUNGGU)) {
                //  holder.binding.tvIsiNotifikasi.setText("Penambahan Saldo Diterima Oleh Admin");
            }
        } else if (NotifikasiModels.get(position).getTipe().equalsIgnoreCase(Const.TIPE_NOTIF_TARIK)) {
            if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_PENGAJUANTARIKDANA_DITERIMA)) {
                holder.binding.tvIsiNotifikasi.setText("Penarikan Saldo Diterima Oleh Admin");
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_PENGAJUANTARIKDANA_DITOLAK)) {
                holder.binding.tvIsiNotifikasi.setText("Penarikan Saldo Ditolak Admin alasan : " + NotifikasiModels.get(position
                ).getStatus());
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_PENGAJUANTARIKDANA_MENUNGGU)) {
            }
        } else if (NotifikasiModels.get(position).getTipe().equalsIgnoreCase(Const.TIPE_NOTIF_TUTUP)) {
            if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TUTUP_DITERIMA)) {
                holder.binding.tvIsiNotifikasi.setText("Penutupan Akun Disetujui ");
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TUTUP_DITOLAK)) {
                holder.binding.tvIsiNotifikasi.setText("Penutupan Akun Ditolak alasan : " + NotifikasiModels.get(position).getStatus());
            } else if (NotifikasiModels.get(position).getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TUTUP_MENUNGGU)) {
            }
        }

    }

    public void setData(List<NotifikasiModel> NotifikasiModels) {
        if (this.NotifikasiModels == null) {
            this.NotifikasiModels.addAll(NotifikasiModels);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return AdapterNotifikasi.this.NotifikasiModels.size();
                }

                @Override
                public int getNewListSize() {
                    return NotifikasiModels.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return AdapterNotifikasi.this.NotifikasiModels.get(oldItemPosition).getId() == NotifikasiModels.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NotifikasiModel lama = AdapterNotifikasi.this.NotifikasiModels.get(oldItemPosition);
                    NotifikasiModel baru = NotifikasiModels.get(newItemPosition);
                    return lama == baru && Objects.equals(lama, baru);
                }
            });
            this.NotifikasiModels = NotifikasiModels;
            result.dispatchUpdatesTo(this);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return NotifikasiModels == null ? 0 : NotifikasiModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemNotifikasiBinding binding;

        public MyViewHolder(@NonNull ItemNotifikasiBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
