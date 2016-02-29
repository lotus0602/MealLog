package com.n.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.n.meallog.LoginActivity;
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
public class JoinFragment extends Fragment implements View.OnClickListener, TextWatcher {
    private Button complete;
    private EditText id;
    private EditText email;
    private EditText pw;
    private EditText pw_again;

    private TextInputLayout id_label;
    private ProgressDialog dialog;
    private LoginService loginService;

    private int count = 0;
    private boolean isEmpty = true;

    public JoinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_join, container, false);

        complete = (Button) v.findViewById(R.id.join_complete);
        Button cancel = (Button) v.findViewById(R.id.join_cancel);
        id = (EditText) v.findViewById(R.id.join_id);
        email = (EditText) v.findViewById(R.id.join_email);
        pw = (EditText) v.findViewById(R.id.join_pw);
        pw_again = (EditText) v.findViewById(R.id.join_pw_again);

        id_label = (TextInputLayout) v.findViewById(R.id.join_id_label);

        complete.setOnClickListener(this);
        cancel.setOnClickListener(this);

        id.addTextChangedListener(this);
        email.addTextChangedListener(this);
        pw.addTextChangedListener(this);
        pw_again.addTextChangedListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginService = ServiceGenerator.createService(LoginService.class);
        dialog = new ProgressDialog(getContext());
        complete.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        final LockableViewPager pager = ((LoginActivity)getActivity()).getViewPager();

        switch (v.getId()) {
            case R.id.join_complete:
                Map<String, String> userJoin = new HashMap<>();
                userJoin.put("signUp_userID", id.getText().toString());
                userJoin.put("signUp_userMail", email.getText().toString());
                userJoin.put("signUp_psw", pw.getText().toString());
                userJoin.put("signUp_pswCheck", pw_again.getText().toString());

                Call<RequestResult> call = loginService.basicJoin(userJoin);
                call.enqueue(new Callback<RequestResult>() {
                    @Override
                    public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                        Log.d("Response In Join", "CODE : " + response.code());

                        if (response.isSuccess()) {
                            RequestResult result = response.body();
                            if (result.getResult().equals("JOIN_OK")) {
                                pager.setCurrentItem(0);
                            } else {
                                Toast.makeText(getContext(), result.getResult(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RequestResult> call, Throwable t) {
                        Log.e("Failure In Join", t.getMessage());
                    }
                });

                break;
            case R.id.join_cancel:
                pager.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        isEmpty = (s.length() == 0);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isEmpty && s.length() > 0) { count++; }
        else if (!isEmpty && s.length() == 0) { count--; }

        if (s == id.getEditableText()) {
            checkDuplicateId(s.toString());
        }
    }

    private boolean isReadyToSend() {
        boolean isReadyToSend;
        isReadyToSend = (count == 4);
        return isReadyToSend;
    }

    private void checkDuplicateId(String s) {
        Call<Boolean> call = loginService.checkId(s);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("Response In Check Id", "CODE : " + response.code());
                if (response.isSuccess()) {
                    boolean isDuplicate = response.body();

                    if (isDuplicate) {
                        id_label.setError("Duplicated ID");
                        id_label.setErrorEnabled(true);
                        complete.setEnabled(false);
                    } else {
                        if (isReadyToSend())
                            complete.setEnabled(true);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Failure In Check Id", t.getMessage());
                dialog.dismiss();
            }
        });
    }
}
