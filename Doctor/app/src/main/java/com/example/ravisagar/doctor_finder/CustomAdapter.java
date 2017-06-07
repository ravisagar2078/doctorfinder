package com.example.ravisagar.doctor_finder;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Sagar on 5/9/2017.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private Activity activity;
    private List data;
    public Resources resources;
    Routes tempValues = null;
    LayoutInflater inflater;

    public CustomAdapter(location activitySpinner, int textViewResourceId, ArrayList objects, Resources resLocal)
    {
        super(activitySpinner,textViewResourceId,objects);

        activity = activitySpinner;
        data = objects;
        resources = resLocal;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public CustomAdapter(special activitySpinner, int textViewResourceId, ArrayList objects, Resources resLocal)
    {
        super(activitySpinner,textViewResourceId,objects);

        activity = activitySpinner;
        data = objects;
        resources = resLocal;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    //    @Override
//    public View getDropDownView(int position, View convertView, ViewGroup parent)
//    {
//        return getCustomView(position,convertView,parent);
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position,convertView,parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        View row = inflater.inflate(R.layout.row_listview,parent,false);

        tempValues = null;
        tempValues = (Routes) data.get(position);

        TextView label = (TextView)row.findViewById(R.id.route);
        TextView sub = (TextView)row.findViewById(R.id.times);

        //System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiii");
        label.setText(tempValues.getRname()+" "+tempValues.getlname());
        sub.setText("Mobile: "+tempValues.getMnumber()+" <-->Speciality: "+tempValues.getSpecial()
                +"<--> Address: "+tempValues.getadd()+"<--> From-Timing: "+tempValues.getftiming()+"<--> To-Timing: "+tempValues.getttiming());

        return row;
    }
}

