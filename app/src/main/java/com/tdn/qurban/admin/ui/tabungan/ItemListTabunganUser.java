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
import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.trian.singleadapter.SingleAdapterRow;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

public class ItemListTabunganUser extends LinearLayout implements SingleAdapterRow<String> {
    private TextView tv_nama;
    private Button btn;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    public ItemListTabunganUser(Context context) {
        super(context);
    }

    public ItemListTabunganUser(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemListTabunganUser(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemListTabunganUser(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        btn = (Button) findViewById(R.id.btn_detail);
    }


    @Override
    public void bindView(String data, onEventClick<String> eventClick, int position) {
        databaseReference.child(Const.BASE_CHILD)
                .child(Const.CHILD_USER)
                .child(data)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            UserModel u = snapshot.getValue(UserModel.class);
                            tv_nama.setText("Nama : " + u.getNama());
                            btn.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    eventClick.onClick(onEventType.onDetail,data,position);
                                }
                            });
                        } else {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
