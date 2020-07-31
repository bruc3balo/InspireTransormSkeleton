package com.example.whitneybb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.whitneybb.R;

import java.util.LinkedList;


public class GridAdapter extends BaseAdapter {
    private Context context;
    private LinkedList<String> item_color; //getPreview
    private LinkedList<String> menu_titles; //getNoteTitle
    private LayoutInflater inflater;
    public GridAdapter(Context applicationContext,LinkedList<String> menu_items, LinkedList<String> menu_titles) {
        this.context = applicationContext;
       this.item_color = menu_items;
       this.menu_titles = menu_titles;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return menu_titles.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.grid_row, null); // inflate the layout
        ImageView icon = view.findViewById(R.id.incidence_pic); // get the reference of ImageView
        CardView noteCardBg = view.findViewById(R.id.noteCardBg);

        try {
            noteCardBg.setCardBackgroundColor(Color.parseColor(item_color.get(i)));
            Glide.with(context).load(R.drawable.ic_note).into(icon);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Image failed to load", Toast.LENGTH_SHORT).show();
            icon.setImageResource(R.drawable.error);
        }

        TextView title = view.findViewById(R.id.incidence_title); // title tv
        title.setText(menu_titles.get(i));

        return view;
    }






}
