package pl.pue.air.sqliteloginreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//A helper class to manage database creation and version management.

//create db using SQLite
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="register.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String KEY_ID ="ID";
    public static final String USERNAME ="username";
    public static final String PASSWORD ="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //add new user to db
    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //leave for additional tests
        /*
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registeruser",null,contentValues);

         */
       // /*
        contentValues.put(USERNAME, user);
        contentValues.put(PASSWORD, password);
        long res = db.insert(TABLE_NAME, null, contentValues);
      //   */
        db.close();
        return  res;
    }

    //check user id/pw
    public boolean checkUser(String username, String password){
        String[] columns = {KEY_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASSWORD + "=?";
        //String selection = USERNAME + "=? AND " + PASSWORD + "=?"; //parameterized query "=?"
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

    //check if the user is already registered
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {USERNAME};
        String selection = USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}