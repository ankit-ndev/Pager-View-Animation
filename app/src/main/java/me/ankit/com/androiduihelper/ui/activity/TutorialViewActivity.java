package me.ankit.com.androiduihelper.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.ankit.com.androiduihelper.R;
import me.ankit.com.androiduihelper.adapter.CustomPagerAdapter;
import me.ankit.com.androiduihelper.utils.ApplyTransformerToPagerView;
import me.ankit.com.androiduihelper.utils.FixedSpeedScroller;


/**
 * Developer: ankit
 * Dated: 28/08/17.
 */
public class TutorialViewActivity extends FragmentActivity implements View.OnClickListener{

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    int currentPage;
    private List<Integer> mList;
    String transitionType;
    PageIndicatorView pageIndicatorView;
    boolean isPageIndicatorAllowed, isPageChangeAutomated;
    private Context mContext;


    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_view);
        if (android.os.Build.VERSION.SDK_INT >= 21)
            manageStatusBarColor();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViewsAndVariables();
        initTutorial(mContext, mList, isPageChangeAutomated, isPageIndicatorAllowed);

    }

    @TargetApi(21)
    private void manageStatusBarColor() {
        Window window = TutorialViewActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(TutorialViewActivity.this ,R.color.status_bar_color));
    }

    /***
     * method to initialize views and variables for the activity
     */
    private void initViewsAndVariables() {

        mContext = TutorialViewActivity.this;
        mPager = findViewById(R.id.pager);
        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        TextView tvSkip = findViewById(R.id.tv_skip);
        tvSkip.setPaintFlags(tvSkip.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        tvSkip.setText(R.string.skip);
        transitionType = getIntent().getBundleExtra("bundle").getCharSequence("transitionType") + "";
        mList = getIntent().getBundleExtra("bundle").getIntegerArrayList("drawableList");
        isPageChangeAutomated = getIntent().getBundleExtra("bundle").getBoolean("isPageChangeAutomated");
        isPageIndicatorAllowed = getIntent().getBundleExtra("bundle").getBoolean("isPageIndicatorAllowed");//isPageChangeAutomated

    }

    /***
     * method to initialize tutorial view which uses pager view.
     * @param context context of the activity which uses the tutorial
     * @param list Integer list of images from drawable
     * @param isPageChangeAllowed boolean value for allowing automated page change
     * @param isPageIndicatorAllowed boolean value for showing page indicator
     */
    public void initTutorial(Context context, List<Integer> list, boolean isPageChangeAllowed, boolean isPageIndicatorAllowed) {
        ApplyTransformerToPagerView.applyTransformationToViewPager(mPager, transitionType);
        if (isPageChangeAllowed) {
            setAutomaticPageChange();
            setSmoothScroller();
        }

        if (isPageIndicatorAllowed) applyPageIndicator();
        else pageIndicatorView.setVisibility(View.GONE);

        CustomPagerAdapter adapter = new CustomPagerAdapter(context, list);
        mPager.setAdapter(adapter);
        currentPage = mPager.getCurrentItem();
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                currentPage = mPager.getCurrentItem();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /***
     * method to show page indicator
     */
    private void applyPageIndicator() {

        pageIndicatorView.setViewPager(mPager);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
    }


    /**
     * Set Smooth and slow scroll between pages.
     */
    private void setSmoothScroller() {

        try {

            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mPager.getContext());
            mScroller.set(mPager, scroller);

        } catch (NoSuchFieldException e) {

            Log.e("Exception", "NoSuchFieldException");

        } catch (IllegalAccessException e) {

            Log.e("Exception", "IllegalAccessException");

        }
    }

    /***
     * Set Automatic Page change with a delay
     */
    private void setAutomaticPageChange() {

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
//                if (currentPage == mList.size() - 1) {
//                    currentPage = 0;
//                }
                mPager.setCurrentItem(currentPage ++, true);
            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 400, 2500);
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_skip){
            finish();
        }
    }
}


