package com.kintekhaiti.appcontact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //DB(this)
        val cursor = DB(applicationContext).select()
        val list = arrayListOf<Contact>()

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex( "id"))
                val first = cursor.getString(cursor.getColumnIndex( "firstname"))
                val last = cursor.getString(cursor.getColumnIndex( "lastname"))
                val phone = cursor.getString(cursor.getColumnIndex( "phone"))
                list.add(Contact(id,first,last,phone))
            }
        }
        cursor.close()

        val listview = findViewById<ListView>(R.id.listView)
        listview.adapter = ContactAdapter( applicationContext, android.R.layout.simple_list_item_1, list)

        listview.setOnItemClickListener { _, _, position, _ ->
            val intent  = Intent(this, ViewContact::class.java)
            intent.putExtra("idC", list.get(position).id)
            startActivity(intent)
        }
    }
    fun totalContact(view: View){
        view.width
        Toast.makeText(applicationContext,"Il y a " + DB(applicationContext).select().count + " contacts", Toast.LENGTH_LONG).show()
    }

    fun addContact(view: View){
        view.width
        startActivity(Intent(this,EditContact::class.java))
    }
}