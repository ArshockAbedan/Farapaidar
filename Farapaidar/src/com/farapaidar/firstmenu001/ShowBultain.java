package com.farapaidar.firstmenu001;

import static com.farapaidar.firstmenu001.Constant.FIRST_COLUMN;
import static com.farapaidar.firstmenu001.Constant.SECOND_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;



public class ShowBultain extends Activity {
	
	
	public static String OperatorGuid;
	public static String ReselerGuid;
	public static String BrancheGuid;
	public static String OperatorFullName;
	
	 @SuppressWarnings("rawtypes")
		private ArrayList<HashMap> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_bultain);
		setTitle("فراپایدار");
		
		
		DatabaseHandler db = new DatabaseHandler(this);

        // Reading all Bultain
        List<Bultain> bultains = db.getAllBultain(); 
        
        
        ListView lview = (ListView)  findViewById(R.id.listViewInBultain);
        
        list =  GridAdapterList(bultains);
        
        ListviewAdapterBultain adapter = new ListviewAdapterBultain(this, list);
        lview.setAdapter(adapter);
		
		
		Button btnContinue = (Button) findViewById(R.id.btnContinueInBultainActivity);
		
		btnContinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class); 
				MainMenuActivity.OperatorGuid = OperatorGuid;
				MainMenuActivity.ReselerGuid = ReselerGuid;
				MainMenuActivity.BrancheGuid = BrancheGuid;
				MainMenuActivity.OperatorFullName = OperatorFullName;
				startActivity(intent);
			
			}
		});
		
		
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////

	@SuppressWarnings({ "rawtypes", "unchecked" })

	private ArrayList<HashMap> GridAdapterList(List<Bultain> bultains)
	{
		list = new ArrayList<HashMap>();

		// Adding Items
		for (Bultain bult : bultains) {        		        	       	 

			HashMap temp = new HashMap();
			temp.put(FIRST_COLUMN,String.valueOf(bult.getId()));
			temp.put(SECOND_COLUMN,bult.getTxt());

			list.add(temp);

		}
		return list;
}


	

	

}
