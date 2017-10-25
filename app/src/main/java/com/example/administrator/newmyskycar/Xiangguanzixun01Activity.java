package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Xiangguanzixun01Activity extends AutoLayoutActivity {

    @BindView(R.id.mXiangguanzixunReturn01)
    ImageView mXiangguanzixunReturn01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zixun01);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mXiangguanzixunReturn01)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, XiangguanzixunActivity.class);
        //转向添加页面
        startActivity(intent);
        finish();
    }
}
