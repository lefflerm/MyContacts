package com.example.administrator.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2/12/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    //The database handler, extending SQLiteOpenHelper has to implement the onCreate and onUpgrade methods from it
    //And also handle important database business
    private static final int DATABASE_VERSION = 1; //the version updates when we have more lists to add!:D
    private static final String DATABASE_NAME = "contacts.db"; //simple name because it's to the point


    public static final String TABLE_CONTACTS= "contacts"; //again, simple name because it hits the mark
    public static final String COLUMN_CONTACT_ID = "_id"; //generating a unique id for each contact so you can have multiple of the same NAME
    public static final String COLUMN_CONTACT_NAME = "contact_name"; //the contact name
    public static final String COLUMN_CONTACT_EMAIL = "contact_email"; //the contact email
    public static final String COLUMN_CONTACT_PHONE = "contact_phone"; //the contact phone number

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory){
        //calling the superclass for SQLiteOpenHelper, sending it the context and cursorfactory, as well as the version and database name we set up
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    //this is one of the required methods to extend SQLiteHelper
    public void onCreate(SQLiteDatabase db) {
        //Basic creation of our table. The phone number is kept as text because it's not going to have any math done on it
        //So it is purely nomial numbers
       String query = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CONTACT_NAME + " TEXT," +
                COLUMN_CONTACT_EMAIL + " TEXT," +
                COLUMN_CONTACT_PHONE + " TEXT" +
                ");";
        //The query set up with all of our information, making sure the table is in this state, with the most recent info
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //the other method necessary for extending SQLiteDatabase
        //This gets rid of previous versions of the table if they exist, then calls the onCreate to make the new one with the new info
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(String name, String email, String phone){
        //this is setting up to actually ADD the contacts with the info we add in whenever we press the add contact buttons in CreateContact
        ContentValues values = new ContentValues(); //this is essentially like an arraylist of contentvalues

        //we're adding the key value pairs of what we titled the spot in the table, and the input value we sent in with the method
        values.put(COLUMN_CONTACT_NAME, name);
        values.put(COLUMN_CONTACT_EMAIL, email);
        values.put(COLUMN_CONTACT_PHONE, phone);
        //a location of the writeable database so we can actually write this values list we made to the database
        SQLiteDatabase db = getWritableDatabase();

        //here we are actually inserting the key value pairs in the values list, into the designated table
        db.insert(TABLE_CONTACTS, null, values);

        //it's polite to close your database
        db.close();
    }

    public Cursor getContacts(){
        //In case we want to get that list for whatever reason
        SQLiteDatabase db = getWritableDatabase();
        //here's the database we're querying
        //and here's the query - we're selecting alllllllllll the information from the Table_Contents table
        return db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
    }

    //adding an extra comment here because I changed the gitignore
    //Please add already XP
    public String getContactNum(){
        //here is just if we want the total number of contacts
        String dbString = "";
        //the string to be returned
        //the query of the contacts we have
        String query = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = getWritableDatabase(); //database, so we can query it

        //the cursor's holding all of our data so we can get info from it
        Cursor c = db.rawQuery(query, null);

        //here we get the number of contacts from the cursor
        int numContacts = c.getCount();

        //here we convert the int into a String just for conventions
        dbString = String.valueOf(numContacts);

        //close your database and return your info!!!
        db.close();
        return dbString;
    }





}
