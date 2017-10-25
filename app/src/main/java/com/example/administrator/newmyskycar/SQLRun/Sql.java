package com.example.administrator.newmyskycar.SQLRun;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newmyskycar.MyData.SqlCarCargodata;
import com.example.administrator.newmyskycar.MyData.SqlFrienddata;
import com.example.administrator.newmyskycar.MyData.SqlMydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-8-29 0029.
 */

public class Sql {
    private SQLiteDatabase db;
    private SqlMydata msqlmydata;
    private static Sql mrecordsDao;
    private static Activity activity;
    private SqlFrienddata msqlfrienddata;
    private SqlCarCargodata msqlcarcargodata;

    public Sql(Activity activity) {
        msqlmydata = new SqlMydata(activity);
        msqlfrienddata = new SqlFrienddata(activity);
        msqlcarcargodata = new SqlCarCargodata(activity);
    }

    public static Sql getInstance(Activity a) {
        if (null == mrecordsDao) {
            synchronized (Sql.class) {
                if (null == mrecordsDao) {
                    mrecordsDao = new Sql(a);
                }
            }
        }
        activity = a;
        return mrecordsDao;
    }

    public long setFriendAddress(String friend_Name, String friend_Phone, String id, String friend_state) {
        db = msqlfrienddata.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SqlFrienddata.USER_NAMEID, id);
        cv.put(SqlFrienddata.Friend_Name, friend_Name);
        cv.put(SqlFrienddata.Friend_Phone, friend_Phone);
        cv.put(SqlFrienddata.Friend_State, friend_state);
        long row = db.insert(SqlFrienddata.Friend_DATA, null, cv);
        return row;
    }

    //验证用户登录
    public Integer checkLogin(String username, String password) {
        Integer result = 0;
        String sql = "select * from " + SqlMydata.USER_DATA + " where " + SqlMydata.USER_NAME + "='" + username + "' and " + SqlMydata.USER_PASSWOED + "='" + password + "'";
        try {
            db = msqlmydata.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);
            result = cursor.getCount();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            db.close();
            msqlmydata.close();
        }
    }

    //获取UserID
    public String SeachUserID(String username, String userpassword) {
        Map<String, String> userMap = new HashMap<String, String>();
        String sql = "SELECT * FROM " + SqlMydata.USER_DATA + " WHERE " + SqlMydata.USER_NAME + "='" + username + "' AND " + SqlMydata.USER_PASSWOED + "='" + userpassword + "'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return null;
        }
        do {
            //moveToNext()移动光标到下一行
            String userid = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAMEID));
            userMap.put(SqlMydata.USER_NAMEID, userid);
        } while (cursor.moveToNext());
        return userMap.get(SqlMydata.USER_NAMEID).toString();
    }

    //验证用户名是否重复
    public boolean CheckUsername(String username) {
        Map<String, String> userMap = new HashMap<String, String>();
        String sql = "SELECT * FROM " + SqlMydata.USER_DATA + " WHERE " + SqlMydata.USER_NAME + "='" + username + "'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return true;
        }
        do {
            //moveToNext()移动光标到下一行
            String name = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAME));
            userMap.put(SqlMydata.USER_NAME, name);
        } while (cursor.moveToNext());
        if (userMap.get(SqlMydata.USER_NAME).toString().equals(username)) {
            return false;
        }
        return true;
    }

    //验证Userid是否重复
    public boolean CheckUserid(String userid) {
        Map<String, String> userMap = new HashMap<String, String>();
        String sql = "SELECT * FROM " + SqlMydata.USER_DATA + " WHERE " + SqlMydata.USER_NAMEID + "='" + userid + "'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return true;
        }
        do {
            //moveToNext()移动光标到下一行
            String id = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAMEID));
            userMap.put(SqlMydata.USER_NAMEID, id);
        } while (cursor.moveToNext());
        if (userMap.get(SqlMydata.USER_NAMEID).toString().equals(userid)) {
            return false;
        }
        return true;
    }

    //获取好友信息List
    public List<Map<String, String>> getFriendList(String userid) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String sql = "SELECT * FROM " + SqlFrienddata.Friend_DATA + " WHERE " + SqlFrienddata.USER_NAMEID + "='" + userid + "'";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return list;
        }
        do {
            //moveToNext()移动光标到下一行
            Map<String, String> friendMap = new HashMap<String, String>();
            String friendname = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Name));
            String friendphone = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Phone));
            String friendstate = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));

            friendMap.put(SqlFrienddata.Friend_Name, friendname);
            friendMap.put(SqlFrienddata.Friend_Phone, friendphone);
            friendMap.put(SqlFrienddata.Friend_State, friendstate);
            list.add(friendMap);
        } while (cursor.moveToNext());
        return list;
    }

    //获取空闲状态好友信息List
    public List<Map<String, String>> getFreeFriendList(String userid) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        String sql = "SELECT * FROM " + SqlFrienddata.Friend_DATA + " WHERE " + SqlFrienddata.USER_NAMEID + "='" + userid + "' And " + SqlFrienddata.Friend_State + "='空闲'";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return list;
        }
        do {
            Map<String, String> friendMap = new HashMap<String, String>();
            //moveToNext()移动光标到下一行
            String friendname = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Name));
            String friendphone = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Phone));
            String friendstate = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));

            friendMap.put(SqlFrienddata.Friend_Name, friendname);
            friendMap.put(SqlFrienddata.Friend_Phone, friendphone);
            friendMap.put(SqlFrienddata.Friend_State, friendstate);
            list.add(friendMap);
        } while (cursor.moveToNext());
        return list;
    }

    //获取Username为输入值得List
    public List<Map<String, String>> getSeachFriendList(String userid, String friendname) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String sql = "SELECT * FROM " + SqlFrienddata.Friend_DATA + " WHERE " + SqlFrienddata.USER_NAMEID + "='" + userid + "' AND " + SqlFrienddata.Friend_Name + "='" + friendname + "'";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return null;
        }
        do {
            Map<String, String> userMap = new HashMap<String, String>();
            //moveToNext()移动光标到下一行
            String _friendname = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Name));
            String friendphone = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_Phone));
            String friendstate = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));

            userMap.put(SqlFrienddata.Friend_Name, _friendname);
            userMap.put(SqlFrienddata.Friend_Phone, friendphone);
            userMap.put(SqlFrienddata.Friend_State, friendstate);
            list.add(userMap);
        } while (cursor.moveToNext());
        return list;
    }

    //获取状态为state的车货信息List
    public List<Map<String, String>> getcarstatusbyidandstate(String userid, String state) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String sql = "SELECT * FROM " + SqlCarCargodata.CarCargo_DATA + " WHERE " + SqlCarCargodata.USER_NAMEID + "='" + userid + "' And " + SqlCarCargodata.CarCargo_State + "='" + state + "'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return list;
        }
        do {
            Map<String, String> truckMap = new HashMap<String, String>();
            //moveToNext()移动光标到下一行
            String Destination = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Destination));
            String Time = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Time));
            String Modelcar = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Model_car));
            String longcar = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Long_car));
            String Level = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Level));
            String Number = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Number));
            String State = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_State));
            String Price = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Price));
            String Friend = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Friend));

            truckMap.put(SqlCarCargodata.CarCargo_Destination, Destination);
            truckMap.put(SqlCarCargodata.CarCargo_Time, Time);
            truckMap.put(SqlCarCargodata.CarCargo_Model_car, Modelcar);
            truckMap.put(SqlCarCargodata.CarCargo_Long_car, longcar);
            truckMap.put(SqlCarCargodata.CarCargo_Level, Level);
            truckMap.put(SqlCarCargodata.CarCargo_Number, Number);
            truckMap.put(SqlCarCargodata.CarCargo_State, State);
            truckMap.put(SqlCarCargodata.CarCargo_Price, Price);
            truckMap.put(SqlCarCargodata.CarCargo_Friend, Friend);

            list.add(truckMap);
        } while (cursor.moveToNext());
        return list;
    }

    //获取username，userpassword验证后的List
    public List<Map<String, String>> getMyDataList(String username, String userpassword) {
        List list = new ArrayList<Map<String, String>>();
        String sql = "SELECT * FROM " + SqlMydata.USER_DATA + " WHERE " + SqlMydata.USER_NAME + "='" + username + "' AND " + SqlMydata.USER_PASSWOED + "='" + userpassword + "'";
        db = msqlmydata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return null;
        }
        do {
            Map<String, String> userMap = new HashMap<String, String>();
            //moveToNext()移动光标到下一行
            String userid = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_ID));
            String _username = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_NAME));
            String _userpassword = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_PASSWOED));
            String userphone = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_PHONE));
            String userrealname = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_REALNAME));
            String usercity = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_CITY));
            String useraddress = cursor.getString(cursor.getColumnIndex(SqlMydata.USER_ADDRESS));

            userMap.put(SqlMydata.USER_ID, userid);
            userMap.put(SqlMydata.USER_NAME, _username);
            userMap.put(SqlMydata.USER_PASSWOED, _userpassword);
            userMap.put(SqlMydata.USER_PHONE, userphone);
            userMap.put(SqlMydata.USER_REALNAME, userrealname);
            userMap.put(SqlMydata.USER_CITY, usercity);
            userMap.put(SqlMydata.USER_ADDRESS, useraddress);
            list.add(userMap);
        } while (cursor.moveToNext());
        return list;
    }

    //根据number返回对应条目的所有信息
    public List<Map<String, String>> seachOncList(String number) {
        List list = new ArrayList();
        String sql = "SELECT * FROM " + SqlCarCargodata.CarCargo_DATA + " WHERE " + SqlCarCargodata.CarCargo_Number + "='No." + number + "'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst() == false) {
            return null;
        }

        do {
            //moveToNext()移动光标到下一行
            Map<String, String> carMap = new HashMap<String, String>();
            String userId = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.USER_NAMEID));
            String Destination = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Destination));
            String Time = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Time));
            String Model_car = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Model_car));
            String Long_car = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Long_car));
            String Level = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Level));
            String Number = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Number));
            String State = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_State));
            String Price = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Price));
            String Friend = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Friend));

            carMap.put(SqlCarCargodata.USER_NAMEID, userId);
            carMap.put(SqlCarCargodata.CarCargo_Destination, Destination);
            carMap.put(SqlCarCargodata.CarCargo_Time, Time);
            carMap.put(SqlCarCargodata.CarCargo_Model_car, Model_car);
            carMap.put(SqlCarCargodata.CarCargo_Long_car, Long_car);
            carMap.put(SqlCarCargodata.CarCargo_Level, Level);
            carMap.put(SqlCarCargodata.CarCargo_Number, Number);
            carMap.put(SqlCarCargodata.CarCargo_State, State);
            carMap.put(SqlCarCargodata.CarCargo_Price, Price);
            carMap.put(SqlCarCargodata.CarCargo_Friend, Friend);
            list.add(carMap);
        } while (cursor.moveToNext());
        return list;
    }
    //根据参数userid和friendname 查找对应条目的Friend_state值若和friendstate相同则直接返回true否则修改Friend_state值为friendstate，修改成功返回true失败则返回false
    public boolean ChangeFriendState(String userid, String friendname, String friendstate) {
        Map<String, String> userMap = new HashMap<String, String>();
        List list = new ArrayList();
        String sql = "SELECT * FROM " + SqlFrienddata.Friend_DATA + " WHERE " + SqlFrienddata.USER_NAMEID + "='" + userid + "' AND " + SqlFrienddata.Friend_Name + "='"+friendname+"'";
        String sql2 = "Update " + SqlFrienddata.Friend_DATA + " SET " + SqlFrienddata.Friend_State + " ='"+ friendstate+"' WHERE " + SqlFrienddata.USER_NAMEID + "='" + userid + "' AND " + SqlFrienddata.Friend_Name + "='"+ friendname+"'";
        db = msqlfrienddata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Cursor cursor2 = db.rawQuery(sql2,null);
        if (cursor.moveToFirst() == false) {
            return false;
        }
        do{
            //moveToNext()移动光标到下一行
            String friendstate2 = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));
            if (friendstate2.equals(friendstate)) {
                return true;
            } else {
                if (cursor2.moveToFirst() == false) {
                    return false;
                }
                do{
                    //moveToNext()移动光标到下一行
                    String friendstate3 = cursor.getString(cursor.getColumnIndex(SqlFrienddata.Friend_State));
                    if (friendstate3.equals(friendstate)) {
                        return true;
                    } else {
                        return false;
                    }
                }while(cursor2.moveToNext());
            }
        }while(cursor.moveToNext());
    }
    //查找车货状态表根据参数userid和number查找对应条目的State值若和carcargostate相同则直接返回true否则修改State值为carcargostate，修改成功返回true失败则返回false
    public boolean ChangeCarCargoState(String userid,String number,String carcargostate){
        List list=new ArrayList();
        String sql="SELECT * FROM " + SqlCarCargodata.CarCargo_DATA + " WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        String sql2="Update " + SqlCarCargodata.CarCargo_DATA +" SET "+SqlCarCargodata.CarCargo_State+"='"+carcargostate+"' WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        Cursor cursor2 = db.rawQuery(sql2, null);
        if (cursor.moveToFirst() == false)
        {
            return false;
        }
        do{
            //moveToNext()移动光标到下一行
            String State = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_State));
            if(State.equals(carcargostate))
            {
                return true;
            }
            else {
                if (cursor2.moveToFirst() == false)
                {
                    return false;
                }
                do{
                    //moveToNext()移动光标到下一行
                    String State2 = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_State));
                    if(State2.equals(carcargostate))
                    {
                        return true;
                    }
                    else {
                        return false;
                    }
                } while (cursor2.moveToNext());
            }
        }while(cursor.moveToNext());
    }
    //根据参数userid和number查找对应条目的Price值若和carcargoprice相同则直接返回true否则修改Price值为carcargoprice，修改成功返回true失败则返回false
    public boolean ChangeCarCargoPrice(String userid,String number,String carcargoprice){
        List list=new ArrayList();
        String sql="SELECT * FROM " + SqlCarCargodata.CarCargo_DATA + " WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        String sql2="Update " + SqlCarCargodata.CarCargo_DATA +" SET "+SqlCarCargodata.CarCargo_Price+"='"+carcargoprice+"' WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        Cursor cursor2 = db.rawQuery(sql2, null);
        if (cursor.moveToFirst() == false) {
            return false;
        }
        do{
            //moveToNext()移动光标到下一行
            String Price = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Price));
            if(Price.equals(carcargoprice)) {
                return true;
            }
            else {
                if (cursor2.moveToFirst() == false) {
                    return false;
                }
                do{
                    //moveToNext()移动光标到下一行
                    String Price2 = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Price));
                    if(Price2.equals(carcargoprice)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }while(cursor2.moveToNext());
            }
        }while(cursor.moveToNext());
    }
    //查找车货状态表根据参数userid和number查找对应条目的Friend值若和carcargofriend相同则直接返回true否则修改Friend值为carcargofriend，修改成功返回true失败则返回false
    public boolean ChangeCarCargoFriend (String userid,String number,String carcargofriend){
        List list=new ArrayList();
        String sql="SELECT * FROM " + SqlCarCargodata.CarCargo_DATA + " WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        String sql2="Update " + SqlCarCargodata.CarCargo_DATA +" SET " + SqlCarCargodata.CarCargo_Friend + "='"+carcargofriend+"' WHERE "+SqlCarCargodata.USER_NAMEID+"='"+userid+"' AND "+SqlCarCargodata.CarCargo_Number+"='"+number+"'";
        db = msqlcarcargodata.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        Cursor cursor2 = db.rawQuery(sql2, null);
        if (cursor.moveToFirst() == false)
        {
            return false;
        }
        do{
            //moveToNext()移动光标到下一行
            String Friend = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Friend));
            if(Friend.equals(carcargofriend))
            {
                return true;
            }
            else {
                if (cursor2.moveToFirst() == false)
                {
                    return false;
                }
                do{
                    //moveToNext()移动光标到下一行
                    String Friend2 = cursor.getString(cursor.getColumnIndex(SqlCarCargodata.CarCargo_Friend));
                    if(Friend2.equals(carcargofriend))
                    {
                        return true;
                    }
                    else {
                        return false;
                    }
                }while(cursor2.moveToNext());
            }
        }while(cursor.moveToNext());
    }
}
