package me.ankit.com.androiduihelper.ui.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import me.ankit.com.androiduihelper.R;
import me.ankit.com.androiduihelper.constants.Constants;


/**
 * Developer: ankit
 * Dated: 28/08/17.
 */

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean visible, isTypeSelected, isAutomatedSelected, isPageIndicatorAllowed, isExpanded, isPageCountSelected;
    private ViewGroup transitionsContainer;
    private TextView tvPageIndicator;
    private TextView tvType, tvPageCount;
    private TextView tvAutomated;
    private Button btnExpand, btnNext;
    private int transformationTypePosition, countPosition;
    private ArrayList<Integer> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_prefernces);
        if (android.os.Build.VERSION.SDK_INT >= 21)
            manageStatusBarColor();
        initViewsAndVariables();

    }

    @TargetApi(21)
    private void manageStatusBarColor() {
        Window window = PreferencesActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(PreferencesActivity.this ,R.color.status_bar_color));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mList.clear();
        mList.add(R.drawable.a1);
        mList.add(R.drawable.a2);
        mList.add(R.drawable.a3);
        mList.add(R.drawable.a4);
        mList.add(R.drawable.a5);
        mList.add(R.drawable.a6);
        mList.add(R.drawable.a7);
        mList.add(R.drawable.a8);
        mList.add(R.drawable.a9);
        mList.add(R.drawable.a10);
    }

    private void initViewsAndVariables() {

        mList = new ArrayList<>();
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        tvPageIndicator = transitionsContainer.findViewById(R.id.tv_page_indicator);
        tvType =  transitionsContainer.findViewById(R.id.tv_type);
        tvPageCount =  transitionsContainer.findViewById(R.id.tv_page_count);
        tvAutomated = transitionsContainer.findViewById(R.id.tv_automated);
        btnExpand = transitionsContainer.findViewById(R.id.btn_expand);
        btnNext = transitionsContainer.findViewById(R.id.btn_next);
    }

    @Override
    public void onClick(View view) {
        final CharSequence[] mCountArray = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};
        final CharSequence[] mTransformationTypeArray = {

                Constants.ACCORDION_TRANSFORMER,
                Constants.BTF_TRANSFORMER,
                Constants.CUBE_OUT_TRANSFORMER,
                Constants.DEFAULT_TRANSFORMER,
                Constants.DEPTH_PAGE_TRANSFORMER,
                Constants.DFB_TRANSFORMER,
                Constants.FTB_TRANSFORMER,
                Constants.STACK_TRANSFORMER,
                Constants.TABLET_TRANSFORMER,
                Constants.ZOOM_IN_TRANSFORMER,
                Constants.ZOS_TRANSFORMER,
        };



        switch (view.getId()){

            case R.id.btn_next:
                String transformationType = Constants.DEFAULT_TRANSFORMER;
                if (isTypeSelected) {
                    transformationType =  mTransformationTypeArray[transformationTypePosition] + "";
                }
                int count = 10;
                if (isPageCountSelected){
                    count = Integer.parseInt(mCountArray[countPosition] + "");
                }
                rectifyListCount(count);

                Intent intent = new Intent(PreferencesActivity.this, TutorialViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("drawableList", mList);
                bundle.putCharSequence("transitionType", transformationType);
                bundle.putBoolean("isPageChangeAutomated", isAutomatedSelected);
                bundle.putBoolean("isPageIndicatorAllowed", isPageIndicatorAllowed);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                break;

            case R.id.btn_expand:

                if (isExpanded){
                    btnExpand.setText(R.string.tap_here_to_continue);
                    isExpanded = false;
                }
                else{

                    btnExpand.setText(R.string.tap_here_to_collapse);
                    isExpanded = true;
                }
                TransitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                tvPageIndicator.setVisibility(visible ? View.VISIBLE : View.GONE);
                tvType.setVisibility(visible ? View.VISIBLE : View.GONE);
                tvPageCount.setVisibility(visible ? View.VISIBLE : View.GONE);
                tvAutomated.setVisibility(visible ? View.VISIBLE : View.GONE);
                btnNext.setVisibility(visible ? View.VISIBLE : View.GONE);
                break;

            case R.id.tv_type:
                isTypeSelected = true;
                tvType.setText(R.string.type_of_tra);
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(mTransformationTypeArray, 0, null)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                transformationTypePosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                String text = tvType.getText().toString() + " : " +  mTransformationTypeArray[transformationTypePosition];
                                tvType.setText(text);
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;


            case R.id.tv_page_count:
                isPageCountSelected = true;
                tvPageCount.setText(R.string.page_count);
                new AlertDialog.Builder(this)
                        .setSingleChoiceItems(mCountArray, 0, null)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                countPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                String text = tvPageCount.getText().toString() + " : " +  mCountArray[countPosition];
                                tvPageCount.setText(text);
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;

            case R.id.tv_automated:

                isAutomatedSelected = !isAutomatedSelected;
                if (isAutomatedSelected) tvAutomated.setText(R.string.allow_automatic_page_change);
                else tvAutomated.setText(R.string.not_allow_automatic_page_change);

                break;

            case R.id.tv_page_indicator:
                isPageIndicatorAllowed = !isPageIndicatorAllowed;
                if (isPageIndicatorAllowed) tvPageIndicator.setText(R.string.page_indicator_allowed);
                else tvPageIndicator.setText(R.string.page_indicator_not_allowed);
                break;
        }
    }

    /***
     * method to change the image drawable list to required number of items in it
     * @param count number of items required
     */
    private void rectifyListCount(int count) {
        int requiredItems = 10 - count;
        for (int i = 0; i < requiredItems; i ++ ){
            mList.remove(mList.size()-1);
        }
    }
}
