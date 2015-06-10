package com.wj.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Toast.makeText(context, "received in MyBroadcastReceiver",
				Toast.LENGTH_SHORT).show();
		//abortBroadcast();//该方法拦截了有序广播，后面的广播就无法在接收到这条广播了
	}

}
