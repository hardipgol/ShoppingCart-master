package com.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shoppingcart.adapter.CartAdapter;
import com.shoppingcart.custom.DividerItemDecoration;
import com.shoppingcart.model.ProductModel;
import com.shoppingcart.netUtils.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    CartAdapter adapter;
    ArrayList<ProductModel> data = new ArrayList<>();
    DBHelper db;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.cart_qty)
    TextView cartQty;
    @BindView(R.id.cart_amount)
    TextView cartAmount;
    @BindView(R.id.place_order)
    TextView placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        db = new DBHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Cart");


        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.isEmpty()) {
                    Toast.makeText(CartActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.placeOrder(data.get(0).getCid(), cartAmount.getText().toString()) == -1) {
                        Toast.makeText(CartActivity.this, "Database Internal error", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CartActivity.this, "Order Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        data = db.getCartItem();
        adapter = new CartAdapter(CartActivity.this, data);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(CartActivity.this);
        recycleView.addItemDecoration(itemDecoration);
        recycleView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);

        cartDetail();
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

    public void cartDetail() {
        try {
            int qty = 0, totalAmount = 0;
            for (int i = 0; i < data.size(); i++) {
                qty += data.get(i).getQty();
                totalAmount += data.get(i).getQty() * data.get(i).getPrice();
            }
            cartQty.setText("" + qty);
            cartAmount.setText("" + totalAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
