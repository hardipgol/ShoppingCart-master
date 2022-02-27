package com.shoppingcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shoppingcart.OrderDetailActivity;
import com.shoppingcart.R;
import com.shoppingcart.model.OrderHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hardip Gol on 10/10/2016.
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    private ArrayList<OrderHistoryModel> data;
    private Context context;

    public OrderHistoryAdapter(Context context, ArrayList<OrderHistoryModel> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_order_history, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.orderId.setText("#" + data.get(i).getId());
        viewHolder.orderAmount.setText("" + data.get(i).getTotal());
        viewHolder.orderDate.setText("" + data.get(i).getOrder_date());
        viewHolder.deliveryDate.setText("" + data.get(i).getDelivery_date());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
