package com.thewalkingdevs.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewalkingdevs.api.constants.AppConstants;
import com.thewalkingdevs.api.constants.AppConstantsPrivate;
import com.thewalkingdevs.api.data.ImmigrateDAO;
import com.thewalkingdevs.api.model.CityIndices;
import com.thewalkingdevs.api.model.CityPrices;
import com.thewalkingdevs.api.model.Places;
import com.thewalkingdevs.api.model.Place;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * API Util for api calls.
 */
public final class ApiUtil {
    private static ImmigrateDAO DAO = new ImmigrateDAO();

    private static final Logger LOG = Logger.getLogger(ApiUtil.class.getName());

    private ApiUtil() {}

    public static String callAPI(String urlStr) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        LOG.info(urlStr);

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

    private static String getQueryParam(String key, String value){
        return "&" + key + "=" + value;
    }

    private static List<Place> getPostOffices(String location){

        StringBuilder sb = getPlacesQueryBase(location);
        sb.append(getQueryParam(AppConstants.PLACES_QUERY_NAME, "Post%20Office"));

        String postOfficesJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, sb.toString()).toString());
        Places immigrationOfficeContainer = convert(postOfficesJson, Places.class);
        List<Place> postOfficesResults = immigrationOfficeContainer.getResults();
        return postOfficesResults;
    }


    private static List<Place> getImmigrationOffices(String location){

        StringBuilder sb = getPlacesQueryBase(location);
        sb.append(getQueryParam(AppConstants.PLACES_QUERY_NAME, "USCIS"));

        String immigrationOfficeJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, sb.toString()).toString());
        Places immigrationOfficeContainer = convert(immigrationOfficeJson, Places.class);
        List<Place> immigrationOfficeResults = immigrationOfficeContainer.getResults();
        return immigrationOfficeResults;
    }

    private static List<Place> getDeptMotorVehiclesOffices(String location){

        StringBuilder sb = getPlacesQueryBase(location);
        sb.append(getQueryParam(AppConstants.PLACES_QUERY_NAME, "Department%20of%20Motor%20Vehicles"));

        String deptMotorVehiclesJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, sb.toString()).toString());
        Places deptMotorVehiclesContainer = convert(deptMotorVehiclesJson, Places.class);
        List<Place> deptMotorVehiclesResults = deptMotorVehiclesContainer.getResults();
        return deptMotorVehiclesResults;
    }

    private static List<Place> getSocialSecurityOffices(String location){

        StringBuilder socialSecurityOfficeQueryStem = getPlacesQueryBase(location);
        socialSecurityOfficeQueryStem.append(getQueryParam(AppConstants.PLACES_QUERY_NAME, "Social%20Security"));

        String socialSecurityOfficeJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, socialSecurityOfficeQueryStem.toString()).toString());
        Places socialSecurityContainer = convert(socialSecurityOfficeJson, Places.class);
        List<Place> socialSecurityResults = socialSecurityContainer.getResults();
        return socialSecurityResults;
    }

    private static StringBuilder getPlacesQueryBase(String location){

        StringBuilder immigrationOfficeQueryStem = new StringBuilder();
        immigrationOfficeQueryStem.append("key=" + AppConstantsPrivate.PLACES_API_KEY);
        immigrationOfficeQueryStem.append(getQueryParam(AppConstants.PLACES_QUERY_RADIUS, "10000"));
        immigrationOfficeQueryStem.append(getQueryParam(AppConstants.PLACES_QUERY_LOCATION, location));
        immigrationOfficeQueryStem.append(getQueryParam("rankBy", "distance"));
        return immigrationOfficeQueryStem;
    }

    /**
     * Returns the city indices data from numbeo.
     * @param location the city to use
     * @return {@link CityIndices}
     */
    public static Places placesEndpoint(String location) {

        Places essentials = new Places();
        List<Place> essentialPlaces = new ArrayList<>();

        List<Place> immigrationOfficePlaces = getImmigrationOffices(location);
        List<Place> socialSecurityPlaces = getSocialSecurityOffices(location);
        List<Place> deptMotoVehiclesPlaces = getDeptMotorVehiclesOffices(location);
        List<Place> postOfficesPlaces = getPostOffices(location);

        if (!immigrationOfficePlaces.isEmpty()){
            // get closest immigration office
            Place immigrationOffice = immigrationOfficePlaces.get(0);
            immigrationOffice.setDescription("Get your permanent resident card, or citizenship");
            essentialPlaces.add(immigrationOffice);
        }

        if (!socialSecurityPlaces.isEmpty()){
            // get closest social security office
            Place socialSecurityOffice = socialSecurityPlaces.get(0);
            socialSecurityOffice.setDescription("Get your ability to work in the USA");
            essentialPlaces.add(socialSecurityOffice);
        }

        if (!deptMotoVehiclesPlaces.isEmpty()){
            // get closest social dept of motor vehicles
            Place deptMotoVehicles = deptMotoVehiclesPlaces.get(0);
            deptMotoVehicles.setDescription("Get your license or state id");
            essentialPlaces.add(deptMotoVehicles);
        }

        if (!postOfficesPlaces.isEmpty()){
            // get closest post office
            Place postOffice = postOfficesPlaces.get(0);
            postOffice.setDescription("Send and receive letters");
            essentialPlaces.add(postOffice);
        }

        essentials.setResults(essentialPlaces);
        return essentials;
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
