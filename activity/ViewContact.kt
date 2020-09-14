package com.kintekhaiti.appcontact

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ViewContact : AppCompatActivity() {
    var idC: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contact)

        idC = intent.getIntExtra("idC", -1)
        val contact = DB(applicationContext).select(idC!!)

        idC = contact.id
        findViewById<TextView>(R.id.firstname).text = contact.firstname
        findViewById<TextView>(R.id.lastname).text = contact.lastname
        findViewById<TextView>(R.id.valPhone).text = contact.phone
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
    }
    fun editContact(view: View){
        val intent  = Intent(this, EditContact::class.java)
        intent.putExtra("idC", idC)
        startActivity(intent)
    }
    fun deleteContact(view: View){
        DB(applicationContext).delete(idC)
        startActivity(Intent(this, HomeActivity::class.java))
    }
}