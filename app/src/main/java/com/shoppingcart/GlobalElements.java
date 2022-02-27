package com.shoppingcart;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * Created by Hardip on 28-12-2018.
 */
public class GlobalElements extends Application {


    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (info != null) {
                if (info.isConnected()) {
                    return true;
                } else {
                    NetworkInfo info1 = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (info1.isConnected()) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static void showDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        // Set Dialog Title
        alertDialog.setTitle("Internet Connection");
        // Set Dialog Message
        alertDialog.setMessage("Please check your internet connection ..");
        // Set OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // Show Alert Message
        alertDialog.show();
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    public static String getDate() {
        Calendar newCalendar = Calendar.getInstance();
        SimpleDateFormat cu_date_time = new SimpleDateFormat("dd-MM-yyyy");
        String datetime = cu_date_time.format(newCalendar.getTime());
        return datetime;
    }


    public static boolean getVersionCheck() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static int randInt(int min, int max) {
        // Usually this should be a field rather than a method variable so
        // that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static boolean compareDateAfter(String fromDate) {
        boolean success = false;
        try {
            if (fromDate.equals("")) {
                return false;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //Date date1 = sdf.parse("31-03-2016");
                Date date1 = sdf.parse("" + fromDate);
                Date date2 = sdf.parse("" + sdf.format(new Date()));
                if (date1.before(date2)) {
                    success = true;
                } else {
                    success = false;
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            return success;
        }
        return success;
    }
}
