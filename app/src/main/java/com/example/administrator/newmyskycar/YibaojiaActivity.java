package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YibaojiaActivity extends AutoLayoutActivity {

    @BindView(R.id.mYibaojiaReturn)
    ImageView mYibaojiaReturn;
    @BindView(R.id.mYibaojiaEditText)
    EditText mYibaojiaEditText;
    @BindView(R.id.mYibaojiaButton)
    Button mYibaojiaButton;
    @BindView(R.id.rLayoutListView)
    ListView rLayoutListView;

    SimpleAdapter adapter;
    private Sql dao = Sql.getInstance(this);
    List<Map<String,Object>> area_Listview=new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_yibaojia);
        ButterKnife.bind(this);
        area_Listview.clear();
        area_Listview=getData();
        adapter=new SimpleAdapter(this,area_Listview,R.layout.linear_yibaojiaself,
                new String[]{"tv_patname_yibaojia","mCarCargoTime_yibaojia","textView19_yibaojia","weight_yibaojia","textView20_yibaojia","textView6_yibaojia"},
                new int[]{R.id.tv_patname_yibaojia,R.id.mCarCargoTime_yibaojia,R.id.textView19_yibaojia,R.id.weight_yibaojia,R.id.textView20_yibaojia,R.id.textView6_yibaojia});
        rLayoutListView.setAdapter(adapter);
    }

    @OnClick({R.id.mYibaojiaReturn, R.id.mYibaojiaButton})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.mYibaojiaReturn:
                intent.setClass(this, MainShouyeActivity.class);
                intent.putExtra("Fragment",0);
                //转向添加页面
                finish();
                startActivity(intent);
                break;
            case R.id.mYibaojiaButton:
                break;
        }
    }
    public List<Map<String,Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String,String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(),"已报价");
        for(int i=0;i<mListTime.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_patname_yibaojia", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
            map.put("mCarCargoTime_yibaojia", "送货时间 "+mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
            map.put("textView19_yibaojia", mListTime.get(i).get(SqlCarCargodata.CarCargo_Level));
            map.put("weight_yibaojia", mListTime.get(i).get(SqlCarCargodata.CarCargo_Model_car));
            map.put("textView20_yibaojia", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
            map.put("textView6_yibaojia",'¥'+mListTime.get(i).get(SqlCarCargodata.CarCargo_Price));
            list.add(map);
        }
        return list;
    }
}
