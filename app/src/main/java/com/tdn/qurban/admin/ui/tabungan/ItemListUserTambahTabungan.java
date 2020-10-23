package com.tdn.qurban.admin.ui.tabungan;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tdn.domain.model.UserModel;
import com.tdn.qurban.R;
import com.trian.singleadapter.SingleAdapter;
import com.trian.singleadapter.SingleAdapterRow;
import com.trian.singleadapter.onEventClick;
import com.trian.singleadapter.onEventType;

public class ItemListUserTambahTabungan extends LinearLayout implements SingleAdapterRow<UserModel> {
    private TextView textView;
    private Button btn;
    public ItemListUserTambahTabungan(Context context) {
        super(context);
    }

    public ItemListUserTambahTabungan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemListUserTambahTabungan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ItemListUserTambahTabungan(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView) findViewById(R.id.tv_nama);
        btn = (Button) findViewById(R.id.btn_pilih);
    }

    @Override
    public void bindView(UserModel data, onEventClick<UserModel> eventClick, int position) {
        textView.setText(data.getNama());
        btn.setOnClickListener(v -> {
            eventClick.onClick(onEventType.onDetail,data,position);
        });
    }
}
