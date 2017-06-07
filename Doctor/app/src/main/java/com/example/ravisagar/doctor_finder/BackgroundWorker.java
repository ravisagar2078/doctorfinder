package com.example.ravisagar.doctor_finder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Ravi Sagar on 5/7/2017.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {

    private String username,email,firstname,lastname,password,mobilenumber,address,city,special,from_timing,to_timing;


    Context context;
    HttpURLConnection httpURLConnection;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    //public String getTrain() {
       /* return trainname;
    }*/

    @Override
    protected String doInBackground(String... params) {
        String response = null;
        String type = params[0];
//        Toast.makeText(context, "2 baar", Toast.LENGTH_LONG).show();
        String cods_url = "http://traintrackagent.000webhostapp.com/registeruser.php";
      String doc_url="http://traintrackagent.000webhostapp.com/doctorregister.php";
        String login_url="http://traintrackagent.000webhostapp.com/login.php";
        // String status_url="http://freakish-assault.000webhostapp.com/changeUserStatus.php";
        //String tname_url="http://freakish-assault.000webhostapp.com/getTrainName.php";

        //String cods_url = "http://traintrackagent.5gbfree.com/insertCods.php";
        //String register_url="http://traintrackagent.5gbfree.com/regis.php";
        //String login_url="http://traintrackagent.5gbfree.com/login.php";


        if (type.equals("registeruser")) {
            try {
     //           Toast.makeText(context, "2 baar", Toast.LENGTH_LONG).show();

                email = params[1];
                username = params[2];
                password = params[3];
                mobilenumber = params[4];
                firstname = params[5];
                lastname = params[6];
                Log.i("email: ", email);
                Log.i("us: ", username);
                Log.i("email: ", password);
                Log.i("email: ", mobilenumber);

                Log.i("email: ", firstname);
                URL url = new URL(cods_url);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        + URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        + URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                        + URLEncoder.encode("lastname", "UTF-8")+"="+URLEncoder.encode(lastname, "UTF-8")+"&"
                        + URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                        + URLEncoder.encode("mobilenumber", "UTF-8")+"="+URLEncoder.encode(mobilenumber, "UTF-8");

                OutputStreamWriter ow = new OutputStreamWriter(httpURLConnection.getOutputStream());
                Log.i("data: ", post_data);

                ow.write(post_data);
              // System.out.printf(post_data);
                ow.flush();
                ow.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));

                String output;
                output = br.readLine();
                Log.i("insertCods Response: ", output);
                br.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return output;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("register")) {
            try {
                //           Toast.makeText(context, "2 baar", Toast.LENGTH_LONG).show();
                username = params[1];
                firstname = params[2];
                lastname = params[3];
                email = params[4];
                password = params[5];
                mobilenumber = params[6];
                address = params[7];
                city = params[8];
                special = params[9];
                from_timing = params[10];
                to_timing = params[11];

                Log.i("email: ", email);
                Log.i("us: ", username);
                Log.i("email: ", password);
                Log.i("email: ", mobilenumber);

                Log.i("email: ", firstname);
                URL url = new URL(doc_url);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                        + URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                        + URLEncoder.encode("firstname", "UTF-8")+"="+URLEncoder.encode(firstname, "UTF-8")+"&"
                        + URLEncoder.encode("lastname", "UTF-8")+"="+URLEncoder.encode(lastname, "UTF-8")+"&"
                        + URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"
                        + URLEncoder.encode("mobilenumber", "UTF-8")+"="+URLEncoder.encode(mobilenumber, "UTF-8")+"&"
                        + URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address, "UTF-8")+"&"
                        + URLEncoder.encode("city", "UTF-8")+"="+URLEncoder.encode(city, "UTF-8")+"&"
                        + URLEncoder.encode("special", "UTF-8")+"="+URLEncoder.encode(special, "UTF-8")+"&"
                        + URLEncoder.encode("from_timing", "UTF-8")+"="+URLEncoder.encode(from_timing, "UTF-8")+"&"
                        + URLEncoder.encode("to_timing", "UTF-8")+"="+URLEncoder.encode(to_timing, "UTF-8");

                OutputStreamWriter ow = new OutputStreamWriter(httpURLConnection.getOutputStream());
                Log.i("data: ", post_data);

                ow.write(post_data);
                // System.out.printf(post_data);
                ow.flush();
                ow.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));

                String output;
                output = br.readLine();
                Log.i("insertCods Response: ", output);
                br.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return output;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (type.equals("login")) {

            try {
                username = params[1];
                password = params[2];

                URL url = new URL(login_url);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");


                OutputStreamWriter ow = new OutputStreamWriter(httpURLConnection.getOutputStream());
                ow.write(post_data);
                ow.flush();
                ow.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader
                        (inputStream, "iso-8859-1"));

                String output;
                output = br.readLine();
                System.out.println("Output: " + output);
//                System.out.println("Output from Server .... \n");
                br.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return output;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (result.equals("success"))               // when driver logged in
        {
      //      Toast.makeText(context, "login hogaya", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, usernext.class);
            //intent.putExtra("uname", username);
         //   intent.putExtra("uname", username);
       //     intent.putExtra("email", email);

            context.startActivity(intent);


        } else if (result.equals("registersuccess"))   // when new user successfully registered
        {
        //     Toast.makeText(context, "achi wayo", Toast.LENGTH_LONG).show();

             Intent intent = new Intent(context, usernext.class);
            context.startActivity(intent);
        } else if (result.equals("register"))       // when current coordinates are inserted/updated in the Coordinates table
        {
            Intent intent = new Intent(context, doctorpage.class);
            //intent.putExtra("uname", username);
            context.startActivity(intent);

        }
        else if (result.equals("update_success"))    //user status changed to 'out' after LOGOUT
        {
        } else if (result.equals("Welcome"))           // when admin Logged in
        {
           // Intent intent1 = new Intent(context, admin.class);
         //   intent1.putExtra("uname", username);
       //     context.startActivity(intent1);
            //Toast.makeText(context,"Admin Logged in",Toast.LENGTH_LONG).show();
        } else {
            builder.setTitle("Alert!");
            builder.setMessage(result);
        //    builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            builder.show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}