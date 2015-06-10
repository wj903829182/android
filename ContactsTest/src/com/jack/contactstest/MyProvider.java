package com.jack.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	/*
	 * MyProvider中新增四个整形常量，其中TABLE1_DIR表示访问table1表中的所有数据，
	 * TABLE1_ITEM表示访问的table1表中的单条数据，TABLE2_DIR表示访问table2表中的所有数据，
	 * TABLE2_ITEM表示访问的table2表中的单条数据。
	 * */
	public static final int TABLE1_DIR=0;
	public static final int TABLE1_ITEM=1;
	public static final int TABLE2_DIR=2;
	public static final int TABLE2_ITEM=3;
	private static UriMatcher uriMatcher;
	
	
	/*
	 * 上面定义常量以后，接着在静态代码块里，创建UriMatcher的实例，并调用addURI()方法，将期望匹配的内容
	 * URI格式传递进去，注意这里传入的路径参数是可以使用通配符的。然后当query()方法被调用的时候，就会通过UriMatcher
	 * 的match()方法对传入的Uri对象进行匹配，如果发现UriMatcher中某个内容URI格式成功匹配了该Uri对象，则
	 * 返回相应的自定义代码，然后就可以判断期望访问的到底是什么数据了。这里只使用query()方法做了一个示范，其实
	 * insert(),update(),delete()这几个方法的实现也是差不多的，它们都会携带Uri这个参数，然后同样利用
	 * UriMatcher的match()方法判断出调用期望访问的是哪一张表，在对该表中的数据进行相应的操作就可以了。
	 * */
	static{
		uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.jack.contactstest.provider", "table1", TABLE1_DIR);
		uriMatcher.addURI("com.jack.contactstest.provider", "table1/#", TABLE1_ITEM);
		uriMatcher.addURI("com.jack.contactstest.provider", "table2", TABLE2_DIR);
		uriMatcher.addURI("com.jack.contactstest.provider", "table2/#", TABLE2_ITEM);
	}
	
	
	
	/*
	 * delete()方法从内容提供器中删除数据。使用uri参数来确定删除哪一张表中的数据，selection和
	 * selectionArgs参数用于约束删除哪些行，被删除的行将作为返回值返回。
	 * */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/*
	 * getType()根据传入的内容URI来返回相应的MIME类型。
	 * */
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri)){
		case TABLE1_DIR:
			//查询table1表中的所有数据
			return "vnd.android.cursor.dir/vnd.com.jack.contactstest.table1";
			
		case TABLE1_ITEM:
			//查询table1表中的单条数据
			return "vnd.android.cursor.item/vnd.com.jack.contactstest.table1";
			
		case TABLE2_DIR:
			//查询table2表中的所有数据
			return "vnd.android.cursor.dir/vnd.com.jack.contactstest.table2";
			
		case TABLE2_ITEM:
			//查询table2表中的单条数据
			return "vnd.android.cursor.item/vnd.com.jack.contactstest.table2";
		
		default:break;
		}
		return null;
	}
    
	
	/*
	 * insert()方法向内容提供器中添加一条数据。使用uri参数来确定添加到的表，待添加的数据保存在values参数中。
	 * 添加完成后，返回一个用于表示这条新记录的URI
	 * */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 * onCreate()方法初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，返回
	 * true表示内容提供器初始化成功，返回false则表示失败。注意只有当存在ContentResolver尝试访问
	 * 我们程序中的数据时，内容提供器才会被初始化。
	 * */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	/*
	 * query()方法从内容提供器中查询数据。使用uri参数来确定查询哪张表，projection参数用于确定查询
	 * 哪些列，selection和selectionArgs参数用于约束查询哪些行，sortOrder参数用于对结果进行排序，
	 * 查询的结果存放在Cursor对象中返回。
	 * */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri)){
		case TABLE1_DIR:
			//查询table1表中的所有数据
			break;
		case TABLE1_ITEM:
			//查询table1表中的单条数据
			break;
		case TABLE2_DIR:
			//查询table2表中的所有数据
			break;
		case TABLE2_ITEM:
			//查询table2表中的单条数据
			break;
		}
		return null;
	}

	
	/*
	 * update()更新内容提供器中已有的数据。使用uri参数来确定更新哪一张表中的数据，更新数据保存
	 * 在values中，selection和selectionArgs参数用于约束更新哪些行，受影响的行将作为返回值返回。
	 * */
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
