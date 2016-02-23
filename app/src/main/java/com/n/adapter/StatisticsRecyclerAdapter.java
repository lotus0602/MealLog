package com.n.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.n.meallog.R;
import com.n.meallog.StatisticsData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by N on 2016-01-25.
 */
public class StatisticsRecyclerAdapter
        extends RecyclerView.Adapter<StatisticsRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<StatisticsData> items;
    private int standardNum;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imageView;
        public ImageView bar;
        public TextView showCountView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.item_statistics_image);
            bar = (ImageView) itemView.findViewById(R.id.item_statistics_bar);
            showCountView = (TextView) itemView.findViewById(R.id.item_statistics_count);
        }
    }

    public StatisticsRecyclerAdapter(Context c, ArrayList<StatisticsData> list) {
        context = c;
        items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_statistics_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams params =  holder.bar.getLayoutParams();

        if (position == 0) {
            standardNum = items.get(position).getNum();
            params.width = 300;
        } else {
            params.width = 300 * items.get(position).getNum() / standardNum;
        }
        holder.bar.setLayoutParams(params);
        holder.showCountView.setText(" " + items.get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
