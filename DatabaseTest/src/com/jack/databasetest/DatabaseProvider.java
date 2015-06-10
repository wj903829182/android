package com.jack.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class DatabaseProvider extends ContentProvider {

	//自定义代码
	public static final int BOOK_DIR=0;
	public static final int BOOK_ITEM=1;
	public static final int CATEGORY_DIR=2;
	public static final int CATEGORY_ITEM=3;
	//权限
	public static final String AUTHORITY="com.jack.databasetest.provider";
	private static UriMatcher uriMatcher;
	private MyDatabaseHelper dbHelper;
	//静态代码块进行初始话
	static {
		uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
		uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
		uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
		uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
	}
	
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		//删除数据
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		int deleteRows=0;
		switch(uriMatcher.match(uri)){
		case BOOK_DIR:
			deleteRows=db.delete("book", selection, selectionArgs);
			break;
		case BOOK_ITEM:
			String bookId=uri.getPathSegments().get(1);
			deleteRows=db.delete("book", "id=?", new String[]{bookId});
			break;
		case CATEGORY_DIR:
			deleteRows=db.delete("category", selection, selectionArgs);
			break;
		case CATEGORY_ITEM:
			String categoryId=uri.getPathSegments().get(1);
			deleteRows=db.delete("category", "id=?",new String[]{categoryId});
			break;	
		default:
			break;
		}
		return deleteRows;//被删除的行数作为返回值返回
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch(uriMatcher.match(uri)){
		case BOOK_DIR:
			return "vnd.android.cursor.dir/vnd.com.jack.databasetest.provider.book";
		case BOOK_ITEM:
			return "vnd.android.cursor.item/vnd.com.jack.databasetest.provider.book";
		case CATEGORY_DIR:
			return "vnd.android.cursor.dir/vnd.com.jack.databasetest.provider.category";
		case CATEGORY_ITEM:
			return "vnd.android.cursor.item/vnd.com.jack.databasetest.provider.category";	
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		//添加数据
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Uri uriReturn=null;
		switch(uriMatcher.match(uri)){
		case BOOK_DIR:
		case BOOK_ITEM:
			long newBookId=db.insert("book", null, values);
			uriReturn=Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
			break;
		case CATEGORY_DIR:
		case CATEGORY_ITEM:
			long newCategoryId=db.insert("category", null, values);
			uriReturn=Uri.parse("content://"+AUTHORITY+"/book/"+newCategoryId);
			break;	
		default:
			break;
		}
		/*
		 * insert()方法要求返回一个能够表示这条新增数据的URI，所以需要调用Uri.parse()方法来将一个内容
		 * URI解析成Uri对象，当然这个内容是以新增数据的id结尾的。
		 * */
		return uriReturn;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper=new MyDatabaseHelper(getContext(), "BookStore.db", null, 1);
		Log.d("DatabaseProvider", "onCreate()--------");
		return true;//返回true表示内容提供器初始化成功，这时数据库就已经完成了创建或升级操作
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		//查询数据
		SQLiteDatabase db=dbHelper.getReadableDatabase();//获得SQLiteDatabase对象
		Cursor cursor=null;
		switch(uriMatcher.match(uri)){
		case BOOK_DIR:
			//进行查询
			cursor=db.query("book", projection, selection, selectionArgs,
					null, null, sortOrder);
			break;
		case BOOK_ITEM:
			//进行查询
			/*Uri对象的getPathSegments()方法会将内容URI权限之后的部分以“、”符号进行分割，并把分割后的结果
			 * 放入到一个字符串列表中，那这个列表的第0个位置存放的就是路径，第1个位置存放的就是id，得到id后，在通过
			 * selection和selectionArgs参数就实现了查询单条数据的功能。
			 * */
			String bookId=uri.getPathSegments().get(1);
			cursor=db.query("book", projection, "id=?", new String[]{bookId}, 
					null, null, sortOrder);
			break;
		case CATEGORY_DIR:
			//进行查询
			cursor=db.query("category", projection, selection, selectionArgs,
					null, null, sortOrder);
			break;
		case CATEGORY_ITEM:
			//进行查询
			String categoryId=uri.getPathSegments().get(1);
			cursor=db.query("book", projection, "id=?", new String[]{categoryId}, 
					null, null, sortOrder);
			break;	
		default:
			break;
		}
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		int updatedRows=0;
		//更新数据
		switch(uriMatcher.match(uri)){
		case BOOK_DIR:
			updatedRows=db.update("book", values, selection,selectionArgs);
			break;
		case BOOK_ITEM:
			String bookId=uri.getPathSegments().get(1);
			updatedRows=db.update("book", values, "id=?", new String[]{bookId});
			break;
		case CATEGORY_DIR:
			updatedRows=db.update("category", values, selection,selectionArgs);
			break;
		case CATEGORY_ITEM:
			String categoryId=uri.getPathSegments().get(1);
			updatedRows=db.update("book", values, "id=?", new String[]{categoryId});
			break;	
		default:
			break;
		}
		return updatedRows;//受影响的行数作为返回值
	}

}
