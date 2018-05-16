package nitrr.meghal.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import nitrr.meghal.R;
import nitrr.meghal.classes.view.ClassListActivity;
import nitrr.meghal.helper.SharedPrefs;
import nitrr.meghal.login.LoginActivity;

public class SplashScheenActivity extends AppCompatActivity {


    private Handler handler;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        sharedPrefs = new SharedPrefs(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!sharedPrefs.isFirstTimeLaunch()) {
                    if (sharedPrefs.isLoggedIn()) {
                        startActivity(new Intent(SplashScheenActivity.this, ClassListActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScheenActivity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashScheenActivity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 2000);
    }


}
