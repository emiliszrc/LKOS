package com.example.Controllers;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lkos.Login;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import com.example.Models.Trip;
import com.example.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

public class NetController extends AsyncTask<String, String, String> {
    private static final String TAG = Login.class.getSimpleName();
    private static String baseURL = "https:lkosapi.azurewebsites.net/api";
    private static HttpURLConnection conn;
    private String Request;
    private String Body;
    private String Context;
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(baseURL + strings[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(2000000000);
            conn.setConnectTimeout(2000000000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod(strings[1]);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            Log.d (TAG, "Yes: "+ url);
            conn.connect();
            //OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            //os.write(strings[2]);
            JsonObject jsonObject = new JsonParser().parse(strings[2]).getAsJsonObject();
            OutputStream os = conn.getOutputStream();
            os.write(jsonObject.toString().getBytes("UTF-8"));
            os.close();
            Log.d (TAG, "Yes: "+ strings[2]);
            Log.d (TAG, "Yes: "+conn.getResponseCode());
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream ip = conn.getInputStream();
                BufferedReader br1 =
                        new BufferedReader(new InputStreamReader(ip));
                StringBuilder response = new StringBuilder();
                String responseSingle = null;
                while ((responseSingle = br1.readLine()) != null) {
                    response.append(responseSingle);
                }
                String A = response.toString();
                Log.d (TAG, "Yes: " + A);
                return A;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d (TAG, "Yes: "+e);
        }
        return "nepaejo";
    }

        public String getToken (User user) throws ExecutionException, InterruptedException {
            Gson gson = new Gson();
            Request = "POST"; Body = gson.toJson(user); Context = "/token";
            NetController getRequest = new NetController();
            //Perform the doInBackground method, passing in our url
            String result = getRequest.execute("/token", "POST", gson.toJson(user)).get();
            if (result!="nepaejo")
                return result;
            return "token";
        }

    }


    /*
    public static Trip getTripById (int id) throws Exception {
        Gson gson = new Gson();
        SharedPreferenceController sharedPreferenceController = new SharedPreferenceController();
        connect("trip/" + String.valueOf(id), "GET", sharedPreferenceController.returnToken());
        if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader in=new BufferedReader( new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return gson.fromJson(sb.toString(), Trip.class);
        }
        return null;
    }
    public static Object getObjectById (int id) throws Exception {
        Gson gson = new Gson();
        SharedPreferenceController sharedPreferenceController = new SharedPreferenceController();
        connect("object/" + String.valueOf(id), "GET", sharedPreferenceController.returnToken());
        if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK)
        {
            BufferedReader in=new BufferedReader( new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line="";
            while((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            return gson.fromJson(sb.toString(), Object.class);
        }
        return null;
    }

     */




