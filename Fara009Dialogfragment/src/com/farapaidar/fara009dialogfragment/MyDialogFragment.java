package com.farapaidar.fara009dialogfragment;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyDialogFragment extends DialogFragment {

	
	static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	//getDialog().setTitle("My Dialog Title");
    	
        View v = inflater.inflate(R.layout.activity_my_dialog_fragment, container, false);
        View tv = v.findViewById(R.id.hello_world);
        Button btn = (Button) v.findViewById(R.id.button5);
        btn.setText("Aref");
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Aref1(v);
			}
		});
        
        ((TextView)tv).setText("This is an instance of MyDialogFragment");
        return v;
    }
    
    
    public void Aref1(View v)
    {
    	 Button btn = (Button) v.findViewById(R.id.button5);
    	 btn.setEnabled(false);
    }

}
