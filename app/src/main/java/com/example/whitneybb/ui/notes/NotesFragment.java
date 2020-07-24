package com.example.whitneybb.ui.notes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.whitneybb.MainActivity;
import com.example.whitneybb.R;
import com.example.whitneybb.adapter.GridAdapter;

import static com.example.whitneybb.MainActivity.smartFab;

public class NotesFragment extends Fragment {

    private NotesViewModel mViewModel;
    int[] incidence_list = new int[]{R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_,R.drawable.ic_file_};
    String[] titles = new String[]{"Note 1","Note 2","Note 3","Note 4","Note 5","Note 6","Note 7","Note 8"};

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.notes_fragment, container, false);

        MainActivity.currentPage = 0;
        smartFab(MainActivity.currentPage);

        GridView gridView = v.findViewById(R.id.notesGrid);
        gridView.setAdapter(new GridAdapter(requireContext(),incidence_list,titles));

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        // TODO: Use the ViewModel
    }

}