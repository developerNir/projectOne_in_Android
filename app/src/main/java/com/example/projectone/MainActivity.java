package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    ListView listView;
    ProgressBar progressBar;
    TextToSpeech textToSpeech;


    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progress_circular);
        //textToSpeech Introducing -----------------
        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        //end textToSpeech introduce --------------


        MyAdapter myAdapter = new MyAdapter();



        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        String url = "https://nirabd.000webhostapp.com/apps/video.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i=0; i<response.length(); i++ ){

                                JSONObject jsonObject = response.getJSONObject(i);

                                String item = jsonObject.getString("item");
                                String Video_id = jsonObject.getString("video_id");

                                hashMap = new HashMap<>();
                                hashMap.put("item", item);
                                hashMap.put("Video_id", Video_id);
                                arrayList.add(hashMap);

                            }
                            // listView eat Adapter ------------------- main subject for array list View----
                            listView.setAdapter(myAdapter);
                            progressBar.setVisibility(View.GONE);




                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "SERVER ERROR", Toast.LENGTH_LONG).show();
            }
        });


        queue.add(jsonArrayRequest);



    }

    // -----------------------------------

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.youtube_layout, null);

            TextView textView;
            WebView webView;
            ImageView imageView;

            imageView = myView.findViewById(R.id.VideoImg);
            webView = myView.findViewById(R.id.webView);
            textView = myView.findViewById(R.id.textHead);

            webView.getSettings().setJavaScriptEnabled(true);




            HashMap<String, String> hashMap1 = arrayList.get(position);
            String item = hashMap1.get("item");
            String Video_id = hashMap1.get("Video_id");
            textView.setText(item);

            Picasso.get().load("https://img.youtube.com/vi/"+Video_id+"/0.jpg").into(imageView);
            imageView.setOnClickListener(v ->{
                imageView.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                webView.loadUrl("https://www.youtube.com/embed/"+Video_id);
            });

            textView.setOnClickListener(v ->{
                textToSpeech.speak(item, TextToSpeech.QUEUE_FLUSH, null, null);
            });

            return myView;


        }
    }

}