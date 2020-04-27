package com.example.Controllers;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.Models.User;
import com.example.lkos.Login;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class NetController extends AsyncTask<String, String, String> {
    private static final String TAG = Login.class.getSimpleName();
    private String Request;
    private String Body;
    private String Context;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            String baseURL = "https://lkosapi.azurewebsites.net/api";
            url = new URL(baseURL + strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(2000000000);
            conn.setConnectTimeout(2000000000);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod(strings[1]);
            if(!strings[0].equals("/token")){
                conn.setDoOutput(false);
                conn.addRequestProperty("Authorization","Bearer " + strings[2]);
            }
            else {
                conn.setDoOutput(true);
            }

            if (strings.length>3 & (strings[0].equals("/trips/search") || strings[0].equals("/object/search")))
            {
                //conn.addRequestProperty("query",strings[3]);
                Gson gson = new Gson();
                query q = new query(strings[3]);
                String query = gson.toJson(q);
                OutputStream os = conn.getOutputStream();
                os.write(query.getBytes(StandardCharsets.UTF_8));
                System.out.println("Adding the query "+ query);
                os.close();

            }


            conn.connect();
            //OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            //os.write(strings[2]);
            if (strings[0].equals("/token")) {

                JsonObject jsonObject = new JsonParser().parse(strings[2]).getAsJsonObject();
                OutputStream os = conn.getOutputStream();
                os.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
                os.close();
            }
            Log.d (TAG, "Yes: "+ conn.getResponseCode());
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream ip = conn.getInputStream();
                BufferedReader br1 =
                        new BufferedReader(new InputStreamReader(ip));
                StringBuilder response = new StringBuilder();
                String responseSingle = null;
                while ((responseSingle = br1.readLine()) != null) {
                    response.append(responseSingle);
                }
                String ReturnValue = response.toString();
                ip.close();
                conn.disconnect();
                if(strings[0].equals("/token") ){
                    JSONObject Response = new JSONObject(ReturnValue);
                    String Token = Response.getString("tokenString");
                    return Token;
                }

                return ReturnValue;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "nepaejo";
    }

        public String getToken (User user) throws ExecutionException, InterruptedException {
        Gson gson = new Gson();
        Request = "POST"; Body = gson.toJson(user); Context = "/token";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body).get();
        if (!result.equals("nepaejo"))
            return result;
        return "token";
    }
    public String getTripById (String token, int id) throws ExecutionException, InterruptedException {
        Request = "GET"; Body = token; Context = "/trips/"+Integer.toString(id);
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }
    public String getObjectById (String token, int id) throws ExecutionException, InterruptedException {
        Request = "GET"; Body = token; Context = "/object/"+Integer.toString(id);
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, token).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }
    public String getObjects (String token) throws ExecutionException, InterruptedException {
        Request = "GET"; Body = token; Context = "/object/";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }
    public String getTrips (String token) throws ExecutionException, InterruptedException {
        Request = "GET"; Body = token; Context = "/trips";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }

    public String getObjectsForTrip (String token, int id) throws ExecutionException, InterruptedException {
        Request = "GET"; Body = token; Context = "/trips/"+Integer.toString(id)+"/objects";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body).get();
        if (!result.equals("nepaejo")){
            System.out.println(result);
            return result;
        }

        return "Unable to retrieve data";
    }
    public String searchForObjects (String token, String searchCriteria) throws ExecutionException, InterruptedException {
        Request = "POST"; Body = token; Context = "/object/search";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body, searchCriteria).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }
    public String searchForTrips (String token, String searchCriteria) throws ExecutionException, InterruptedException {
        Request = "POST"; Body = token; Context = "/trips/search";
        NetController getRequest = new NetController();
        //Perform the doInBackground method, passing in our url
        String result = getRequest.execute(Context, Request, Body, searchCriteria).get();
        if (!result.equals("nepaejo"))
            return result;
        return "Unable to retrieve data";
    }
    }

    class query {
    String query;
    query (String q)
    {
        this.query=q;
    }
    }




