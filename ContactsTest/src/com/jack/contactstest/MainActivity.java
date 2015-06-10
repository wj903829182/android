package com.jack.contactstest;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView contactsView;
	private ArrayAdapter<String> adapter;
	private List<String> contactsList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获得ListView组件
		contactsView=(ListView) findViewById(R.id.contacts_view);
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				contactsList);
		contactsView.setAdapter(adapter);//设置适配器
		readContacts();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void readContacts(){
		Cursor cursor=null;//定义游标
		try{
			//查询联系人数据
			cursor=(Cursor) getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
					null, null, null, null);
			
			/*
			 * 上面可以看到query()方法中没有使用Uri.parse()方法去解析一个内容URI字符串，这是因为
			 * ContactsContract.CommonDataKinds.Phone类已经帮我们做好了封装，提供了一个CONTENT_URI常量
			 * ，而这个常量就是使用Uri.parse()方法解析出来的结果，接着对Cursor对象进行遍历，将联系人姓名和手机号码这些数据
			 * 逐个取出来，联系人姓名对应的常量是ContactsContract.CommonDataKinds.Photo.DISPLAY_NAME，
			 * 联系人手机号码这一列对应的常量是ContactsContract.CommonDataKinds.Phone.NUMBER。这两个数据取出来后
			 * 放ListView中。最后记得关闭Cursor对象。
			 * */
			//遍历cursor取出数据
			while(cursor.moveToNext()){
				//获取联系人姓名
				String name=cursor.getString(cursor.getColumnIndex(
						ContactsContract.CommonDataKinds.Photo.DISPLAY_NAME));
				//获取联系人手机号码
				String number=cursor.getString(cursor.getColumnIndex(
						ContactsContract.CommonDataKinds.Phone.NUMBER));
				//把数据添加到contactsList中
				contactsList.add(name+"\n"+number);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(cursor!=null){
				cursor.close();//关闭游标
			}
		}
		
	}
	
	
}
