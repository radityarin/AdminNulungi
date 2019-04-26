package com.rai.adminnulungi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.homebutton:
                    FragmentHome homeFragment = new FragmentHome();
                    fragmentTransaction.replace(R.id.main_frame, homeFragment, "Home Fragment");
                    fragmentTransaction.commit();
                    setTitle("Home");
                    return true;
                case R.id.history:
                    FragmentHistory historyFragment = new FragmentHistory();
                    fragmentTransaction.replace(R.id.main_frame, historyFragment,"Histori Fragment");
                    fragmentTransaction.commit();
                    setTitle("History");
                    return true;
                case R.id.notification:
                    FragmentNotification notificationFragment = new FragmentNotification();
                    fragmentTransaction.replace(R.id.main_frame, notificationFragment, "Inbox Fragment");
                    fragmentTransaction.commit();
                    setTitle("Inbox");
                    return true;
                case R.id.profilebutton:
                    FragmentProfile profileFragment = new FragmentProfile();
                    fragmentTransaction.replace(R.id.main_frame, profileFragment, "Profile Fragment");
                    fragmentTransaction.commit();
                    setTitle("Profile");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentHome homeFragment = new FragmentHome();
        fragmentTransaction.replace(R.id.main_frame, homeFragment, "Home Fragment");
        fragmentTransaction.commit();
        setTitle("Home");
    }

    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }
}
