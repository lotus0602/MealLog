package com.n.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.n.meallog.R;
import com.n.model.MealInfo;
import com.n.net.MealInfoService;
import com.n.net.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Calendar;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputMealFragment extends Fragment {
    public static final int REQUEST_CAMERA = 1;
    public static final int SELECT_PHOTO = 2;

    private EditText foodName;
    private static TextView dateView;
    private ImageButton datePickerBtn;
    private CheckBox share;
    private Spinner mealTimeSpinner;
    private Spinner categorySpinner;
    private EditText contents;
    private ImageView foodImage;
    private Button completeBtn;

    private Uri photoUri;

    public InputMealFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input_meal, container, false);

        foodName = (EditText) v.findViewById(R.id.input_food_name);
        dateView = (TextView) v.findViewById(R.id.input_date_show);
        datePickerBtn = (ImageButton) v.findViewById(R.id.input_date_picker_btn);
        share = (CheckBox) v.findViewById(R.id.input_share);
        mealTimeSpinner = (Spinner) v.findViewById(R.id.input_meal_time_spinner);
        categorySpinner = (Spinner) v.findViewById(R.id.input_meal_category_spinner);
        contents = (EditText) v.findViewById(R.id.input_meal_contents);
        foodImage = (ImageView) v.findViewById(R.id.input_food_imageview);
        completeBtn = (Button) v.findViewById(R.id.input_complete_btn);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month, day);

        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerDialog = new DatePickerFragment();
                datePickerDialog.show(getFragmentManager(), "datePicker");
            }
        });

        foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealInfo info = new MealInfo(foodName.getText().toString(),
                        dateView.getText().toString(),
                        mealTimeSpinner.getSelectedItem().toString(),
                        categorySpinner.getSelectedItem().toString(),
                        contents.getText().toString(),
                        share.isChecked());
                Log.d("Upload!!!", "Idx : " + info.getIdx()
                        + ", Username : " + info.getUsername()
                        + ", Name : " + info.getName()
                        + ", Category : " + info.getCategory()
                        + ", Content : " + info.getContent()
                        + ", Eatdate : " + info.getEatdate()
                        + ", Wheneat : " + info.getWheneat()
                        + ", Picpath : " + info.getPicpath()
                        + ", Share : " + info.isShare());

                File file = new File(getRealPathFromUri(photoUri));
                Log.d("File path!!!", file.getPath());

//                RequestBody requestBody =
//                        RequestBody.create(MediaType.parse("image/jpeg"), file);


//                RequestBody requestBody = new MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addPart(
//                                Headers.of("Content-Disposition", "form-data; name=\"image.jpg\""),
//                                RequestBody.create(MediaType.parse("image/jpeg"), file))
//                        .build();

                MultipartBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("File", file.getName(),
                                RequestBody.create(MediaType.parse("image/jpeg"), file))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"CONTENT\""),
                                RequestBody.create(MediaType.parse("text"),
                                        contents.getText().toString()))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"NAME\""),
                                RequestBody.create(MediaType.parse("text"),
                                        foodName.getText().toString()))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"EATDATE\""),
                                RequestBody.create(MediaType.parse("text"),
                                        dateView.getText().toString()))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"WHENEAT\""),
                                RequestBody.create(MediaType.parse("text"),
                                        mealTimeSpinner.getSelectedItem().toString()))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"CATEGORY\""),
                                RequestBody.create(MediaType.parse("text"),
                                        categorySpinner.getSelectedItem().toString()))
                        .addPart(
                                Headers.of("Content-Disposition", "form-data; name=\"SHARE\""),
                                RequestBody.create(MediaType.parse("text"),
                                        String.valueOf(share.isChecked())))
                        .build();

//                requestBody.contentType().charset(Charset.forName("UTF-8"));

                MealInfoService mealInfoService =
                        ServiceGenerator.createService(MealInfoService.class);
                Call<String> call = mealInfoService.uploadMeal(requestBody);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("Response In Upload", "CODE : " + response.code());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Failure In Upload!", t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            photoUri = data.getData();
            Log.d("Uri path!!!", photoUri.toString());
            if (requestCode == REQUEST_CAMERA) {
                Picasso.with(getActivity())
                        .load(photoUri)
                        .fit()
                        .centerCrop()
                        .into(foodImage);
            } else if (requestCode == SELECT_PHOTO) {
                Picasso.with(getActivity())
                        .load(photoUri)
                        .fit()
                        .centerCrop()
                        .into(foodImage);
            }
        }
    }

    public String getRealPathFromUri(Uri uri) {
        String res = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(colum_index);
            }
            cursor.close();
        } else {
            Log.d("Get Real Path!", "Cursor is null");
            return uri.getPath();
        }
        return res;
    }

    private static void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[which].equals("Choose from gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select"), SELECT_PHOTO);
                }
            }
        });
        builder.show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            showDate(year, monthOfYear + 1, dayOfMonth);
        }
    }
}
