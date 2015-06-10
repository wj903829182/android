package com.wj.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		Toast.makeText(context, "received in AnotherBroadcastReceiver",
				Toast.LENGTH_SHORT).show();
	}

}
