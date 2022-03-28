package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIFTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SIXTH_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SEVENTH_COLUMN;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farapaidar.firstmenu001.R;

public class ListviewAdapterGoods extends BaseAdapter {

	
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> list;
    Activity activity;
 
    public ListviewAdapterGoods(Activity activity, @SuppressWarnings("rawtypes") ArrayList<HashMap> list) {
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
    	
    	TextView txtSixth;
    	TextView txtFifth;
    	TextView txtFourth;
        TextView txtThird;
        TextView txtSecond;
        TextView txtFirst;
      }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
    	
    	View vi = convertView;
 
        // TODO Auto-generated method stub
                ViewHolder holder;
                LayoutInflater inflater =  activity.getLayoutInflater();
 
                if (convertView == null)
                {
                    convertView = inflater.inflate(R.layout.goodlistview_row, null);
                    holder = new ViewHolder();
                    holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
                    holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
                    holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
                    holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
                    holder.txtFifth = (TextView) convertView.findViewById(R.id.FifthText);
                    holder.txtSixth = (TextView) convertView.findViewById(R.id.SixthText);
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
                holder.txtSixth.setText(map.get(FIRST_COLUMN).toString());
                holder.txtSixth.setTypeface(tf);
				}
				if (! map.get(SECOND_COLUMN).equals(null))
				{
                holder.txtFifth.setText(map.get(SECOND_COLUMN).toString());
                holder.txtFifth.setTypeface(tf);
				}
				Object m =   map.get(THIRD_COLUMN);
				//if (! map.get(THIRD_COLUMN).equals(null))					
				if (m!=null)
				{
                holder.txtFourth.setText(map.get(THIRD_COLUMN).toString());
                holder.txtFourth.setTypeface(tf);
				}
				if (! map.get(FOURTH_COLUMN).equals(null))
				{
                holder.txtThird.setText(map.get(FOURTH_COLUMN).toString());
                holder.txtThird.setTypeface(tf);
				}
				if (! map.get(FIFTH_COLUMN).equals(null))
				{
                holder.txtSecond.setText(map.get(FIFTH_COLUMN).toString());
                holder.txtSecond.setTypeface(tf);
				}
				if (! map.get(SIXTH_COLUMN).equals(null))
				{
                holder.txtFirst.setText(map.get(SIXTH_COLUMN).toString());
                holder.txtFirst.setTypeface(tf);
				}
				if (! map.get(SEVENTH_COLUMN).equals(null))
				{
                holder.txtFirst.setTag(map.get(SEVENTH_COLUMN).toString());
				}
 
            return convertView;
    }
 
	
	
}
