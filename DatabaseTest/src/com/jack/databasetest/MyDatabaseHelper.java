package com.jack.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_BOOK="create table book ("
			+ "id integer primary key autoincrement,"
			+ "author text, "
			+ "price real, "
			+ "pages integer, "
			+ "name text)";
	
	public static final String CREATE_CATEGORY="create table category("
			+ "id integer primary key autoincrement, "
			+ "category_name text, "
			+ "category_code integer)";
	
	
	
	//private Context context;
	
	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		//this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
		/*Toast.makeText(context, "create succeeded",
				Toast.LENGTH_SHORT).show();*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		/*db.execSQL("drop table if exists book");
		db.execSQL("drop table if exists category");
		onCreate(db);*/
		switch(oldVersion){
		case 1:
			db.execSQL(CREATE_CATEGORY);
		case 2:
			db.execSQL("alert table book add column category_id integer");
		default:break;
		}
	}

}
