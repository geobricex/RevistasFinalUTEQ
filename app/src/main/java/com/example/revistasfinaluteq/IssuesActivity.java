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
import com.example.revistasfinaluteq.Holder.JournalsHolder;
import com.example.revistasfinaluteq.Model.Issues;
import com.example.revistasfinaluteq.Model.Journals;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IssuesActivity extends AppCompatActivity {
    String j_id = "";

    PlaceHolderView placeHolderViewIssues;
    RequestQueue requestQueueIssues;
    private static final String URLIssues = "https://revistas.uteq.edu.ec/ws/issues.php";
    public JsonArray JSONoriginIssues;
    public String JSON;
    Issues issues;// = new Journals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Logs", "STARTING THE ISSUES");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);// Only one activity is necessary to which the layouts are loaded
        requestQueueIssues = Volley.newRequestQueue(this);//Initialize the RequestQueue object with Google Volley
        Bundle bundle = this.getIntent().getExtras();
        j_id = bundle.getString("j_id");
        Log.i("Logs", "j_id= " + j_id);
        initialize();//Method that initializes objects in declared variables

        onInit();
    }

    private void initialize() {
        placeHolderViewIssues = findViewById(R.id.plchdListissues);
    }

    private void onInit() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLIssues + "?j_id=" + j_id, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String jsonarrayorg = response.toString();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(jsonarrayorg).getAsJsonArray();
                JSONoriginIssues = jsonArray;

                Log.i("Logs", "Length URLIssues: " + jsonArray.size());

                for (int i = 0; i < jsonArray.size(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        issues = new Issues();

                        issues.setIssue_id(jsonObject.getString("issue_id"));
                        issues.setVolume(jsonObject.getString("volume"));
                        issues.setNumber(jsonObject.getString("number"));
                        issues.setYear(jsonObject.getString("year"));
                        issues.setDate_published(jsonObject.getString("date_published"));
                        issues.setTitle(jsonObject.getString("title"));
                        issues.setDoi(jsonObject.getString("doi"));
                        issues.setCover(jsonObject.getString("cover"));
                        Log.i("Logs", "Length URLIssues: " + jsonArray.size());

                        placeHolderViewIssues.addView(new IssuesHolder(getApplicationContext(), issues));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IssuesActivity.this, "ERROR IN GOOGLE VOLLEY", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueueIssues.add(jsonArrayRequest);
    }

}