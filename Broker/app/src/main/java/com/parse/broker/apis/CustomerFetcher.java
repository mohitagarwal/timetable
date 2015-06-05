package com.parse.broker.apis;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.parse.broker.entities.Customer;
import com.parse.broker.entities.CustomersResponse;
import com.parse.broker.entities.GetAllProviderCustomers;
import com.parse.broker.entities.Tips;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CustomerFetcher extends AsyncTask<Void, String, List<CustomersResponse>> {

    private String PROVIDER_CUSTOMER_FETCH_URL="http://172.20.209.45:30000/stock/providerCustomers/";
    private final int providerId;
    private final ObjectMapper objectMapper;

    public CustomerFetcher(int providerId, ObjectMapper objectMapper){
        this.providerId = providerId;
        this.objectMapper = objectMapper;
        PROVIDER_CUSTOMER_FETCH_URL = PROVIDER_CUSTOMER_FETCH_URL + providerId;
    }

    @Override
    protected List<CustomersResponse> doInBackground(Void... params) {

        String urlString = PROVIDER_CUSTOMER_FETCH_URL;

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
        GetAllProviderCustomers getAllProviderCustomers = null;
        try {
            getAllProviderCustomers = objectMapper.readValue(jsonResponse, GetAllProviderCustomers.class);
        } catch (IOException e) {
            Log.d("CustomerFetcher","Encountered exception while fetching customers for provider "+e.getMessage());
            return null;
        }

        return getAllProviderCustomers.getCustomers();
    }

    @Override
    protected void onPostExecute(List<CustomersResponse> customersResponses) {

    }
}
