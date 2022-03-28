package com.farapaidar.fara003simpledb;


import static com.farapaidar.fara003simpledb.Constant.FIRST_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.SECOND_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.THIRD_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.FOURTH_COLUMN;
import static com.farapaidar.fara003simpledb.Constant.FIFTH_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class listviewAdapter extends BaseAdapter {

	
	@SuppressWarnings("rawtypes")
	public ArrayList<HashMap> list;
    Activity activity;
 
    public listviewAdapter(Activity activity, @SuppressWarnings("rawtypes") ArrayList<HashMap> list) {
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
       
      }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
 
        // TODO Auto-generated method stub
                ViewHolder holder;
                LayoutInflater inflater =  activity.getLayoutInflater();
 
                if (convertView == null)
                {
                    convertView = inflater.inflate(R.layout.listview_row, null);
                    holder = new ViewHolder();
                    holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
                    holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
                    holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
                    holder.txtFourth = (TextView) convertView.findViewById(R.id.FourthText);
                    holder.txtFifth = (TextView) convertView.findViewById(R.id.FifthText);
                    convertView.setTag(holder);
                }
                else
                {
                    holder = (ViewHolder) convertView.getTag();
                }
 
				@SuppressWarnings("rawtypes")
				HashMap map = list.get(position);
				if (! map.get(FIRST_COLUMN).equals(null))
				{
                holder.txtFifth.setText(map.get(FIRST_COLUMN).toString());
				}
				if (! map.get(SECOND_COLUMN).equals(null))
				{
                holder.txtFourth.setText(map.get(SECOND_COLUMN).toString());
				}
				Object m =   map.get(THIRD_COLUMN);
				//if (! map.get(THIRD_COLUMN).equals(null))					
				if (m!=null)
				{
                holder.txtThird.setText(map.get(THIRD_COLUMN).toString());
				}
				if (! map.get(FOURTH_COLUMN).equals(null))
				{
                holder.txtSecond.setText(map.get(FOURTH_COLUMN).toString());
				}
				if (! map.get(FIFTH_COLUMN).equals(null))
				{
                holder.txtFirst.setText(map.get(FIFTH_COLUMN).toString());
				}
 
            return convertView;
    }
 
	
}
