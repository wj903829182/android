package com.wj.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
		/*Intent intent=getIntent();
		String data =intent.getStringExtra("extra_data");
		Log.d("SecondActivity", data);*/
		
		//获得按钮组件
		Button button2=(Button) findViewById(R.id.button_2);
		button2.setOnClickListener(new OnClickListener(){//进行监听

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("data_return", "Hello FirstActivity");
				//setResult()方法接收两个参数，第一个参数用于向上一个活动返回处理结果，一般使用
				//RESULT_OK or RESULT_CANCELED,第二个参数则是把带有数据的Intent传递回去，然后调用
				//finish方法来销毁当前活动
				setResult(RESULT_OK,intent);
				finish();//销毁当前活动
			}
			
		});
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.putExtra("data_return", "Hello FirstActivity");
		//setResult()方法接收两个参数，第一个参数用于向上一个活动返回处理结果，一般使用
		//RESULT_OK or RESULT_CANCELED,第二个参数则是把带有数据的Intent传递回去，然后调用
		//finish方法来销毁当前活动
		setResult(RESULT_OK,intent);
		finish();//销毁当前活动
	}

	public static void actionStart(Context context,
			String data1,String data2){
		Intent intent =new Intent(context,SecondActivity.class);
		intent.putExtra("param1", data1);
		intent.putExtra("param2", data2);
	}
	
	
	
	
}
