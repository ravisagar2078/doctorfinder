package com.example.ravisagar.doctor_finder;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ravi Sagar on 5/6/2017.
 */
public class register extends AppCompatActivity {
    EditText email,username,password, phnumber ,conf_pass,fname,lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityregister);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email= (EditText)findViewById(R.id.et_email);
        username= (EditText)findViewById(R.id.et_username);
        password= (EditText)findViewById(R.id.et_password);
        phnumber= (EditText)findViewById(R.id.et_number);
        conf_pass=(EditText)findViewById(R.id.confirmPassword);
        fname=(EditText)findViewById(R.id.et_fname);
        lname=(EditText)findViewById(R.id.et_lname);
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
    public void onRegister(View view)
    {
 //       Toast.makeText(getApplicationContext(), "Pooch gaya", Toast.LENGTH_LONG).show();

        if(isOnline()) {
            if (checkValidation()) {
                String str_email = email.getText().toString();
                String str_username = username.getText().toString();
                String str_password = password.getText().toString();
                String str_phnumber = phnumber.getText().toString();
                String str_fname = fname.getText().toString();
                String str_lname = lname.getText().toString();
                //   Toast.makeText(getApplicationContext(), str_lname+str_fname, Toast.LENGTH_LONG).show();
                String type = "registeruser";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, str_email, str_username, str_password, str_phnumber, str_fname, str_lname);

            } else {
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
