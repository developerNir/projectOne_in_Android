package com.example.projectone;

import static android.view.Gravity.START;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class LauncherActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TextView headerTitle;
    View headerView, buttonImage;
    ImageView imageViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        // layout introducing
        drawerLayout = findViewById(R.id.DrawerLayout);
        materialToolbar = findViewById(R.id.MataToollber);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        //how to introduce Drawer layout Header view ---------------
       headerView = navigationView.getHeaderView(0);
       imageViewHeader = headerView.findViewById(R.id.imageView);
       buttonImage = headerView.findViewById(R.id.closeButton);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(LauncherActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);

        drawerLayout.addDrawerListener(toggle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new home_fragment());
        fragmentTransaction.commit();

        // ToolBar Property ---------------------------------
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.share){
                    Toast.makeText(LauncherActivity.this, "this is share Button", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        // navigation View Property -------------------------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.home){
//                    headerTitle.setText("Home of the Earth");
                    Intent myIntent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(myIntent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.ToDo) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout, new home_fragment());
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.video) {
                    startActivity(new Intent(LauncherActivity.this, MainActivity2.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId() == R.id.setting) {
                    startActivity(new Intent(LauncherActivity.this,viewTodoActivity.class));
                }
                return true;
            }
        });
        buttonImage.setOnClickListener(v->{
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        Picasso.get().load("https://m.media-amazon.com/images/I/61N4K6Y2yDL.jpg").into(imageViewHeader);




    }
}