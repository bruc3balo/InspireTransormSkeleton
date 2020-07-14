package com.example.whitneybb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whitneybb.R;


public class GridAdapter extends BaseAdapter {
    private Context context;
    private int menu_items[]; //getPreview
    private String menu_titles []; //getNoteTitle
    private LayoutInflater inflater;
    public GridAdapter(Context applicationContext, int[] incidence_list, String[] titles) {
        this.context = applicationContext;
        this.menu_items = incidence_list;
        this.menu_titles = titles;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return menu_items.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.grid_row, null); // inflate the layout
        ImageView icon = view.findViewById(R.id.incidence_pic); // get the reference of ImageView

        try {
            Glide.with(context).load(menu_items[i]).into(icon);
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Image failed to load", Toast.LENGTH_SHORT).show();
            icon.setImageResource(R.drawable.error);
        }

        TextView title = view.findViewById(R.id.incidence_title); // title tv
        title.setText(menu_titles[i]);

        return view;
    }






}
