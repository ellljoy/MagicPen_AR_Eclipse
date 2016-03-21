package com.example.magicpen;

//import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;

import com.example.magicpen.R;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private ImageView btn_how;
    private ImageView Image_start;

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_how = (ImageView) findViewById(R.id.how);
        btn_how.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	// Switch to PageB
                Intent intent = new Intent(MainActivity.this, SwiptPages.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        Image_start= (ImageView) findViewById(R.id.imageView);
        Image_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	// Switch to PageB
                Intent intent = new Intent(MainActivity.this, firstpage.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.mainpic);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private int[] mainImages = new int[] {
                R.drawable.instru1
        };

        @Override
        public int getCount() {
            return mainImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = MainActivity.this;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mainImages[position]);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}
