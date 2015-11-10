package com.thewalkingdevs.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewalkingdevs.api.constants.AppConstants;
import com.thewalkingdevs.api.data.ImmigrateDAO;
import com.thewalkingdevs.api.model.CityIndices;
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
public final class ApiUtil {
    private static ImmigrateDAO DAO = new ImmigrateDAO();

    private ApiUtil() {}

    public static String callAPI(String urlStr) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlStr);

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

            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * Converts json to applicable objects.
     *
     * @param json the json payload
     * @param clazz the class to use
     * @param <T> generic type parameter
     * @return An instance of the class clazz
     */
    private static <T> T convert(String json, Class clazz) {
        T result = null;
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonParser jsonParser = jsonFactory.createJsonParser(json);
            if (jsonParser.nextToken() == JsonToken.START_OBJECT) {
                result = (T) mapper.readValue(jsonParser, clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getRequestUrl(String location) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append("&radius=500&types=food");
        return requestUrl.toString();
    }

    private String getQueryParam(String key, String value){
        return "&" + key + "=" + value;
    }

    /**
     * Returns the city indices data from numbeo.
     * @param city the city to use
     * @return {@link CityIndices}
     */
    public static CityIndices cityIndicesEndpoint(String city) {
        String stem = "/" + AppConstants.NUMBEO_INDICES_ENDPOINT +
                AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                "&query=" + city;

        String json = callAPI(DAO.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());
        return convert(json, CityIndices.class);
    }

    /**
     * Returns the city prices data from numbeo.
     * @param city the city to use
     * @return {@link CityPrices}
     */
    public static CityPrices cityPricesEndpoint(String city) {
        String stem = "/" + AppConstants.NUMBEO_CITY_PRICES_ENDPOINT +
                AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                "&query=" + city;

        String json = callAPI(DAO.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());
        return convert(json, CityPrices.class);
    }
}
