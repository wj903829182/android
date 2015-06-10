package com.jack.smstest;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView sender;
	private TextView content;
	private IntentFilter receiverFilter;//过滤器
	private MessageReceiver messageReceiver;//广播接收器
	private EditText to;//接收人号码
	private EditText msgInput;//短信内容
	private Button send;//发送按钮
	
	private IntentFilter sendFilter;//发送短信过滤器
	private SendStatusReceiver sendStatusReceiver;//发送短信广播接收器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sender=(TextView) findViewById(R.id.sender);
		content=(TextView) findViewById(R.id.content);
		receiverFilter=new IntentFilter();
		receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		receiverFilter.setPriority(100);//设计优先级
		messageReceiver=new MessageReceiver();
		registerReceiver(messageReceiver,receiverFilter);//注册广播
		
		
		to=(EditText) findViewById(R.id.to);
		msgInput=(EditText) findViewById(R.id.msg_input);
		send=(Button) findViewById(R.id.send);
		
		sendFilter=new IntentFilter();
		sendFilter.addAction("SENT_SMS_ACTION");
		sendStatusReceiver=new SendStatusReceiver();
		registerReceiver(sendStatusReceiver,sendFilter);
		
		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * 当send按钮被点击时，会先调用SmsManager的getDefault()方法
				 * 获取到SmsManager的实例，然后再调用它的sendTextMessage()方法就可以
				 * 去发送短信了。sendTextMessage()方法接收5个参数，其中第一个参数用于指定
				 * 接收人的手机号码，第三个参数用于指定短信的内容，其他的几个参数我们暂时用不到，
				 * 直接传入null就可以了。
				 * */
				SmsManager smsManager=SmsManager.getDefault();
				Intent sendIntent=new Intent("SENT_SMS_ACTION");
				PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this,
						0, sendIntent, 0);
				smsManager.sendTextMessage(to.getText().toString(),
						null, msgInput.getText().toString(), 
						pi, null);
				/*
				 * 在send按钮的点击事件里面我们调用了PendingIntent.getBroadcast()方法
				 * 获取到一个PendingIntent对象，并将它作为第四个参数传递到sendTextMessage()
				 * 方法中。然后又注册一个新的广播接收器SendStatusReceiver，这个广播接收器就是专门
				 * 用于监听短信发送状态的，当getResultCode()==RESULT_OK就会提示发送成功，否则
				 * 提示发送失败。
				 * */
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(messageReceiver);//解绑广播
		unregisterReceiver(sendStatusReceiver);//解绑广播
	}


     
	class MessageReceiver extends BroadcastReceiver{
     //定义广播接收器
	
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			/*
			 * 首先从Intent参数中取出一个Bundle对象，然后使用pdu密钥来提取一个SMS pdus数组，其中
			 * 每一个pdu都表示一条短信消息。接着使用SmsMessage的createFromPdu()方法将每一个
			 * pdu字节数组转换为SmsMessage对象，调用这个对象的getOriginatingAddress()方法
			 * 就可以获取到发送短信的发送方号码，调用getMessageBody()方法就可以获取到短信的内容，然后
			 * 将获取到的发送方号码和短信内容显示在TextView上。
			 * */
			Bundle bundle=intent.getExtras();
			Object[] pdus=(Object[]) bundle.get("pdus");//提取短信消息
			SmsMessage[] messages=new SmsMessage[pdus.length];
			for(int i=0;i<messages.length;i++){
				messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			String address=messages[0].getOriginatingAddress();//获取发送号码
			String fullMessage="";
			for(SmsMessage message: messages){
				fullMessage+=message.getMessageBody();//获取短信内容
			}
			sender.setText(address);
			content.setText(fullMessage);
			abortBroadcast();//截断广播
		}
		
	}
	
	
	class SendStatusReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(getResultCode()==RESULT_OK){
				//短信发送成功
				Toast.makeText(context, "send succeed", 
						Toast.LENGTH_LONG).show();;
			}else{
				//短信发送失败
				Toast.makeText(context, "send failed",
						Toast.LENGTH_LONG).show();;
			}
		}
		
	}
	
	
	

}
