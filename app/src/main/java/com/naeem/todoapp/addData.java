package com.naeem.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class addData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        //initilaizing objects
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button closeButton = (Button) findViewById(R.id.closeButton);

        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.email);

        Room.databaseBuilder(
                getApplicationContext().getApplicationContext(),
                DatabaseApp.class,
                "DatabaseApp")
                .allowMainThreadQueries()
                .build();

        //listening for clicks
        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String firstnameText = firstName.getText().toString();
                String lastnameText = lastName.getText().toString();
                String emailText = email.getText().toString();
                submitbuttonPressed(firstnameText,getApplicationContext(),lastnameText,emailText,firstName,lastName,email); //this method called when button pressed. for adding data.
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    private void submitbuttonPressed(String firstName,Context context,String lastName,String email,EditText firstCntrl,EditText lastCntrl,EditText emailCntrl) {


        if(checkValues(firstName,lastName,email,context)){// checking if all values are input or not.
            if(checkEmail(email,context)){// checking if email already exist
                  addValue(firstName,lastName,email,context);// adding data
                //clearing text field values
                firstCntrl.setText("");
                lastCntrl.setText("");
                emailCntrl.setText("");


            }else{
                Toast.makeText(context,"This Email address already exist.",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context,"Please enter complete data",Toast.LENGTH_LONG).show();
        }
    }

    private void addValue(String firstName, String lastName, String email, Context context) {
        DatabaseApp db = DatabaseApp.getInstance(context);
         Room.databaseBuilder(
                context.getApplicationContext(),
                DatabaseApp.class,
                "DatabaseApp")
                .allowMainThreadQueries()  // add this line
                .build();
        profile profile = new profile(firstName.toString(),lastName,email);
        db.Dao().insert(profile);

        Toast.makeText(context,"Data Inserted",Toast.LENGTH_LONG).show();

    }

    private boolean checkEmail(String email, Context context) {
        boolean check =  true;
        Room.databaseBuilder(
                context.getApplicationContext(),
                DatabaseApp.class,
                "DatabaseApp")
                .allowMainThreadQueries()  // add this line
                .build();

        DatabaseApp db = DatabaseApp.getInstance(this);
        List<profile> list = db.Dao().getAll();

        for(int i=0 ;i<list.size();i++){
            if(list.get(i).getEmail().toString().equals(email)){
                check=false;
            }
        }

        return check;
    }

    private boolean checkValues(String firstName,String lastName, String email, Context context) {
        boolean check =  true;
        if(firstName.equals("") || lastName.equals("") || email.equals("")){
            check = false;
        }
    return check;
    }
}