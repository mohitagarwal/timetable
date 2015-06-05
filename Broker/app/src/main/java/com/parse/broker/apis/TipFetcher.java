package com.parse.broker.apis;

import android.os.AsyncTask;
import android.util.Log;

import com.parse.broker.entities.Tips;

import org.apache.http.util.ByteArrayBuffer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TipFetcher extends AsyncTask<String, String, List<Tips>> {

    private static final String TIP_FETCHER_URL = "";
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected List<Tips> doInBackground(String... params) {

        String urlString = params[0];

        InputStream in = null;
        StringBuffer apiResponse = new StringBuffer();

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            byte[] contents = new byte[1024];
            int bytesRead = 0;

            while ((bytesRead = in.read(contents)) != -1) {
                apiResponse = apiResponse.append(new String(contents, 0, bytesRead));
            }

        } catch (Exception e) {
            Log.e("TipFetcher", "Couldnot fetch the tips " + e.getMessage());
            return null;
        }

        String jsonResponse = apiResponse.toString();
        
        return null;
    }

    @Override
    protected void onPostExecute(List<Tips> tipsList) {

    }
}