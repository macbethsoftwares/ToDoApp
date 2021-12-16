package com.naeem.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class updateData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        //initilaizing objects
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button closeButton = (Button) findViewById(R.id.closeButton);

        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.email);
        int  id=-1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             id = extras.getInt("key");
            //The key argument here must match that used in the other activity
        }

        DatabaseApp db = DatabaseApp.getInstance(this);
        List<profile> list = db.Dao().getAll();

        for(int i=0 ;i<list.size();i++){//fetching data
            if(list.get(i).getId()==id){
                firstName.setText(list.get(i).getFirstName());
                lastName.setText(list.get(i).getLastName());
                email.setText(list.get(i).getEmail());
            }
        }

        //listening for clicks
        int finalId = id;
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String firstnameText = firstName.getText().toString();
                String lastnameText = lastName.getText().toString();
                String emailText = email.getText().toString();
                submitbuttonPressed(firstnameText,getApplicationContext(),lastnameText,emailText,firstName,lastName,email, finalId); //this method called when button pressed. for adding data.
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
    private void submitbuttonPressed(String firstName, Context context, String lastName, String email, EditText firstCntrl, EditText lastCntrl, EditText emailCntrl,int id) {


        if(checkValues(firstName,lastName,email,context)){// checking if all values are input or not.
                updateValue(firstName,lastName,email,context,id);// adding data
                //clearing text field values
                firstCntrl.setText("");
                lastCntrl.setText("");
                emailCntrl.setText("");



        }else{
            Toast.makeText(context,"Please enter complete data",Toast.LENGTH_LONG).show();
        }}



    private void updateValue(String firstName, String lastName, String email, Context context,int id) {
        DatabaseApp db = DatabaseApp.getInstance(context);
        Room.databaseBuilder(
                context.getApplicationContext(),
                DatabaseApp.class,
                "DatabaseApp")
                .allowMainThreadQueries()  // add this line
                .build();
        profile profile = new profile(firstName.toString(),lastName,email);
        profile.setId(id);
        db.Dao().update(profile);

        Toast.makeText(context,"Data Updated",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    private boolean checkValues(String firstName,String lastName, String email, Context context) {
        boolean check =  true;
        if(firstName.equals("") || lastName.equals("") || email.equals("")){
            check = false;
        }
        return check;
    }
}