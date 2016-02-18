package com.example.administrator.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity {

    Intent intent; //the intent we'll use to hop around to places on the menu
    EditText nameEditText; //the name edit text set up on this page
    EditText emailEditText; //the email edit text set up on this page
    EditText phoneEditText; //the phone number edit text set up on this page
    DBHandler dbHandler; //This handles the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DBHandler(this, null); //handling our databases, even if there's not that much goin on with them

        //this is initializing our editTexts using the findViewById method to find the ids we set up for those edit texts
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present;
        getMenuInflater().inflate(R.menu.menu_create_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //same switch statement as in the main activity, sending things to places they need to go
        switch(item.getItemId()){
            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_contact:
                intent = new Intent(this, CreateContact.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //This method creates the contact when the menu plus button is pressed
    public void createContact(MenuItem menuItem){

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if(name.trim().equals("") || email.trim().equals("") || phone.trim().equals("")){
            Toast.makeText(this, "Please enter a name, email and phone number!", Toast.LENGTH_LONG).show();
        }
        else{
            dbHandler.addContact(name, email, phone); //properly connecting the toast saying 'created' and actual creation
            Toast.makeText(this, "Contact Created!", Toast.LENGTH_LONG).show();
        }
    }


}
