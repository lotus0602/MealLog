package com.n.meallog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.n.fragment.ShowMyMealFragment;
import com.n.model.MealInfo;
import com.n.model.RequestResult;
import com.n.net.MealInfoService;
import com.n.net.ServiceGenerator;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView title, contents, date, category;
    private TextView mealtime, like, id;
    private ImageView imageView;
    private Button btnEdit, btnDelete, btnCancel;

    private MealInfoService mealInfoService;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MealInfo info = (MealInfo) getIntent().getSerializableExtra(ShowMyMealFragment.MEAL_INFO);
        index = getIntent().getIntExtra(ShowMyMealFragment.MEAL_INDEX, -1);
        mealInfoService = ServiceGenerator.createService(MealInfoService.class);

        initView();
        loadMeal(info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_btn_edit:
                break;
            case R.id.detail_btn_delete:
                deleteMeal(index);
                finish();
                break;
            case R.id.detail_btn_cancel:
                finish();
                break;
        }
    }

    private void initView() {
        title = (TextView) findViewById(R.id.detail_title);
        contents = (TextView) findViewById(R.id.detail_contents);
        date = (TextView) findViewById(R.id.detail_date);
        category = (TextView) findViewById(R.id.detail_category);
        mealtime = (TextView) findViewById(R.id.detail_mealtime);
        like = (TextView) findViewById(R.id.detail_like);
        id = (TextView) findViewById(R.id.detail_id);
        imageView = (ImageView) findViewById(R.id.detail_image);
        btnEdit = (Button) findViewById(R.id.detail_btn_edit);
        btnDelete = (Button) findViewById(R.id.detail_btn_delete);
        btnCancel = (Button) findViewById(R.id.detail_btn_cancel);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void loadMeal(MealInfo mealInfo) {
        Call<MealInfo> call = mealInfoService.getMealDetails(mealInfo.getIndex());
        call.enqueue(new Callback<MealInfo>() {
            @Override
            public void onResponse(Call<MealInfo> call, Response<MealInfo> response) {
                if (response.isSuccess()) {
                    MealInfo detailInfo = response.body();
                    setMealDetailsToView(detailInfo);
                }
            }

            @Override
            public void onFailure(Call<MealInfo> call, Throwable t) {
                Log.e("In Detail Activity", t.getMessage());
            }
        });
    }

    private void setMealDetailsToView(MealInfo details) {
        title.setText(details.getTitle());
        contents.setText(details.getContents());
        date.setText(details.getDate());
        category.setText(details.getFoodCategory());
        mealtime.setText(details.getMealTime());
        String temp = "by " + details.getId();
        id.setText(temp);
        Picasso.with(this)
                .load(ServiceGenerator.API_BASE_URL + "meal/" + details.getImagePath())
                .fit()
                .centerCrop()
                .into(imageView);
    }

    private void deleteMeal(int i) {
        Call<RequestResult> call = mealInfoService.deleteMeal(i);
        call.enqueue(new Callback<RequestResult>() {
            @Override
            public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {
                if (response.isSuccess()) {
                    RequestResult result = response.body();
                    Log.d("In Detail Activity", result.getResult());
                }
            }

            @Override
            public void onFailure(Call<RequestResult> call, Throwable t) {
                Log.e("In Detail Activity", t.getMessage());
            }
        });
    }
}
