package com.example.android.newsappproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Sidak Pasricha on 19-Jul-18.
 */

public class DataFetcher {

    private static final String LOG_TAG = "DataFetcher";

    public DataFetcher(){
    }

    private static URL stringToURL(String s){
        URL url = null;

        try {
            url = new URL(s);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "stringToURL: Could not Parse String to URL");
        }

        return url;
    }

    private static String readFromInputStream(InputStream is) throws IOException{
        StringBuilder sb = new StringBuilder();

        if(is != null){
            InputStreamReader inputStreamReader = new InputStreamReader(is, Charset.forName("utf-8"));
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                line = br.readLine();
            }
        }
        return sb.toString();
    }

    private static String requestData(String urlString) throws IOException{
        URL url = stringToURL(urlString);
        String response = "";
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();

            if(connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                response = readFromInputStream(inputStream);
            }
        } catch (IOException e){
            Log.e(LOG_TAG, "requestData: IOException Occurred");
        } finally {
            if(connection != null){
                connection.disconnect();
            }

            if(inputStream != null){
                inputStream.close();
            }
        }

        return  response;
    }

    private static String[] titleSplit(String webTitle){
        int index = webTitle.indexOf("|");
        if(index < 0){
            return new String[]{webTitle, ""};
        }
        String title = webTitle.substring(0, index-1);
        String author = webTitle.substring(index+2);
        return new String[]{title, author};
    }

    private static String dateFormatter(String webDate){
        // Credits to the following link for this method:
        // https://stackoverflow.com/questions/44705738/format-date-and-time-in-string-format-from-an-api-response

        String toReturn = "";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        Date date = null;
        try {
            date = parser.parse(webDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimeZone tz = TimeZone.getDefault();
        SimpleDateFormat s1 = new SimpleDateFormat("d MMM yyyy  h:mm a", Locale.ENGLISH);
        s1.setTimeZone(tz);

        toReturn = s1.format(date);

        return toReturn;
    }

    public static ArrayList<News> parseJSON(String urlString) throws JSONException {
        String response = "";
        ArrayList<News> news = new ArrayList<>();

        if(urlString.equals("")){
            return news;
        }

        try {
            response = requestData(urlString);
        } catch (IOException e){
            e.printStackTrace();
        }

        JSONObject root = new JSONObject(response);
        JSONObject resp = root.getJSONObject("response");
        JSONArray items = resp.getJSONArray("results");

        for(int i = 0; i < items.length(); i++){
            JSONObject item = (JSONObject) items.get(i);
            String webTitle = item.getString("webTitle");
            String section = item.getString("sectionName");
            String webDate = item.optString("webPublicationDate", "No Date");
            String url = item.getString("webUrl");
            String[] splitWebTitle = titleSplit(webTitle);
            String title = splitWebTitle[0];
            String author = splitWebTitle[1];
            String date = dateFormatter(webDate);
            News newsItem = new News(title, author, section, date, url);
            news.add(newsItem);
        }

        return news;
    }

}

