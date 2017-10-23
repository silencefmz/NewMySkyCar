package com.example.administrator.newmyskycar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;

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

public class YundanFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.area_listview)
    ListView areaListview;
    Unbinder unbinder;
    private Button yizhongbiao;
    private Button yunshuzhong;
    private Button yiwancheng;
    SimpleAdapter adapter;
    private Sql dao = Sql.getInstance(getActivity());
    List<Map<String,Object>> area_Listview=new ArrayList<Map<String,Object>>();

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
        area_Listview=getData();
        adapter=new SimpleAdapter(getContext(),area_Listview,R.layout.linear_yizhongbiaoself,
                new String[]{"tv_patname_yizhongbiao","mCarCargoTime_yizhongbiao","textView19_yizhongbiao","weight_yizhongbiao","textView20_yizhongbiao","textView6_yizhongbiao"},
                new int[]{R.id.tv_patname_yizhongbiao,R.id.mCarCargoTime_yizhongbiao,R.id.textView19_yizhongbiao,R.id.weight_yizhongbiao,R.id.textView20_yizhongbiao,R.id.textView6_yizhongbiao});
        areaListview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button4) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break_hei);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
        } else if (view.getId() == R.id.button3) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break_hei);
            yiwancheng.setBackgroundResource(R.mipmap.button_break);
        } else if (view.getId() == R.id.button2) {
            yizhongbiao.setBackgroundResource(R.mipmap.button_break);
            yunshuzhong.setBackgroundResource(R.mipmap.button_break);
            yiwancheng.setBackgroundResource(R.mipmap.button_break_hei);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public List<Map<String,Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String,String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(),"已中标");
        for(int i=0;i<mListTime.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_patname_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
            map.put("mCarCargoTime_yizhongbiao", "送货时间 "+mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
            map.put("textView19_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Level));
            map.put("weight_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Model_car));
            map.put("textView20_yizhongbiao", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
            map.put("textView6_yizhongbiao",'¥'+mListTime.get(i).get(SqlCarCargodata.CarCargo_Price));
            list.add(map);
        }
        return list;
    }
}
