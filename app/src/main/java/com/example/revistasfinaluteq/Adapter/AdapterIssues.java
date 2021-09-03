package com.example.revistasfinaluteq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.revistasfinaluteq.Model.*;
import com.example.revistasfinaluteq.R;

import java.util.ArrayList;

public class AdapterIssues extends ArrayAdapter<Issues> {
    TextView lblvolume;
    TextView lblnumber;
    TextView lblyear;
    TextView lbldate_published;
    TextView lbltitle;
    TextView lbldoi;

    ImageView imgcover;

    public AdapterIssues(Context context, ArrayList<Issues> issuesArrayList) {
        super(context, R.layout.layout_issues, issuesArrayList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View viewItem = inflater.inflate(R.layout.layout_issues, null);

        initializeAdapterIssues(viewItem);

        lblvolume.setText(getItem(position).getVolume());
        lblnumber.setText(getItem(position).getNumber());
        lblyear.setText(getItem(position).getYear());
        lbldate_published.setText(getItem(position).getDate_published());
        lbltitle.setText(getItem(position).getTitle());
        lbldoi.setText(getItem(position).getDoi());

        Glide.with(getContext()).load(getItem(position).getCover()).into(imgcover);

        return (viewItem);
    }

    public void initializeAdapterIssues(View pviewItem) {
        lblvolume = pviewItem.findViewById(R.id.lblvolumen);
        lblnumber = pviewItem.findViewById(R.id.lblnumero);
        lblyear = pviewItem.findViewById(R.id.lblanio);
        lbldate_published = pviewItem.findViewById(R.id.lblDescripcion);
        lbltitle = pviewItem.findViewById(R.id.lbltitle);
        lbldoi = pviewItem.findViewById(R.id.lbldoi);
    }
}