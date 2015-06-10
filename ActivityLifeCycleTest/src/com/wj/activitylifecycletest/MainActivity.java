package com.wj.activitylifecycletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	public static final String TAG="MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState!=null){
			String tempData=savedInstanceState.getString("data_key");
			Log.d(TAG, tempData);
		}
		
		
		//获得组件
		Button startNormalActivity=(Button) findViewById(R.id.start_normal_activity);
		Button startDialogActivity=(Button) findViewById(R.id.start_dialog_activity);
		
		startNormalActivity.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,NormalActivity.class);
				startActivity(intent);
			}
			
		});
		
		
		startDialogActivity.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(MainActivity.this,DialogActivity.class);
				startActivity(intent);
			}
			
		});
		
		
	}

	
	
	
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}







	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause()");
	}







	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(TAG, "onRestart()");
	}







	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume()");
	}







	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart()");
	}







	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop()");
	}







	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}







	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		String tempData="something you just typed";
		/*
		 * onSaveInstanceState方法携带了一个Bundle类型的参数，Bundle提供了一系列的
		 * 方法用于保存数据，具体的可以查看api，主要方法都有key-value参数，来保存数据的。
		 * */
		outState.putString("data_key", tempData);
		Log.d(TAG, "onSaveInstanceState()");
		
	}
	
	
	
	

}
