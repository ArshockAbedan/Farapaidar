package com.farapaidar.firstmenu001;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.farapaidar.firstmenu001.R;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private final String[] ItemsValues;

	public ImageAdapter(Context context, String[] itemsValues) {
		this.context = context;
		this.ItemsValues = itemsValues;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from items.xml
			gridView = inflater.inflate(R.layout.items, null);

			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(ItemsValues[position]);

			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);

			String item = ItemsValues[position];

			
			if (item.equals("موجودی کالا")) {
				imageView.setImageResource(R.drawable.stock);
			} else if (item.equals("اطلاعات مشتریان")) {
				imageView.setImageResource(R.drawable.contact);
			} else if (item.equals("نقشه")) {
				imageView.setImageResource(R.drawable.map);
			} else if (item.equals("ثبت سفارش")) {
				imageView.setImageResource(R.drawable.orderregister);
			} else if (item.equals("برگشت از فروش")) {
				imageView.setImageResource(R.drawable.orderbackregister);
			}else if (item.equals("گالری تصاویر")) {
				imageView.setImageResource(R.drawable.imgallary);
			}else if (item.equals("دریافت اطلاعات از ستاد")) {	
				imageView.setImageResource(R.drawable.receive);			
			}else if (item.equals("ارسال اطلاعات به ستاد")) {					
				imageView.setImageResource(R.drawable.send);				
			}else if (item.equals("گردش کار")) {					
				imageView.setImageResource(R.drawable.cartable);				
			}else if (item.equals("تخلیه اطلاعات")) {					
				imageView.setImageResource(R.drawable.sync);				
			}else if (item.equals("گزارش ریز عملیات")) {				
				imageView.setImageResource(R.drawable.report);				
			}else if (item.equals("مرور سفارشات")) {					
				imageView.setImageResource(R.drawable.report);				
			}else {				
				imageView.setImageResource(R.drawable.ic_launcher);
			}
		
		} else {
			gridView = convertView;
		}

		return gridView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ItemsValues.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}



}
