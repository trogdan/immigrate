package com.thewalkingdevs.api.data;

import com.thewalkingdevs.api.constants.AppConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class ImmigrateDAO {

    private static final Logger LOG = Logger.getLogger(ImmigrateDAO.class.getName());

    public ImmigrateDAO(){}

    private String getJSON(String requestUrl){

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String jsonData = null;

        try {

            URL url = new URL(requestUrl.toString());

            // Create the request and open connection to movie db
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
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
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            jsonData = buffer.toString();

        } catch (IOException e) {

            LOG.info(AppConstants.MESSAGE_CONNECTION_ERROR);
            return null;

        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    LOG.info("Error closing stream");
                }
            }
        }

        return jsonData;

    }

    public String getPlaces(String location) {

        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(AppConstants.PLACES_QUERY_BASE);
        requestUrl.append(getQueryParam(AppConstants.PLACES_QUERY_LOCATION, location));

        //following string is for test implemtation purposes, will be updated
        requestUrl.append("&radius=500&types=food&key=***REMOVED***");

        String response = getJSON(requestUrl.toString());
        return response;
    }

    private String getQueryParam(String key, String value){
        return "&" + key + "=" + value;
    }

}
