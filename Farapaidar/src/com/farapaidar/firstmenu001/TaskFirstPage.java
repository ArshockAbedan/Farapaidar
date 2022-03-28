package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;
import static com.farapaidar.firstmenu001.Constant.THIRD_COLUMN;
import static com.farapaidar.firstmenu001.Constant.FOURTH_COLUMN;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;

public class TaskFirstPage extends Activity {
	
	@SuppressWarnings("rawtypes")
	private ArrayList<HashMap> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_first_page);

		DatabaseHandler db = new DatabaseHandler(this);
		
		// Reading Tasks
        EditText editTxtInTaskFirstPage  = (EditText) findViewById(R.id.editTxtInTaskFirstPage);
        List<Tasks> tasks = db.getAllTasks("1",editTxtInTaskFirstPage.getText().toString());

        ListView lview = (ListView) findViewById(R.id.listViewTaskInTaskFirstPage);
        
        list =  GridAdapterList(tasks);
        ListviewAdapterTasks adapter = new ListviewAdapterTasks(this, list);
        lview.setAdapter(adapter);
        
        
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
	        	  OnitemClickFunc(hashMapItem,  position,id);
				return true;
	        	  }
		});
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(getApplicationContext(), WorkflowActivity.class);
		startActivity(intent);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		// Reading Tasks
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        
        List<Tasks> tasks =  new ArrayList<Tasks>();
     
        // get selected radio button from radioGroup
        int selectedRgId = rg.getCheckedRadioButtonId();
        
        RadioButton selectedRadioBtn = (RadioButton) findViewById(selectedRgId);
        
        EditText editTxtInTaskFirstPage  = (EditText) findViewById(R.id.editTxtInTaskFirstPage);
        
        if (selectedRadioBtn.getText().toString().equalsIgnoreCase("ÇÑÌÇÚ ÔÏå"))
        {
        	// Is Refered (in TaskFirstPage Form) was selected!
        	
             tasks = db.getAllTasks("1",editTxtInTaskFirstPage.getText().toString());
        }
        else if (selectedRadioBtn.getText().toString().equalsIgnoreCase("ÇÑÌÇÚ äÔÏå"))
        {
        	// Is Not Refered (in TaskFirstPage Form) was selected!
             tasks = db.getAllTasks("0",editTxtInTaskFirstPage.getText().toString());
        }
 
        
        ListView lview = (ListView) findViewById(R.id.listViewTaskInTaskFirstPage);
        
        list =  GridAdapterList(tasks);
        ListviewAdapterTasks adapter = new ListviewAdapterTasks(this, list);
        lview.setAdapter(adapter);
        
        
        lview.setOnItemLongClickListener(new OnItemLongClickListener() {

        	@SuppressWarnings("rawtypes")
			HashMap hashMapItem = new HashMap();
        	
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long id) {
	        	  OnitemClickFunc(hashMapItem,  position,id);
				return true;
	        	  }
		});
		
		
	}
	

	
	
	// making header & Contents of grid's Adapter  
			@SuppressWarnings({ "rawtypes", "unchecked" })
			private ArrayList<HashMap> GridAdapterList(List<Tasks> tasksList)
			{
				   list = new ArrayList<HashMap>();

				    // Adding Items
				        for (Tasks task : tasksList) {        		        	       	 
				        	 
				             HashMap temp = new HashMap();
				                 temp.put(FIRST_COLUMN,task.getTitle());
				                 temp.put(SECOND_COLUMN,task.getTypeTaskName());
				                 temp.put(THIRD_COLUMN, task.getTypePriorityName());
				                 temp.put(FOURTH_COLUMN, task.getGuid());
				                 
				             list.add(temp);
				        }
				        
				        return list;
			}
			
			
			
			// Run after an Item (a TaskItem) clicked in List(DataGrid)
			@SuppressWarnings("rawtypes")
			public void OnitemClickFunc(HashMap hashMapItem, int position, long id)
			{
				  hashMapItem =  (HashMap) ((ListView) findViewById(R.id.listViewTaskInTaskFirstPage)).getItemAtPosition(position);

				  FragmentOfTasksFirstPage fragment = FragmentOfTasksFirstPage.newInstance();
				  fragment.GuidTask = hashMapItem.get(FOURTH_COLUMN).toString();
				  fragment.TitleTask = hashMapItem.get(FIRST_COLUMN).toString();
				  fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
				  fragment.show(getFragmentManager(), "dialog");
					
				  //String ItemName =  hashMapItem.get(FIRST_COLUMN).toString();

					//Toast.makeText(getApplicationContext(),ItemName, Toast.LENGTH_SHORT).show();  
	  				
			}
			
			
			public void SearchButtonFunc(View view)
			{
				DatabaseHandler db = new DatabaseHandler(this);
				// Reading Tasks
		        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		        
		        List<Tasks> tasks =  new ArrayList<Tasks>();
		     
		        // get selected radio button from radioGroup
		        int selectedRgId = rg.getCheckedRadioButtonId();
		        
		        RadioButton selectedRadioBtn = (RadioButton) findViewById(selectedRgId);
		        EditText editTxtInTaskFirstPage  = (EditText) findViewById(R.id.editTxtInTaskFirstPage);
		        
		        if (selectedRadioBtn.getText().toString().equalsIgnoreCase("ÇÑÌÇÚ ÔÏå"))
		        {
		        	// Is Refered (in TaskFirstPage Form) was selected!
		        	
		             tasks = db.getAllTasks("1",editTxtInTaskFirstPage.getText().toString());
		        }
		        else if (selectedRadioBtn.getText().toString().equalsIgnoreCase("ÇÑÌÇÚ äÔÏå"))
		        {
		        	// Is Not Refered (in TaskFirstPage Form) was selected!
		             tasks = db.getAllTasks("0",editTxtInTaskFirstPage.getText().toString());
		        }
		        
		        
		        ListView lview = (ListView) findViewById(R.id.listViewTaskInTaskFirstPage);
		       
		        list =  GridAdapterList(tasks);
		        ListviewAdapterTasks adapter = new ListviewAdapterTasks(this, list);
		        lview.setAdapter(null);
		        lview.setAdapter(adapter);
		 
			}
			
			

}
