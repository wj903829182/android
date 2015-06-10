package com.jack.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	/*
	 * 首先需要一个NotificationManager来对通知进行管理，可以调用Context的getSystemService()方法
	 * 获取到。getSystemService方法接收一个字符串参数用于确定获取系统的哪个服务，这里我们传入的
	 * Context.NOTIFICATION_SERVICE即可。因此获取NotificationManager的实例就可以写成：
	 * NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	 * 接下来要创建一个Notification对象，这个对象用于存储通知所需的各种信息，我们使用它的构造函数来进行创建。
	 * Notification的有参构造函数接收三个参数，第一个参数用于指定通知的图标，比如项目的res/drawable目录
	 * 下有一张ic_launcher图片，那么这里就可以传入R.drawable.ic_launcher，第二个参数用于指定通知
	 * 的ticker内容，当通知刚被创建的时候，它会在系统的状态栏一闪而过，属于瞬时的提示信息。第三个参数用于
	 * 指定通知被创建的时间，以毫秒为单位，当下拉系统状态栏时，这里指定的时间会显示在相应的通知上。因此创建
	 * 一个Notification对象就可以写成：
	 * Notification notification=new Notification(R.drawable.ic_launcher,
					"this is ticker text",System.currentTimeMillis());
					
	* 创建好了Notification对象后，我们还需要对通知的布局进行设定，这里只需要调用Notification的
	* setLatestEventInfo（）方法就可以给通知设置一个标准布局。这个方法接收四个参数，第一个参数
	* 是Context。第二个参数用于指定通知的标题内容，下拉系统状态栏就可以看到这部分内容。第三个参数用于指定
	* 通知的正文内容，同样下拉系统状态栏就可以看到这部分内容。第四个参数我们暂时用不到，可以传入null。因此
	* 对通知的布局进行设定就可以写成：
	* notification.setLatestEventInfo(this, "this is content title",
					"this is content text", null);
	  以上工作都做完了后，只需要调用NotificationManager的notify()方法就可以让通知显示出来了。
	  notify()方法接收2个参数，第一个参数是id，要保证为每个通知所指定的id都是不同的。第二个参数则是
	  Notification对象，这里直接将我们刚刚创建好的Notification对象传入即可。因此，显示一个通知就可以
	  写成：
	  manager.notify(1,notification);
	* */
	
	private Button sendNotice;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendNotice=(Button) findViewById(R.id.send_notice);
		sendNotice.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.send_notice:
			NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification=new Notification(R.drawable.ic_launcher,
					"this is ticker text",System.currentTimeMillis());
			
			Intent intent=new Intent(this,NotificationActivity.class);
			PendingIntent pi=PendingIntent.getActivity(this, 0,
					intent, PendingIntent.FLAG_CANCEL_CURRENT);
			
			notification.setLatestEventInfo(this, "this is content title",
					"this is content text", pi);
			manager.notify(1,notification);
			break;
		default:
			break;
		}
	}

}
