package com.shoppingcart.netUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hardip Gol on 3/01/2019.
 */

public class MyPreferences {

    public static String id = "id",name="name",email="email",mobile="mobile_no",refreshedtoken="refreshedtoken";

    Context context;
    public static String PreferenceName = "Shopping";
    public static String cid = "cid";
    public MyPreferences(Context context) {
        this.context = context;
    }

    String value = "";

    public String getPreferences(String key) {
        value = "";
        try {
            SharedPreferences channel = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            value = channel.getString("" + key, "").toString();
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }

        return value;
    }

    public void setPreferences(String key, String value) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString("" + key, value);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* todo tutoral praferance */

    public static String PreferenceTutoralName = "Shopping_tutoral";

    public static String dashboard_tutorial = "dashboard_tutorial";
    public static String notification_tutorial = "notification_tutorial";

    public String getTutoralPreferences(String key) {
        String value = null;
        try {
            SharedPreferences channel = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            value = channel.getString("" + key, "").toString();
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }

        return value;
    }

    public void setTutoralPreferences(String key, String value) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString("" + key, value);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearTutoralPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
