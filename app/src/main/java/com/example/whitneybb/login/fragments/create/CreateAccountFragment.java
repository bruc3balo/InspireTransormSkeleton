package com.example.whitneybb.login.fragments.create;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whitneybb.R;

public class CreateAccountFragment extends Fragment implements DatePicker.OnDateChangedListener {

    private CreateAccountViewModel mViewModel;
    private String dateOfBirth;


    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.create_account_fragment, container, false);
        final TextView showDatePickerTv = v.findViewById(R.id.showDatePickerTv);
        showDatePickerTv.setTextColor(Color.BLACK);
        final DatePicker creationDatePicker = v.findViewById(R.id.creationDatePicker);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationDatePicker.setOnDateChangedListener(this);
        } else {
            Toast.makeText(requireContext(), "Minimum android version not met for date picker", Toast.LENGTH_SHORT).show();
        }
        creationDatePicker.setVisibility(View.GONE);
        showDatePickerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDatePicker(showDatePickerTv,creationDatePicker);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        // TODO: Use the ViewModel
    }

    private void toggleDatePicker(TextView textView,DatePicker datePicker){
        if (datePicker.getVisibility() == View.VISIBLE) {
            textView.setTextColor(Color.BLACK);
            textView.setText("Click to change date");
            textView.setBackgroundColor(Color.TRANSPARENT);
            datePicker.setVisibility(View.GONE);
        } else {
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setTextColor(Color.RED);
            textView.setText("Click to close date picker");
            datePicker.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateOfBirth = "Year is "+ year + " and Month is "+monthOfYear + " on day "+dayOfMonth;
        Toast.makeText(requireContext(), dateOfBirth, Toast.LENGTH_SHORT).show();
    }
}