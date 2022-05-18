package np.com.surajphueudin.bloodbankingapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bloodBankDb";

    public MyDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME + "." + "userDetails");
        String createQuery = "CREATE TABLE userDetails (id INTEGER PRIMARY KEY AUTOINCREMENT, uid INTEGER, name TEXT, email TEXT, token TEXT)";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME) ;
        onCreate(sqLiteDatabase);
    }

    public void insertTokenData(String id, String name, String email, String token){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM userDetails");

        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", Integer.parseInt(id));
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("token", token);

        db.insert("userDetails", null,contentValues);
        db.close();
    }


    public void deleteTokenData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM userDetails");

        db.close();
    }

    public Cursor selectTokenData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM userDetails";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
