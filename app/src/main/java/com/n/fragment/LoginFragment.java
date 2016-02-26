package com.n.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.n.meallog.MainActivity;
import com.n.meallog.R;

import com.n.model.RequestResult;
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
public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText id;
    private EditText pw;
    private ProgressDialog dialog;

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
        id = (EditText) v.findViewById(R.id.login_id);
        pw = (EditText) v.findViewById(R.id.login_pw);

        login.setOnClickListener(this);
        join.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dialog = new ProgressDialog(getContext());
    }

    @Override
    public void onClick(View v) {
        LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.login_login_btn:
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("Login...");
                dialog.show();

                LoginService loginService =
                        ServiceGenerator.createService(LoginService.class);

                Map user = new HashMap();
                user.put("userID", id.getText().toString());
                user.put("userPW", pw.getText().toString());

                Call<RequestResult> call = loginService.basicLogin(user);
                call.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        Log.d("Response In Login", "CODE : " + response.code());
                        dialog.dismiss();

                        RequestResult result = response.body();
                        if (result.getResult().equals("LOGIN_OK")) {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), result.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable t) {
                        dialog.dismiss();
                        Log.e("Failure In Login", t.getMessage());
                    }
                });

                break;
            case R.id.login_join_btn:
                pager.setCurrentItem(1);
                break;
        }
    }
}
