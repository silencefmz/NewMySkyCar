package com.example.administrator.newmyskycar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newmyskycar.Item.MyDataItem;
import com.example.administrator.newmyskycar.MainShouyeActivity;
import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.R;
import com.example.administrator.newmyskycar.SQLRun.Sql;
import com.example.administrator.newmyskycar.fragment.YundanFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-23 0023.
 */

public class lvButtonAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView tv_patname_yizhongbiao;
        TextView mCarCargoTime_yizhongbiao;
        TextView textView19_yizhongbiao;
        TextView weight_yizhongbiao;
        TextView textView20_yizhongbiao;
        TextView textView6_yizhongbiao;
        Button lianxiren;
    }

    private List<Map<String,Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String [ ] keyString;
    private int [ ] valueViewID;
    private buttonViewHolder holder;
    GroupAdapter groupAdapter;

    public lvButtonAdapter(Context c, List<Map<String, Object>> appList, int resource,
                           String [ ] from , int [ ] to) {
        mAppList = appList;
        mContext = c;
        mInflater = ( LayoutInflater) mContext. getSystemService( Context . LAYOUT_INFLATER_SERVICE) ;
        keyString = new String [ from . length ] ;
        valueViewID = new int [ to. length ] ;
        System . arraycopy ( from , 0, keyString, 0, from . length ) ;
        System . arraycopy ( to, 0, valueViewID, 0, to. length ) ;
    }

    @Override
    public int getCount ( ) {
        return mAppList. size ( ) ;
    }

    @Override
    public Object getItem ( int position ) {
        return mAppList. get ( position ) ;
    }

    @Override
    public long getItemId( int position ) {
        return position ;
    }

    public void removeItem ( int position ) {
        mAppList. remove ( position ) ;
        this . notifyDataSetChanged( ) ;
    }

    @Override
    public View getView (int position , View convertView, ViewGroup parent ) {
        if ( convertView!= null ) {
            holder = ( buttonViewHolder) convertView. getTag ( ) ;
        } else {
            convertView = mInflater. inflate (R.layout.linear_yizhongbiaoself, null ) ;
            holder = new buttonViewHolder( ) ;
            holder.tv_patname_yizhongbiao=(TextView)convertView.findViewById(valueViewID[0]);
            holder.mCarCargoTime_yizhongbiao=(TextView)convertView.findViewById(valueViewID[1]);
            holder.textView19_yizhongbiao=(TextView)convertView.findViewById(valueViewID[2]);
            holder.weight_yizhongbiao=(TextView)convertView.findViewById(valueViewID[3]);
            holder.textView20_yizhongbiao=(TextView)convertView.findViewById(valueViewID[4]);
            holder.textView6_yizhongbiao=(TextView)convertView.findViewById(valueViewID[5]);
            holder. lianxiren = (Button) convertView. findViewById( valueViewID[6] ) ;
            convertView. setTag( holder) ;
        }

        Map < String , Object > appInfo = mAppList. get ( position ) ;
        if ( appInfo!= null ) {
            holder.tv_patname_yizhongbiao.setText((String)appInfo.get(keyString[0]));
            holder.mCarCargoTime_yizhongbiao.setText((String)appInfo.get(keyString[1]));
            holder.textView19_yizhongbiao.setText((String)appInfo.get(keyString[2]));
            holder.weight_yizhongbiao.setText((String)appInfo.get(keyString[3]));
            holder.textView20_yizhongbiao.setText((String)appInfo.get(keyString[4]));
            holder.textView6_yizhongbiao.setText((String)appInfo.get(keyString[5]));
            holder. lianxiren. setOnClickListener( new lvButtonListener( position ) ) ;
        }
        return convertView;
    }

    class lvButtonListener implements View.OnClickListener {
        private int position ;
        private PopupWindow popupWindow;
        private View view;
        private ListView lv_group;
        private List<String> groups;


        lvButtonListener( int pos) {
            position = pos;
        }

        @Override
        public void onClick( View v) {
            int vid=v.getId();
            if ( vid == holder.lianxiren.getId( ))
                showWindow(v,position);
        }
        private void showWindow(final View parent, final int parent_position) {
            final Sql dao = Sql.getInstance((Activity) parent.getContext());
            if (popupWindow == null) {
                LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = layoutInflater.inflate(R.layout.group_list, null);

                lv_group = (ListView) view.findViewById(R.id.lvGroup);
                // 加载数据
                List<Map<String,String>> mListTime=dao.getFreeFriendList(MyDataItem.getInstance().getUserId());
                groups = new ArrayList<String>();
                for(int i=0;i<mListTime.size();i++){
                    groups.add(mListTime.get(i).get(SqlFrienddata.Friend_Name));
                }
                groupAdapter = new GroupAdapter(parent.getContext(),groups);
                lv_group.setAdapter(groupAdapter);
                // 创建一个PopuWidow对象
                popupWindow = new PopupWindow(view, 300, 350);
            }

            // 使其聚集
            popupWindow.setFocusable(true);
            // 设置允许在外点击消失
            popupWindow.setOutsideTouchable(true);

            // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            WindowManager windowManager = (WindowManager)view.getContext().getSystemService(Context.WINDOW_SERVICE);
            // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
            int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                    - popupWindow.getWidth() / 2;
//            Log.i("coder", "xPos:" + xPos);

            popupWindow.showAsDropDown(parent, xPos, 0);

            lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view,
                                        int position, long id) {
                    Toast.makeText(view.getContext(),"安排成功", Toast.LENGTH_LONG).show();
                    Sql dao = Sql.getInstance((Activity) view.getContext());
                    dao.ChangeFriendState(MyDataItem.getInstance().getUserId(),groups.get(position).toString(),"出车");
                    String a=mAppList.get(parent_position).get("textView20_yizhongbiao").toString();
                    dao.ChangeCarCargoFriend(MyDataItem.getInstance().getUserId(),a,groups.get(position).toString());
                    dao.ChangeCarCargoState(MyDataItem.getInstance().getUserId(),a,"运输中");
                    Intent intent = new Intent();
                    intent.setClass(parent.getContext(), MainShouyeActivity.class);
                    intent.putExtra("Fragment",1);
                    //转向添加页面
                    parent.getContext().startActivity(intent);
                    ((Activity) parent.getContext()).finish();
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            });
        }
    }
}
