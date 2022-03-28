package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListviewAdapterOperator extends BaseAdapter {

	
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> list;
    Activity activity;
 
    public ListviewAdapterOperator(Activity activity, @SuppressWarnings("rawtypes") ArrayList<HashMap> list) {
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
       
      }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
 
        // TODO Auto-generated method stub
                ViewHolder holder;
                LayoutInflater inflater =  activity.getLayoutInflater();
 
                if (convertView == null)
                {
                    convertView = inflater.inflate(R.layout.operatorslistview_row, null);
                    holder = new ViewHolder();
                    holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstTextOperator);
                    holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondTextOperator);
                   
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
                holder.txtFirst.setTag(map.get(THIRD_COLUMN).toString());
				}
				
 
            return convertView;
    }
 
	
}
