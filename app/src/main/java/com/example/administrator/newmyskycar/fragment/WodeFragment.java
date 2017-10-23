package com.example.administrator.newmyskycar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlMydata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017-10-10 0010.
 */

public class WodeFragment extends Fragment {
    @BindView(R.id.mMyName)
    TextView mMyName;
    @BindView(R.id.mMyPhone)
    TextView mMyPhone;
    Unbinder unbinder;
    private Sql dao = Sql.getInstance(getActivity());

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.linear_wode, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void init(){
        List<Map<String,String>> mListTime = dao.getMyDataList(MyDataItem.getInstance().getUserName(),MyDataItem.getInstance().getUserPassword());
        mMyName.setText(mListTime.get(0).get(SqlMydata.USER_REALNAME).toString());
        mMyPhone.setText(mListTime.get(0).get(SqlMydata.USER_PHONE).toString());
    }
}
