package com.example.whitneybb.ui.aboutUs;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;

import static com.example.whitneybb.MainActivity.smartFab;

public class AboutUs extends Fragment {

    private AboutUsViewModel mViewModel;

    public static AboutUs newInstance() {
        return new AboutUs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainActivity.currentPage = 7;
        smartFab(MainActivity.currentPage);
        return inflater.inflate(R.layout.about_us_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AboutUsViewModel.class);
        // TODO: Use the ViewModel
    }

}