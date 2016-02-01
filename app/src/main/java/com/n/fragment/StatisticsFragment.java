package com.n.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.n.adapter.StatisticsRecyclerAdapter;
import com.n.meallog.R;
import com.n.meallog.StatisticsData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {
    private RecyclerView recyclerView;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistics, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.statistics_recycler_view);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<StatisticsData> list = new ArrayList<>();

        list.add(new StatisticsData("a", 400));
        list.add(new StatisticsData("b", 257));
        list.add(new StatisticsData("c", 189));
        list.add(new StatisticsData("d", 56));
        list.add(new StatisticsData("e", 15));

        StatisticsRecyclerAdapter adapter = new StatisticsRecyclerAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
