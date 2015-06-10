package com.jack.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private MyDatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获得数据库操作类对象
		dbHelper=new MyDatabaseHelper(this, "BookStore.db", 
				null, 2);
		Button createDatabase=(Button) findViewById(R.id.create_database);
		createDatabase.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * 在onCreate方法中构建了一个MyDatabaseHelper对象，并且通过构造函数的参数
				 * 将数据库名指定为BookStore.db，版本号为1，然后在create database按钮的点击
				 * 事件里调用了getWritableDatabase()方法，这样当第一次点击create database按钮时
				 * 就会检测当前程序并没有BookStore.db这个数据库，于是会创建该数据库并调用MyDatabaseHelper
				 * 中的onCreate()方法，这样Book表也就得到了创建，然后会弹出一个Toast提示创建成功。再次点击
				 * create database按钮时，会发现此时已经存在BookStore.db数据库了，因此不会再创建一次了。
				 * 
				 * */
				dbHelper.getWritableDatabase();
			}
		});
		
		
		//添加数据
		Button addData=(Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				ContentValues values=new ContentValues();
				//开始组装第一条数据
				values.put("name", "the da vinci code");
				values.put("author", "jack");
				values.put("pages", 500);
				values.put("price", 18.98);
				db.insert("book", null, values);//插入第一条数据
				values.clear();
				//开始组装第二条数据
				values.put("name", "the lost symbol");
				values.put("author", "jack");
				values.put("pages", 600);
				values.put("price", 56.87);
				db.insert("book", null, values);//插入第二条数据
			}
			
		});
		
		
		//更新数据
		Button updateDate=(Button) findViewById(R.id.update_data);
		updateDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				ContentValues values=new ContentValues();
				values.put("price", 99.9);
				db.update("book", values, "name=?", 
						new String[]{"the da vinci code"});
			}
		});
		
		//删除数据
		Button deleteButton=(Button) findViewById(R.id.delete_data);
		deleteButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				db.delete("book", "pages>?", new String[]{"500"});
				
			}
			
		});
		
		
		//查询数据
		Button queryButton=(Button) findViewById(R.id.query_data);
		queryButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				//查询book表中的所有数据
				Cursor cursor=db.query("book", null, null, null,null, null, null);
				
				/*
				 * 调用moveToFirst()方法将数据的指针移动到第一行的位置，然后进入一个循环当中，去遍历查询到
				 * 的每一行数据。在这个循环中可以通过Cursor的getColumnIndex()方法取到某一列在表中对应的位置索引，
				 * 然后将这个索引传入到相应的取值方法当中，接可以得到从数据库中查询到的数据了。接着使用Log的方式将
				 * 取出来的数据打印出来，借此来检查一下读取工作有没有成功完成。最后别忘了调用close()方法来关闭Cursor。
				 * */
				if(cursor.moveToFirst()){
					do{
						//遍历Cursor对象，取出数据并打印
						String name=cursor.getString(cursor.getColumnIndex("name"));
						String author=cursor.getString(cursor.getColumnIndex("author"));
					    int pages=cursor.getInt(cursor.getColumnIndex("pages"));
						double price=cursor.getDouble(cursor.getColumnIndex("price"));
						//打印数据
						Log.d("MainActivity", "book name is "+name);
						Log.d("MainActivity", "book author is "+author);
						Log.d("MainActivity", "book pages is "+pages);
						Log.d("MainActivity", "book price is "+price);
						
						
					}while(cursor.moveToNext());
				}
				cursor.close();
			}
			
		});
		
		
		//android数据库中的事务处理
		Button replaceButton=(Button) findViewById(R.id.replace_data);
		replaceButton.setOnClickListener(new OnClickListener(){

			/*
			 * SQLiteDatabase的beginTransaction()方法来开启一个事务，然后在一个异常捕获
			 * 的代码中去执行具体的数据库操作，当所有的操作完成之后，调用setTransactionSuccessful()，
			 * 表示事务已经执行成功了，最后在finally代码块中调用endTransaction()来结束事务。
			 * 注意观察，我们在刚删除旧数据的操作完成之后手动抛出一个NullPointerException，这样
			 * 添加新数据的代码就得不到执行。不过由于事务的存在，中途出现异常会导致事务的失败，此时
			 * 旧数据应该是删除不掉的。
			 * */
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				db.beginTransaction();//开启事务
				try{
					db.delete("book", null, null);
					if(true){
						throw new NullPointerException();
					}
					ContentValues values=new ContentValues();
					values.put("name", "jack");
					values.put("author", "tom");
					values.put("pages", 810);
					values.put("price",60.78);
					db.insert("book", null, values);
					db.setTransactionSuccessful();//事务已经执行成功
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					db.endTransaction();//结束事务
				}
				
				
			}
			
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
