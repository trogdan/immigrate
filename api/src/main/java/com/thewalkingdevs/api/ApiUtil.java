package com.thewalkingdevs.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;

/**
 * API Util for api calls.
 */
public class ApiUtil {

    public String callApi(String urlStr) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            //URL url = new URL();

            // Create the request and open the connection
           // urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            return buffer.toString();
        } catch (IOException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }

        return null;
    }

    public String getRequestUrl(String location) {
        StringBuilder requestUrl = new StringBuilder();
        //requestUrl.append(AppConstants.PLACES_QUERY_BASE);
        //requestUrl.append(getQueryParam(AppConstants.PLACES_QUERY_LOCATION, location));
        requestUrl.append("&radius=500&types=food");
        return requestUrl.toString();
    }

    private String getQueryParam(String key, String value){
        return "&" + key + "=" + value;
    }
}
