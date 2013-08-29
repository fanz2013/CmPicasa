package com.example.cmpicasa.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 
 * Image content provider for CmPicasa. Update this content provider will also
 * update media store content provider.
 * 
 */
public class ImageProvider extends ContentProvider {
	private static final String TAG = ImageProvider.class.getSimpleName();
	public static final String PROVIDER_NAME = "com.example.provider.cmpicasa";
	private static final String IMAGE_PROVIDER_PATH = "image";

	private static final String IMAGE_URL = "content://" + PROVIDER_NAME + "/"
			+ IMAGE_PROVIDER_PATH;
	public static final Uri IMAGE_URI = Uri.parse(IMAGE_URL);

	public static final UriMatcher uriMatcher;
	private static final int IMAGE_URIMATCHER_ID = 1;
	private static final int INDIVIDULE_IMAGE_URIMACHER_ID = 2;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, IMAGE_PROVIDER_PATH,
				IMAGE_URIMATCHER_ID);
		uriMatcher.addURI(PROVIDER_NAME, IMAGE_PROVIDER_PATH + "/#",
				INDIVIDULE_IMAGE_URIMACHER_ID);
	}
	

	/** The backed data base for this content provider. */
	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		ImageDatabase database = new ImageDatabase(getContext());
		db = database.getWritableDatabase();
		return db == null ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * INSERT TO ITSELF AND MEDIA STORE CONTENT PROVIDER.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {

		long rowId = db.insert(ImageDatabase.IMAGE_TABLE_NAME, null, values);
		Uri uriFromImageProvider = null;
		if (rowId > 0) {
			uriFromImageProvider = ContentUris.withAppendedId(IMAGE_URI, rowId);
			getContext().getContentResolver().notifyChange(
					uriFromImageProvider, null);
		}
		if (uriFromImageProvider != null) {
			// update media store content provider.
			String urlInImageProvider = uriFromImageProvider.toString();
			Log.d(TAG, "in insert, urlInImageProvider: " +  urlInImageProvider);
			
			// prepare values to insert into media store content provider
			ContentValues valuesForMediaStore = new ContentValues();
			values.put(MediaStore.Images.ImageColumns.TITLE, "test fanz");
			values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "test fanz");
			values.put( MediaStore.Images.Media.BUCKET_ID, PROVIDER_NAME.hashCode() );
			//values.put(MediaStore.Images.Media., "test fanz");
		    values.put( MediaStore.Images.Media.BUCKET_DISPLAY_NAME, PROVIDER_NAME );

		    values.put( MediaStore.Images.Media.MIME_TYPE, "image/jpeg" );
			values.put(MediaStore.Images.ImageColumns.DATA, "/storage/emulated/0/DCIM/Camera/IMG_20130711_213423.jpg");
			
			Uri uriFromMediaStore = getContext().getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valuesForMediaStore);
			Log.d(TAG, "in insert, uriFromMediaStore: " +  uriFromMediaStore);
			return uriFromMediaStore == null ? null : uriFromImageProvider;
		} else {
			return null;
		}

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
