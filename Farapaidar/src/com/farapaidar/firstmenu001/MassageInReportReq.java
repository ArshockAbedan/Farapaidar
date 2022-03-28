package com.farapaidar.firstmenu001;

import java.util.List;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MassageInReportReq extends  DialogFragment {
	
	public String guidIteminReportReq;
	
	public String FactorIsSended;

	
	
	public View v ;
	static MassageInReportReq newInstance() {
        return new MassageInReportReq();
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	           Bundle savedInstanceState) {
		if (FactorIsSended.equalsIgnoreCase("false"))
		{
		v = inflater.inflate(R.layout.fragment_massage_in_report_req, container, false);
		
		
		Button btnEditInReportReqFragment = (Button) v.findViewById(R.id.btnEditInReportReqFragment);
		btnEditInReportReqFragment.setText("ویرایش");
		
		btnEditInReportReqFragment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), OrderFirstPage.class);
				OrderFirstPage.EditMode = true;
				OrderFirstPage.EditModeguidIteminReportReq = guidIteminReportReq;
				startActivity(intent);
			}
		});
		
		
		
		Button btnDeleteInReportReqFragment = (Button) v.findViewById(R.id.btnDeleteInReportReqFragment);
		btnDeleteInReportReqFragment.setText("حذف");
		
		btnDeleteInReportReqFragment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View bd) {
				// TODO Auto-generated method stub
				
				bd.setEnabled(false);
				DatabaseHandler db = new DatabaseHandler(bd.getContext());
				List<Orders> orders = db.getOrdersWithGuidReq(guidIteminReportReq);
				
				Orders orderscontainFactorGuid = orders.get(0);	
				db.deleteFactorByGuid(orderscontainFactorGuid.getGuidInvcRequest());
				
				for (Orders ords : orders) {
					db.deleteOrder(ords);
				}
				
				//bd.setEnabled(true);
				
				//FragmentManager fragmentManager = getFragmentManager();
                //fragmentManager.popBackStackImmediate();
			}
		});
		
	
		}
		
		////////////////////////////////////////////////////////////////////////////////
		
		else if (FactorIsSended.equalsIgnoreCase("true"))
		{
		v = inflater.inflate(R.layout.fragment_massage_in_report_req_sended, container, false);
		
		
		TextView txtviewInReportReqFragmentTextView =(TextView) v.findViewById(R.id.txtviewInReportReqFragment);
		/*
		Button btnEditInReportReqFragment = (Button) v.findViewById(R.id.btnEditInReportReqFragment);
		btnEditInReportReqFragment.setText("ویرایش");
		
		btnEditInReportReqFragment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(), OrderFirstPage.class);
				OrderFirstPage.EditMode = true;
				OrderFirstPage.EditModeguidIteminReportReq = guidIteminReportReq;
				startActivity(intent);
			}
		});
	
		*/
		}
		
		
		 return v;
		
	}

		
	

}
