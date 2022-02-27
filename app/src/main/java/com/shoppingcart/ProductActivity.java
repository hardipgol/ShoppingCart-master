package com.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.shoppingcart.adapter.DashboardAdapter;
import com.shoppingcart.adapter.ProductAdapter;
import com.shoppingcart.custom.DividerItemDecoration;
import com.shoppingcart.custom.EqualSpacingItemDecoration;
import com.shoppingcart.model.ProductModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {
    ArrayList<ProductModel> data = new ArrayList<>();
    ProductAdapter adapter;
    String cid = "";
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        cid = intent.getStringExtra("id");
        getSupportActionBar().setTitle("" + intent.getStringExtra("name"));

        if (cid.equals("1")) {
            getFruit();
        } else if (cid.equals("2")){
            getElectronic();
        }else if (cid.equals("3")){
            getToy();
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

    public void getFruit() {

        ProductModel da = new ProductModel();
        da.setId("1");
        da.setCid(cid);
        da.setName("Apple");
        da.setImage_path("https://image.flaticon.com/icons/png/512/135/135728.png");
        da.setPrice(20);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("2");
        da.setCid(cid);
        da.setName("Banana");
        da.setImage_path("https://image.flaticon.com/icons/png/512/590/590769.png");
        da.setPrice(50);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("3");
        da.setCid(cid);
        da.setName("blueberry");
        da.setImage_path("https://image.flaticon.com/icons/png/512/135/135587.png");
        da.setPrice(30);
        da.setQty(1);
        data.add(da);


        da = new ProductModel();
        da.setId("4");
        da.setCid(cid);
        da.setName("Grapes");
        da.setImage_path("https://image.flaticon.com/icons/png/512/167/167241.png");
        da.setPrice(70);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("5");
        da.setCid(cid);
        da.setName("Mango");
        da.setImage_path("https://image.flaticon.com/icons/png/512/700/700804.png");
        da.setPrice(450);
        da.setQty(1);
        data.add(da);

        adapter = new ProductAdapter(ProductActivity.this, data);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductActivity.this);
        recycleView.addItemDecoration(itemDecoration);
        recycleView.setLayoutManager(new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
    }

    public void getElectronic() {

        ProductModel da = new ProductModel();
        da.setId("6");
        da.setCid(cid);
        da.setName("Microwave oven");
        da.setImage_path("https://image.flaticon.com/icons/png/512/150/150384.png");
        da.setPrice(450);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("7");
        da.setCid(cid);
        da.setName("Refrigerators");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1444/1444696.png");
        da.setPrice(8000);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("8");
        da.setCid(cid);
        da.setName("Vacuum cleaner");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1169/1169368.png");
        da.setPrice(780);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("9");
        da.setCid(cid);
        da.setName("Electric water heater");
        da.setImage_path("https://image.flaticon.com/icons/png/512/948/948041.png");
        da.setPrice(250);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("10");
        da.setCid(cid);
        da.setName("Fan");
        da.setImage_path("https://image.flaticon.com/icons/png/512/188/188886.png");
        da.setPrice(1250);
        da.setQty(1);
        data.add(da);

        adapter = new ProductAdapter(ProductActivity.this, data);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductActivity.this);
        recycleView.addItemDecoration(itemDecoration);
        recycleView.setLayoutManager(new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
    }

    public void getToy() {

        ProductModel da = new ProductModel();
        da.setId("11");
        da.setCid(cid);
        da.setName("Lego");
        da.setImage_path("https://image.flaticon.com/icons/png/512/501/501615.png");
        da.setPrice(120);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("12");
        da.setCid(cid);
        da.setName("Gummy bear");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1035/1035108.png");
        da.setPrice(120);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("13");
        da.setCid(cid);
        da.setName("Puppy");
        da.setImage_path("https://image.flaticon.com/icons/png/512/480/480443.png");
        da.setPrice(350);
        da.setQty(1);
        data.add(da);


        da = new ProductModel();
        da.setId("14");
        da.setCid(cid);
        da.setName("Stickers");
        da.setImage_path("https://image.flaticon.com/icons/png/512/1411/1411186.png");
        da.setPrice(25);
        da.setQty(1);
        data.add(da);

        da = new ProductModel();
        da.setId("15");
        da.setCid(cid);
        da.setName("Doll");
        da.setImage_path("https://image.flaticon.com/icons/png/512/191/191563.png");
        da.setPrice(160);
        da.setQty(1);
        data.add(da);

        adapter = new ProductAdapter(ProductActivity.this, data);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProductActivity.this);
        recycleView.addItemDecoration(itemDecoration);
        recycleView.setLayoutManager(new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.VERTICAL, false));
        recycleView.setAdapter(adapter);
    }
}
