package com.jack.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private TextView text;//文本组件
	private Button changeText;//按钮
	public static final int UPDATE_TEXT=1;//整型常量
	/*
	 * 定义一个Handler对象，并重写handleMessage()方法，在这里对具体的Message进行处理
	 * ，如果发现Message的what字段的值等于UPDATE_TEXT，就将TextView显示的内容
	 * 改为nice to meet you。
	 * */
	private Handler handler=new Handler(){
    
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case UPDATE_TEXT:
				//在这里进行ui操作
				text.setText("nice to meet you");
			default:
				break;
				}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text=(TextView) findViewById(R.id.text);
		changeText=(Button) findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
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
		/*在点击事件中并没有在子线程中直接进行ui操作，而是创建了一个Message对象，并将它
		 * 的what字段的值指为UPDATE_TEXT，然后调用Handler的sendMessage()方法将
		 * 这条Message发送出去。很快，Handler就会收到这条Message，并在handlerMessage()方法
		 * 中对它进行处理。注意此时handlerMessage()方法中的代码是在主线程当中运行的。所以我们可以
		 * 放心的在这里进行UI操作。接下来对Message携带的what字段值进行判断，如果等于UPDATE_TEXT，
		 * 就将TextView显示的内容改成nice to  meet  you。
		 * 
		 * */
		switch(v.getId()){
		case R.id.change_text:
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message message = new Message();
					message.what=UPDATE_TEXT;
					handler.sendMessage(message);//将Messge对象发送出去
				}
				
			}).start();
			
			break;
		default:
			break;
		}
	}

}
/*
解析异步消息处理机制
Android中的异步消息处理主要由四部分组成，Message，Handler，MessageQueue和Looper。其中
Message和Handler在上面的例子里已经接触了，而MessageQueue和Looper可能比较的陌生。
1.Message：message是在线程之间传递消息，它可以在内部携带少量的信息，用于在不同线程之间交换数据
。比如使用的Message的what字段，除此之外还可以使用arg1和arg2字段来携带一些整形数据，使用obj字段
携带一个Object对象。

2.Handler：顾名思义也就是处理者的意思，它主要用于发送和处理消息的。发送消息一般使用Handler的
sendMessage()方法，而发送的消息经过一系列的辗转处理后，最终会传递到Handler的handleMessage()方法中。

3.MessageQueue：是消息队列的意思，它主要用于存放所有通过Handler发送的消息。这部分消息会一直存在
消息队列中，等待被处理。每个线程中只会有一个MessageQueue对象。

4.Looper：Looper是每个线程中的MessageQueue的管家，调用Looper的loop()方法后，就会进入到一个
无限循环当中，然后每当发现MessageQueue中存在一条消息，就会将它取出，并传递到Handler的hangleMessage()
方法中。每个线程中只会有一个Looper管家。

异步处理的整个流程：首先需要在主线程当中创建一个Handler对象，并重写handleMessage()方法。然后当子线程
中需要进行ui操作时，就创建一个Message对象，并通过Handler将这条消息发送出去。之后这条消息会被添加到MessageQueue
的队列中等待被处理，而Looper则会一直尝试从MessageQueue中取出待处理消息，最后分发回Handler的
handleMessage()方法中。由于Handler是在主线程中创建的，所以此时handlerMessage()方法中的代码也会
在主线程中运行，于是我们在这里可以放心的进行ui操作了。
*/







