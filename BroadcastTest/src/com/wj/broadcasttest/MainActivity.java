package com.wj.broadcasttest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;
	private LocalReceiver localReceiver;
	private LocalBroadcastManager localBroadcastManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取LocalBroadcastManager的实例
		localBroadcastManager=LocalBroadcastManager.getInstance(this);
		Button button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent intent=new Intent("com.wj.broadcasttest.MY_BROADCAST");*/
				//sendBroadcast(intent);
				/*将sendBroadcast(intent)方法改成sendOrderedBroadcast(intent, null)
				 * 就可以发送有序广播了。sendOrderedBroadcast(intent, null)方法接收两个参数
				 * ，第一个参数是仍然是Intent，第二参数是一个与权限相关的字符串，这里传入的是null。
				 * */
				/*sendOrderedBroadcast(intent, null);//发送有序广播。*/		
				
				
				//发送本地广播
				Intent intent=new Intent("com.wj.broadcasttest.LOCAL_BROADCAST");
				localBroadcastManager.sendBroadcast(intent);//发送本地广播
				}
		});
		
		/*intentFilter=new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver=new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);*/
		
		
		
		//注册本地广播
		intentFilter=new IntentFilter();
		intentFilter.addAction("com.wj.broadcasttest.LOCAL_BROADCAST");
		localReceiver=new LocalReceiver();
		localBroadcastManager.registerReceiver(localReceiver, intentFilter);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		/*unregisterReceiver(networkChangeReceiver);*/
		localBroadcastManager.unregisterReceiver(localReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	class LocalReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "receiver local broadcast",
					Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	
	//继承BroadcastReceiver，重写onReceiver方法，进行广播接收处理。
	class NetworkChangeReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
			if(networkInfo!=null&&networkInfo.isAvailable()){
				/*Toast.makeText(context, "network is Available",
						Toast.LENGTH_LONG).show();*/
			}else{
				Toast.makeText(context, "network is unAvailable",
						Toast.LENGTH_LONG).show();
			}
			
		}
		
	}
	

}
