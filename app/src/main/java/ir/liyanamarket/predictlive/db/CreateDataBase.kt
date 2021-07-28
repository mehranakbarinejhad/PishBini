package ir.liyanamarket.predictlive.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
const val DATABASE_NAME="PredictLive"
const val DATABASE_VERSION=3
class CreateDataBase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table if not exists tbl_users(id integer primary key autoincrement,username nvarchar(50),name nvarchar(50),password nvarchar(50),image text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists tbl_users")
        onCreate(db)
    }


}