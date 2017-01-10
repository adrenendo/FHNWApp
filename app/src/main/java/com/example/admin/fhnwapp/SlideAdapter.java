package com.example.admin.fhnwapp;

import android.support.v4.view.PagerAdapter;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 04.01.2017.
 */

public class SlideAdapter extends PagerAdapter {
        private static final String TAG = "SlideAdapter";

        Context mContext;
        TextView myCaption;
        ViewPager myPager;

        public SlideAdapter(Context context, TextView caption, ViewPager pager) {
            this.myCaption = caption;
            this.mContext = context;
            this.myPager = pager;
        }

        @Override
        public int getCount() {
            return sliderImagesId.length;
        }

        private int[] sliderImagesId = new int[]{
                R.drawable.graduation,
                R.drawable.innovation_week_1,
                R.drawable.innovation_week_2,
                R.drawable.innovation_week_3,
                R.drawable.innovation_week_4,
                R.drawable.semester_start,
                R.drawable.silicon_valley
        };

        private String[] sliderImageCaption = new String[]{
                "Graduation",
                "Innovation Week 1",
                "Innovation Week 2",
                "Innovation Week 3",
                "Innovation Week 4",
                "Semester start",
                "Silicon Valley"
        };
        @Override
        public boolean isViewFromObject(View v, Object obj) {
            return v == ((ImageView) obj);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int i) {
            ImageView mImageView = new ImageView(mContext);
            mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mImageView.setImageResource(sliderImagesId[i]);

            //TextView myCaption = (TextView) container.findViewById(R.id.tvCaption);
            //Log.i(TAG, "instantiateItem Current position: " + );

            //Log.i(TAG, "instantiateItem: " + i);
            ((ViewPager) container).addView(mImageView, 0);
            return mImageView;
        }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);

        myCaption.setText(sliderImageCaption[myPager.getCurrentItem()]);
        Log.i(TAG, "finishUpdate: " + myPager.getCurrentItem());
    }

    @Override
        public void destroyItem(ViewGroup container, int i, Object obj) {
            ((ViewPager) container).removeView((ImageView) obj);
        }

}
