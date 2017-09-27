package me.ankit.com.androiduihelper.ui.activity;

import android.annotation.TargetApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import me.ankit.com.androiduihelper.R;

public class AboutActivity {

    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= 21)
            manageStatusBarColor();
    }


    @TargetApi(21)
    private void manageStatusBarColor() {
//        Window window = AboutActivity.this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(AboutActivity.this ,R.color.status_bar_color));
    }
}
