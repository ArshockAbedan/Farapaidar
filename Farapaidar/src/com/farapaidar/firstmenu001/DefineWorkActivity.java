package com.farapaidar.firstmenu001;

import java.util.List;
import java.util.UUID;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DefineWorkActivity extends Activity {
	
	public static Tasks TaskneedUpdate;
	public static boolean FlagForCreateOrUpdate;  // True=Create  & False=Update

	public String spinnerTypeID;
	public String spinnerTypeName;
	
	public String spinnerPriorityID;
	public String spinnerPriorityName;

	
	
	Spinner spinnerTypeInDefineWork;
	Spinner spinnerPriorityInDefineWork;
	
	String[] TypeSpinItemsIDList;
	String[] TypeSpinItemsNameList;
	
	String[] PrioritySpinItemsIDList;
	String[] PrioritySpinItemsNameList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_define_work);
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		
		
		/// Set Type ComboBox   /////////////////////////////////////////////////////
		
		spinnerTypeInDefineWork = (Spinner) findViewById(R.id.spinnerTypeInDefineWork);
		
		List<TaskTypes> taskTypeList = db.getAllTaskTypes();
		
		TypeSpinItemsIDList  = new String[taskTypeList.size()];
		TypeSpinItemsNameList  = new String[taskTypeList.size()];
		
		for (int i = 0; i < taskTypeList.size(); i++) {
			TypeSpinItemsIDList[i] = taskTypeList.get(i).getID();
			TypeSpinItemsNameList[i] = taskTypeList.get(i).getName();
		}
		
		
		ArrayAdapter<String> adapterTypeSpinner = 
				new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,TypeSpinItemsNameList);
		
		spinnerTypeInDefineWork.setAdapter(adapterTypeSpinner);
		
		
		spinnerTypeInDefineWork.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				 spinnerTypeID =  TypeSpinItemsIDList[position];
				 spinnerTypeName = TypeSpinItemsNameList[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/// Set Priority ComboBox   /////////////////////////////////////////////////////
		
		spinnerPriorityInDefineWork = (Spinner) findViewById(R.id.spinnerPriorityInDefineWork);
		
		List<PriorityTypes> priorityList = db.getAllPriorityTypes();
		
		
		PrioritySpinItemsIDList  = new String[priorityList.size()];
		PrioritySpinItemsNameList  = new String[priorityList.size()];
		
		for (int i = 0; i < priorityList.size(); i++) {
			PrioritySpinItemsIDList[i] = priorityList.get(i).getID();
			PrioritySpinItemsNameList[i] = priorityList.get(i).getName();
		}
		
		
		ArrayAdapter<String> adapterPrioritySpinner = 
				new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,PrioritySpinItemsNameList);
		
		spinnerPriorityInDefineWork.setAdapter(adapterPrioritySpinner);
		
		spinnerPriorityInDefineWork.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				spinnerPriorityID =  PrioritySpinItemsIDList[position];
				spinnerPriorityName = PrioritySpinItemsNameList[position];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////
		
	
		
		if (FlagForCreateOrUpdate == false)  // Update time
		{
			
			EditText edittxtTitleInDefineWork = (EditText) findViewById(R.id.edittxtTitleInDefineWork);
			edittxtTitleInDefineWork.setText(TaskneedUpdate.getTitle().toString());
			
			EditText edittxtNoteinDefineWork = (EditText) findViewById(R.id.edittxtNoteinDefineWork);
			edittxtNoteinDefineWork.setText(TaskneedUpdate.getNote().toString());
			
			spinnerTypeInDefineWork = (Spinner) findViewById(R.id.spinnerTypeInDefineWork);
			spinnerTypeInDefineWork.setSelection(Integer.parseInt(TaskneedUpdate.getTypeTaskId()));
			
			spinnerPriorityInDefineWork = (Spinner) findViewById(R.id.spinnerPriorityInDefineWork);
			spinnerPriorityInDefineWork.setSelection(Integer.parseInt(TaskneedUpdate.getTypePriorityId()));
			
		}
	}

	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if (FlagForCreateOrUpdate == false)  // Update time
		{
			
			EditText edittxtTitleInDefineWork = (EditText) findViewById(R.id.edittxtTitleInDefineWork);
			edittxtTitleInDefineWork.setText(TaskneedUpdate.getTitle().toString());
			
			EditText edittxtNoteinDefineWork = (EditText) findViewById(R.id.edittxtNoteinDefineWork);
			edittxtNoteinDefineWork.setText(TaskneedUpdate.getNote().toString());
			
			spinnerTypeInDefineWork = (Spinner) findViewById(R.id.spinnerTypeInDefineWork);
			spinnerTypeInDefineWork.setSelection(Integer.parseInt(TaskneedUpdate.getTypeTaskId())-1);
			
			spinnerPriorityInDefineWork = (Spinner) findViewById(R.id.spinnerPriorityInDefineWork);
			spinnerPriorityInDefineWork.setSelection(Integer.parseInt(TaskneedUpdate.getTypePriorityId())-1);
			
		}
		
		
	}



	@SuppressLint("DefaultLocale")
	public void RegisterDifindedWork(View view)
	{
		EditText edittxtTitleInDefineWork = (EditText) findViewById(R.id.edittxtTitleInDefineWork);
		if (edittxtTitleInDefineWork.getText().length() < 1)
		{
			Toast.makeText(this, "عنوان کار نمی تواند خالی باشد", Toast.LENGTH_LONG).show();
		}else
		{
			if (FlagForCreateOrUpdate == true)
			{
							
				Tasks task = new Tasks();
				
				UUID GuidTask = UUID.randomUUID();		
				task.setGuid(GuidTask.toString().toUpperCase());
				
				//EditText edittxtTitleInDefineWork = (EditText) findViewById(R.id.edittxtTitleInDefineWork);		
				task.setTitle(edittxtTitleInDefineWork.getText().toString());
		
		
				task.setTypeTaskId(spinnerTypeID.toString());	
				task.setTypeTaskName(spinnerTypeName.toString());		
		
				task.setTypePriorityId(spinnerPriorityID.toString());		
				task.setTypePriorityName(spinnerPriorityName.toString());		
		
				EditText edittxtNoteinDefineWork = (EditText) findViewById(R.id.edittxtNoteinDefineWork);		
				task.setNote(edittxtNoteinDefineWork.getText().toString());	
		
				task.setComment("");
				
				task.setClosed("0");	
		
				DatabaseHandler db = new  DatabaseHandler(this);		
				db.addTasks(task);
				
				Toast.makeText(getApplicationContext(), "کار جدید به لیست کارها اضافه شد", Toast.LENGTH_SHORT).show();
			
				this.finish();
		
		
			}	
			else if (FlagForCreateOrUpdate == false)
			{
			//EditText edittxtTitleInDefineWork = (EditText) findViewById(R.id.edittxtTitleInDefineWork);
			TaskneedUpdate.setTitle(edittxtTitleInDefineWork.getText().toString());
			
			TaskneedUpdate.setTypeTaskId(spinnerTypeID.toString());
			TaskneedUpdate.setTypeTaskName(spinnerTypeName.toString());
			
			TaskneedUpdate.setTypePriorityId(spinnerPriorityID.toString());
			TaskneedUpdate.setTypePriorityName(spinnerPriorityName.toString());
			
			EditText edittxtNoteinDefineWork = (EditText) findViewById(R.id.edittxtNoteinDefineWork);
			TaskneedUpdate.setNote(edittxtNoteinDefineWork.getText().toString());
			
			
			DatabaseHandler db = new  DatabaseHandler(this);
			
			db.updateTasks(TaskneedUpdate);
			
			Toast.makeText(getApplicationContext(),  "کار " + TaskneedUpdate.getTitle() +
					" بروزرسانی شد", Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(getApplicationContext(), TaskFirstPage.class);
			startActivity(intent);		
			
			}

		}
	}

}
