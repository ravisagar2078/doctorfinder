package com.example.ravisagar.doctor_finder;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class location extends AppCompatActivity {
    private String jsonResult;
    private String url = "https://traintrackagent.000webhostapp.com/getcity.php";
    private String url1 = "https://traintrackagent.000webhostapp.com/getdoctor.php";

    private Spinner spinner;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        spinner =(Spinner) findViewById(R.id.spinner1);
        accessWebService(url,"train",null);
    }
    public void onNext(View view) {

        String city = spinner.getSelectedItem().toString();
        String[]  p=city.split("=");
       // System.out.print("city name"+city);
      // String[] ww =p[1].split("}");
       // String tid = p[0];
       p=p[1].split(" ");
String cc=p[0];
        System.out.print("city name"+cc);
        accessWebService(url1,"route",cc);
    }
    public void accessWebService(String url,String type,String id)
    {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(url,type,id );
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
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return params[1];
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
            if(result.equals("train")) {
                ListDrawer();

            }
            else {fillRoutes();}
        }
    }// end async task
    public void fillRoutes()
    {
        ArrayList<Routes> arrayList = new ArrayList<>();
        ListView listItems;
        location activity = this;
        CustomAdapter adapter;
        listItems = (ListView) findViewById(R.id.listview1);
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
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_LONG).show();

        }

        //-----------------------
        Resources res =getResources();
        adapter = new CustomAdapter(activity,R.layout.row_listview,arrayList,res);
        listItems.setAdapter(adapter);

    }


    // build hash set for list view
    public void ListDrawer()
    {

        List<Map<String, String>> userList = new ArrayList<>();

        try
        {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("Trains");

            for (int i = 0; i < jsonMainNode.length(); i++)
            {
                JSONObject row = jsonMainNode.getJSONObject(i);
                String id = row.optString("city");
                String id_latlng = id+"    ";
                userList.add(createUsers("trains", id_latlng));

            }
        }
        catch (JSONException e)
        {
         //   Toast.makeText(getApplicationContext(), "No records found.",
           //         Toast.LENGTH_LONG).show();

        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, userList,
                android.R.layout.simple_list_item_1,
                new String[] {"trains"}, new int[] { android.R.id.text1 });

        simpleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createUsers(String list, String id_name)
    {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(list, id_name);
        return user;
    }
}

