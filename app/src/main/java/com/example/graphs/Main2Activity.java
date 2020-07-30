package com.example.graphs;

import android.content.Intent;
import android.os.Bundle;

import com.example.graphs.DataEntry.salesByBrands;
import com.example.graphs.DataEntry.salesByProduct;
import com.example.graphs.DataEntry.todaySales;
import com.example.graphs.DataEntry.viewSalesByBrands;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
/*import android.support.v7.widget.Toolbar;*/
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.graphs.Sales.Sales_Brand;
import com.example.graphs.Sales.Sales_Category;
import com.example.graphs.Sales.Sales_Date;
import com.example.graphs.Sales.Sales_Product;
import com.example.graphs.Sales.Sales_Stock;
import com.example.graphs.Sales.Sales_today;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    androidx.appcompat.app.ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Selecto Analytics App");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new androidx.appcompat.app.ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.setBackgroundColor(Integer.parseInt("#f1f1f1"));
        toggle.syncState();
       // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.Toogle_color));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);/*
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // Remove hamburger
            toggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        } else {
            super.onBackPressed();/*
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            toggle.setToolbarNavigationClickListener(null);*/
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Fragment fragment=null;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            Intent intent=new Intent(getApplicationContext(),Test_Authentication.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //Group g=findViewById(R.id.ITEMS0);

        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_Sales_today) {

            fragment = new Sales_today();

        } else if (id == R.id.nav_Sales_date) {
            fragment = new Sales_Date();

        } else if (id == R.id.nav_Sales_brand) {
            fragment = new Sales_Brand();

        } else if (id == R.id.nav_Sales_Product) {
            fragment =new Sales_Product();
        } else if (id == R.id.nav_Sales_Category) {
            fragment = new Sales_Category();
        } else if (id == R.id.nav_stock) {
            fragment = new Sales_Stock();
        }
        else if (id==R.id.brandSales)
        {

            fragment=new salesByBrands();
        }
        else if(id==R.id.viewBrandSales)
        {
            fragment=new viewSalesByBrands();
        }
        if (fragment!= null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame,fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        return true;
    }
}
