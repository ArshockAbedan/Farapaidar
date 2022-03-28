package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;


import com.farapaidar.firstmenu001.TouchImageView.OnTouchImageViewListener;
import com.farapaidar.firstmenu001.TouchImageView;

public class Map extends Activity {
	
	private TouchImageView image;

	//Animation zoomin, zoomout; //declared as public
	//ImageView bgImage ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		String filePath = "/sdcard/Farapaidar/map.bmp";
		Bitmap bitmap = BitmapFactory.decodeFile(filePath);
		image = (TouchImageView) findViewById(R.id.img);
		image.setImageBitmap(bitmap);

		
		
		//
		// Set the OnTouchImageViewListener which updates edit texts
		// with zoom and scroll diagnostics.
		//
		image.setOnTouchImageViewListener(new OnTouchImageViewListener() {
			
			@Override
			public void onMove() {
				PointF point = image.getScrollPosition();
				RectF rect = image.getZoomedRect();
				float currentZoom = image.getCurrentZoom();
				boolean isZoomed = image.isZoomed();
				
			}
		});
	}
}


