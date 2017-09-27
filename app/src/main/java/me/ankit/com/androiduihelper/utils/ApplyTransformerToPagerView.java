package me.ankit.com.androiduihelper.utils;

import android.support.v4.view.ViewPager;

import me.ankit.com.androiduihelper.R;
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

/**
 * Developer: ankit
 * Dated: 27/09/17.
 */

public class ApplyTransformerToPagerView {

    /***
     * initialize pager with required transformation
     */
    public static  void applyTransformationToViewPager(ViewPager mPager, String transitionType) {

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
}
