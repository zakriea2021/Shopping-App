package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.Buyers.HomeActivity;
import com.example.shopping.Buyers.homeapp.HomeAppFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeApp extends AppCompatActivity {

    TextView categories_restaurant , prodect,restaurant_list;

    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        prodect=findViewById(R.id.prodect);
        categories_restaurant=findViewById(R.id.categories_restaurant);
        restaurant_list=findViewById(R.id.restaurant_list);


        FloatingActionButton fab = findViewById(R.id.fab);


        Paper.init(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cart,  R.id.nav_settings, R.id.nav_logout, R.id.nav_home, R.id.nav_resturants)
                .setDrawerLayout(drawer)
                .build();
      /*  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/


    /*    fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.nav_cart);


            }
        });*/

       /*
        restaurant_list=findViewById(R.id.restaurant_list);
        //profile=findViewById(R.id.profile);
        //categories_details=findViewById(R.id.categories_details);

      /* categories_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Order_Details.class);
                startActivity(intent);

            }
        });*/



        categories_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeApp.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        prodect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeApp.this, HomeActivity.class);
                startActivity(intent);

            }
        });


        restaurant_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeApp.this, HomeAppFragment.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_search :
                Toast.makeText(this,"search my app",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_home :
                Toast.makeText(this,"home my ap",Toast.LENGTH_LONG).show();
                break;

            case R.id.action_favorites :
                Toast.makeText(this,"favorites my app",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeApp.this, HomeApp.class);
                startActivity(intent);
                break;

            case R.id.action_profile :
                Toast.makeText(this,"profile my app",Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_settings :
                Toast.makeText(this,"settings my app",Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}