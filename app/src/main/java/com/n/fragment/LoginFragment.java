package com.n.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.n.meallog.LoginActivity;
import com.n.meallog.MainActivity;
import com.n.meallog.R;
import com.n.view.LockableViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        Button login = (Button) v.findViewById(R.id.login_login_btn);
        Button join = (Button) v.findViewById(R.id.login_join_btn);
        login.setOnClickListener(this);
        join.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.login_login_btn:
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();
                break;
            case R.id.login_join_btn:
                pager.setCurrentItem(1);
                break;
        }
    }
}
