package com.example.administrator.newmyskycar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maindaibaojia extends AutoLayoutActivity implements View.OnClickListener {

    private List<String> list_mudidi = new ArrayList<String>();
    private ArrayAdapter<String> adapter_mudidi;
    private List<String> list_yongcheleixing = new ArrayList<String>();
    private ArrayAdapter<String> adapter_yongcheleixing;
    private List<String> list_chechang = new ArrayList<String>();
    private ArrayAdapter<String> adapter_chechang;
    private List<String> list_chexing = new ArrayList<String>();
    private ArrayAdapter<String> adapter_chexing;
    private Sql dao = Sql.getInstance(this);
    ListView area_list;


    ImageView rebreak;
    Spinner mudidi;
    Spinner yongcheleixing;
    Spinner chexing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daibaojia);
        init();
    }
    private void init(){
        area_list=(ListView)findViewById(R.id.area_listview);
        rebreak=(ImageView)findViewById(R.id.mReturn);

        mudidi=(Spinner)findViewById(R.id.spinner4);
        yongcheleixing=(Spinner)findViewById(R.id.spinner3);
        chexing=(Spinner)findViewById(R.id.spinner);

        rebreak.setOnClickListener(this);

        setlist();
        showlist(mudidi,list_mudidi,adapter_mudidi);
        showlist(yongcheleixing,list_yongcheleixing,adapter_yongcheleixing);
        showlist(chexing,list_chexing,adapter_chexing);

//        rebreak.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        rebreak
//                                .setBackgroundResource(R.mipmap.ease_delete_expression_hei);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        rebreak
//                                .setBackgroundResource(R.mipmap.ease_delete_expression);
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
        SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.daibaojiaself,
                new String[]{"tv_patname","time","textView19","weight","textView20"},
                new int[]{R.id.tv_patname,R.id.mCarCargoTime,R.id.textView19,R.id.weight,R.id.textView20});
        area_list.setAdapter(adapter);

    }
    private void showlist(Spinner title,List<String> list,ArrayAdapter<String> adapter){
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        title.setAdapter(adapter);
        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //Toast.makeText(Maindaibaojia.this, "你点击的是:", Toast.LENGTH_SHORT).show();
                //arg0.setVisibility(View.VISIBLE);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                //arg0.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setlist(){
        list_mudidi.add("目的地");
        list_mudidi.add("北京");
        list_mudidi.add("上海");

        list_yongcheleixing.add("用车类型");
        list_yongcheleixing.add("整车");
        list_yongcheleixing.add("拼车");

        list_chechang.add("车长");
        list_chechang.add("12.5");
        list_chechang.add("18.5");
        list_chechang.add("4.2");

        list_chexing.add("车型");
        list_chexing.add("危险品");
        list_chexing.add("自卸货");
        list_chexing.add("高栏车");
        list_chexing.add("中栏车");
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if(view.getId()==R.id.mReturn){
            intent.setClass(Maindaibaojia.this, MainShouyeActivity.class);
            intent.putExtra("Fragment",0);
            //转向添加页面
            finish();
            startActivity(intent);
        }
    }
    public List<Map<String,Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String,String>> mListTime = dao.getcarstatusbyidandstate(MyDataItem.getInstance().getUserId(),"待报价");
        for(int i=0;i<mListTime.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_patname", mListTime.get(i).get(SqlCarCargodata.CarCargo_Destination));
            map.put("time", "送货时间 "+mListTime.get(i).get(SqlCarCargodata.CarCargo_Time));
            map.put("textView19", mListTime.get(i).get(SqlCarCargodata.CarCargo_Level));
            map.put("weight", mListTime.get(i).get(SqlCarCargodata.CarCargo_Model_car));
            map.put("textView20", mListTime.get(i).get(SqlCarCargodata.CarCargo_Number));
            list.add(map);
        }
        return list;
    }
}
