package com.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private ArrayList<ProductModel> data;
    private Context context;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public OrderItemAdapter(Context context, ArrayList<ProductModel> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_order_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(data.get(i).getName());
        viewHolder.price.setText("Rs." + data.get(i).getPrice());
        viewHolder.qty.setText("Qty : " + data.get(i).getQty());

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
        @BindView(R.id.qty)
        TextView qty;
        @BindView(R.id.price)
        TextView price;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
