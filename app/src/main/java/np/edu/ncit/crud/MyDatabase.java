package np.edu.ncit.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final Integer DB_VERSION=1;
    public static final String DB_NAME="students_db";
    public static final String TABLE_NAME="students";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_PHONE="phone";
    private static final String DELETE_SQL="DELETE *FROM "+TABLE_NAME;


    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String composeQuery="CREATE TABLE "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY NOT NULL,"+KEY_NAME+" TEXT,"+KEY_PHONE+" TEXT )";
        sqLiteDatabase.execSQL(composeQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

onCreate(sqLiteDatabase);
    }

    void addStudent(String name,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_PHONE,phone);
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
    ArrayList<StudentModel> getStudents(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<StudentModel>arrayList=new ArrayList<>();
        StudentModel studentModel;
        while(cursor.moveToNext()){

            studentModel=new StudentModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            arrayList.add(studentModel);
        }
        db.close();
        return arrayList;
    }
    void deleteAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }
}
