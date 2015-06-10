package com.jack.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	/*private DownloadBinder mBinder=new DownloadBinder();
	class DownloadBinder extends Binder{
		public void startDownload(){
			Log.d("MyService", "startdownload executed");
		}
		
		public int getProgress(){
			Log.d("MyService", "getProgress executed");
			return 0;
		}
		
	}*/
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		//return mBinder;
		return null;
	}

	/*@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		@SuppressWarnings("deprecation")
		Notification notification=new Notification(R.drawable.ic_launcher,
				"Notification comes",System.currentTimeMillis());
		Intent notificationIntent=new Intent(this,MainActivity.class);
		PendingIntent pendingIntent=PendingIntent.getActivity(this, 0,notificationIntent,
				0);
		notification.setLatestEventInfo(this, "this is title", "this is content",
				pendingIntent);
		startForeground(1, notification);
		
		 可以看到，这里只是修改了onCreate()方法中的代码，相信这部分代码你会非常眼熟。这就是我们前面学习的
		 创建通知的方法。只不过这次在构建出Notification对象并没有使用NotificationManager来将通知显示
		 出来，而是调用了startForeground()方法。这个方法接收两个参数，第一个参数是通知的id，类似于notify()方法
		 的第一个参数，第二个参数则是构建出来的Notification对象。调用startForeground()方法后就会让MyService变成
		 一个前台服务，并在系统状态显示出来。
		 
		 
		Log.d("MyService", "onCreate()");
	}
*/
	/*@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("MyService", "onDestroy()");
	}*/

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		Log.d("MyService", "onStartCommand");
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//处理具体的逻辑
				stopSelf();
			}
			
		}).start();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	

}
