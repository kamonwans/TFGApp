package com.project.finalyear.thaispellinggame.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.finalyear.thaispellinggame.adapter.ViewPagerAdapter;
import com.project.finalyear.thaispellinggame.fragment.ContactAppFragment;
import com.project.finalyear.thaispellinggame.fragment.ContactUsFragment;
import com.project.finalyear.thaispellinggame.fragment.EditProfileFragment;
import com.project.finalyear.thaispellinggame.fragment.LearningMainFragment;
import com.project.finalyear.thaispellinggame.fragment.TestFragment;
import com.project.finalyear.thaispellinggame.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Button btnContactUs, btnContactApp;
    private ImageView imgCancelSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("ThaiSpellingGame");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setFragment(new ViewPagerAdapter());//init

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            sendToStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home){

            setFragment(new ViewPagerAdapter());

        }else if (id == R.id.nav_learn) {

            setFragment(new LearningMainFragment());

        } else if (id == R.id.nav_exam) {

            setFragment(new TestFragment());

        } else if (id == R.id.nav_profile) {

            setFragment(new EditProfileFragment());

        } else if (id == R.id.nav_setting) {
            dialogSetting();

        } else if (id == R.id.nav_contact) {

            dialogContact();

        }else if (id == R.id.nav_sign_out){

            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialogContact() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_popup_contact);

        btnContactUs = (Button) dialog.findViewById(R.id.btnContactUs);
        btnContactApp = (Button) dialog.findViewById(R.id.btnContactApp);

        btnContactApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ContactAppFragment());
                dialog.cancel();

            }
        });
        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new ContactUsFragment());
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void dialogSetting() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_popup_setting);
        imgCancelSetting = (ImageView) dialog.findViewById(R.id.imgCancelSetting);
        imgCancelSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();

    }
    public void setFragment(Fragment fragment){
        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();
        }
    }
}
