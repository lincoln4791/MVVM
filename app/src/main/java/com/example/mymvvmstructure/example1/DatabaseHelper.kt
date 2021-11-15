package com.example.mymvvmstructure.example1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.mymvvmstructure.example1.modelClasses.User

class DatabaseHelper(var context:Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_AGE + " INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, user.name)
        contentValues.put(COL_AGE, user.age)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("Range")
    fun readData(): ArrayList<User> {
        val list: ArrayList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                //val idT= result.getString(result.getColumnIndex(COL_ID)).toInt()
                val nameT = result.getString(result.getColumnIndex(COL_NAME))
                val ageT = result.getString(result.getColumnIndex(COL_AGE)).toInt()
                val user = User(nameT,ageT.toString())
                list.add(user)
            }
            while (result.moveToNext())
        }
        return list
    }

    companion object{
        val DATABASENAME = "MY DATABASE"
        val TABLENAME = "Users"
        val COL_NAME = "name"
        val COL_AGE = "age"
        val COL_ID = "id"
    }


}