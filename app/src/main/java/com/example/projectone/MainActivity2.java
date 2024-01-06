package com.example.projectone;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {


    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    ListView listView;
    FloatingActionButton floatingActionButton;
    EditText header, description;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    String url = "https://nirabd.000webhostapp.com/apps/todo.php";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        listView = findViewById(R.id.list_item);
        progressBar = findViewById(R.id.progress_circular);
        floatingActionButton = findViewById(R.id.fab);
        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        progressBar.setVisibility(View.GONE);



//--------------------------------------- post  ----------- request ------------

        floatingActionButton.setOnClickListener(v->{
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_alart_dialog);
//            dialog.setCancelable(false);
            Button button = dialog.findViewById(R.id.buttonDialog);
            header = dialog.findViewById(R.id.header_title);
            description = dialog.findViewById(R.id.description);

            button.setOnClickListener(e ->{
                String name = header.getText().toString();
                String des = description.getText().toString();
                String myUrl = url+"?h="+name+"&d="+des;


                StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                if (name.length()>5 && des.length()>10){
//                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
                    requestQueue.add(stringRequest);
                }else {
                    header.setError("enter your Header");
                    description.setError("required");
                }


                Toast.makeText(this, name+" "+des, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
            dialog.show();


        });






    }

//    -----------------------------------------------------------------


    private class MyAdapter extends BaseAdapter{
        TextView header, description;

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
            @SuppressLint("ViewHolder") View myView = layoutInflater.inflate(R.layout.todo_list, null);
            header = myView.findViewById(R.id.headerTodo);
            description =myView.findViewById(R.id.description);


//            hashMap = arrayList.get(position);
//            String up = hashMap.get("head");
//            String down = hashMap.get("des");
//            header.setText(up);
//            description.setText(down);


            return myView;
        }
    }
}