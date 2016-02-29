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
import com.n.model.MealInfo;
import com.n.net.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by N on 2016-01-13.
 */
public class ShowMealGridAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<MealInfo> infos;

    public ShowMealGridAdapter(Context c) {
        context = c;
        infos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return infos == null ? 0 : infos.size();
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
                .load(ServiceGenerator.API_BASE_URL + "meal/" + infos.get(position).getImagePath())
                .fit()
                .centerCrop()
                .into(viewHolder.thumbnail);
        viewHolder.title.setText(infos.get(position).getTitle());

        Log.d("IN getView!!!!!", position + " : "
                + ServiceGenerator.API_BASE_URL + "meal/" + infos.get(position).getImagePath());

        return convertView;
    }

    private static class ViewHolder{
        ImageView thumbnail;
        TextView title;
    }

    public void setInfos(ArrayList<MealInfo> mealInfos) {
        infos = mealInfos;
        Log.d("In setInfos!!!!!", "SIZE : " + infos.size());
    }
}
