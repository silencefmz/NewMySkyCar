package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiangguanzixunActivity extends AutoLayoutActivity {

    @BindView(R.id.mXiangguanzixunReturn)
    ImageView mXiangguanzixunReturn;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView6)
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_xiangguanzixun);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mXiangguanzixunReturn, R.id.textView10, R.id.textView9, R.id.textView8, R.id.textView7, R.id.textView6})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mXiangguanzixunReturn:
                intent.setClass(this, MainShouyeActivity.class);
                intent.putExtra("Fragment",0);
                //转向添加页面
                finish();
                startActivity(intent);
                break;
            case R.id.textView10:
                break;
            case R.id.textView9:
                break;
            case R.id.textView8:
                break;
            case R.id.textView7:
                break;
            case R.id.textView6:
                break;
        }
    }
}
