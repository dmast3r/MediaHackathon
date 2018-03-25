package com.dbot5.application.mediahackathon;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
private static final String LOG_CAT = HomeActivity.class.getName();
private final int REQ_CODE_SPEECH_INPUT = 1001;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(HomeActivity.this,
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        case REQ_CODE_SPEECH_INPUT: {
        if (resultCode == HomeActivity.this.RESULT_OK && null != data) {
        Log.e(LOG_CAT,"Speech Finished");
        ArrayList<String> result = data
        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        Log.e(LOG_CAT,"sheldon cooper"+result.get(0));
        //sendCommandThroughSpeech(result.get(0).toLowerCase());
        }
        break;
        }

        }
        }
    RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
    String getRequestURL = "https://newsapi.org/v2/everything?sources=business-insider&apiKey=8ae5a7a68c8b49c19f4db4d8b8a34ec8";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getRequestURL, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String responseJSON = response.toString();
                    Gson gson = new Gson();
                 BusinessInsiderInstance businessInsiderResponse = gson.fromJson(responseJSON, BusinessInsiderInstance.class);

                    if(Boolean.parseBoolean(businessInsiderResponse.status)) {
                      StringBuilder stringBuilder = new StringBuilder();
                  // initialize i as required
                           int i =0;
                        businessInsiderResponse.articles[i].displayTitle(stringBuilder);

                            businessInsiderResponse.articles[i].displayAuthor( stringBuilder);
                        businessInsiderResponse.articles[i].displayPublishTime( stringBuilder);
                     businessInsiderResponse.articles[i].displayDescription(stringBuilder);
                        textView.setText(stringBuilder.toString());
                    }
                    else {
                       textView.setText("Error" + ". Possible cause:-\n1. You misspelled the name of the movie. Recheck the spelling\n" +
                                "2. No record for such movie currently exists in the API Database.");
                    }
               // }
            }
            new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
    });
                    requestQueue.add(jsonObjectRequest);


}