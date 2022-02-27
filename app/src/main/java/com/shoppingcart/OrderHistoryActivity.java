package com.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.shoppingcart.adapter.OrderHistoryAdapter;
import com.shoppingcart.model.OrderHistoryModel;
import com.shoppingcart.netUtils.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryActivity extends AppCompatActivity {

    DBHelper db;
    ArrayList<OrderHistoryModel> data = new ArrayList<>();
    OrderHistoryAdapter adapter;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        ButterKnife.bind(this);
        db = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order History");

        data = db.getOrderHistory();
        adapter = new OrderHistoryAdapter(OrderHistoryActivity.this, data);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
