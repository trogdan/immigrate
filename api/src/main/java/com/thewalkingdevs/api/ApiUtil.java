package com.thewalkingdevs.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewalkingdevs.api.constants.AppConstants;
import com.thewalkingdevs.api.constants.AppConstantsPrivate;
import com.thewalkingdevs.api.data.ImmigrateDAO;
import com.thewalkingdevs.api.model.CityBag;
import com.thewalkingdevs.api.model.CityCrime;
import com.thewalkingdevs.api.model.CityCrimeSkinny;
import com.thewalkingdevs.api.model.CityHealthCareInfo;
import com.thewalkingdevs.api.model.CityHealthCareInfoSkinny;
import com.thewalkingdevs.api.model.CityIndices;
import com.thewalkingdevs.api.model.CityPrices;
import com.thewalkingdevs.api.model.Places;
import com.thewalkingdevs.api.model.Place;
import com.thewalkingdevs.api.model.CityPricesSkinny;
import com.thewalkingdevs.api.model.ItemPrice;
import com.thewalkingdevs.api.model.ItemPriceSkinny;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.List;

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

    private static List<Place> getEssentials(String location){

        StringBuilder sb = getPlacesQueryBase(location);
        sb.append(getQueryParam("types", "department_store%7Cgrocery_or_supermarket"));

        String transportationJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, sb.toString()).toString());
        Places transportationContainer = convert(transportationJson, Places.class);
        List<Place> transportationResults = transportationContainer.getResults();
        List<Place> transportationResultsSubList = transportationResults.subList(0,5);
        return transportationResultsSubList;
    }

    private static List<Place> getTransportation(String location){

        StringBuilder sb = getPlacesQueryBase(location);
        sb.append(getQueryParam("types", "bus_station%7Csubway_station%7Ctrain_station%7Cairport"));

        String essentialsJson = callAPI(DAO.getRequestUrl(AppConstants.PLACES_QUERY_BASE, sb.toString()).toString());
        Places essentialsContainer = convert(essentialsJson, Places.class);
        List<Place> essientialsResults = essentialsContainer.getResults();

        List<Place> essientialsResultsSubList = essientialsResults.subList(0,5);
        return essientialsResultsSubList;
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

    private static void setPlacesDistance(List<Place> places, String location){
        for (Place place : places){
            float distFrom = AppUtils.getPlaceDistance(place, location);
            place.setDistFrom(distFrom);
        }
    }

    public static Places placesEndpointEssentials(String location){

        Places essentialsContainer = new Places();
        List<Place> essentialsPlaces = getEssentials(location);
        setPlacesDistance(essentialsPlaces, location);

        essentialsContainer.setResults(essentialsPlaces);
        return essentialsContainer;
    }

    public static Places placesEndpointTransportation(String location){

        Places transportationContainer = new Places();
        List<Place> transportationPlaces = getTransportation(location);
        setPlacesDistance(transportationPlaces, location);

        transportationContainer.setResults(transportationPlaces);
        return transportationContainer;
    }

    /**
     * @param location the city to use
     * @return {@link CityIndices}
     */
    public static Places placesEndpointServices(String location) {

        Places services = new Places();
        List<Place> servicesPlaces = new ArrayList<>();

        List<Place> immigrationOfficePlaces = getImmigrationOffices(location);
        List<Place> socialSecurityPlaces = getSocialSecurityOffices(location);
        List<Place> deptMotoVehiclesPlaces = getDeptMotorVehiclesOffices(location);
        List<Place> postOfficesPlaces = getPostOffices(location);

        if (!immigrationOfficePlaces.isEmpty()){
            // get closest immigration office
            Place immigrationOffice = immigrationOfficePlaces.get(0);
            // set description
            immigrationOffice.setDescription("Get your permanent resident card, or citizenship");
            // set distance
            float distFrom = AppUtils.getPlaceDistance(immigrationOffice, location);
            immigrationOffice.setDistFrom(distFrom);

            servicesPlaces.add(immigrationOffice);
        }

        if (!socialSecurityPlaces.isEmpty()){
            // get closest social security office
            Place socialSecurityOffice = socialSecurityPlaces.get(0);
            socialSecurityOffice.setDescription("Get your ability to work in the USA");
            // set distance
            float distFrom = AppUtils.getPlaceDistance(socialSecurityOffice, location);
            socialSecurityOffice.setDistFrom(distFrom);
            servicesPlaces.add(socialSecurityOffice);
        }

        if (!deptMotoVehiclesPlaces.isEmpty()){
            // get closest social dept of motor vehicles
            Place deptMotoVehicles = deptMotoVehiclesPlaces.get(0);
            deptMotoVehicles.setDescription("Get your license or state id");
            // set distance
            float distFrom = AppUtils.getPlaceDistance(deptMotoVehicles, location);
            deptMotoVehicles.setDistFrom(distFrom);
            servicesPlaces.add(deptMotoVehicles);
        }

        if (!postOfficesPlaces.isEmpty()){
            // get closest post office
            Place postOffice = postOfficesPlaces.get(0);
            postOffice.setDescription("Send and receive letters");
            float distFrom = AppUtils.getPlaceDistance(postOffice, location);
            postOffice.setDistFrom(distFrom);
            servicesPlaces.add(postOffice);
        }

        services.setResults(servicesPlaces);
        return services;
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
        CityPrices prices = null;

        String stem = "/" + AppConstants.NUMBEO_CITY_PRICES_ENDPOINT +
                AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                "&query=" + city;

        String json = callAPI(DAO.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());

        try {
            prices = convert(json, CityPrices.class);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return prices;
    }

    /**
     * Returns the city healthcare data from numbeo.
     * @param city the city to use
     * @return {@link CityPrices}
     */
    public static CityHealthCareInfo cityHealthCareInfoEndpoint(String city) {
        CityHealthCareInfo healthCareInfo = null;

        String stem = "/" + AppConstants.NUMBEO_CITY_HEALTHCARE_ENDPOINT +
                AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                "&query=" + city;

        String json = callAPI(DAO.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());

        try {
            healthCareInfo = convert(json, CityHealthCareInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return healthCareInfo;
    }

    /**
     * Returns the city crime data from numbeo.
     * @param city the city to use
     * @return {@link CityPrices}
     */
    public static CityCrime cityCrimeEndpoint(String city) {
        CityCrime cityCrime = null;

        String stem = "/" + AppConstants.NUMBEO_CITY_CRIME_ENDPOINT +
                AppConstants.NUMBEO_API_KEY_PREFIX + AppConstants.NUMBEO_API_KEY_VALUE +
                "&query=" + city;

        String json = callAPI(DAO.getRequestUrl(AppConstants.NUMBEO_QUERY_BASE, stem).toString());

        try {
            cityCrime = convert(json, CityCrime.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityCrime;
    }

    /**
     * Returns a city bag with all the relevant city data.
     * @param city the city to use
     * @return an instance of {@link CityBag}
     */
    public static CityBag cityBagEndpoint(String city) {
        CityBag cityBag = new CityBag();

        cityBag.setCityPrices(toCityPricesSkinny(cityPricesEndpoint(city)));
        cityBag.setCityCrime(toCityCrime(cityCrimeEndpoint(city)));
        cityBag.setCityHealthCareInfo(toHealthCareInfo(cityHealthCareInfoEndpoint(city)));

        return cityBag;
    }

    private static CityHealthCareInfoSkinny toHealthCareInfo(CityHealthCareInfo cityHealthCareInfo) {
        CityHealthCareInfoSkinny healthCareInfoSkinny = new CityHealthCareInfoSkinny();

        if (cityHealthCareInfo != null) {
            healthCareInfoSkinny.setAccuracyIndex(cityHealthCareInfo.getAccuracyIndex());
            healthCareInfoSkinny.setFriendlinessIndex(cityHealthCareInfo.getFriendlinessIndex());
            healthCareInfoSkinny.setHealthCareCost(cityHealthCareInfo.getHealthCareCost());
            healthCareInfoSkinny.setHealthCareStaffCompetency(cityHealthCareInfo.getHealthCareStaffCompetency());
            healthCareInfoSkinny.setResponsivenessIndex(cityHealthCareInfo.getResponsivenessIndex());
            healthCareInfoSkinny.setSpeedIndex(cityHealthCareInfo.getSpeedIndex());
            healthCareInfoSkinny.setModernEquipmentIndex(cityHealthCareInfo.getModernEquipmentIndex());
        }

        return healthCareInfoSkinny;
    }

    private static CityCrimeSkinny toCityCrime(CityCrime cityCrime) {
        CityCrimeSkinny crimeSkinny = new CityCrimeSkinny();

        if (cityCrime != null) {
            crimeSkinny.setCorruptionIndex(cityCrime.getCorruptionIndex());
            crimeSkinny.setCrimeLevel(cityCrime.getCrimeLevel());
            crimeSkinny.setViolentCrimeIndex(cityCrime.getViolentCrimeIndex());
            crimeSkinny.setDrugCrimeIndex(cityCrime.getDrugCrimeIndex());
            crimeSkinny.setDaylightSafetyIndex(cityCrime.getDaylightSafetyIndex());
            crimeSkinny.setNightSafetyIndex(cityCrime.getNightSafetyIndex());
            crimeSkinny.setDiversityThreatIndex(cityCrime.getDiversityThreatIndex());
        }

        return crimeSkinny;
    }

    private static CityPricesSkinny toCityPricesSkinny(CityPrices cityPrices) {
        CityPricesSkinny nonfat = new CityPricesSkinny();

        if (cityPrices != null) {
            nonfat.setCurrency(cityPrices.getCurrency());

            List<ItemPriceSkinny> itemPricesSkinny = new ArrayList<ItemPriceSkinny>();

            for (ItemPrice item : cityPrices.getPrices()) {
                ItemPriceSkinny nonfatItem = new ItemPriceSkinny();
                if (idOfInterest(item.getItemId())) {
                    nonfatItem.setItemName(item.getItemName());
                    nonfatItem.setLowestPrice(item.getLowestPrice());
                    nonfatItem.setItemId(item.getItemId());
                    nonfatItem.setHighestPrice(item.getHighestPrice());
                    itemPricesSkinny.add(nonfatItem);
                }
            }

            nonfat.setPrices(itemPricesSkinny);
        }

        return nonfat;
    }

    private static boolean idOfInterest(int itemId) {
        return itemId == 1 || itemId == 9 || itemId == 11 || itemId == 13 || itemId == 24 ||
                itemId == 26 || itemId == 28 || itemId == 30;
    }
}
