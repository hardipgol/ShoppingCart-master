package com.shoppingcart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matrixxun.starry.badgetextview.MenuItemBadge;
import com.shoppingcart.adapter.DashboardAdapter;
import com.shoppingcart.custom.EqualSpacingItemDecoration;
import com.shoppingcart.model.DashboardModel;
import com.shoppingcart.netUtils.DBHelper;
import com.shoppingcart.netUtils.MyPreferences;
import com.shoppingcart.tutoral.TapIntroHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DashboardAdapter adapter;
    ArrayList<DashboardModel> data = new ArrayList<>();


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    MyPreferences myPreferences;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        db = new DBHelper(this);
        myPreferences = new MyPreferences(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getCategory();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        try {
            MenuItem menuItemNotification = menu.findItem(R.id.shopping_cart);
            MenuItemBadge.update(this, menuItemNotification, new MenuItemBadge.Builder()
                    .iconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_shopping_cart_white_24dp))
                    .iconTintColor(Color.WHITE)
                    .textBackgroundColor(Color.parseColor("#EF4738"))
                    .textColor(Color.WHITE));
            MenuItemBadge.getBadgeTextView(menuItemNotification).setBadgeCount(db.getCartCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.shopping_cart) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_order_history) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_reset) {
            // Handle the camera action
            try {
                myPreferences.clearTutoralPreferences();
                TapIntroHelper.showDashboardIntro(this, getResources().getColor(R.color.colorPrimary));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getCategory() {
        DashboardModel da = new DashboardModel();
        da.setId("1");
        da.setName("Fruit");
        da.setImage_path("https://image.flaticon.com/icons/png/512/706/706164.png");
        data.add(da);

        da = new DashboardModel();
        da.setId("2");
        da.setName("Electronic");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1055/1055687.png");
        data.add(da);


        da = new DashboardModel();
        da.setId("3");
        da.setName("Toy");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1398/1398098.png");
        data.add(da);

        da = new DashboardModel();
        da.setId("4");
        da.setName("Mobile");
        da.setImage_path("https://image.flaticon.com/icons/png/512/149/149005.png");
        data.add(da);


        da = new DashboardModel();
        da.setId("5");
        da.setName("Bike");
        da.setImage_path("https://image.flaticon.com/icons/png/512/324/324247.png");
        data.add(da);

        da = new DashboardModel();
        da.setId("6");
        da.setName("Laptop");
        da.setImage_path("https://image.flaticon.com/icons/png/512/230/230298.png");
        data.add(da);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        recycleView.addItemDecoration(new EqualSpacingItemDecoration(8));
        adapter = new DashboardAdapter(MainActivity.this, data);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adapter);


        try {
            TapIntroHelper.showDashboardIntro(MainActivity.this, getResources().getColor(R.color.colorPrimary));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
