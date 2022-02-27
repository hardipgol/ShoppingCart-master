package com.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shoppingcart.R;
import com.shoppingcart.model.ProductModel;
import com.shoppingcart.netUtils.DBHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hardip Gol on 10/10/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    DBHelper db;
    private ArrayList<ProductModel> data;
    private Context context;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public ProductAdapter(Context context, ArrayList<ProductModel> data) {
        this.data = data;
        this.context = context;
        db = new DBHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(data.get(i).getName());
        viewHolder.price.setText("Rs." + data.get(i).getPrice());
        viewHolder.qty.setText("" + data.get(i).getQty());

        if (data.get(i).getImage_path().equals("")) {
            viewHolder.img.setImageResource(R.mipmap.ic_launcher);
        } else {
            try {
                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                        .showImageOnFail(R.mipmap.ic_launcher)
                        .cacheInMemory(true)
                        .build();
                imageLoader.displayImage(data.get(i).getImage_path(), viewHolder.img, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(viewHolder.qty.getText().toString()) - 1;
                if (count >= 1) {
                    viewHolder.qty.setText("" + count);
                    data.get(i).setQty(count);
                }
            }
        });

        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(viewHolder.qty.getText().toString()) + 1;
                if (count >= 1) {
                    viewHolder.qty.setText("" + count);
                    data.get(i).setQty(count);
                }
            }
        });

        viewHolder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.addToCart("1", data.get(i).getId(), data.get(i).getName(), data.get(i).getImage_path(), "" + data.get(i).getPrice(), "" + data.get(i).getQty());
                    viewHolder.qty.setText("1");
                    data.get(i).setQty(1);
                    Toast.makeText(context, "Product Add To Cart", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_dashboard_img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.minus)
        ImageView minus;
        @BindView(R.id.qty)
        TextView qty;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.plus)
        ImageView plus;
        @BindView(R.id.add_to_cart)
        TextView addToCart;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
