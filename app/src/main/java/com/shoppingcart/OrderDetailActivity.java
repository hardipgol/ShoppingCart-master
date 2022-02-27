package com.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.shoppingcart.adapter.OrderItemAdapter;
import com.shoppingcart.adapter.ProductAdapter;
import com.shoppingcart.custom.DividerItemDecoration;
import com.shoppingcart.model.OrderHistoryModel;
import com.shoppingcart.model.ProductModel;
import com.shoppingcart.netUtils.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends AppCompatActivity {

    OrderHistoryModel model = new OrderHistoryModel();
    ArrayList<ProductModel> data = new ArrayList<>();
    OrderItemAdapter adapter;
    DBHelper db;
    @BindView(R.id.order_id)
    TextView orderId;
    @BindView(R.id.order_status)
    TextView orderStatus;
    @BindView(R.id.order_amount)
    TextView orderAmount;
    @BindView(R.id.order_date)
    TextView orderDate;
    @BindView(R.id.delivery_date)
    TextView deliveryDate;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        db = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Detail");
        recycleView.setNestedScrollingEnabled(false);

        try {
            Bundle bundle = getIntent().getExtras();
            model = (OrderHistoryModel) bundle.getSerializable("data");
            orderId.setText("#" + model.getId());
            orderAmount.setText("" + model.getTotal());
            orderDate.setText("" + model.getOrder_date());
            deliveryDate.setText("" + model.getDelivery_date());

            data = db.getCartItemDetail(model.getId());
            adapter = new OrderItemAdapter(OrderDetailActivity.this, data);
            recycleView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this, LinearLayoutManager.VERTICAL, false));
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(OrderDetailActivity.this);
            recycleView.addItemDecoration(itemDecoration);
            recycleView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
