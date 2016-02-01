package com.n.fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.n.adapter.ShowMealGridAdapter;
import com.n.meallog.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMealFragment extends Fragment {

    private ArrayList<String> thumbList;

    public ShowMealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_meal, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        thumbList = new ArrayList<>();
        getPhoto();

        GridView gridView = (GridView) getView().findViewById(R.id.show_meal_grid);
        ShowMealGridAdapter gridAdapter = new ShowMealGridAdapter(getView().getContext());

        gridAdapter.setList(thumbList);
        gridView.setAdapter(gridAdapter);
    }

    private void getPhoto() {
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA
        };

        Cursor cursor = MediaStore.Images.Media.query(
                getActivity().getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                "",
                null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC");

        if ((cursor != null) && cursor.moveToFirst()) {
            int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                String path = cursor.getString(dataColumn);
                thumbList.add(path);
                if (thumbList.size() > 10)
                    break;
            } while (cursor.moveToNext());
        }
    }
}
