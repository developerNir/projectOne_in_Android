package com.example.projectone;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class home_fragment extends Fragment {



    GridView gridView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home_fragment, container,false);

        gridView = myView.findViewById(R.id.GridView);


        hashMap = new HashMap<>();
        hashMap.put("name", "Video");
        hashMap.put("image", "https://square.github.io/picasso/static/sample.png");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "todo");
        hashMap.put("image", "https://square.github.io/picasso/static/sample.png");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "todo view");
        hashMap.put("image", "https://square.github.io/picasso/static/sample.png");
        arrayList.add(hashMap);

        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);


        return myView;


    }

    private class MyAdapter extends BaseAdapter{
        TextView textView;
        ImageView imageView;
        CardView cardView;

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
            View myLayout = layoutInflater.inflate(R.layout.grid_view_layout, null);
            textView = myLayout.findViewById(R.id.TextView);
            imageView = myLayout.findViewById(R.id.imageViewOne);
            cardView = myLayout.findViewById(R.id.cardView);

            hashMap = arrayList.get(i);
            String name = hashMap.get("name");
            String image = hashMap.get("image");
            textView.setText(name);
            Picasso.get().load(image).into(imageView);
            
            textView.append(" "+i);
            if (i == 0){
                cardView.setOnClickListener(v ->{
                    startActivity(new Intent(getContext(), MainActivity.class));
                });
            } else if (i == 1) {
                cardView.setOnClickListener(v ->{
                    startActivity(new Intent(getContext(), MainActivity2.class));
                });
            } else if ( i == 2) {
                cardView.setOnClickListener(v ->{
                    startActivity(new Intent(getContext(),viewTodoActivity.class));
                });
            }


            return myLayout;
        }
    }


}
