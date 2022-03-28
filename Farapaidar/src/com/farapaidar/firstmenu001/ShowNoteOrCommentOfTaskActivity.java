package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;


public class ShowNoteOrCommentOfTaskActivity extends Activity {

	public static String GuidTask;


	public static boolean FlagForNoteOrComment;  // True=Note  & False=Comment
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_note_or_comment_of_task);
		
		if (FlagForNoteOrComment == true)
		{
			this.setTitle("‘—Õ ò«—");
		}
		else if (FlagForNoteOrComment == false)
		{
			this.setTitle("ê“«—‘«  ò«—");
		}
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		Tasks task =  db.getSpecificTasksByGuid(GuidTask.toUpperCase());
		
		TextView TitleInNoteOrCommentOfTask = 
				(TextView) findViewById(R.id.txtviewTitleInNoteOrCommentOfTask);
		TitleInNoteOrCommentOfTask.setText(task.getTitle());
		
		
		
		TextView DiscribeInNoteOrCommentOfTask = 
				(TextView) findViewById(R.id.txtviewdiscribeInNoteOrCommentOfTask);
		
		if (FlagForNoteOrComment == true)
		{
			DiscribeInNoteOrCommentOfTask.setText(task.getNote());	
		}
		else if (FlagForNoteOrComment == false)
		{
			DiscribeInNoteOrCommentOfTask.setText(task.getComment());
		}	
		
	}

	

}
