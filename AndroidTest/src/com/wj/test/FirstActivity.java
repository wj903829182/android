package com.wj.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("FirstActivity", this.toString());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		Button button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
/*				Toast.makeText(FirstActivity.this, "you clicked button1",
					Toast.LENGTH_SHORT).show();*/
				/*
				 * Intent的使用分为显示的Intent和隐式的Intent，下面的是显示的Intent
				 * Intent有多个构造函数，我使用的是一个简单的的，第一个参数Context要求提供一个启动
				 * 活动的上下文，第二个参数Class则是指定想要启动的目标活动，通过这个构造函数构建出Intent
				 * 的“意图”，然后使用startActivity(intent);启动目标活动。
				 * Intent是android程序中个组件之间进行交互的一种重要的方式，它不紧可以指明当前组想要执行
				 * 的动作，还可以在不同组件之间传递数据。Intent一般可被用于启动活动，服务，以及发送广播等。
				 * */
				/*Intent intent=new Intent(FirstActivity.this,
						SecondActivity.class);
				startActivity(intent);*/
				
				//使用隐式的Intent
				/*Intent intent=new Intent("com.wj.test.activitytest.ACTION_START");
				intent.addCategory("com.wj.activitytest.MY_CATEGORY");
				startActivity(intent);*/
				
				//更多隐式的Intent的用法
				//打开浏览器
				/*
				 * 这里我们指定了Intent的action是Intent.ACTION_VIEW，这是一个android系统内置的动作，
				 * 其常量为android.intent.action.VIEW，然后通过Uri.parse方法，将一个网址字符串解析成一个
				 * Uri对象，在调用Intent的setData（）方法将这个对象传递进去。
				 * */
				/*Intent intent=new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://www.baidu.com"));
				startActivity(intent);*/
				
				
				//实现拨打电话
				/*Intent intent=new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:10086"));
				startActivity(intent);*/
				
				
				//向下一个活动传递数据
				/*
				 * Intent中提供了一系列putExtra()方法的重载，可以把我们想要传递的数据暂存在Intent中
				 * ，启动了另一个活动后，只需把这些数据再从Intent中取出就可以了。
				 * */
				/*String data="Hello SecondActivity";
				Intent intent =new Intent(FirstActivity.this,SecondActivity.class);
				intent.putExtra("extra_data", data);
				startActivity(intent);*/
				
				
				//返回数据给上一个活动
				//Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
				/*
				 * startActivityForResult方法接收2个参数，一个参数是Intent，第二个参数是请求码
				 * ，用于在之后的的回调中判断数据的来源
				 * */
				//startActivityForResult(intent, 1);
				
				
				
				//测试android活动的standard模式
				Intent intent=new Intent(FirstActivity.this,FirstActivity.class);
				startActivity(intent);
			}
			
		});
	}

	
	
	
	
	//重写onActivityResult方法获取返回的结果数据
	//@Override
	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch(requestCode){
		case 1:
			if(resultCode==RESULT_OK){
				String returnedData=data.getStringExtra("data_return");
				Log.d("FirstActivity", returnedData);
			}
			break;
		default:break;
		}
		
	}*/






	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		/**
		 * getMenuInflater()方法能够得到MenuInflater对象，在调用他的inflate方法就可以给
		 * 当前活动创建菜单了。inflate方法接收两个参数，第一个参数用于指定我们通过哪一个资源文件来创建
		 * 菜单，这里当然传入R.menu.main，第二个参数用于指定我们的菜单将添加到哪一个Menu对象中，这里直接
		 * 使用onCreateOptionsMenu(Menu menu)传入的menu参数。然后给这个方法返回true，表。示
		 * 允许创建的菜单显示出来，如果返回false，创建的菜单将无法显示
		 *
		 */
	
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		/*
		 * 通过调用item.getItemId()来判断我们点击的是哪一个菜单项。
		 * */
		switch(item.getItemId()){
		case R.id.add_item:
			Toast.makeText(FirstActivity.this, "you clicked add",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_item:
			Toast.makeText(FirstActivity.this, "you clicked remove",
					Toast.LENGTH_SHORT).show();
			break;
		default:break;
		}
		//return super.onOptionsItemSelected(item);
		return true;
	}

	
	
	
	
}
