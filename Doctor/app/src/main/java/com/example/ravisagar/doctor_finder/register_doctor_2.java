package com.example.ravisagar.doctor_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ravi Sagar on 5/7/2017.
 */
public class register_doctor_2 extends AppCompatActivity {

    String username,email,password,number,add,fname,lname;
    EditText city,ftiming,ttiming;
    Spinner s,s1;
   // private TimePicker timePicker1;

    ArrayList<String> al,a2;
    ArrayAdapter<String> aa,bb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_doctor2);

        Intent intent=getIntent();
        username=intent.getExtras().getString("username");
        email=intent.getExtras().getString("email");
        password=intent.getExtras().getString("password");
        number=intent.getExtras().getString("number");
        add=intent.getExtras().getString("add");
        fname=intent.getExtras().getString("fname");
        lname=intent.getExtras().getString("lname");


        city= (EditText)findViewById(R.id.et_city);
        ftiming= (EditText)findViewById(R.id.et_ftiming);
        ttiming= (EditText)findViewById(R.id.et_ttiming);
     //   String str_email = email.getText().toString();
      //  String str_username = username.getText().toString();
       // String str_password = password.getText().toString();
 //       timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
              s = (Spinner) findViewById(R.id.spinner);
 //       s1 = (Spinner) findViewById(R.id.spinner1);

        al = new ArrayList<String>();


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("di.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                al.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, al);
        s.setAdapter(aa);



    }
    public void register(View view)
    {

        String cty = city.getText().toString();
        String ftime = ftiming.getText().toString();
        String ttime = ttiming.getText().toString();

        String spec = s.getSelectedItem().toString();
        String type = "register";

        // System.out.println(str_username);
        //System.out.println(str_password);
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,  username,fname,lname, email, password,number,add,cty,spec,ftime,ttime);

    }
    }
