package com.example.administrator.newmyskycar.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by zhanglei on 2017/8/2.
 */

public class ProgressUtil {
    private static ProgressDialog progressDialog;

    /**
     * 显示正在加载的进度条
     */
    public static void showProgress(Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("拼命加载中...请稍后...");
        progressDialog.setCanceledOnTouchOutside(false);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    public static void showProgress(Context context, String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    public static ProgressDialog createProgress(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        return progressDialog;
    }


    /**
     * 隐藏正在加载的进度条
     */
    public static void dismissProgress() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
