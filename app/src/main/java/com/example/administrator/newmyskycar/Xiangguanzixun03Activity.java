package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Xiangguanzixun03Activity extends AppCompatActivity {

    @BindView(R.id.mXiangguanzixunReturn03)
    ImageView mXiangguanzixunReturn03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zixun03);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mXiangguanzixunReturn03)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, XiangguanzixunActivity.class);
        //转向添加页面
        startActivity(intent);
        finish();
    }
}
