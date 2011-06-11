package plotAFriend.PlotAFriendSaver;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class SQLDbAdapter {

	public static final String KEY_NAME = "name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_ROWID= "_id";
	
	 private static final String TAG = "SQLDbAdapter";
	 private DatabaseHelper DbHelper;
	 private SQLiteDatabase mDb;
	 
	 /**
	 * Database creation sql statement
	 */
	 
	 private static final String DATABASE_CREATE =
	        "create table FriendLocations (_id integer primary key autoincrement, "
	        + "name text not null, location text not null);";

	 private static final String DATABASE_NAME = "PlotAFriend";
	 private static final String DATABASE_TABLE = "FriendLocations";
	 private static final int DATABASE_VERSION = 1;

	 private final Context mCtx;
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper
	 {
		
		 DatabaseHelper(Context context)
		 {
			super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
		 }
		 
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
			
			db.execSQL("DROP TABLE IF EXISTS FriendLocations");
			onCreate(db);
			
		}
		 
	 }
	 
	 public SQLDbAdapter(Context ctx)
	 {
		this.mCtx = ctx; 
	 }
	 
	 public SQLDbAdapter open() throws SQLException
	 {
		 DbHelper = new DatabaseHelper(mCtx);
		 mDb = DbHelper.getWritableDatabase();
		 return this;
	 
	 }
	 
	 public void close()
	 {
		DbHelper.close(); 
	 }
	 
	 public long createFriendLocation(String name, String location)
	 {
		 ContentValues contentValues = new ContentValues();
		 
		 contentValues.put(KEY_NAME, name);
		 contentValues.put(KEY_LOCATION, location);
		 
		 return mDb.insert(DATABASE_TABLE, null, contentValues);
		 
		 
	 }
	 
	 public boolean deleteFriendLocation(long rowId)
	 {
		 return mDb.delete( DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	 }
	 
	 public Cursor fetchAllFriendLocations()
	 {
		 return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_LOCATION}, null, null, null, null, null);
	 }	
	 
	  
	    public boolean updateFriendLocation(long rowId, String name, String location) {
	        ContentValues args = new ContentValues();
	        args.put(KEY_NAME, name);
	        args.put(KEY_LOCATION, location);

	        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	    }
	    
	    public Cursor fetchFriend(long rowId) throws SQLException {

	        Cursor mCursor =

	            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
	                    KEY_NAME, KEY_LOCATION}, KEY_ROWID + "=" + rowId, null,
	                    null, null, null, null);
	        if (mCursor != null) {
	            mCursor.moveToFirst();
	        }
	        return mCursor;

	    }
	 
 }
	 

