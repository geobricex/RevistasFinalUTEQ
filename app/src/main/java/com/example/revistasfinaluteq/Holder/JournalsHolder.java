package com.example.revistasfinaluteq.Holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.revistasfinaluteq.IssuesActivity;
import com.example.revistasfinaluteq.Model.*;
import com.example.revistasfinaluteq.R;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Animate(Animate.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.layout_journals)
public class JournalsHolder {

    Journals journals = new Journals();

    private Context context;

    @View(R.id.plchdList)
    PlaceHolderView plchdListRevista;

    @View(R.id.imgcover)
    ImageView imgPortadaRevista;

    @View(R.id.lblDescripcion)
    TextView txtDescripcion;

    @View(R.id.lbltitle)
    TextView txtTitulo;

    @Click(R.id.linerLayaoutGlobalJournals)
    public void OnClickListener() {
        Intent intent = new Intent(context, IssuesActivity.class);
        Bundle b = new Bundle();
        b.putString("j_id", journals.getJournal_id());
        Log.i("Logs", "j_id= " + journals.getJournal_id());//Review in LogCar by regex SOUT
        intent.putExtras(b);
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public JournalsHolder(Context context, Journals journals) {
        this.journals = journals;
        this.context = context;
    }

    @Resolve
    public void onResolved() {
        this.txtTitulo.setText(journals.getName());
        this.txtDescripcion.setText(Html.fromHtml(journals.getDescripccion()));
        Glide.with(context).load(journals.getPortada()).into(imgPortadaRevista);
    }

}

