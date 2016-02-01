package com.n.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.n.meallog.LoginActivity;
import com.n.meallog.R;
import com.n.view.LockableViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends Fragment implements View.OnClickListener{


    public JoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_join, container, false);

        Button complete = (Button) v.findViewById(R.id.join_complete);
        Button cancel = (Button) v.findViewById(R.id.join_cancel);
        complete.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.join_complete:
                pager.setCurrentItem(0);
                break;
            case R.id.join_cancel:
                pager.setCurrentItem(0);
                break;
        }
    }
}
