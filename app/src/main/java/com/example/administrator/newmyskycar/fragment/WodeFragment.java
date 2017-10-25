package com.example.administrator.newmyskycar.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlMydata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class WodeFragment extends Fragment {
    @BindView(R.id.mMyName)
    TextView mMyName;
    @BindView(R.id.mMyPhone)
    TextView mMyPhone;
    Unbinder unbinder;
    @BindView(R.id.mMyTouXiang)
    ImageButton mMyTouXiang;
    private Sql dao = Sql.getInstance(getActivity());


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_wode, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void init() {
        List<Map<String, String>> mListTime = dao.getMyDataList(MyDataItem.getInstance().getUserName(), MyDataItem.getInstance().getUserPassword());
        mMyName.setText(mListTime.get(0).get(SqlMydata.USER_REALNAME).toString());
        mMyPhone.setText(mListTime.get(0).get(SqlMydata.USER_PHONE).toString());
    }

    @OnClick(R.id.mMyTouXiang)
    public void onViewClicked() {
    }

}
