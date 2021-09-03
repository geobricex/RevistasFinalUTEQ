package com.example.revistasfinaluteq.Holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.revistasfinaluteq.Model.*;
import com.example.revistasfinaluteq.R;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Animate(Animate.CARD_TOP_IN_DESC)
@NonReusable
@Layout(R.layout.layout_pubs)
public class PubsHolder {

    Pubs pubs = new Pubs();

    private Context context;

    @View(R.id.lbltitle)
    TextView lblsection;

    @View(R.id.lblDescripcion)
    TextView lbltitle;

    @View(R.id.lblnumero)
    TextView lblabstract;

    @View(R.id.lblanio)
    TextView lbldate_published;

    @View(R.id.lblvolumen)
    TextView lblkeywords;

    @View(R.id.lblauthors)
    TextView lblauthors;

    @Click(R.id.btnHTML)
    public void OnClickListenerHTML() {
        String doi = pubs.get_doi();
        Log.i("Logs", "doi= " + doi);
        Uri doiEnlace = Uri.parse(doi);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(doiEnlace);
        this.context.startActivity(intent);
    }

    @Click(R.id.btnPDF)
    public void OnClickListenerPDF() {
        String URL_pdf = "https://revistas.uteq.edu.ec/index.php/csye/article/view/" + pubs.get_publication_id() + "/" + pubs.get_submission_id() + ".pdf";
        this.context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL_pdf)));
    }

    public PubsHolder(Context context, Pubs pubs) {
        this.pubs = pubs;
        this.context = context;
    }

    @Resolve
    public void onResolved() {
        this.lblsection.setText(" Sección: " + pubs.get_section());
        this.lbltitle.setText(" Título: " + pubs.get_title());
//        this.lblabstract.setText(" Resumen: " + pubs.get_abstract());
        this.lbldate_published.setText(" Publicado: " + pubs.get_date_published());
        this.lblkeywords.setText(" Palabras clave: " + pubs.get_keywords());
        this.lblauthors.setText(" Autores: " + pubs.get_authors());
    }

}

