package me.ankit.com.androiduihelper.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import me.ankit.com.androiduihelper.R;
import me.ankit.com.androiduihelper.constants.Constants;

/**
 * Developer: ankit
 * Dated: 28/08/17.
 */

public class SplashActivity extends AppCompatActivity {

    private Runnable splashRunnable;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    /***
     * method to start new activity
     * @param cls class to navigate to
     */
    private void startNewActivity(Class cls) {
        Intent i = new Intent(SplashActivity.this, cls);
        startActivity(i);
        finish();
    }

    /***
     * This handler inside onResume is used to delay next activity launch,
     * so that it can be imitated to user that splash screen is going on
     * */
    @Override
    protected void onResume() {

        handler = new Handler();
        handler.postDelayed(splashRunnable = new Runnable() {
            @Override
            public void run() {
               startNewActivity(PreferencesActivity.class);
            }
        }, Constants.SPLASH_SCREEN_TIME);
        super.onResume();
    }

    /***
     * unregister splash callbacks in onPause
     */
    @Override
    protected void onPause() {
        handler.removeCallbacks(splashRunnable);
        super.onPause();
    }


    @Override
    public void finish() {
        super.finish();
    }

}
