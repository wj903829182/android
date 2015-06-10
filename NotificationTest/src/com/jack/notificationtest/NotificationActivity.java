package com.jack.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		NotificationManager manager=(NotificationManager) 
				getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(1);
		/*
		 * 我们在cancel()方法中传入了1，这个1是什么意思了？这是我们给这条通知设置的id就是1。
		 * 因此如果，你想要取消哪一条通知，就在cancel()方法中传入该通知的id就行了。
		 * */
	}

}
