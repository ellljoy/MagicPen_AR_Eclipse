package com.example.magicpen;

import com.example.magicpen.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;


public class Instruction extends Activity {

    private ImageView btn_play2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_intruc);

        btn_play2 = (ImageView) findViewById(R.id.btn_play);
        btn_play2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	// Switch to PageB
                Intent intent = new Intent(Instruction.this, firstpage.class);
                startActivity(intent);
                Instruction.this.finish();
            }

        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.subpager);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mImages = new int[] {

                R.drawable.instru2,
                R.drawable.instru3,
                R.drawable.instru4,
                R.drawable.instru5
        };

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = Instruction.this;
            ImageView imageView = new ImageView(context);
//            int padding = context.getResources().getDimensionPixelSize(
//                    R.dimen.activity_horizontal_margin);
//            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

}
