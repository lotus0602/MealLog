package com.n.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.n.adapter.ShowMealGridAdapter;
import com.n.meallog.R;
import com.n.model.MealInfo;
import com.n.model.MealList;
import com.n.net.MealInfoService;
import com.n.net.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMyMealFragment extends Fragment {
    private MealList mealList;
    private GridView gridView;
    private ShowMealGridAdapter gridAdapter;

    public ShowMyMealFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_my_meal, container, false);
        gridView = (GridView) v.findViewById(R.id.show_my_meal_grid);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mealList = new MealList();
        getMyMealList();
        Log.d("After getMyMealList!!!!", "SIZE : " + mealList.getInfos().size());

        gridAdapter = new ShowMealGridAdapter(getView().getContext());
    }

    private void getMyMealList(){
        MealInfoService mealInfoService = ServiceGenerator.createService(MealInfoService.class);
        Call<MealList> call = mealInfoService.getMyMealList();

        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(Call<MealList> call, Response<MealList> response) {
                Log.d("Response In MyMeal", "CODE : " + response.code());
                mealList = response.body();
                for (MealInfo mealInfo : mealList.getInfos()) {

                    Log.d("In Response", "Idx : " + mealInfo.getIdx()
                            + ", Username : " + mealInfo.getUsername()
                            + ", Name : " + mealInfo.getName()
                            + ", Category : " + mealInfo.getCategory()
                            + ", Content : " + mealInfo.getContent()
                            + ", Eatdate : " + mealInfo.getEatdate()
                            + ", Wheneat : " + mealInfo.getWheneat()
                            + ", Picpath : " + mealInfo.getPicpath()
                            + ", Share : " + mealInfo.isShare());
                }

                gridAdapter.setInfos(mealList.getInfos());
                gridView.setAdapter(gridAdapter);
            }

            @Override
            public void onFailure(Call<MealList> call, Throwable t) {
                Log.e("Failure In MyMeal", t.getMessage());
            }
        });
    }
}
