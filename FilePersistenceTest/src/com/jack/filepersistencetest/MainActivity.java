package com.jack.filepersistencetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.edit);
		
		String inputText=load();
		if(!TextUtils.isEmpty(inputText)){
			editText.setText(inputText);
			editText.setSelection(inputText.length());
			Toast.makeText(this, "Restoring succeeded",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void save(String inputText){
		FileOutputStream fileOutputStream=null;//文件输出流对象
		BufferedWriter bufferedWriter=null;//字符缓冲流对象
		try {
			//初始化文件输出流对象
			fileOutputStream=openFileOutput("datafile", Context.MODE_PRIVATE);
			//初始化字符缓冲流对象
			bufferedWriter=new BufferedWriter(new OutputStreamWriter(fileOutputStream));
			//向缓冲内存中写入字符串
			bufferedWriter.write(inputText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				if(bufferedWriter!=null){
					bufferedWriter.close();//关闭文件
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		String inputText=editText.getText().toString();//获取到文本框里面的内容
		save(inputText);//保存文本框中的信息
	}
	
	
	public String load(){
		FileInputStream in=null;//文件输入流
		BufferedReader reader=null;//字符缓冲流
		StringBuffer content=new StringBuffer();//StringBuffer对象
		try {
			in=openFileInput("datafile");//获得文件输入流
			reader=new BufferedReader(new InputStreamReader(in));//获得缓冲对象
			String line="";//
			while((line=reader.readLine())!=null){//读取一行内容
				content.append(line);//把读取的内容添加到content
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();//关闭读取流
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return content.toString();//返回字符串
		
	}
	
}
