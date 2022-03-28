package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FragmentOfTasksFirstPage extends DialogFragment {

	public String GuidTask;
	public String TitleTask;
	public View v ;
	static FragmentOfTasksFirstPage newInstance() {
        return new FragmentOfTasksFirstPage();
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	           Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 v = inflater.inflate(R.layout.fragment_of_tasks_first_page, container, false);
		 
		 final DatabaseHandler db = new DatabaseHandler(getActivity());
		 
		// Set onClickEvent for btnWorkRefered
			Button btnWorkRefered = (Button) v.findViewById(R.id.btnWorkReferedInFragmentOfTask);
			btnWorkRefered.setText("ارجاع کار");
			btnWorkRefered.setOnClickListener( new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					 //Context contaxt = getActivity();
					 View vp = (View) v.getParent();
					 Intent intent = new Intent(vp.getContext(), OperatorFirstPage.class);
					 
					 OperatorFirstPage.GuidTask = GuidTask;
					 OperatorFirstPage.TitleTask =  TitleTask;
				
					 OperatorFirstPage.FlagForTaskOrInbox = true;
					 startActivity(intent);
				}  
	       });
		 
			// Set onClickEvent for btnWorkDescribe
						Button btnWorkDescribe = (Button) v.findViewById(R.id.btnWorkDescribeInFragmentOfTask);
						btnWorkDescribe.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View v) {

								 
								 View vp = (View) v.getParent();
								 Intent intent = new Intent(vp.getContext(), ShowNoteOrCommentOfTaskActivity.class);
								 
								 ShowNoteOrCommentOfTaskActivity.GuidTask = GuidTask;
								 ShowNoteOrCommentOfTaskActivity.FlagForNoteOrComment = true;
								 
								 startActivity(intent);
							}  
				       });
		 
						
						
						// Set onClickEvent for btnWorkReports
						Button btnWorkReports = (Button) v.findViewById(R.id.btnWorkReportsInFragmentOfTask);
						btnWorkReports.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View v) {

								 //Context contaxt = getActivity();
								 View vp = (View) v.getParent();
								 Intent intent = new Intent(vp.getContext(), ShowNoteOrCommentOfTaskActivity.class);
								 
								 ShowNoteOrCommentOfTaskActivity.GuidTask = GuidTask;
								 ShowNoteOrCommentOfTaskActivity.FlagForNoteOrComment = false;
								 
								 startActivity(intent);
							}  
				       });
						
						
						// Set onClickEvent for btnDelete
						Button btnDelete = (Button) v.findViewById(R.id.btnDeleteInFragmentOfTask);
						btnDelete.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View view) {
							 Tasks task = db.getSpecificTasksByGuid(GuidTask.toUpperCase());	
							 if (task.getClosed().equalsIgnoreCase("1"))
							 {
								 //View vp = (View) v.getParent();
								 Toast.makeText(getActivity(), "کار " + task.getTitle() + " ارجاع شده است و قابل حذف نمی باشد", 
										 Toast.LENGTH_LONG).show();
								 
								 v.setEnabled(false);
							 }else{
								 db.deleteTasksByGuid(GuidTask.toUpperCase());	
								 View vp = (View) v.getParent();
								 Intent intent = new Intent(vp.getContext(), TaskFirstPage.class);
								 startActivity(intent);
							 }	
							}  
				       });
						
						
						// Set onClickEvent for btnUpdate
						Button btnUpdate = (Button) v.findViewById(R.id.btnUpdateInFragmentOfTask);
						btnUpdate.setOnClickListener( new OnClickListener() {
							
							@Override
							public void onClick(View view) {
								Tasks task = db.getSpecificTasksByGuid(GuidTask.toUpperCase());	
								 if (task.getClosed().equalsIgnoreCase("1"))
								 {
									 Toast.makeText(getActivity(), "کار " + task.getTitle() + " ارجاع شده است و قابل ویرایش نمی باشد", 
											 Toast.LENGTH_LONG).show();
									 
									 v.setEnabled(false);
								 }else{
									 
									 View vp = (View) v.getParent();
									 Intent intent = new Intent(vp.getContext(), DefineWorkActivity.class);
									 DefineWorkActivity.TaskneedUpdate = task;
									 DefineWorkActivity.FlagForCreateOrUpdate = false;
									 startActivity(intent); 
								 }
								
								
							}  
				       });
		 return v;
		
	}



}
