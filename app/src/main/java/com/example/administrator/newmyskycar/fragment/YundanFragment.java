package com.example.administrator.newmyskycar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.newmyskycar.DaibaojiaSelfActivity;
import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.example.administrator.newmyskycar.adapter.lvButtonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class YundanFragment extends Fragment implements View.OnClickListener{

    Unbinder unbinder;
    @BindView(R.id.area_listview_yizhongbiao)
    ListView areaListviewYizhongbiao;
    @BindView(R.id.area_listview_yunshuzhong)
    ListView areaListviewYunshuzhong;
    @BindView(R.id.area_listview_yiwancheng)
    ListView areaListviewYiwancheng;
    private Button yizhongbiao;
    private Button yunshuzhong;
    private Button yiwancheng;
    SimpleAdapter adapter;
    SimpleAdapter adapter01;
    SimpleAdapter adapter02;
    private Sql dao = Sql.getInstance(getActivity());
    List<Map<String, Object>> area_Listview = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> area_Listview01 = new ArrayList<Map<String, Object>>();
    List<Map<String, Object>> area_Listview02 = new ArrayList<Map<String, Object>>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_yundan, container, false);
        yizhongbiao = (Button) view.findViewById(R.id.button4);
        yunshuzhong = (Button) view.findViewById(R.id.button3);
        yiwancheng = (Button) view.findViewById(R.id.button2);
        yizhongbiao.setOnClickListener(this);
        yunshuzhong.setOnClickListener(this);
        yiwancheng.setOnClickListener(this);
        unbinder = ButterKnife.bind(this, view);
        area_Listview.clear();
        area_Listview = getData(0);
//        adapter = new SimpleAdapter(getContext(), area_Listview, R.layout.linear_yizhongbiaoself,
//                new String[]{"tv_patname_yizhongbiao", "mCarCargoTime_yizhongbiao", "textView19_yizhongbiao", "weight_yizhongbiao", "textView20_yizhongbiao", "textView6_yizhongbiao"},
//                new int[]{R.id.tv_patname_yizhongbiao, R.id.mCarCargoTime_yizhongbiao, R.id.textView19_yizhongbiao, R.id.weight_yizhongbiao, R.id.textView20_yizhongbiao, R.id.textView6_yizhongbiao});
//        areaListviewYizhongbiao.setAdapter(adapter);
//        areaListviewYizhongbiao.setOnItemClickListener(this);

        lvButtonAdapter listItemAdapter = new lvButtonAdapter(getContext(),
                area_Listview, //数据源
                R.layout.linear_yizhongbiaoself, //ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"tv_patname_yizhongbiao", "mCarCargoTime_yizhongbiao", "textView19_yizhongbiao", "weight_yizhongbiao", "textView20_yizhongbiao", "textView6_yizhongbiao","Button"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.tv_patname_yizhongbiao, R.id.mCarCargoTime_yizhongbiao, R.id.textView19_yizhongbiao, R.id.weight_yizhongbiao, R.id.textView20_yizhongbiao, R.id.textView6_yizhongbiao,R.id.button});
        areaListviewYizhongbiao.setAdapter(listItemAdapter);

        area_Listview01.clear();
        area_Listview01=getData(1);
        adapter01 = new SimpleAdapter(getContext(), area_Listview01, R.layout.linear_yunshuzhongself,
                new String[]{"tv_patname_yunshuzhong", "mCarCargoTime_yunshuzhong", "textView19_yunshuzhong", "weight_yunshuzhong", "textView20_yunshuzhong", "textView6_yunshuzhong","textView15_yunshuzhong"},
                new int[]{R.id.tv_patname_yunshuzhong, R.id.mCarCargoTime_yunshuzhong, R.id.textView19_yunshuzhong, R.id.weight_yunshuzhong, R.id.textView20_yunshuzhong, R.id.textView6_yunshuzhong,R.id.textView15_yunshuzhong});
        areaListviewYunshuzhong.setAdapter(adapter01);

        area_Listview02.clear();
        area_Listview02=getData(2);
        adapter02 = new SimpleAdapter(getContext(), area_Listview02, R.layout.linear_yiwanchengself,
                new String[]{"tv_patname_yiwancheng", "mCarCargoTime_yiwancheng", "textView20_yiwancheng", "textView6_yiwancheng","textView15_yiwancheng"},
                new int[]{R.id.tv_patname_yiwancheng, R.id.mCarCargoTime_yiwancheng, R.id.textView20_yiwancheng, R.id.textView6_yiwancheng,R.id.textView15_yiwancheng});
        areaListviewYiwancheng.setAdapter(adapter02);
        areaListviewYunshuzhong.setVisibility(View.GONE);
        areaListviewYiwancheng.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button4) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break_hei);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
            areaListviewYizhongbiao.setVisibility(View.VISIBLE);
            areaListviewYunshuzhong.setVisibility(View.GONE);
            areaListviewYiwancheng.setVisibility(View.GONE);
        } else if (view.getId() == R.id.button3) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break_hei);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
            areaListviewYizhongbiao.setVisibility(View.GONE);
            areaListviewYunshuzhong.setVisibility(View.VISIBLE);
            areaListviewYiwancheng.setVisibility(View.GONE);
        } else if (view.getId() == R.id.button2) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break_hei);
            areaListviewYizhongbiao.setVisibility(View.GONE);
            areaListviewYunshuzhong.setVisibility(View.GONE);
            areaListviewYiwancheng.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public List<Map<String, Object>> getData(int a) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if(a==0){
            List<Map<String, String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(), "已中标");
            for (int i = 0; i < mListTime.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tv_patname_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
                map.put("mCarCargoTime_yizhongbiao", "送货时间 " + mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
                map.put("textView19_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Level));
                map.put("weight_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Model_car));
                map.put("textView20_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
                map.put("textView6_yizhongbiao", '¥' + mListTime.get(i).get(SqlCarCargodata.CarCargo_Price));
                list.add(map);
            }
        }
        else if(a==1){
            List<Map<String, String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(), "运输中");
            for (int i = 0; i < mListTime.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tv_patname_yunshuzhong", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
                map.put("mCarCargoTime_yunshuzhong", "送货时间 " + mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
                map.put("textView19_yunshuzhong", mListTime.get(i).get(SqlCarCargodata.CarCargo_Level));
                map.put("weight_yunshuzhong", mListTime.get(i).get(SqlCarCargodata.CarCargo_Model_car));
                map.put("textView20_yunshuzhong", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
                map.put("textView6_yunshuzhong", '¥' + mListTime.get(i).get(SqlCarCargodata.CarCargo_Price));
                map.put("textView15_yunshuzhong", "司机："+mListTime.get(i).get(SqlCarCargodata.CarCargo_Friend));
                list.add(map);
            }
        }
        else if(a==2){
            List<Map<String, String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(), "已完成");
            for (int i = 0; i < mListTime.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("tv_patname_yiwancheng", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
                map.put("mCarCargoTime_yiwancheng", "送货时间 " + mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
                map.put("textView20_yiwancheng", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
                map.put("textView6_yiwancheng", '¥' + mListTime.get(i).get(SqlCarCargodata.CarCargo_Price));
                map.put("textView15_yiwancheng", "司机："+mListTime.get(i).get(SqlCarCargodata.CarCargo_Friend));
                list.add(map);
            }
        }
        return list;
    }
}
