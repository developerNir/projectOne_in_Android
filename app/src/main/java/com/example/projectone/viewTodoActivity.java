package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class viewTodoActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    ListView listView;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todo);
        listView = findViewById(R.id.listViewFirst);
        progressBar = findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        MyAdapter myAdapter = new MyAdapter();
        String url = "https://nirabd.000webhostapp.com/apps/todoGet.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                for (int x =0; x<response.length(); x++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        String header = jsonObject.getString("header");
                        String dis = jsonObject.getString("description");

                        hashMap = new HashMap<>();
                        hashMap.put("Header", header);
                        hashMap.put("description", dis);
                        arrayList.add(hashMap);


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

                listView.setAdapter(myAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ser",  error.toString());
                progressBar.setVisibility(View.GONE);
                Toast.makeText(viewTodoActivity.this, "server Error", Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(viewTodoActivity.this);
        requestQueue.add(jsonArrayRequest);


    }
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView =  layoutInflater.inflate(R.layout.todo_list, null);

            TextView header, description;

            header = myView.findViewById(R.id.headerTodo);
            description =myView.findViewById(R.id.description);


            HashMap<String, String> hashMap1 = arrayList.get(i);
            String up = hashMap1.get("Header");
            String down = hashMap1.get("description");
            header.setText(up);
            description.setText(down);


            return myView;
        }
    }
}