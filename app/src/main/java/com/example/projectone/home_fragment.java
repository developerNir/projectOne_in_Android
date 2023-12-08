package com.example.projectone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class home_fragment extends Fragment {

    FloatingActionButton floatingActionButton;
    ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.fragment_home_fragment, container,false);

        // element introducing
        floatingActionButton = myView.findViewById(R.id.fab);
        listView = myView.findViewById(R.id.todoListView);


        floatingActionButton.setOnClickListener(v->{
            new AlertDialog.Builder(getContext())
                    .setTitle("ADD ToDo")
                    .setMessage("my name is Flow")
                    .show();
        });

       MyAdapter myAdapter = new MyAdapter();

       listView.setAdapter(myAdapter);



        return myView;


    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View inView = layoutInflater.inflate(R.layout.todo_list, null);
            return inView;
        }
    }
}