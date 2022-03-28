package com.farapaidar.firstmenu001;

import java.io.File;
import java.util.ArrayList;

import com.farapaidar.firstmenu001.ExtendedViewPager;
import com.farapaidar.firstmenu001.R;
import com.farapaidar.firstmenu001.TouchImageView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.v4.view.PagerAdapter;

public class ViewPagerExampleActivity extends Activity {
	public static ArrayList<String> imagespath = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager_example);
		File sdDir = null;
		imagespath = new ArrayList<String>();
		/*
		String secStore = System.getenv("SECONDARY_STORAGE");
		if (secStore == null)
		{
			//sdDir = new File("/sdcard/Farapaidar/Images/");
		}else{
			secStore += "/Farapaidar/Images/";
			sdDir = new File(secStore);
		}
		*/
		sdDir = new File("/sdcard/Farapaidar/Images/");
		File[] sdDirFiles = sdDir.listFiles();
		for(File singleFile : sdDirFiles)
		{
			imagespath.add(singleFile.getAbsolutePath().toString()); 
		}
		
		
		ExtendedViewPager mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TouchImageAdapter());
        
	}

	
	static class TouchImageAdapter extends PagerAdapter {

	       // private static int[] images = { R.drawable.nature_1, R.drawable.nature_2, R.drawable.nature_3, R.drawable.nature_4, R.drawable.nature_5 };
	       // private static int[] images = {};
		 
	        @Override
	        public int getCount() {
	        	return imagespath.size();
	        	//return images.length;
	        }

	        @Override
	        public View instantiateItem(ViewGroup container, int position) {
	        	
	        	String filePath = imagespath.get(position);
	    		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
	            TouchImageView img = new TouchImageView(container.getContext());
	            img.setImageBitmap(bitmap);
	            //img.setImageResource(images[position]);
	            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
	            return img;
	        }

	        @Override
	        public void destroyItem(ViewGroup container, int position, Object object) {
	            container.removeView((View) object);
	        }

	        @Override
	        public boolean isViewFromObject(View view, Object object) {
	            return view == object;
	        }

	    }
	}