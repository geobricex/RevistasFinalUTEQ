package com.example.revistasfinaluteq.Adapter;

import android.content.Context;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
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

public class AdapterJournals extends ArrayAdapter<Journals> {
    TextView lblTitulo;
    TextView lblDescripcion;
    ImageView imgPortada;

    public AdapterJournals(Context context, ArrayList<Journals> journalsArrayList) {
        super(context, R.layout.layout_journals, journalsArrayList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View viewItem = inflater.inflate(R.layout.layout_journals, null);

        initializeAdapter(viewItem);

        lblTitulo.setText(getItem(position).getName());
        lblDescripcion.setText(Html.fromHtml(getItem(position).getDescripccion()));

        Glide.with(getContext()).load(getItem(position).getPortada()).into(imgPortada);

        return (viewItem);
    }

    public void initializeAdapter(View pviewItem) {
        lblTitulo = pviewItem.findViewById(R.id.lbltitle);
        lblDescripcion = pviewItem.findViewById(R.id.lblDescripcion);
        lblDescripcion.setMovementMethod(new ScrollingMovementMethod());
        imgPortada = pviewItem.findViewById(R.id.imgcover);
    }
}