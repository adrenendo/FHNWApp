package com.example.admin.fhnwapp;

import android.support.v4.view.PagerAdapter;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
/**
 * Created by admin on 04.01.2017.
 */

public class SlideAdapter extends PagerAdapter {
        Context mContext;

        public SlideAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return sliderImagesId.length;
        }

        private int[] sliderImagesId = new int[]{
                R.drawable.fhnw_logo, R.drawable.main, R.drawable.fhnw_logo,
                R.drawable.fhnw_logo, R.drawable.main, R.drawable.fhnw_logo,
        };

        @Override
        public boolean isViewFromObject(View v, Object obj) {
            return v == ((ImageView) obj);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int i) {
            ImageView mImageView = new ImageView(mContext);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setImageResource(sliderImagesId[i]);
            ((ViewPager) container).addView(mImageView, 0);
            return mImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int i, Object obj) {
            ((ViewPager) container).removeView((ImageView) obj);
        }

}
