package com.tdn.qurban.admin.ui.tabungan;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tdn.data.Const;
import com.tdn.domain.model.TabunganModel;
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.trian.singleadapter.SingleAdapterRow;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

public class ItemListTabunganPerUser extends LinearLayout implements SingleAdapterRow<TabunganModel> {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Button btn;
    private TextView tv_nama;
    private TextView tv_jumlah;
    private TextView tv_status;
    private TextView tv_tanggal;
    public ItemListTabunganPerUser(Context context) {
        super(context);
    }

    public ItemListTabunganPerUser(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemListTabunganPerUser(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemListTabunganPerUser(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        tv_jumlah = (TextView) findViewById(R.id.tv_jumlah);
        tv_tanggal = (TextView) findViewById(R.id.tv_tanggal);
        tv_status = (TextView) findViewById(R.id.tv_status);
        btn = (Button) findViewById(R.id.btn_detail);
    }

    @Override
    public void bindView(TabunganModel data, onEventClick<TabunganModel> eventClick, int position) {
        tv_jumlah.setText(data.getNominal());
        tv_tanggal.setText(data.created_at_to_date());
        if (data.getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITERIMA)) {
            tv_status.setText("Sudah Di terima Oleh Admin");
        } else if (data.getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITOLAK)) {
           tv_status.setText("Ditolak");
        } else if (data.getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_MENUNGGU)) {
            tv_status.setText("Menunggu Konfirmasi");
        } else if (data.getStatus().equalsIgnoreCase(Const.STATUS_NOTIF_TAMBAHSALDO_DITARIK)) {
            tv_status.setText("Sudah Di Ambil");
        }
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(data.getUser_uid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel u = snapshot.getValue(UserModel.class);
                            assert u != null;
                            tv_nama.setText(u.getNama());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        btn.setOnClickListener(v -> eventClick.onClick(onEventType.onDetail,data,position));
    }
}
