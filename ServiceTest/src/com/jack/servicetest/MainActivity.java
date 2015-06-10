package com.jack.servicetest;

//import com.jack.servicetest.MyService.DownloadBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button startService;
	private Button stopService;
	private Button bindService;
	private Button unbindService;
	private Button startIntentService;
	//private MyService.DownloadBinder downloadBinder;
	private ServiceConnection connection=new ServiceConnection() {
		/*
		 * 这里创建了一个ServiceConnection的匿名类，在这里重写了onServiceConnected方法和
		 * onServiceDisconnected方法，这两个方法分别会在活动与服务成功绑定以及解除绑定的时候调用。
		 * 在onServiceConnected方法中，我们又通过向下转型得到了DownloadBinder的实例，有了这个
		 * 实例，活动和服务之间的关系就变得非常紧密了，现在我们可以在活动中根据具体的场景来调用DownloadBinder
		 * 中的任何public方法，及实现了指挥服务干什么，服务就干什么的功能，这里只做了简单的测试，在onServiceConnected
		 * 中调用了DownloadBinder的startDownload()，getProgress()方法。
		 * */
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			/*downloadBinder=(MyService.DownloadBinder) service;
			downloadBinder.startDownload();
			downloadBinder.getProgress();*/
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService=(Button) findViewById(R.id.start_service);
		stopService=(Button) findViewById(R.id.stop_service);
		startService.setOnClickListener(this);
		stopService.setOnClickListener(this);
		
		bindService = (Button) findViewById(R.id.bind_service);
		unbindService = (Button) findViewById(R.id.unbind_service);
		bindService.setOnClickListener(this);
		unbindService.setOnClickListener(this);
		
		
		startIntentService=(Button) findViewById(R.id.start_intent_service);
		startIntentService.setOnClickListener(this);
		
		
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
		case R.id.start_service:
			Intent startIntent =new Intent(this,MyService.class);
			startService(startIntent);//启动服务
			break;
		case R.id.stop_service:
			Intent stopIntent =new Intent(this,MyService.class);
			stopService(stopIntent);//停止服务
			break;
		case R.id.bind_service:
			/*
			 *现在我们需要进行活动和服务的绑定，构建一个Intent对象，然后调用bindService()方法将
			 *MainActivity()和MyService进行绑定。 bindService方法接收三个参数，第一个参数就是
			 *上面创建出的Intent对象，第二个参数就是前面创建出的ServiceConnection的实例，第三个
			 *参数则是一个标志位，这里传入BIND_AUTO_CREATE表示在活动和服务进行绑定后自动创建服务。
			 *这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行。
			 * */
			Intent bindIntent=new Intent(this,MyService.class);
	        bindService(bindIntent, connection, BIND_AUTO_CREATE);//绑定服务
			break;
		case R.id.unbind_service:
			/*
			 * 如果我们想解除活动和服务之间的绑定，调用一下unbindService()方法就可以了。
			 * */
			unbindService(connection);//解绑服务
			break;
		case R.id.start_intent_service:
			//打印主线程的id
			Log.d("MainActivity", "Thread id is"+Thread.currentThread().getId());
			Intent intentService=new Intent(this,MyIntentService.class);
			startService(intentService);
			break;
		default:
			break;
		}
	}

}

/*
 服务也有自己的生命周期，前面我们使用到的onCreate(),onStartCommand(),onBind()和onDestroy()等方法
 都是在服务的生命周期内可能回掉的方法。
    一旦在项目的任何位置调用了Context的startService()方法，相应的服务就会启动起来，并回调onStartCommand()。如果
    这个服务之前还没创建过，onCreate()方法会先于onStartCommand()方法执行。服务启动了之后一直保持运行状态，
    直到stopService()或stopSelf()方法被调用。注意虽然每调用一次startService()方法，onStartCommand()就会
    执行一次，但实际上每个服务都只会存在一个实例。所以不管你调用了多少次startService()方法，只需调用一次stopService()
  或stopSelf()方法，服务就会停止下来了。
  另外，还可以调用Context的bindService()来获取一个服务的持久连接，这时就会回调服务中的onBind()方法。类似地，
  如果这个服务之前还没有创建过，onCreate()方法会先于onBind()方法执行。之后，调用方可以获取到onBind()方法里
  返回的IBinder对象的实例，这样就能自由地和服务进行通信了。只要调用方和服务之间的连接没有断开，服务就会一直保持运行状态。
   当调用了startService()方法后，又去调用stopService()方法，这时服务中的onDestroy()方法就会执行，表示
   服务已经销毁了。类似地，当调用了bindService()方法后，又去调用unbindService()方法，onDestroy()方法也会执行，这
   两种情况都很好理解。但是需要注意，我们是完全有可能对一个服务既调用了startService()方法，又调用了bindService()方法的，
   这种情况下该如何才能让服务销毁掉？根据android系统的机制，一个服务只要被启动或者绑定了之后就会一直处于运行状态，必须要让以上两种条件同时
   不满足，服务才能被销毁。所以，这种情况下需要同时调用stopService()和unbindService()方法，onDestroy()方法才会执行。
 */






