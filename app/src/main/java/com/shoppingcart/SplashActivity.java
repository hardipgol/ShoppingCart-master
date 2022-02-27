package com.shoppingcart;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.shoppingcart.netUtils.DBHelper;
import com.shoppingcart.netUtils.RuntimePermissionsActivity;

public class SplashActivity extends RuntimePermissionsActivity {

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db = new DBHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.super.requestAppPermissions(new
                                String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, R.string.runtime_permissions_txt
                        , 20);

            }
        }, 1000);

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        db.createDatabase();
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
