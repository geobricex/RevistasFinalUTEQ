package com.example.revistasfinaluteq.Holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.revistasfinaluteq.Model.*;
import com.example.revistasfinaluteq.PubsActivity;
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
@Layout(R.layout.layout_issues)
public class IssuesHolder {

    Issues issues = new Issues();

    private Context context;

    @View(R.id.lblvolumen)
    TextView lblvolume;

    @View(R.id.lblnumero)
    TextView lblnumber;

    @View(R.id.lblanio)
    TextView lblyear;

    @View(R.id.lblDescripcion)
    TextView lbldate_published;

    @View(R.id.lbltitle)
    TextView lbltitle;

    @View(R.id.lbldoi)
    TextView lbldoi;

    @View(R.id.imgcover)
    ImageView imgcover;

    @View(R.id.plchdList)
    PlaceHolderView plchdList;

    @Click(R.id.linerLayaoutGlobalIssues)
    public void OnClickListener() {
        Intent intent = new Intent(context, PubsActivity.class);
        Bundle b = new Bundle();
        b.putString("i_id", issues.getIssue_id());
        Log.i("Logs", "i_id= " + issues.getIssue_id());//Review in LogCar by regex SOUT
        intent.putExtras(b);
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public IssuesHolder(Context context, Issues issues) {
        this.issues = issues;
        this.context = context;
    }

    @Resolve
    public void onResolved() {
        this.lblvolume.setText("Volumen: " + issues.getVolume());
        this.lblnumber.setText("Número: " + issues.getNumber());
        this.lblyear.setText("Año: " + issues.getYear());
        this.lbldate_published.setText("Publicado: " + issues.getDate_published());
        this.lbltitle.setText(issues.getTitle());
        this.lbldoi.setText("DOI: " + issues.getDoi());

        Glide.with(context).load(issues.getCover()).into(imgcover);
    }

}

