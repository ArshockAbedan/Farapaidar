package com.farapaidar.firstmenu001;


import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SEVENTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.EIGHTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.NINTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.TENTH_COLUMN;



import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;


public class ListviewAdapterOrders extends BaseAdapter {

	
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> list;
    Activity activity;
    
    
    public ListviewAdapterOrders(Activity activity, @SuppressWarnings("rawtypes") ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;
    }
    
    
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }
     
        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }
     
        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
     
        private class ViewHolder {
        	
        	TextView txtFirst;
        	TextView txtSecond;
        	TextView txtThird;
        	TextView txtFourth;
        	TextView txtFifth;
        	TextView TextViewcount;
        	TextView itemTotalValue;
        	TextView txtNinth;
        	TextView txtTenth;
          }
        
		@Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
     
            // TODO Auto-generated method stub
                    final ViewHolder holder;
                    LayoutInflater inflater =  activity.getLayoutInflater();
     
                    if (convertView == null)
                    {
                        convertView = inflater.inflate(R.layout.orderlistview_row, null);
                        holder = new ViewHolder();
                        holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
                        holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
                        holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
                        holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
                        holder.txtFifth = (TextView) convertView.findViewById(R.id.FifthText);
                        holder.itemTotalValue = (TextView) convertView.findViewById(R.id.itemTotalValue);
                        holder.TextViewcount = (TextView) convertView.findViewById(R.id.TextViewcount);
                        holder.txtNinth = (TextView) convertView.findViewById(R.id.TxtViewQValueInOrder);
                        holder.txtTenth = (TextView) convertView.findViewById(R.id.TxtViewBoxValueInOrder);
                        convertView.setTag(holder);
                    }
                    else
                    {
                        holder = (ViewHolder) convertView.getTag();
                    }
                    
                    
                    Typeface tf = Typeface.createFromAsset(convertView.getContext().getAssets(),
                    		"fonts/BYekan.ttf");
     
    				@SuppressWarnings("rawtypes")
    				HashMap map = list.get(position);
    				if (! map.get(FIRST_COLUMN).equals(null))
    				{
                    holder.txtFirst.setText(map.get(FIRST_COLUMN).toString());
                    holder.txtFirst.setTypeface(tf);
    				}
    				if (! map.get(SECOND_COLUMN).equals(null))
    				{
                    holder.txtSecond.setText(map.get(SECOND_COLUMN).toString());
                    holder.txtSecond.setTypeface(tf);
    				}				
    				if (! map.get(THIRD_COLUMN).equals(null))
    				{
                    holder.txtThird.setText(map.get(THIRD_COLUMN).toString());
                    holder.txtThird.setTypeface(tf);
    				}
    				if (! map.get(FOURTH_COLUMN).equals(null))
    				{
                    holder.txtFourth.setText(map.get(FOURTH_COLUMN).toString());
                    holder.txtFourth.setTypeface(tf);
    				}
    				if (! map.get(FIFTH_COLUMN).equals(null))
    				{
                    holder.txtFifth.setText(map.get(FIFTH_COLUMN).toString());
                    holder.txtFifth.setTypeface(tf);
    				}    				
    				if (! map.get(SIXTH_COLUMN).equals(null))
    				{
    				holder.TextViewcount.setText(map.get(SIXTH_COLUMN).toString());
    				holder.TextViewcount.setTypeface(tf);
    				}
    				if (! map.get(SEVENTH_COLUMN).equals(null))
    				{
    					CharSequence s = map.get(SEVENTH_COLUMN).toString();
    					
    					int SeparatorLocation = 0;
    					SeparatorLocation =  s.length() % 3;
    					
    					if (SeparatorLocation == 0)
    					{
    						SeparatorLocation =3;
    					}

    					StringBuilder sb = new StringBuilder();
    					for (int i = 0; i < s.length(); i++) 
    					{
							sb.append(s.charAt(i));
							if (i==SeparatorLocation-1)
							{
								if (i!=s.length()-1)
								{
								sb.append(',');
								SeparatorLocation+=3;
								}
							}
						}
    					holder.itemTotalValue.setText(sb.toString());
    					holder.itemTotalValue.setTypeface(tf);
    				//holder.itemTotalValue.setText(map.get(SEVENTH_COLUMN).toString());
    				}
    				if (! map.get(EIGHTH_COLUMN).equals(null))
    				{
    				holder.txtFirst.setTag(map.get(EIGHTH_COLUMN).toString());
    				}
    				if (! map.get(NINTH_COLUMN).equals(null))
    				{
    				holder.txtNinth.setText(map.get(NINTH_COLUMN).toString());
    				holder.txtNinth.setTypeface(tf);
    				}
    				if (! map.get(TENTH_COLUMN).equals(null))
    				{
    				holder.txtTenth.setText(map.get(TENTH_COLUMN).toString());
    				holder.txtTenth.setTypeface(tf);
    				}
     
                return convertView;
        }
     
    	
    }
