package com.example.ravisagar.doctor_finder;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ravi Sagar on 5/6/2017.
 */
public class register_doctor extends AppCompatActivity {
    EditText email,username,password, phnumber ,conf_pass,fname,lname,address;
    Spinner s,s1;
    ArrayList<String> al,a2;

    ArrayAdapter<String> aa,bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_doctor);
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email= (EditText)findViewById(R.id.et_email);
        username= (EditText)findViewById(R.id.et_username);
        password= (EditText)findViewById(R.id.et_password);
        phnumber= (EditText)findViewById(R.id.et_number);
        conf_pass=(EditText)findViewById(R.id.confirmPassword);
        fname= (EditText)findViewById(R.id.et_fname);
        lname= (EditText)findViewById(R.id.et_lname);
        address= (EditText)findViewById(R.id.et_address);

        //      s = (Spinner) findViewById(R.id.spinner);
    //    al = new ArrayList<String>();

      //  s1 = (Spinner) findViewById(R.id.spinner1);
        //a2 = new ArrayList<String>();
/*        al.add("Bangalore");
        al.add("Chennai");
        al.add("Hyderabad");
        al.add("Delhi");
*/

/*

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("city.txt"), "UTF-8"));

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

        String q = s.getSelectedItem().toString();*/
/*
        if(q=="Sindh")
        {
            BufferedReader reader1 = null;
            try {
                reader1 = new BufferedReader(
                        new InputStreamReader(getAssets().open("sindh.txt"), "UTF-8"));

                // do reading, usually loop until end of file reading
                String mLine;
                while ((mLine = reader1.readLine()) != null) {
                    //process line
                    a2.add(mLine);
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
        }
        if(q=="Punjab")
        {
            BufferedReader reader1 = null;
            try {
                reader1 = new BufferedReader(
                        new InputStreamReader(getAssets().open("punjab.txt"), "UTF-8"));

                // do reading, usually loop until end of file reading
                String mLine;
                while ((mLine = reader1.readLine()) != null) {
                    //process line
                    a2.add(mLine);
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
        }
        bb = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, a2);
        s.setAdapter(bb);
*/
    }
    public boolean isOnline()
    {
        Log.i("internet::","Checking internet connection");
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }
    public void next(View view)
    {
        if(isOnline())
        {
            if (checkValidation())
            {
                String str_email = email.getText().toString();
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                String str_phnumber = phnumber.getText().toString();
                String str_add = address.getText().toString();
                String str_fname = fname.getText().toString();
                String str_lname = lname.getText().toString();
                Intent intent=new Intent(this, register_doctor_2.class);
               intent.putExtra("email", str_email);
                intent.putExtra("username",str_username);
                intent.putExtra("password",str_password);
                intent.putExtra("number",str_phnumber);
                intent.putExtra("add",str_add);
                intent.putExtra("fname",str_fname);
                intent.putExtra("lname",str_lname);

                this.startActivity(intent);


                // String type = "register";
//                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                //              backgroundWorker.execute(type, str_email, str_username, str_password, str_phnumber);

            }
            else
            {
             Toast.makeText(getApplicationContext(), "Password mismatch.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No internet connection found!", Toast.LENGTH_LONG).show();
        }

    }
    //    public void onlogin(View view)
//    {
//        startActivity(new Intent(this,login.class) );
//    }
    public boolean checkValidation()
    {
        String getPassword = password.getText().toString();
        String getConfirmPassword = conf_pass.getText().toString();
        if (!getConfirmPassword.equals(getPassword))
            return false;
        return  true;
    }

}
