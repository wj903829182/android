package com.jack.sharedpreferencestest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button saveData;
	private Button restoreData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveData=(Button) findViewById(R.id.save_data);
		restoreData=(Button) findViewById(R.id.restore_data);
		saveData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获得SharedPreferences.Editor对象
				SharedPreferences.Editor editor=getSharedPreferences("share",
						MODE_PRIVATE).edit();
				editor.putString("name", "jack");
				editor.putInt("age", 22);
				editor.putBoolean("married", false);
				editor.commit();//提交数据
			}
		});
		
		
		restoreData.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref=getSharedPreferences("share", MODE_PRIVATE);
				String name=pref.getString("name", null);
				int age=pref.getInt("age", 0);
				boolean married=pref.getBoolean("married", false);
				Log.d("MainActivity", "name is "+name);
				Log.d("MainActivity", "age is "+age);
				Log.d("MainActivity", "married is "+married);
				
			}
			
		});
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
