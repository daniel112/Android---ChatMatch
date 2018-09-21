package com.chatmatch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chatmatch.models.SideMenuModel;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    private SideMenuModel home;
    private SideMenuModel website;

    private ActionBarDrawerToggle navigationDrawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpSideMenuObjects();
        setUpSideMenu();



    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

//            getSupportFragmentManager().beginTransaction().replace(parent.getId(), new HomeFragment()).commit();
//            getSupportActionBar().setTitle(R.string.app_name);
//
//        FragmentManager fragMan = getFragmentManager();
//        FragmentTransaction fragTransaction = fragMan.beginTransaction();
//
//        HomeFragment homeFragment = new HomeFragment();
//        fragTransaction.add(frameLayout.getId(), homeFragment, "homefrag");
//        fragTransaction.commit();

        return super.onCreateView(parent, name, context, attrs);
    }

    //    public Menu

    public View setUpHeaderView() {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setWeightSum(1);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ImageView imageView_Logo = new ImageView(this);
//        imageView_Logo.setImageResource();


        return linearLayout;
    }

    public void setUpSideMenuObjects() {
        // Home object
        home = new SideMenuModel("Home", getDrawable(R.mipmap.ic_launcher), 1, 1, 1);

        // Website Object
        website = new SideMenuModel("Website", getDrawable(R.mipmap.ic_launcher), 1, 1, 2);
    }

    public void setUpSideMenu() {
        drawerLayout = new DrawerLayout(this);
        DrawerLayout.LayoutParams drawerLayoutParams = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        drawerLayoutParams.gravity = Gravity.RIGHT;
        drawerLayout.setLayoutParams(drawerLayoutParams);
        navigationDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(navigationDrawerToggle);
        navigationDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(frameLayoutParams);
        int id = 123;
        frameLayout.setId(id);

        NavigationView navigationView = new NavigationView(this);
        NavigationView.LayoutParams navigationViewParams = new NavigationView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationViewParams.gravity = Gravity.START;
        navigationView.setLayoutParams(navigationViewParams);

        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        NavigationView navigationView_Main = new NavigationView(this);
        NavigationView.LayoutParams navViewParams = new NavigationView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationView_Main.setLayoutParams(navViewParams);
        navigationView_Main.setPadding(10, 0, 10, 0);
        navigationViewParams.gravity = Gravity.END;
        navigationView_Main.setFitsSystemWindows(true);
        navigationView_Main.addHeaderView(setUpHeaderView());
        navigationView_Main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        drawerLayout.openDrawer(GravityCompat.START);
                        return true;
                }
                return false;
            }
        });

        Menu navMenu = navigationView_Main.getMenu();
        MenuItem homeItem = navMenu.add(home.GroupId, home.ItemId, home.Order, home.Title);
        //        homeItem.setIcon(home.Icon);
        MenuItem websiteItem = navMenu.add(website.GroupId, website.ItemId, website.Order, website.Title);
//        websiteItem.setIcon(website.Icon);

        drawerLayout.addView(frameLayout);
        frameLayout.addView(navigationView_Main);
        frameLayout.addView(linearLayout);
        //        navigationView.addView(linearLayout);
//        linearLayout.addView(navigationView_Main);

        setContentView(drawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (navigationDrawerToggle.onOptionsItemSelected(item)) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                drawerLayout.openDrawer(Gravity.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public ActionBarDrawerToggle getNavigationDrawerToggle() {
        return navigationDrawerToggle;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    public void setNavigationDrawerToggleOff() {navigationDrawerToggle.setDrawerIndicatorEnabled(false);}
    public void setNavigationDrawerToggleOn() {navigationDrawerToggle.setDrawerIndicatorEnabled(true);}

}


