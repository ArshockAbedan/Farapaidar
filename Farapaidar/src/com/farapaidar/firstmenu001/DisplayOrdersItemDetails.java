package com.farapaidar.firstmenu001;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;

import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class DisplayOrdersItemDetails extends Activity {
	
	public static boolean flagForCreateOrUpdate;
	
	public static String GuidItem; 
	public static String Count;
	public static String ItemTotalValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_orders_item_details);
		

		DatabaseHandler db = new DatabaseHandler(this);

		if (flagForCreateOrUpdate == true)
		{
			
			Goods goods = db.getGoodsByGuid(GuidItem);
			List<Contact> contact = db.getAllContactsTemp();
			
			
			String GuidProdScaleStr = goods.getGuid();
			String GuidCustomerCategoryStr = contact.get(0).getGuidCustomerCategory();
			
			String ComputedFee = FuncForSetPrice(GuidCustomerCategoryStr, GuidProdScaleStr);
			TextView FirstText = (TextView) findViewById(R.id.FirstText);
			FirstText.setText(goods.getID());
			
			TextView SecondText = (TextView) findViewById(R.id.SecondText);
			SecondText.setText(goods.getGoodName());
			
			TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
			ThirdText.setText(ComputedFee);
			//ThirdText.setText(goods.getPrice());
			// HOld QBox Value
			ThirdText.setTag(goods.getQBox());
			
			TextView FourthText = (TextView) findViewById(R.id.FourthText);
			FourthText.setText(goods.getPriceType());
			// Hold Stock Value
			FourthText.setTag(goods.getStock());
			
			TextView FifthText = (TextView) findViewById(R.id.FifthText);
			FifthText.setText(goods.getCountUnit());
			
			TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);   
			itemTotalValue.setText(ItemTotalValue);
		}
		else if (flagForCreateOrUpdate == false)
		{
			GoodsTemp goodstemp = new GoodsTemp();
			goodstemp = db.getSpecificGoodTempByGuid(GuidItem);

			List<Contact> contact = db.getAllContactsTemp();
			
			
			String GuidProdScaleStr = goodstemp.getGuid();
			String GuidCustomerCategoryStr =contact.get(0).getGuidCustomerCategory();
			
			String ComputedFee = FuncForSetPrice(GuidCustomerCategoryStr, GuidProdScaleStr);
			
			TextView FirstText = (TextView) findViewById(R.id.FirstText);
			FirstText.setText(goodstemp.getID());
			
			TextView SecondText = (TextView) findViewById(R.id.SecondText);
			SecondText.setText(goodstemp.getGoodName());
			
			TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
			//ThirdText.setText(goodstemp.getPrice());
			ThirdText.setText(ComputedFee);
			// HOld QBox Value
			ThirdText.setTag(goodstemp.getQBox());
			
			TextView FourthText = (TextView) findViewById(R.id.FourthText);
			FourthText.setText(goodstemp.getPriceType());
			// Hold Stock Value
			FourthText.setTag(goodstemp.getStock());
			
			TextView FifthText = (TextView) findViewById(R.id.FifthText);
			FifthText.setText(goodstemp.getCountUnit());
			
			TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);   
			itemTotalValue.setText(ItemTotalValue);
		}
		
		
		
		
		
		EditText edittxt = (EditText) findViewById(R.id.editTextcount);
		edittxt.setText(Count);
		edittxt.setSelection(edittxt.getText().length());
		edittxt.addTextChangedListener( new TextWatcher() {
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s,
					int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub					
				 int numb = 0;
				 
				 long qLong = 0;
				 long box = 0 ;
				 
				 
				 TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
				 int Price = Integer.parseInt(ThirdText.getText().toString());
				 
				 StringBuilder sb = new StringBuilder(s.length());
				 sb.append(s);
				 String CC = s.toString();
				 
				 if (CC.toString() == ItemTotalValue)
				 {
					 numb = Integer.parseInt(ItemTotalValue);
				 }
				 else if (CC.toString().length() > 0)
				 {
					  numb = Integer.parseInt(s.toString()); 
				 }
				 
				 qLong = numb;
				 
				 long QBOX = Integer.parseInt(ThirdText.getTag().toString());
				 
				  if (QBOX == 0)
				  {
					  qLong = numb;
					  box = 0;
					  
				  }else
				  {
					  while(qLong >= QBOX )
						 {
							 qLong = qLong - QBOX;
							 box ++ ;
						 }
				  }
				
				  Typeface tf = Typeface.createFromAsset(getAssets(),
	                		"fonts/BYekan.ttf");

				 TextView Q = (TextView) findViewById(R.id.TxtViewQValueInDisplayOrderItem);
				 Q.setText(String.valueOf(qLong));
				 Q.setTypeface(tf);
				 
				 TextView Box = (TextView) findViewById(R.id.TxtViewBoxValueInDisplayOrderItem);
				 Box.setText(String.valueOf(box));
				 Box.setTypeface(tf);
			 
				 int totalItem = Price * numb;
																 
				 TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);                    
				 itemTotalValue.setText(String.valueOf(totalItem)); 
			
			}
			
		});
		
		/*
		Button btnSabt = (Button) findViewById(R.id.btnSabtItemsCount);
		btnSabt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DatabaseHandler db = new DatabaseHandler(getApplicationContext());
				
				TextView FourthText = (TextView) findViewById(R.id.FourthText);
				
				
				TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);
				EditText editTextcount = (EditText)  findViewById(R.id.editTextcount);
				
				int valueOFStock = Integer.parseInt(FourthText.getTag().toString());
				int valueOfEnteredCount = Integer.parseInt(editTextcount.getText().toString());
				
				if ( valueOfEnteredCount  > valueOFStock )
				{
					Builder builder = new AlertDialog.Builder(getApplicationContext());
					String msg = "تعداد وارد شده از موجودی کالا بیشتر است";
					builder.create();
				   builder.setCancelable(true);
				   
				}
				
				
				
				
			}
		});
*/
		

	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		 Typeface tf = Typeface.createFromAsset(this.getAssets(),
         		"fonts/BYekan.ttf");
		 
		 
		 TextView FirstText = (TextView) findViewById(R.id.FirstText);
			FirstText.setTypeface(tf);
			
			TextView SecondText = (TextView) findViewById(R.id.SecondText);
			SecondText.setTypeface(tf);
			
			TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
			ThirdText.setTypeface(tf);
			
			TextView FourthText = (TextView) findViewById(R.id.FourthText);
			FourthText.setTypeface(tf);
			
			TextView FifthText = (TextView) findViewById(R.id.FifthText);
			FifthText.setTypeface(tf);
			
			TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);   
			itemTotalValue.setTypeface(tf);
			
			
			EditText edittxt = (EditText) findViewById(R.id.editTextcount);
			edittxt.setText(Count);
			edittxt.setSelection(edittxt.getText().length());
			edittxt.addTextChangedListener( new TextWatcher() {
				
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
				}

				@Override
				public void beforeTextChanged(CharSequence s,
						int start, int count, int after) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					// TODO Auto-generated method stub					
					 int numb = 0;
					 
					 long qLong = 0;
					 long box = 0 ;
					 
					 
					 TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
					 int Price = Integer.parseInt(ThirdText.getText().toString());
					 
					 StringBuilder sb = new StringBuilder(s.length());
					 sb.append(s);
					 String CC = s.toString();
					 
					 if (CC.toString() == ItemTotalValue)
					 {
						 numb = Integer.parseInt(ItemTotalValue);
					 }
					 else if (CC.toString().length() > 0)
					 {
						  numb = Integer.parseInt(s.toString()); 
					 }
					 
					 qLong = numb;
					 
					 long QBOX = Integer.parseInt(ThirdText.getTag().toString());
					 
					  if (QBOX == 0)
					  {
						  qLong = numb;
						  box = 0;
						  
					  }else
					  {
						  while(qLong >= QBOX )
							 {
								 qLong = qLong - QBOX;
								 box ++ ;
							 }
					  }
					

					  Typeface tf = Typeface.createFromAsset(getAssets(),
		                		"fonts/BYekan.ttf");
					  
					  TextView Q = (TextView) findViewById(R.id.TxtViewQValueInDisplayOrderItem);						 
					  Q.setText(String.valueOf(qLong));
					  Q.setTypeface(tf);
						 
						
					  TextView Box = (TextView) findViewById(R.id.TxtViewBoxValueInDisplayOrderItem);						 
					  Box.setText(String.valueOf(box));
					  Box.setTypeface(tf);
				 
					 int totalItem = Price * numb;
																	 
					 TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);                    
					 itemTotalValue.setText(String.valueOf(totalItem)); 
				
				}
				
			});


			
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_orders_item_details, menu);
		return true;
	}

	
	public void registerItemCount(View view){
		DatabaseHandler db = new DatabaseHandler(this);
		
		TextView FourthText = (TextView) findViewById(R.id.FourthText);
		
		
		TextView ThirdText = (TextView) findViewById(R.id.ThirdText);
		
		
		TextView itemTotalValue = (TextView) findViewById(R.id.itemTotalValue);
		EditText editTextcount = (EditText)  findViewById(R.id.editTextcount);
		
		int valueOFStock = Integer.parseInt(FourthText.getTag().toString());
		int valueOfEnteredCount = Integer.parseInt(editTextcount.getText().toString());
		
		//if ( valueOfEnteredCount  > valueOFStock )
		//{

			//Toast.makeText(getApplicationContext(), "تعداد وارد شده از موجودی کالا بیشتر است", Toast.LENGTH_LONG).show();
			
		//}else
		//{ 
				//Goods SelectedgoodTemps = db.getGoodsTempByGuid(GuidItem);
				if (flagForCreateOrUpdate == true)
				{
					Goods Selectedgood = db.getGoodsByGuid(GuidItem);
				GoodsTemp dhtemp = new GoodsTemp(Selectedgood.getGuid(),Selectedgood.getID(),
						Selectedgood.getGoodName(), Selectedgood.getStock(), Selectedgood.getCountUnit(),
						ThirdText.getText().toString(), Selectedgood.getPriceType(), Selectedgood.getQBox(),
						String.valueOf(editTextcount.getText()),
						String.valueOf(itemTotalValue.getText()));
			    db.addGoodsTemp(dhtemp);
			    
				}else if (flagForCreateOrUpdate == false)
				{
					db.updateGoodsTempByGuid(GuidItem, String.valueOf(editTextcount.getText()),
							String.valueOf(itemTotalValue.getText()));
				}
			
				finish();
		//}
		
		 
		
	}
	
	
	public String FuncForSetPrice( String GuidCustomerCategory, String GuidProdScale)
	{
		String returnStr ="";
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		PriceList pricelist = db.getSpecificPriceList(GuidCustomerCategory, GuidProdScale);
		
		if (pricelist != null)
		{
			returnStr = pricelist.getFee();
		}
		else if (pricelist == null)
		{
			PriceList pricelistjustWithGuidProdScale = db.getSpecificPriceList(GuidProdScale);
			if (pricelistjustWithGuidProdScale != null)
			{
				returnStr = pricelistjustWithGuidProdScale.getFee();
			}
			else if (pricelistjustWithGuidProdScale == null)
			{
				returnStr = "0";
			}
			
		}
		
		return returnStr;
	}

}
