package com.kintekhaiti.appcontact

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context: Context) : SQLiteOpenHelper(context, "db_contacts", null, 1) {
    val TB_NAME = "tb_contacts"
    val COL_ID = "id"
    val COL_FIRSTNAME = "firstname"
    val COL_LASTNAME = "lastname"
    val COL_PHONE = "phone"

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE $TB_NAME (" +
            "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COL_FIRSTNAME VARCHAR(50)," +
            "$COL_LASTNAME VARCHAR(50)," +
            "$COL_PHONE VARCHAR(50)" +
            ")"
        db?.execSQL(sql)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        //
    }
    fun select(): Cursor {
        return this.readableDatabase.rawQuery("SELECT * FROM $TB_NAME", null)
    }
    fun select(id: Int): Contact{
        val cursor = this.writableDatabase.rawQuery("SELECT * FROM $TB_NAME WHERE $COL_ID = $id",null)
        cursor.moveToFirst()
            val first = cursor.getString(cursor.getColumnIndex(COL_FIRSTNAME))
            val last = cursor.getString(cursor.getColumnIndex(COL_LASTNAME))
            val phone = cursor.getString(cursor.getColumnIndex(COL_PHONE))
        cursor.close()
        return Contact(id, first, last, phone)
    }
    fun insert(firstname: String, lastname: String, phone: String): String{
        val values = ContentValues()
            values.put(COL_FIRSTNAME, firstname)
            values.put(COL_LASTNAME, lastname)
            values.put(COL_PHONE, phone)
        val x = this.writableDatabase.insert(TB_NAME, null, values)
        if(x != (-1).toLong()){
            return "save OK"
        }
        return "failed to save"
    }
    fun update(id: Int?, firstname: String, lastname: String, phone: String): String{
        val values = ContentValues()
            values.put(COL_FIRSTNAME, firstname)
            values.put(COL_LASTNAME, lastname)
            values.put(COL_PHONE, phone)
        if(this.writableDatabase.update(TB_NAME, values, "$COL_ID=?", arrayOf(id.toString())) != (-1)){
            return "edit OK"
        }
        return "failed to edit"
    }
    fun delete(id: Int?): String{
        if(this.writableDatabase.delete(TB_NAME,"$COL_ID=?", arrayOf(id.toString())) != (-1)) {
            return "delete OK"
        }
        return "failed to delete"
    }
}