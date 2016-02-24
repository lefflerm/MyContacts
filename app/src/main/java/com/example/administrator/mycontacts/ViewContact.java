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

public class ViewContact extends AppCompatActivity {
    Intent intent; //very intentional (this helps us with our menu)
    Bundle bundle; //bundlebee (this is holding all sorts of information from the db)
    long id; //this is the id from the bundle
    DBHandler dbHandler; //the database, of course
    EditText nameEditText; //the name field
    EditText emailEditText; //email field
    EditText phoneEditText; //phone field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //the bundle of information from the database, and the 'extras' (id) we sent along
        bundle = this.getIntent().getExtras();
        //the id of the contact from the bundle
        id = bundle.getLong("_id");
        //oh dbhandler, you handle my databases so well
        dbHandler = new DBHandler(this, null);

        //here we're syncing up our edit texts with their actual versions on the content page
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        //this contact fills in all the information from our database table so we can set it in the editTexts
        Contact c = dbHandler.getContact((int) id);
        //putting uneditable text into our edit texts :D
        nameEditText.setText(c.getName().toString());
        emailEditText.setText(c.getEmail().toString());
        phoneEditText.setText(c.getPhone().toString());


    }

    //These two methods are lifted from the main activity almost pefectly, other than making sure it inflates the right menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This is the switch statement to actually tell what we're doing with items from the menus
        switch(item.getItemId()){
            //if the item id is the id we set up for the 'home' button, it sends you back to the main activity
            case R.id.action_home :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            //if the item id is the id we set up for create contact, it sends us to the Create Contact class, same as the openCreateContact does
            //when we click the floating action button
            case R.id.action_create_contact:
                intent = new Intent(this, CreateContact.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //This method deletes the contact and runs a toast to show that it occurred
    public void deleteContact(MenuItem menuItem){
        //Its called by a menu resource, so it takes in a MenuItem despite not using it
        dbHandler.deleteContact((int) id); //we call the newly implemented deleteContact method from the dbHandler
        Toast.makeText(this, "Contact Deleted!", Toast.LENGTH_LONG).show(); //simple toast to show a job well done
    }

}
