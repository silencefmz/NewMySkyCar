package com.example.administrator.newmyskycar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.example.administrator.newmyskycar.utils.ProgressUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaibaojiaSelfActivity extends AutoLayoutActivity {

    @BindView(R.id.mDaibaojiaSelfReturn)
    ImageView mDaibaojiaSelfReturn;
    @BindView(R.id.button12)
    Button button12;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.textView15)
    TextView textView15;
    @BindView(R.id.mDaibaojiaEditText)
    EditText mDaibaojiaEditText;
    @BindView(R.id.mQYD)
    TextView mQYD;
    @BindView(R.id.mShuXing)
    TextView mShuXing;
    @BindView(R.id.mNumber)
    TextView mNumber;
    private Sql dao = Sql.getInstance(this);
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_daibaojiaself);
        ButterKnife.bind(this);
        context=this;
        init();
    }

    @OnClick({R.id.mDaibaojiaSelfReturn, R.id.button12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mDaibaojiaSelfReturn:
                Intent intent = new Intent();
                intent.setClass(this, Maindaibaojia.class);
                //转向添加页面
                startActivity(intent);
                finish();
                break;
            case R.id.button12:
                ProgressUtil.showProgress(this);
                new Thread(){
                    public void run(){
                        SystemClock.sleep(2000);
                        ProgressUtil.dismissProgress();
                        if (!mDaibaojiaEditText.getText().toString().equals("")) {
                            dao.ChangeCarCargoPrice(MyDataItem.getInstance().getUserId(), getIntent().getExtras().getString("Number"), mDaibaojiaEditText.getText().toString());
                            dao.ChangeCarCargoState(MyDataItem.getInstance().getUserId(), getIntent().getExtras().getString("Number"), "已报价");
                        }
                    }
                }.start();
                if (!mDaibaojiaEditText.getText().toString().equals("")) {
                    Toast.makeText(this, "报价成功！请退出刷新！", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void init() {
        textView17.setText(getIntent().getExtras().getString("MuDiDi"));
        textView15.setText(getIntent().getExtras().getString("Level"));
        mQYD.setText("起源地—目的地："+getIntent().getExtras().getString("MuDiDi"));
        mShuXing.setText("车货属性："+getIntent().getExtras().getString("weight"));
        mNumber.setText("单号："+getIntent().getExtras().getString("Number"));
    }
}
