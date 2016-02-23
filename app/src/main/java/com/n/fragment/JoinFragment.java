package com.n.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.n.meallog.LoginActivity;
import com.n.meallog.R;
import com.n.model.JoinResult;
import com.n.net.LoginService;
import com.n.net.ServiceGenerator;
import com.n.view.LockableViewPager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends Fragment implements View.OnClickListener{
    private EditText id;
    private EditText email;
    private EditText pw;
    private EditText pw_again;

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
        id = (EditText) v.findViewById(R.id.join_id);
        email = (EditText) v.findViewById(R.id.join_email);
        pw = (EditText) v.findViewById(R.id.join_pw);
        pw_again = (EditText) v.findViewById(R.id.join_pw_again);

        complete.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        final LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.join_complete:
                LoginService loginService =
                        ServiceGenerator.createService(LoginService.class);
                Map userJoin = new HashMap();
                userJoin.put("signUp_userID", id.getText().toString());
                userJoin.put("signUp_userMail", email.getText().toString());
                userJoin.put("signUp_psw", pw.getText().toString());
                userJoin.put("signUp_pswCheck", pw_again.getText().toString());

                Call<JoinResult> call = loginService.basicJoin(userJoin);
                call.enqueue(new Callback<JoinResult>() {
                    @Override
                    public void onResponse(Call<JoinResult> call, Response<JoinResult> response) {
                        Log.d("Response In Join", "CODE : " + response.code());

                        JoinResult joinResult = response.body();

                        if (joinResult.getResult().equals("JOIN_OK")) {
                            pager.setCurrentItem(0);
                        } else {
                            Toast.makeText(getContext(), joinResult.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JoinResult> call, Throwable t) {
                        Log.e("Failure In Join", t.getMessage());
                    }
                });
                break;
            case R.id.join_cancel:
                pager.setCurrentItem(0);
                break;
        }
    }
}
