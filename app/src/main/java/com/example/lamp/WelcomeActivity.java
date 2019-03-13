package com.example.lamp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lamp.Adapters.MyFragmentPageAdapter;
import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.WelcomeActivityFragments.FifthFragment;
import com.example.lamp.WelcomeActivityFragments.FirstFragment;
import com.example.lamp.WelcomeActivityFragments.FourthFragment;
import com.example.lamp.WelcomeActivityFragments.SecondFragment;
import com.example.lamp.WelcomeActivityFragments.ThirdFragment;

public class WelcomeActivity extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();

    public static ViewPager viewPager;
    private UserSettings checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        checkUser = new UserSettings(this);
        if (checkUser.getLoggedIn()) {
            goToActivity(new Intent(WelcomeActivity.this, StartActivity.class));
        }
        FifthFragment fifthFragment = new FifthFragment(getApplicationContext());
         Fragment[] fragments = new Fragment[]{firstFragment, secondFragment, thirdFragment, fourthFragment, fifthFragment};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewPager = findViewById(R.id.welcomePager);
        MyFragmentPageAdapter adapter = new MyFragmentPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void goToActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    public  void toastText(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
