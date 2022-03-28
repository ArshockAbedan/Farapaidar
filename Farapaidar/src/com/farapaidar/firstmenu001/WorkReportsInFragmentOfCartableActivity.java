package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class WorkReportsInFragmentOfCartableActivity extends Activity {
	
	public static String GuidInbox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_reports_in_fragment_of_cartable);
		
		//Context context =  this.getBaseContext();
		
		final DatabaseHandler db = new DatabaseHandler(this);
		final Inboxes inbox =  db.getSpecificInboxByGuid(GuidInbox.toUpperCase());
		
		final Tasks task = db.getSpecificTasksByGuid(inbox.getTaskGuid().toUpperCase());
		
		TextView WorkTitle = (TextView)  findViewById(R.id.TxtViewWorkTitleInWorkReportsInFrgOfcartable);
		WorkTitle.setText(task.getTitle().toString());
		
		
		
		TextView PreviousReport = (TextView)  findViewById(R.id.TxtViewPreviousReportInWorkReportsInFrgOfcartable);
		PreviousReport.setText(task.getComment().toString());
		
		
		Button btnInsert = (Button) findViewById(R.id.btnInsertInWorkReports);
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				EditText CurrentReportEditTxt = (EditText) findViewById(R.id.editTxtCurrentReportInWorkReportsInFrgOfcartable);
				String newEnteredReportStr = inbox.getOperatorName() + " :\n    " + CurrentReportEditTxt.getText().toString();
				
				String CurrentReport =   task.getComment() + "\n" +  newEnteredReportStr + "\n" ;
				
				task.setComment(CurrentReport);
				
				db.updateTasks(task);
				
				View vp = (View) view.getParent();
				Toast.makeText(vp.getContext(), "ê“«—‘ ò«— œ—Ã ‘œ", Toast.LENGTH_SHORT).show();
				
				finish();
				
			}
		});
		
	}


}
