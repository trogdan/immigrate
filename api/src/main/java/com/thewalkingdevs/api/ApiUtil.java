package com.thewalkingdevs.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewalkingdevs.api.constants.AppConstants;
import com.thewalkingdevs.api.data.ImmigrateDAO;
import com.thewalkingdevs.api.model.CityPrices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * API Util for api calls.
 */
public class ApiUtil {

    public static CityPrices callApi(String urlStr) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            ImmigrateDAO dao = new ImmigrateDAO();
            String stem = "/" + AppConstants.NUMBEO_CITY_PRICES_ENDPOINT +
                    AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                    "&query=" + urlStr;
            URL url = new URL(dao.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());

            // Create the request and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
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

            return toCityPrices(buffer.toString());
        } catch (IOException e) {

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

    private static CityPrices toCityPrices(String input) {
        CityPrices cityPrices = null;
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser jsonParser = jsonFactory.createJsonParser(input);
            if (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                cityPrices = mapper.readValue(jsonParser, CityPrices.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityPrices;
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
