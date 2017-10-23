package com.example.administrator.newmyskycar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.newmyskycar.MainActivity;
import com.example.administrator.newmyskycar.MainShouyeActivity;
import com.example.administrator.newmyskycar.Maindaibaojia;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.XiangguanzixunActivity;
import com.example.administrator.newmyskycar.YibaojiaActivity;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class ShouyeFragment extends Fragment implements View.OnClickListener,View.OnTouchListener{
    private Button daibaojia;
    private Button yizhongbiao;
    private Button yibaojia;
    private Button xiangguanzixun;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_shouye, container, false);
        daibaojia=(Button)view.findViewById(R.id.daibaojia);
        yizhongbiao=(Button)view.findViewById(R.id.yizhongbiao);
        yibaojia=(Button)view.findViewById(R.id.yibaojia);
        xiangguanzixun=(Button)view.findViewById(R.id.xiangguanzixun);
        daibaojia.setOnClickListener(this);
        yibaojia.setOnClickListener(this);

        daibaojia.setOnTouchListener(this);
        yizhongbiao.setOnTouchListener(this);
        yibaojia.setOnTouchListener(this);
        xiangguanzixun.setOnTouchListener(this);
        yizhongbiao.setOnClickListener(this);
        xiangguanzixun.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if(view.getId()==R.id.daibaojia){
            intent.setClass(getActivity(), Maindaibaojia.class);
            //转向添加页面
            getActivity().startActivity(intent);
            getActivity().finish();
        }
        else if(view.getId()==R.id.yibaojia){
            intent.setClass(getActivity(), YibaojiaActivity.class);
            //转向添加页面
            getActivity().finish();
            startActivity(intent);
        }
        else if(view.getId()==R.id.yizhongbiao){
            intent.setClass(getActivity(), MainShouyeActivity.class);
            intent.putExtra("Fragment",1);
            //转向添加页面
            getActivity().finish();
            startActivity(intent);
        }else if(view.getId()==R.id.xiangguanzixun){
            intent.setClass(getActivity(), XiangguanzixunActivity.class);
            //转向添加页面
            getActivity().finish();
            startActivity(intent);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view
                        .setBackgroundResource(R.mipmap.button_break_hei);
                break;
            case MotionEvent.ACTION_UP:
                view
                        .setBackgroundResource(R.mipmap.button_break);
                break;
            default:
                break;
        }
        return false;
    }
}
