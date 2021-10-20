package com.example.shopping.Buyers.homeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.Buyers.HomeActivity;
import com.example.shopping.HomeApp;
import com.example.shopping.R;

public class HomeAppFragment extends AppCompatActivity {

    TextView categories_restaurant , prodect,restaurant_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app_fragment);

        prodect=findViewById(R.id.prodect);
        categories_restaurant=findViewById(R.id.categories_restaurant);
        restaurant_list=findViewById(R.id.restaurant_list);



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
                Intent intent = new Intent(HomeAppFragment.this, HomeApp.class);
                startActivity(intent);

            }
        });

        prodect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAppFragment.this, HomeActivity.class);
                startActivity(intent);

            }
        });


        restaurant_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAppFragment.this, HomeAppFragment.class);
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
                Intent intent = new Intent(HomeAppFragment.this, HomeApp.class);
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