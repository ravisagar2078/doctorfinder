package com.example.ravisagar.doctor_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ravi Sagar on 5/6/2017.
 */
public class startpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);
    }

    public void open_login(View view)
    {
        startActivity(new Intent(this, login_activity.class));
    }

    public void open_signup(View view)
    {
        startActivity(new Intent(this, user_type.class));
    }

}
