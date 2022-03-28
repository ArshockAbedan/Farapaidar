package com.farapaidar.firstmenu001;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class NoteOfOrder extends DialogFragment {

	public String notefragment;
	public View v ;
	static NoteOfOrder newInstance() {
        return new NoteOfOrder();
    }
	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState) {
		 
		 v = inflater.inflate(R.layout.fragment_note_of_order, container, false);
		 
		 EditText editTextNote = (EditText) v.findViewById(R.id.editTextNote);
		 
		 
		 editTextNote.setText(notefragment);
		 editTextNote.setSelection(editTextNote.getText().length());

		 return v;
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		
		 EditText editTextNote = (EditText) v.findViewById(R.id.editTextNote);
		 
		 OrderFirstPage.Note = editTextNote.getText().toString();
		
	}

}
