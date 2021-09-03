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
import com.example.revistasfinaluteq.Holder.JournalsHolder;
import com.example.revistasfinaluteq.Model.Journals;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    PlaceHolderView placeHolderView;
    RequestQueue requestQueue;
    private static final String URLJournal = "https://revistas.uteq.edu.ec/ws/journals.php/";
    public JsonArray JSONorigin;
    public String JSON;
    Journals journals;// = new Journals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Logs", "STARTING THE APPLICATION");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);//Initialize the RequestQueue object with Google Volley

        initialize();//Method that initializes objects in declared variables

        onInit();
    }

    private void initialize() {
        placeHolderView = findViewById(R.id.plchdList);
    }

    private void onInit() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLJournal, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String jsonarrayorg = response.toString();
                JsonParser parser = new JsonParser();
                JsonArray jsonArray = parser.parse(jsonarrayorg).getAsJsonArray();
                JSONorigin = jsonArray;

                Log.i("Logs", "Length: " + jsonArray.size());

                for (int i = 0; i < jsonArray.size(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                        journals = new Journals();
                        journals.setName(jsonObject.getString("name"));
                        journals.setJournal_id(jsonObject.getString("journal_id"));
                        journals.setDescripccion(jsonObject.getString("description"));
                        journals.setAbbreviation(jsonObject.getString("abbreviation"));
                        journals.setJournalThumbnail(jsonObject.getString("journalThumbnail"));
                        journals.setPortada(jsonObject.getString("portada"));

                        placeHolderView.addView(new JournalsHolder(getApplicationContext(), journals));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR IN GOOGLE VOLLEY", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}