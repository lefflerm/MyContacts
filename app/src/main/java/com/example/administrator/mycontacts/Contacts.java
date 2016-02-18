package com.example.administrator.mycontacts;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2/14/2016.
 */
public class Contacts extends CursorAdapter {
    public Contacts(Context context, Cursor cursor, int flags){
        //calling the constructor for CursorAdapter, passing the context, the cursor and any necessary flags
        super(context, cursor, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //this is going to inflate the custom row we made to the list on the page this is used on
        return LayoutInflater.from(context).inflate(R.layout.li_contact_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //this is binding the specific information to the specific slots we made in the custom view
        //name obviously goes with name, gotten from the dbHandler
        ((TextView) view.findViewById(R.id.nameTextView)).
                setText(cursor.getString(cursor.getColumnIndex("contact_name")));
        //email with email, again from the DBHandler
        ((TextView) view.findViewById(R.id.emailTextView)).
                setText(cursor.getString(cursor.getColumnIndex("contact_email")));
        //phone with phone from the DBHandler
        ((TextView) view.findViewById(R.id.phoneTextView)).
                setText(cursor.getString(cursor.getColumnIndex("contact_phone")));
    }
}
