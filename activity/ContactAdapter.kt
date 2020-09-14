package com.kintekhaiti.appcontact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ContactAdapter(context: Context, var resource: Int, var objects: MutableList<Contact>) :
    ArrayAdapter<Contact>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val convertView = LayoutInflater.from(context).inflate(R.layout.basic_contact_layout, null, false)

        convertView.findViewById<TextView>(R.id.textFirst).text = objects[position].firstname
        convertView.findViewById<TextView>(R.id.textLast).text = objects[position].lastname

        return convertView
    }
}