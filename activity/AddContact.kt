package com.kintekhaiti.appcontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class AddContact : AppCompatActivity() {
    var idC: Int? = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        idC = intent.getIntExtra("idC", -1)
        if (idC != -1) {
            val contact = DB(applicationContext).select(idC!!)
            idC = contact.id
            findViewById<EditText>(R.id.addFirst).setText(contact.firstname)
            findViewById<EditText>(R.id.addLast).setText(contact.lastname)
            findViewById<EditText>(R.id.addPhone).setText(contact.phone)
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
    }
    fun save(view: View) {
        view.width
        val i = idC
        val f = findViewById<EditText>(R.id.addFirst).text.toString()
        val l = findViewById<EditText>(R.id.addLast).text.toString()
        val p = findViewById<EditText>(R.id.addPhone).text.toString()

        if (idC == -1) {
            Toast.makeText(this, DB(applicationContext).insert(f, l, p), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, DB(applicationContext).update(i, f, l, p), Toast.LENGTH_SHORT).show()
        }
        startActivity(Intent(this, HomeActivity::class.java))
    }
    fun cancel(view: View){
        view.width
        startActivity(Intent(this, HomeActivity::class.java))
    }
}