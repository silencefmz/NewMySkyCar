package com.example.administrator.newmyskycar.Item;

/**
 * Created by Administrator on 2017-10-20 0020.
 */

public class MyDataItem {
    private static MyDataItem instance = null;// 单例的消息管理器
    public static MyDataItem getInstance() {
        if (instance == null) {
            instance = new MyDataItem();
        }
        return instance;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getUserPassword() {
        return UserPassword;
    }
    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
    public String getUserId() {
        return UserId;
    }
    public void setUserId(String userId) {
        UserId = userId;
    }
    private String UserName="";
    private String UserPassword="";
    private String UserId="";
}
