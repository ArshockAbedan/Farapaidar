package com.farapaidar.firstmenu001;




import com.farapaidar.firstmenu001.ImageAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class MainMenuActivity extends Activity {
	
	//XML node keys
	static final String KEY_DataBaseName = "database_name";
	static final String KEY_USER_NAME = "username";
	static final String KEY_PASSWORD = "password";
	static final String KEY_IP_LOCAL = "ip_local";
	static final String KEY_IP_REMOTE = "ip_remote";
	
	
	public static String OperatorGuid;
	public static String ReselerGuid;
	public static String BrancheGuid;
	public static String OperatorFullName;
	GridView gridView;
	
	
	static final String[] Items = {"موجودی کالا","اطلاعات مشتریان","نقشه", "ثبت سفارش" ,"برگشت از فروش" ,"گالری تصاویر",
		"گردش کار", "گزارش ریز عملیات", "مرور سفارشات",
		"دریافت اطلاعات از ستاد","ارسال اطلاعات به ستاد","تخلیه اطلاعات" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		
		
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		
		
		// Set today's Date in Main Menu (Down)
		TextView txtTodayDateValue = (TextView) findViewById(R.id.txtTodayDateValue);
		  
		com.ghasemkiani.util.SimplePersianCalendar c =	
				new com.ghasemkiani.util.SimplePersianCalendar();   						
		com.ghasemkiani.util.DateFields t = c.getDateFields();				    			
			
		@SuppressWarnings("static-access")
		String month = (String)	(c.getPersianMonthName(t.getMonth())).toString();					    				
		String CurrentDate = t.getDay()  +" " +  month.toString() + " "+ t.getYear();
				
		txtTodayDateValue.setText(CurrentDate);
		
		
	     // Set Operator's name in Main Menu (Down)
		TextView txtOperatorNameValue = (TextView) findViewById(R.id.txtOperatorNameValue);
		txtOperatorNameValue.setText(OperatorFullName);
		

			gridView = (GridView) findViewById(R.id.gridView1);
	 
			gridView.setAdapter(new ImageAdapter(this, Items));
	 
	 gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.grid_item_label))
								.getText(), Toast.LENGTH_SHORT).show();

				if(	((TextView) v.findViewById(R.id.grid_item_label))
				.getText() == "اطلاعات مشتریان")
				{
				Intent intent = new Intent(getApplicationContext(), ContactFirstPage.class); 
				startActivity(intent);	
				}
			
				if(	((TextView) v.findViewById(R.id.grid_item_label))
					.getText() == "ثبت سفارش")	
				{
					Intent intent = new Intent(getApplicationContext(), OrderFirstPage.class);
					OrderFirstPage.formType = "1";
					OrderFirstPage.OperatorGuid = OperatorGuid;
					OrderFirstPage.ReselerGuid = ReselerGuid;
					OrderFirstPage.BrancheGuid = BrancheGuid;
					startActivity(intent);	
				}
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "برگشت از فروش")	
					{
					
						Intent intent = new Intent(getApplicationContext(), OrderFirstPage.class);
						OrderFirstPage.formType = "2";
						OrderFirstPage.OperatorGuid = OperatorGuid;
						OrderFirstPage.ReselerGuid = ReselerGuid;
						OrderFirstPage.BrancheGuid = BrancheGuid;
						startActivity(intent);	
					}
			
				
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "موجودی کالا")	
					{
						Intent intent = new Intent(getApplicationContext(), GoodFirstPage.class); 
						startActivity(intent);	
					}
				
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "دریافت اطلاعات از ستاد")		
					{
					RemoteReceiver newFragment = RemoteReceiver.newInstance();
					newFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
				    newFragment.show(getFragmentManager(), "dialog");
						
						//Intent intent = new Intent(getApplicationContext(), SendGoodsToCenter.class); 
						//startActivity(intent);
					}
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "ارسال اطلاعات به ستاد")		
					{
					RemoteSender SendFragment = RemoteSender.newInstance();
					SendFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
					SendFragment.show(getFragmentManager(), "dialog");
						
						//Intent intent = new Intent(getApplicationContext(), SendGoodsToCenter.class); 
						//startActivity(intent);
					}
				
				
				
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "تخلیه اطلاعات")		
					{
					LocalSendReceiver SendFragment = LocalSendReceiver.newInstance();
					SendFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
					SendFragment.show(getFragmentManager(), "dialog");
						
						
					}
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "گزارش ریز عملیات")	
					{
						Intent intent = new Intent(getApplicationContext(), ReportSalesFirstPage.class); 
						startActivity(intent);	
					}
				
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "مرور سفارشات")	
					{
						Intent intent = new Intent(getApplicationContext(), ReportReqstFirstPage.class); 
						startActivity(intent);	
					}
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "گردش کار")	
					{
						Intent intent = new Intent(getApplicationContext(), WorkflowActivity.class); 
						startActivity(intent);	
					}
				
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "نقشه")	
					{
						Intent intent = new Intent(getApplicationContext(), Map.class); 
						startActivity(intent);	
					}
				
				if(	((TextView) v.findViewById(R.id.grid_item_label))
						.getText() == "گالری تصاویر")	
					{
						Intent intent = new Intent(getApplicationContext(), ViewPagerExampleActivity.class); 
						startActivity(intent);	
					}

			}
		});
	
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Set Count of Unsended Orders		
		DatabaseHandler db = new DatabaseHandler(this);
				
		TextView txtviewCountUnsendedValue = (TextView) findViewById(R.id.txtviewCountUnsendedValue);				
		String  UnsendedCountValueStr = String.valueOf(db.getOrdersCount(OperatorGuid.toUpperCase()));		
		txtviewCountUnsendedValue.setText(UnsendedCountValueStr);
	}


	@Override
    public void onBackPressed() {
        // Write your code here
		//android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
        
			
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
	    homeIntent.addCategory( Intent.CATEGORY_HOME );
	    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
	    startActivity(homeIntent);
	    finish();
    }
	


	
}
