package com.example.revistasfinaluteq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.revistasfinaluteq.Holder.IssuesHolder;
import com.example.revistasfinaluteq.Holder.PubsHolder;
import com.example.revistasfinaluteq.Model.Issues;
import com.example.revistasfinaluteq.Model.Pubs;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PubsActivity extends AppCompatActivity {

    String i_id = "";

    PlaceHolderView placeHolderViewPubs;
    RequestQueue requestQueuePubs;
    private static final String URLPubs = "https://revistas.uteq.edu.ec/ws/pubs.php";
    public JsonArray JSONoriginIssues;
    public String JSON;
    Pubs pubs;// = new Journals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Logs", "STARTING THE PUBS");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pubs);// Only one activity is necessary to which the layouts are loaded
        requestQueuePubs = Volley.newRequestQueue(this);//Initialize the RequestQueue object with Google Volley
        Bundle bundle = this.getIntent().getExtras();
        i_id = bundle.getString("i_id");
        Log.i("Logs", "i_id= " + i_id);
        initialize();//Method that initializes objects in declared variables

        onInit();
    }

    private void initialize() {
        placeHolderViewPubs = findViewById(R.id.plchdListpubs);
    }

    private void onInit() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLPubs + "?i_id=" + i_id, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String jsonarrayorg = response.toString();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(jsonarrayorg).getAsJsonArray();
                JSONoriginIssues = jsonArray;

                Log.i("Logs", "Length: " + jsonArray.size());

                for (int i = 0; i < jsonArray.size(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        pubs = new Pubs();

                        pubs.set_section(jsonObject.getString("section"));
                        pubs.set_publication_id(jsonObject.getString("publication_id"));
                        pubs.set_title(jsonObject.getString("title"));
                        pubs.set_doi(jsonObject.getString("doi"));
                        pubs.set_abstract(jsonObject.getString("abstract"));
                        pubs.set_date_published(jsonObject.getString("date_published"));
                        pubs.set_submission_id(jsonObject.getString("submission_id"));
                        pubs.set_section_id(jsonObject.getString("section_id"));
                        pubs.set_seq(jsonObject.getString("seq"));
                        pubs.set_galeys(jsonObject.getString("galeys"));

                        JSONArray jsonArraykeywords = jsonObject.getJSONArray("keywords");
                        String keywordsString = "";
                        for (int indx = 0; indx < jsonArraykeywords.length(); indx++) {
                            JSONObject jsonObjectkeywords = new JSONObject(jsonArraykeywords.get(indx).toString());
                            keywordsString = keywordsString + jsonObjectkeywords.getString("keyword") + "-";
                        }
                        pubs.set_keywords(keywordsString);

                        String authorsString = "";
                        JSONArray jsonArrayauthors = jsonObject.getJSONArray("authors");
                        for (int indx = 0; indx < jsonArrayauthors.length(); indx++) {
                            JSONObject jsonObjectauthors = new JSONObject(jsonArrayauthors.get(indx).toString());
                            authorsString = authorsString + jsonObjectauthors.getString("nombres") + "-";
                        }
                        pubs.set_authors(authorsString);

                        placeHolderViewPubs.addView(new PubsHolder(getApplicationContext(), pubs));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PubsActivity.this, "ERROR IN GOOGLE VOLLEY", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueuePubs.add(jsonArrayRequest);
    }

}
