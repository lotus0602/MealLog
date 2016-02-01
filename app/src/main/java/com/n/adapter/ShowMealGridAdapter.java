package com.n.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.n.meallog.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by N on 2016-01-13.
 */
public class ShowMealGridAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<String> list;

    public ShowMealGridAdapter(Context c) {
        context = c;
        list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_grid_meal, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.item_meal_thumbnail);
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_meal_title);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
                .load("file://" + list.get(position))
                .fit()
                .centerCrop()
                .into(viewHolder.thumbnail);

        Log.d("IN getView!!!!!", position + " : " + list.get(position));

        return convertView;
    }

    private static class ViewHolder{
        ImageView thumbnail;
        TextView title;
    }

    public void setList(ArrayList thumbList) {
        list = thumbList;
    }
}
