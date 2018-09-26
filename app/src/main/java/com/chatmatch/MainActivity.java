package com.chatmatch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import com.chatmatch.fragments.HomeFragment;
import com.chatmatch.fragments.WebViewFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, WebViewFragment.OnFragmentInteractionListener {
    private Context context;

    ActionBarDrawerToggle navigationDrawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView_Main);
        navigationView.setNavigationItemSelectedListener(this);
        navigationDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(navigationDrawerToggle);
        navigationDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setItemTextAppearance(R.style.navigation_fontstyle);

        TextView textViewBuildInfo = findViewById(R.id.textView_BuildInfo);
        try {
            textViewBuildInfo.setText(getVersionInfo());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                    new HomeFragment()).commit();
            getSupportActionBar().setTitle("ChatMatch");

        }
    }


    public String getBuildNumber() throws PackageManager.NameNotFoundException {
        context = getApplicationContext();
        return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA).versionCode);
    }

    public String getVersionNumber() throws PackageManager.NameNotFoundException {
        context = getApplicationContext();
        return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA).versionName;
    }

    public String getVersionInfo() throws PackageManager.NameNotFoundException {
        String buildNumber = getBuildNumber();
        String versionNumber = getVersionNumber();

        String versionInfo = "Version " + buildNumber + "Build " + versionNumber;
        return versionInfo;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
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

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();

        switch (item.getItemId()) {
            case R.id.menuItem_Home:
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.frameLayout, homeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                setActionBarTitle("Chatmatch");
                // set bar titles in strings
                break;
            case R.id.menuItem_Website:
                WebViewFragment webViewFragment = new WebViewFragment();
                bundle.putBoolean("isLogIn", false);
                webViewFragment.setArguments(bundle);
                transaction.replace(R.id.frameLayout, webViewFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                setActionBarTitle("Chatmatch.me");
                // set bar titles in strings
                break;
        }
        drawerLayout.closeDrawers();
        return false;
    }
}


