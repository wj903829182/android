package com.jack.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

	/*
	 这里首先是提供了一个无参的构造函数，并且必须在其内部调用父类的有参构造函数。然后要在子类中去实现
	 onHandleIntent()这个抽象方法，在这个方法中可以处理一些具体的逻辑，而且不用担心ANR的问题，因为
	 这个方法已经是在子线程中运行的了。这里为了证实一下，我们在onHandleIntent()方法中打印了当前线程的id。
	 另外根据IntentService的特性，这个服务在运行结束后应该是会自动停止的，所以我们又重写了onDestroy()方法，在
	 这里也打印l一行日志，以证实是不是停止掉了。
	 */
	public MyIntentService() {
		super("MyIntentService");//调用父类的有参构造函数
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		//打印当前线程的id
		Log.d("MyIntentService", "Thread id is "+Thread.currentThread().getId());
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("MyIntentService", "onDestroy() executed");
	}

	
}
