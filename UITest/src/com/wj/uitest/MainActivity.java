package com.wj.uitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener{

	private Button button1;
	private EditText editText;
	private ImageView imageView;
	private ProgressBar progressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		editText=(EditText) findViewById(R.id.edit_text);
		imageView=(ImageView) findViewById(R.id.image_view);
		progressBar=(ProgressBar) findViewById(R.id.progress_bar);
		/*button1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//添加事件处理逻辑
			}
			
		});*/
		
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
		case R.id.button1:
			//在此处添加事件处理逻辑
			/*String text=editText.getText().toString();
			Toast.makeText(MainActivity.this, text, 
					Toast.LENGTH_SHORT).show();*/
			
			
			//通过ImageView的setImageResource函数改变显示的图片
			/*imageView.setImageResource(R.drawable.note2);*/
			
			//控制进度条的显示和消失
			/*if(progressBar.getVisibility()==View.GONE){
				//getVisibility()方法获取进度条的显示状态
				progressBar.setVisibility(View.VISIBLE);
			}else{
				progressBar.setVisibility(View.GONE);
			}*/
			
			//点击按钮，增加进度条的进度
			/*int progress=progressBar.getProgress();//获取到当前进度条的进度
			progress=progress+10;
			progressBar.setProgress(progress);*/
			
			/*
			 * AlertDialog可以在当前的界面弹出一个对话框，这个对话框是置与所有界面元素之上的，能够
			 * 屏蔽掉其他控件的交互能力，因此一般AlertDialog都用于提示一些非常重要的内容或者警告信息
			 * */
			/*AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
			dialog.setTitle("this is dialog");//对话框标题
			dialog.setMessage("Something import.");//设置提示信息
			dialog.setCancelable(false);
			//为对话框设置确定按钮的点击事件
			dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//进行逻辑处理
				}
			});
			//为对话框设置取消按钮的点击事件
			dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//进行逻辑处理
				}
				
			});
			//显示对话框
			dialog.show();*/
			
			
			
			/*
			 * ProgressDialog和AlertDialog有点类似，都是可以在界面弹出一个对话框，都能够屏蔽
			 * 掉其他控件的交互能力。不同的是，ProgressDialog会在对话框中显示一个进度条，一般是用于
			 *表示当前操作比较耗时，让用户耐心等待。
			 * */
			//创建组件
			ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
			//设置标题
			progressDialog.setTitle("this is progressDialog");
			//设置提示内容
			progressDialog.setMessage("Loading....");
			//setCancelable方法如果传入的false，表示ProgressDialog是不能通过back键取消掉的，
			//这时你就要在代码中做好控制，当数据加载完后必须要调用ProgressDialog的dismiss()方法来关闭
			//对话框，否则ProgressDialog将会一直存在
			progressDialog.setCancelable(true);
			//进行显示
			progressDialog.show();
			
			
			
			
			
			break;
		default:
			break;
		}
	}

}
