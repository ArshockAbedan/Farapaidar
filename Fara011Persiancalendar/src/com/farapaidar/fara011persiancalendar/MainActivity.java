package com.farapaidar.fara011persiancalendar;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


import com.ghasemkiani.util.SimplePersianCalendar;



import android.os.Bundle;
import android.app.Activity;
import android.text.format.Time;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SimplePersianCalendar c = new SimplePersianCalendar();
		
		com.ghasemkiani.util.DateFields t = c.getDateFields();
		
		String Month = String.valueOf(t.getMonth()+1);
		
		
		String CurrentDate = t.getYear() +"/" +  Month + "/"+ t.getDay();
		
		
    	
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ssa");
		String Time =  sdf.format(cal.getTime());
		String timestr = Time;
		
		
		TextView txt = (TextView) findViewById(R.id.txt);
		txt.setText(CurrentDate);
		
		TextView txt1 = (TextView) findViewById(R.id.textView1);
		txt1.setText(timestr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String RepairMonth(int UnCorrectedMonthInt)
	{
		UnCorrectedMonthInt +=1;
		
		if (UnCorrectedMonthInt == 13)
		{		
			UnCorrectedMonthInt = 1;
		}
		
		
		return String.valueOf(UnCorrectedMonthInt);
	}

}
