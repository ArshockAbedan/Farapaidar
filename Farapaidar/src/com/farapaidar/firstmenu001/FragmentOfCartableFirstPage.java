package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FragmentOfCartableFirstPage extends DialogFragment {
	
	public String GuidTask;
	public String TitleTask;
	
	public String GuidInbox;
	
	public View v ;
	
	static FragmentOfCartableFirstPage newInstance() {
        return new FragmentOfCartableFirstPage();
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	           Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 v = inflater.inflate(R.layout.fragment_of_cartable_first_page, container, false);
		 
		 
		// Set onClickEvent for btnWorkRefered
					Button btnWorkRefered = (Button) v.findViewById(R.id.btnWorkReferedInFragmentOfCartable);
					btnWorkRefered.setText("ÇÑÌÇÚ ˜ÇÑ");
					btnWorkRefered.setOnClickListener( new OnClickListener() {
						
						@Override
						public void onClick(View view) {

							// After this moment, this record is visited! 
							 updateInboxRecordTovisited(GuidInbox.toUpperCase());

							 View vp = (View) v.getParent();
							 Intent intent = new Intent(vp.getContext(), OperatorFirstPage.class);
							 
							 OperatorFirstPage.GuidInbox = GuidInbox;
							 OperatorFirstPage.GuidTask = GuidTask;
							 OperatorFirstPage.TitleTask =  TitleTask;
						
							 OperatorFirstPage.FlagForTaskOrInbox = false;
							 startActivity(intent);
						}  
			       });
					
					
					// Set onClickEvent for btnWorkDescribe
					Button btnWorkDescribeInFragmentOfCartable = (Button) v.findViewById(R.id.btnWorkDescribeInFragmentOfCartable);
					
					btnWorkDescribeInFragmentOfCartable.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View view) {

							 // After this moment, this record is visited! 
							 updateInboxRecordTovisited(GuidInbox.toUpperCase());
							 
							 View vp = (View) v.getParent();
							 Intent intent = new Intent(vp.getContext(), ShowNoteOrCommentOfTaskActivity.class);
			 
							 ShowNoteOrCommentOfTaskActivity.GuidTask = getTaskGuidOfInbox(GuidInbox);
							 ShowNoteOrCommentOfTaskActivity.FlagForNoteOrComment = true;
							 
							 startActivity(intent);

						}
					});
					
					
					
					
					
		// Set onClickEvent for btnInsertWorkReports
					Button btnInsertWorkReportsInFragmentOfCartable = (Button) v.findViewById(R.id.btnInsertWorkReportsInFragmentOfCartable);
					
					btnInsertWorkReportsInFragmentOfCartable.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View view) {
							
							// After this moment, this record is visited! 
							 updateInboxRecordTovisited(GuidInbox.toUpperCase());
							 
							 View vp = (View) v.getParent();
							 Intent intent = new Intent(vp.getContext(), WorkReportsInFragmentOfCartableActivity.class);
							 WorkReportsInFragmentOfCartableActivity.GuidInbox = GuidInbox.toUpperCase();
							 startActivity(intent);
	
						}
					});
					
					

		 return v;
	}
	
	
	private void updateInboxRecordTovisited(String guidInbox)
	{
		DatabaseHandler db = new DatabaseHandler(v.getContext());
		
		Inboxes inbox = db.getSpecificInboxByGuid(guidInbox.toUpperCase());
		
		inbox.setVisited("1");
		
		db.updateInbox(inbox);
	}
	
	private String getTaskGuidOfInbox(String guidInbox)
	{
		DatabaseHandler db = new DatabaseHandler(v.getContext());
		 Inboxes existedInboxRecord = db.getSpecificInboxByGuid(guidInbox.toUpperCase());
		 
		 return existedInboxRecord.getTaskGuid().toUpperCase();
		
	}
}
