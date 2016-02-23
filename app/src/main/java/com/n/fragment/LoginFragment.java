package com.n.fragment;

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
<<<<<<< HEAD
import com.n.model.LoginResult;
=======
import com.n.model.User;
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
import com.n.net.LoginService;
import com.n.net.ServiceGenerator;
import com.n.view.LockableViewPager;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
=======
import java.io.IOException;

import retrofit2.Call;
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private EditText id;
    private EditText pw;

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
    public void onClick(View v) {
        LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.login_login_btn:
<<<<<<< HEAD
                LoginService loginService =
                        ServiceGenerator.createService(LoginService.class);

                Map user = new HashMap();
                user.put("userID", id.getText().toString());
                user.put("userPW", pw.getText().toString());

                Call<LoginResult> call = loginService.basicLogin(user);
                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        Log.d("Response In Login", "CODE : " + response.code());

                        LoginResult loginResult = response.body();

                        if (loginResult.getResult().equals("LOGIN_OK")) {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), loginResult.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Log.e("Failure In Login", t.getMessage());
                    }
                });

=======
                LoginService loginService = ServiceGenerator.createService(LoginService.class, "aa", "aa");
                Call<User> call = loginService.basicLogin();

                try {
                    User user = call.execute().body();
                    Log.d("execute", "!!!!!!!!!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();
>>>>>>> fad17f4d5895fb16ce9d42cfeb4731d81e7d4dd0
                break;
            case R.id.login_join_btn:
                pager.setCurrentItem(1);
                break;
        }
    }
}
