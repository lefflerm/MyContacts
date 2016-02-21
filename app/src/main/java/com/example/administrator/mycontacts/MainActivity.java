package com.example.administrator.mycontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //this is the intent, it is run as an activity, its controlled by a switch statement in a method
    Intent intent;
    //This is the database handler, it handles the database
    DBHandler dbHandler;
    //This is the cursor adapter that we made
    Contacts contactAdapter;
    //This is a list view widget
    ListView contactListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new DBHandler(this, null); //handlin our databases oh yeah

        contactListView = (ListView) findViewById(R.id.contactsListView); //this is the listView in the content_main xml
        contactAdapter = new Contacts(this, dbHandler.getContacts(), 0); //this actually initializes our cursorAdapter with the info from the dbhandler
        contactListView.setAdapter(contactAdapter); //let's connect the two!
        toolbar.setSubtitle("Total # Contacts: " + dbHandler.getContactNum()); //getting the total number of contacts and displaying it
        //PUUUUUUUUSH

        //This on click listener lets us open the contact when it's selected
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //the intent takes us from this activity to the ViewContact one
                intent = new Intent(MainActivity.this, ViewContact.class);
                //it also sends along the ID, like ya do
                intent.putExtra("_id", id);
                //and actually runs the intent
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openCreateContact(View view){
        //This is intializing the intent we used earlier, hooking it up to the Create Contact class, which is what we intend to open
        intent = new Intent(this, CreateContact.class);
        //we actually are starting the intent up, doing as we intend to - in this case, heading over to the Create Contact class
        startActivity(intent);
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
}
