package com.example.cmpicasa.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Provide database operations for images like query, insert and delete.
 *
 */
public class ImageDatabase extends SQLiteOpenHelper{
	/** DATABASE AND TABLE DEFINE.*/
	public static final String DATABASE_NAME = "CmPicasa";
	public static final String IMAGE_TABLE_NAME = "images";
	private static final int DATABASE_VERSION = 1;
	
	/** THE FOLLOWINGS DEFINE THE TABLE FIELDS.*/
	public static final String _ID = "_id";
	public static final String TITLE = "title";
	public static final String HTTP_URL = "http_url";
	
	private static final String CREATE_IMAGE_TABLE = "CREATE TABLE "
			+ IMAGE_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ TITLE + " TEXT NOT NULL, " 
			+ HTTP_URL + " TEXT NOT NULL);";
	
	private static final String DELETE_IMAGE_TABLE = "DROP TABLE IF EXISTS " + IMAGE_TABLE_NAME;

	public ImageDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_IMAGE_TABLE);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DELETE_IMAGE_TABLE);
	    onCreate(db);
	}

}
