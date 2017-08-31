package me.ankit.com.androiduihelper.ui.activity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.ankit.com.androiduihelper.R;
import me.ankit.com.androiduihelper.adapter.CustomPagerAdapter;
import me.ankit.com.androiduihelper.constants.Constants;
import me.ankit.com.androiduihelper.transformers.AccordionTransformer;
import me.ankit.com.androiduihelper.transformers.BackgroundToForegroundTransformer;
import me.ankit.com.androiduihelper.transformers.CubeInTransformer;
import me.ankit.com.androiduihelper.transformers.CubeOutTransformer;
import me.ankit.com.androiduihelper.transformers.DefaultTransformer;
import me.ankit.com.androiduihelper.transformers.DepthPageTransformer;
import me.ankit.com.androiduihelper.transformers.DrawFromBackTransformer;
import me.ankit.com.androiduihelper.transformers.FlipHorizontalTransformer;
import me.ankit.com.androiduihelper.transformers.FlipVerticalTransformer;
import me.ankit.com.androiduihelper.transformers.ForegroundToBackgroundTransformer;
import me.ankit.com.androiduihelper.transformers.ParallaxPageTransformer;
import me.ankit.com.androiduihelper.transformers.RotateDownTransformer;
import me.ankit.com.androiduihelper.transformers.RotateUpTransformer;
import me.ankit.com.androiduihelper.transformers.StackTransformer;
import me.ankit.com.androiduihelper.transformers.TabletTransformer;
import me.ankit.com.androiduihelper.transformers.ZoomInTransformer;
import me.ankit.com.androiduihelper.transformers.ZoomOutPageTransformer;
import me.ankit.com.androiduihelper.transformers.ZoomOutSlideTransformer;
import me.ankit.com.androiduihelper.transformers.ZoomOutTransformer;
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
        initViewsAndVariables();
        initTutorial(mContext, mList, isPageChangeAutomated, isPageIndicatorAllowed);

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
        applyTransformationToViewPager();
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

    /***
     * initialize pager with required transformation
     */
    private void applyTransformationToViewPager() {

        switch(transitionType){
            case Constants.ZOP_TRANSFORMER:
                mPager.setPageTransformer(true, new ZoomOutPageTransformer());

                break;
            case Constants.DEPTH_PAGE_TRANSFORMER:
                mPager.setPageTransformer(true, new DepthPageTransformer());

                break;
            case Constants.BTF_TRANSFORMER:
                mPager.setPageTransformer(true, new BackgroundToForegroundTransformer());

                break;
            case Constants.CUBE_IN_TRANSFORMER:
                mPager.setPageTransformer(true, new CubeInTransformer());

                break;
            case Constants.CUBE_OUT_TRANSFORMER:
                mPager.setPageTransformer(true, new CubeOutTransformer());

                break;
            case Constants.DEFAULT_TRANSFORMER:
                mPager.setPageTransformer(true, new DefaultTransformer());

                break;
            case Constants.DFB_TRANSFORMER:
                mPager.setPageTransformer(true, new DrawFromBackTransformer());

                break;
            case Constants.FLIP_HORIZONTAL_TRANSFORMER:
                mPager.setPageTransformer(true, new FlipHorizontalTransformer());

                break;
            case Constants.FLIP_VERTICAL_TRANSFORMER:
                mPager.setPageTransformer(true, new FlipVerticalTransformer());

                break;
            case Constants.FTB_TRANSFORMER:
                mPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());

                break;
            case Constants.PARALLAX_PAGE_TRANSFORMER:
                mPager.setPageTransformer(true, new ParallaxPageTransformer(R.id.tv_title));

                break;
            case Constants.ROTATE_DOWN_TRANSFORMER:
                mPager.setPageTransformer(true, new RotateDownTransformer());

                break;
            case Constants.ROTATE_UP_TRANSFORMER:
                mPager.setPageTransformer(true, new RotateUpTransformer());

                break;
            case Constants.STACK_TRANSFORMER:
                mPager.setPageTransformer(true, new StackTransformer());

                break;
            case Constants.TABLET_TRANSFORMER:
                mPager.setPageTransformer(true, new TabletTransformer());

                break;
            case Constants.ZOOM_IN_TRANSFORMER:
                mPager.setPageTransformer(true, new ZoomInTransformer());

                break;
            case Constants.ZOOM_OUT_TRANSFORMER:
                mPager.setPageTransformer(true, new ZoomOutTransformer());

                break;
            case Constants.ZOS_TRANSFORMER:
                mPager.setPageTransformer(true, new ZoomOutSlideTransformer());

                break;
            case Constants.ACCORDION_TRANSFORMER:
                mPager.setPageTransformer(true, new AccordionTransformer());

                break;
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_skip){
            finish();
        }
    }
}


