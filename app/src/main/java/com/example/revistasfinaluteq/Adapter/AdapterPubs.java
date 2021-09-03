package com.example.revistasfinaluteq.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.revistasfinaluteq.Model.Pubs;
import com.example.revistasfinaluteq.R;

import java.util.ArrayList;

public class AdapterPubs extends ArrayAdapter<Pubs> {
    TextView lblsection;
    TextView lbltitle;
    TextView lblabstract;
    TextView lbldate_published;
    TextView lblkeywords;
    TextView lblauthors;

    public AdapterPubs(Context context, ArrayList<Pubs> pubsArrayList) {
        super(context, R.layout.layout_pubs, pubsArrayList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View viewItem = inflater.inflate(R.layout.layout_pubs, null);

        initializeAdapterPubs(viewItem);

        lblsection.setText(getItem(position).get_section());
        lbltitle.setText(getItem(position).get_title());
        lblabstract.setText(getItem(position).get_abstract());
        lbldate_published.setText(getItem(position).get_date_published());
        lblkeywords.setText(getItem(position).get_keywords());
        lblauthors.setText(getItem(position).get_authors());

        return (viewItem);
    }

    public void initializeAdapterPubs(View pviewItem) {
        lblsection = pviewItem.findViewById(R.id.lbltitle);
        lbltitle = pviewItem.findViewById(R.id.lblDescripcion);
        lblabstract = pviewItem.findViewById(R.id.lblnumero);
        lbldate_published = pviewItem.findViewById(R.id.lblanio);
        lblkeywords = pviewItem.findViewById(R.id.lblvolumen);
        lblauthors = pviewItem.findViewById(R.id.lblauthors);
    }
}