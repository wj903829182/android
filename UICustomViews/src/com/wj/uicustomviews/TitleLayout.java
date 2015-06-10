package com.wj.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {

	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		/*
		 * 重写LinearLayout中的带有2个参数的构造函数，在布局中引入TitleLayout控件就会调用这个构造函数
		 * 然后在构造函数中需要对标题栏布局进行动态加载，这就是要借助LayoutInflater来实现了。通过LayoutInflater的
		 * from()方法可以构建出一个LayoutInflater对象，然后调用inflate()方法就可以动态加载一个布局文件，inflate方法
		 * 接收2个参数数，第一个参数是要加载的布局的id，这里我们传入R.layout.title，第二个参数是给加载好的布局在添加一个父布局，
		 * 这里我们想要指定为TitleLayout，于是就传入this
		 * 
		 * */
		LayoutInflater.from(context).inflate(R.layout.title, this);
		//对按钮进行事件处理
		Button titleBack=(Button) findViewById(R.id.title_back);
		Button titleEdit=(Button) findViewById(R.id.title_edit);
		titleBack.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Activity)getContext()).finish();
			}
			
		});
		
		
		titleEdit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "you clicked edit button",
						Toast.LENGTH_SHORT).show();
			}
			
		});
		
		
		
	}
	
	

}
