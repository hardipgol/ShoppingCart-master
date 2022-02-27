package com.shoppingcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.shoppingcart.ProductActivity;
import com.shoppingcart.R;
import com.shoppingcart.model.DashboardModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hardip Gol on 10/10/2016.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    private ArrayList<DashboardModel> data;
    private Context context;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();

    public DashboardAdapter(Context context, ArrayList<DashboardModel> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_dashboard, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(data.get(i).getName());

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

        viewHolder.list_dashboard_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent g = new Intent(context, ProductActivity.class);
                    g.putExtra("id", "" + data.get(i).getId());
                    g.putExtra("name", "" + data.get(i).getName());
                    context.startActivity(g);
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
        @BindView(R.id.list_dashboard_name)
        TextView name;
        @BindView(R.id.list_dashboard_img)
        ImageView img;
        @BindView(R.id.list_dashboard_main)
        LinearLayout list_dashboard_main;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
