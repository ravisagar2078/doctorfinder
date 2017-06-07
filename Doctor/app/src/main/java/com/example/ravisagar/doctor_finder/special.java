package com.example.ravisagar.doctor_finder;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class special extends AppCompatActivity {
    ArrayList<String> al,a2;

    private String jsonResult;
    private String url = "https://traintrackagent.000webhostapp.com/getdoctor2.php";

    Spinner s;
    ArrayAdapter<String> aa,bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

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
    public void spec(View view){
        String d = s.getSelectedItem().toString();
       // String[]  p=city.split("=");
        // System.out.print("city name"+city);
        // String[] ww =p[1].split("}");
        // String tid = p[0];
      //  Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();
        //p=p[1].split(" ");
      //  String cc=p[0];
        System.out.print("city name"+d);
        accessWebService(url,d);
    }
    public void accessWebService(String url,String id)
    {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(url,"route",id );
    }
    private class JsonReadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            if(params[1].equals("route")) {
                httppost.setHeader("Content-Type", "application/json");
                //this is your JSON string you are sending as a request
                String JsonString = "{\"city\":\"" + params[2] + "\"}";

                //pass the json string request in the entity
                HttpEntity entity = null;
                try {
                    entity = new ByteArrayEntity(JsonString.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httppost.setEntity(entity);
            }


            try
            {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            }
            catch (ClientProtocolException e)
            {

               // e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Not Available ",Toast.LENGTH_LONG).show();

            }
            catch (IOException e)
            {
          //      e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Not Available ",Toast.LENGTH_LONG).show();

            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is)
        {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try
            {
                while ((rLine = rd.readLine()) != null)
                {
                    answer.append(rLine);
                }
            }
            catch (IOException e)
            {
                //           Toast.makeText(getApplicationContext(),
                //                 "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }

            return answer;
        }

        @Override
        protected void onPostExecute(String result)
        {
            System.out.println("BEFORE POST EXECUTE");

            fillRoutes();
        }
    }// end async task
    public void fillRoutes()
    {
        ArrayList<Routes> arrayList = new ArrayList<>();
        ListView listItems;
        special activity = this;
        CustomAdapter adapter;
        listItems = (ListView) findViewById(R.id.listView);
        //-----------------------
        try
        {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Routes");

            for (int i = 0; i < jsonMainNode.length(); i++)
            {
                JSONObject row = jsonMainNode.getJSONObject(i);
                final Routes route = new Routes();
                route.setfname(row.optString("firstname"));
                route.setmobilenumber(row.optString("mobilenumber"));
                route.setspecial(row.optString("special"));

                route.setlname(row.optString("lastname"));
                route.setftiming(row.optString("from_timing"));
                route.setadd(row.optString("address"));
                route.setttiming(row.optString("to_timing"));


                arrayList.add(route);
            }
        }
        catch (JSONException e)
        {
     //       Toast.makeText(getApplicationContext(), "Error" + e.toString(),
       //             Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Doctor Not Available For this Speciality ",Toast.LENGTH_LONG).show();

        }

        //-----------------------
        Resources res =getResources();
        adapter = new CustomAdapter(activity,R.layout.row_listview,arrayList,res);
        listItems.setAdapter(adapter);

    }


    // build hash set for list view

    private HashMap<String, String> createUsers(String list, String id_name)
    {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(list, id_name);
        return user;
    }
}

