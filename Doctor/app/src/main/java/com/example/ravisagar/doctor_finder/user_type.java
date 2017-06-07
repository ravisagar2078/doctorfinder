package com.example.ravisagar.doctor_finder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Ravi Sagar on 5/6/2017.
 */
public class user_type extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);
    }

    public void simple_user_reg(View view)
    {
        startActivity(new Intent(this, register.class));
    }

    public void simple_doctor_reg(View view)
    {
        startActivity(new Intent(this, register_doctor.class));
    }


}
