package com.ahao.myapplication.banner;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ahao.myapplication.R;

import java.util.List;
import java.util.Random;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private List<Entity> data;

    private Context context;

    private Random random = new Random();

    private float itemWidth = 1.0f;
    private float ratio = 1.0f;  // 宽高比

    private DisplayMetrics displayMetrics;

    public BannerAdapter(List<Entity> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_banner_item, parent, false);
        final BannerViewHolder holder = new BannerViewHolder(view);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onclick :" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                listener.onClick();
            }
        });
        return holder;
    }

    public BannerAdapter setListener(onClickListener listener) {
        this.listener = listener;
        return this;
    }

    onClickListener listener;

    public interface onClickListener {
        void onClick();
    }

    @Override
    public void onBindViewHolder(@NonNull final BannerViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getName());
        holder.textView.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        layoutParams.width = (int) (displayMetrics.widthPixels * itemWidth);
        layoutParams.height = (int) (layoutParams.width * ratio);
//        if (data.get(0).isOpen()) {
//            holder.tvMeddle.setVisibility(View.VISIBLE);
//        } else {
//            holder.tvMeddle.setVisibility(View.GONE);
//        }
//        holder.tvMeddle.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView tvMeddle;
        RelativeLayout root;

        public BannerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            tvMeddle = itemView.findViewById(R.id.tvMeddle);
            root = itemView.findViewById(R.id.root);
        }
    }

    public float getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(float itemWidth) {
        this.itemWidth = itemWidth;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
