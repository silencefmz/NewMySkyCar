package com.example.administrator.newmyskycar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class SijiFragment extends Fragment {
    @BindView(R.id.mBreatheSetEditText)
    EditText mBreatheSetEditText;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.area_listview)
    ListView areaListview;
    Unbinder unbinder;
    private Sql dao = Sql.getInstance(getActivity());

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_siji, container, false);
        unbinder = ButterKnife.bind(this, view);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.linear_friendeslf,
                new String[]{"mFriendName","mFriendPhone","mFriendState"},
                new int[]{R.id.mFriendName,R.id.mFriendPhone,R.id.mFriendState});
        areaListview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
    }

    public List<Map<String, Object>> getData() {
        List<Map<String,String>> mListTime = dao.getFriendList("11");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(int i=0;i<mListTime.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("mFriendName", mListTime.get(i).get(SqlFrienddata.Friend_Name));
            map.put("mFriendPhone", mListTime.get(i).get(SqlFrienddata.Friend_Phone));
            map.put("mFriendState", mListTime.get(i).get(SqlFrienddata.Friend_State));
            list.add(map);
        }
        return list;
    }
}
