package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.MyData.MySet;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.MyData.SqlMydata;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuCe2Activity extends AutoLayoutActivity {

    @BindView(R.id.mZhuCe2Return)
    ImageView mZhuCe2Return;
    @BindView(R.id.mZhuCe)
    Button mZhuCe;
    @BindView(R.id.mUserName)
    EditText mUserName;
    @BindView(R.id.mPassWord)
    EditText mPassWord;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.mNoPhoneText)
    EditText mNoPhoneText;
    @BindView(R.id.xieyi)
    CheckBox xieyi;
    private SqlMydata db;
    private SqlFrienddata db2;
    private SqlCarCargodata db3;
    private String Zhenshixingming = "";
    private String Shenfenzhenghao = "";
    private String Suozaishengshi = "";
    private String Bangongdizhi = "";
    private Sql dao = Sql.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_zhuce2);
        db = new SqlMydata(this);
        db2 = new SqlFrienddata(this);
        db3 = new SqlCarCargodata(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mZhuCe2Return, R.id.mZhuCe})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mZhuCe2Return:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mZhuCe:
                Zhenshixingming = mUserName.getText().toString();
                Shenfenzhenghao = mPassWord.getText().toString();
                Suozaishengshi = editText.getText().toString();
                Bangongdizhi = mNoPhoneText.getText().toString();
                boolean kong = false;
                if (Zhenshixingming.equals(""))
                    Toast.makeText(this, "真实姓名不能为空", Toast.LENGTH_SHORT).show();
                else if (Shenfenzhenghao.equals(""))
                    Toast.makeText(this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                else if (Suozaishengshi.equals(""))
                    Toast.makeText(this, "所在省市不能为空", Toast.LENGTH_SHORT).show();
                else if (Bangongdizhi.equals(""))
                    Toast.makeText(this, "办公地址不能为空", Toast.LENGTH_SHORT).show();
                else
                    kong = true;
                if (kong && !xieyi.isChecked()) {
                    Toast.makeText(this, "请先确认同意协议", Toast.LENGTH_SHORT).show();
                    kong = false;
                }
                if (kong) {
                    if(dao.CheckUserid(Shenfenzhenghao)){
                        MySet.AppUser_id = Shenfenzhenghao;
                        MySet.AppUser_realname = Zhenshixingming;
                        MySet.AppUser_city = Suozaishengshi;
                        MySet.AppUser_address = Bangongdizhi;
                        db.setServerAddress(MySet.AppUser_name, MySet.AppUser_password, MySet.AppUser_id, MySet.AppUser_phone, MySet.AppUser_realname
                                , MySet.AppUser_city, MySet.AppUser_address);
                        db.close();
                        db2.setServerAddress("付明振","11011011011",Shenfenzhenghao,"空闲");
                        dao.setFriendAddress("寇权","11211211111",Shenfenzhenghao,"空闲");
                        dao.setFriendAddress("吕国强","12321515684",Shenfenzhenghao,"空闲");
                        dao.setFriendAddress("李润发","15621568785",Shenfenzhenghao,"空闲");
                        dao.setFriendAddress("李宇同","12345678998",Shenfenzhenghao,"空闲");
                        db2.close();
                        db3.setServerAddress(Shenfenzhenghao,"北京-南京","2017-11-17 18:00—18:30","货车 4.2米 | 1500吨","4.2米","整车","No.000001","待报价","","");
                        db3.setServerAddress(Shenfenzhenghao,"南京-上海","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000002","待报价","","");
                        db3.setServerAddress(Shenfenzhenghao,"上海-广东","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000003","待报价","","");
                        db3.setServerAddress(Shenfenzhenghao,"广东-青岛","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000004","待报价","","");
                        db3.setServerAddress(Shenfenzhenghao,"青岛-北京","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000005","待报价","","");

                        db3.setServerAddress(Shenfenzhenghao,"北京-南京","2017-11-17 18:00—18:30","货车 4.2米 | 1500吨","4.2米","整车","No.000006","已中标","2000","");
                        db3.setServerAddress(Shenfenzhenghao,"南京-上海","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000007","已中标","4000","");
                        db3.setServerAddress(Shenfenzhenghao,"上海-广东","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000008","已中标","5000","");
                        db3.setServerAddress(Shenfenzhenghao,"广东-青岛","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000009","已中标","3000","");
                        db3.setServerAddress(Shenfenzhenghao,"青岛-北京","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000010","已中标","3500","");

                        db3.setServerAddress(Shenfenzhenghao,"北京-南京","2017-11-17 18:00—18:30","货车 4.2米 | 1500吨","4.2米","整车","No.000011","已报价","2000","");
                        db3.setServerAddress(Shenfenzhenghao,"南京-上海","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000012","已报价","4000","");
                        db3.setServerAddress(Shenfenzhenghao,"上海-广东","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000013","已报价","5000","");
                        db3.setServerAddress(Shenfenzhenghao,"广东-青岛","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000014","已报价","3000","");
                        db3.setServerAddress(Shenfenzhenghao,"青岛-北京","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000015","已报价","3500","");

                        db3.setServerAddress(Shenfenzhenghao,"北京-南京","2017-11-17 18:00—18:30","货车 4.2米 | 1500吨","4.2米","整车","No.000016","运输中","2000","付XX");
                        db3.setServerAddress(Shenfenzhenghao,"南京-上海","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000017","运输中","4000","付XX");
                        db3.setServerAddress(Shenfenzhenghao,"上海-广东","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000018","运输中","5000","付XX");
                        db3.setServerAddress(Shenfenzhenghao,"广东-青岛","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000019","运输中","3000","付XX");
                        db3.setServerAddress(Shenfenzhenghao,"青岛-北京","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000020","运输中","3500","付XX");

                        db3.setServerAddress(Shenfenzhenghao,"北京-南京","2017-11-17 18:00—18:30","货车 4.2米 | 1500吨","4.2米","整车","No.000021","已完成","2000","王XX");
                        db3.setServerAddress(Shenfenzhenghao,"南京-上海","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000022","已完成","4000","王XX");
                        db3.setServerAddress(Shenfenzhenghao,"上海-广东","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000023","已完成","5000","王XX");
                        db3.setServerAddress(Shenfenzhenghao,"广东-青岛","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","拼车","No.000024","已完成","3000","王XX");
                        db3.setServerAddress(Shenfenzhenghao,"青岛-北京","2017-11-17 18:00—18:30","中栏车 5.8米 | 2000吨","5.6米","整车","No.000025","已完成","3500","王XX");
                        db3.close();
                        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
                        intent.setClass(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "该身份证号已注册，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
