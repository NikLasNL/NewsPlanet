package com.nancompany.newsplanet;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.nancompany.newsplanet.fragments.NewsFragment;
import com.nancompany.newsplanet.fragments.SettingsFragment;
import com.nancompany.newsplanet.fragments.SubscribeFragment;

public class StartActivity extends AppCompatActivity {

    private FragmentManager manager;
    private ActionBar actionBar;
    private Toolbar toolBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        manager = getFragmentManager();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        toolBar = (Toolbar) findViewById(R.id.toolBar);

        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_list_black_24px);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState== null) {
            manager.beginTransaction().replace(R.id.frameLayout, new NewsFragment())
                    .commit();
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.app.Fragment fragment = null ;
                switch (item.getItemId()){
                    case (R.id.feedMenuItem):
                        fragment = new NewsFragment();
                        break;
                    case (R.id.faseMenuItem):
                        fragment = new SubscribeFragment();
                        break;
                    case (R.id.settingsMenuItem):
                        fragment = new SettingsFragment();
                        break;
                }
                manager.beginTransaction().replace(R.id.frameLayout, fragment)
                        .addToBackStack(this.getClass().getName()).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
    public void checkMenu(int id){
        navigationView.getMenu().findItem(id).setChecked(true);
    }

    public void openSub() {
        manager.beginTransaction().replace(R.id.frameLayout,new SubscribeFragment())
                .addToBackStack(this.getClass().toString()).commit();
    }
}
