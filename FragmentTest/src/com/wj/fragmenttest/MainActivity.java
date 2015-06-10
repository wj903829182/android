package com.wj.fragmenttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button=(Button) findViewById(R.id.button1);
		button.setOnClickListener(this);
		
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
		/*switch(v.getId()){
		case R.id.button1:
			//创建碎片
			AnotherRightFragment fragment=new AnotherRightFragment();
			//获得布局管理器
			FragmentManager fragmentManager=getFragmentManager();
			//获得碎片的事物
			FragmentTransaction transaction=fragmentManager.beginTransaction();
			//替换id为R.id.right_layout里面的内容
			transaction.replace(R.id.right_layout, fragment);
			transaction.addToBackStack(null);
			transaction.commit();//提交事务
			break;
		default:
			break;
		}*/
	}

}
