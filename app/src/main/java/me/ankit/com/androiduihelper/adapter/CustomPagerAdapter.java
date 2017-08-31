package me.ankit.com.androiduihelper.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import me.ankit.com.androiduihelper.R;

/**
 * Developer: ankit
 * Dated: 28/08/17.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Integer> mList;
    private Context mContext;

    public CustomPagerAdapter(Context context, List<Integer> list) {

        mList = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        ImageView imageView = itemView.findViewById(R.id.iv_pager_item);
        Glide.with(mContext).load(mList.get(position)).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}