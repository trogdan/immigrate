/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.thewalkingdevs.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.thewalkingdevs.api.data.ImmigrateDAO;
import com.thewalkingdevs.api.model.CityIndices;
import com.thewalkingdevs.api.model.CityPrices;
import com.thewalkingdevs.api.model.Places;
import com.thewalkingdevs.api.model.PlacesBag;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "api.thewalkingdevs.com",
                ownerName = "api.thewalkingdevs.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    private static final Logger LOG = Logger.getLogger(ImmigrateDAO.class.getName());

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);
        //making a change --Levan
        return response;
    }

    @ApiMethod(
            name = "getCityPrices",
            path = "cityprices",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public CityPrices getCityPrices(@Named("city") String city) {
        return ApiUtil.cityPricesEndpoint(city);
    }

    @ApiMethod(
            name = "getCityIndices",
            path = "cityindices",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public CityIndices getCityIndices(@Named("city") String city) {

        city = "Boston";
        return ApiUtil.cityIndicesEndpoint(city);
    }

    @ApiMethod(
            name = "getPlacesBag",
            path = "placesbag",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public PlacesBag getPlacesBag(@Named("location") String location) {

        Places services =  ApiUtil.placesEndpointServices(location);
        Places transportation = ApiUtil.placesEndpointTransportation(location);
        Places essentials = ApiUtil.placesEndpointEssentials(location);

        PlacesBag placesBag = new PlacesBag();
        placesBag.setEssentials(essentials);
        placesBag.setServices(services);
        placesBag.setTransportation(transportation);

        return placesBag;
    }

    @ApiMethod(
            name = "getPlaces",
            path = "places",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Places getPlaces(@Named("location") String location) {

        Places essentials = ApiUtil.placesEndpointServices(location);
        return essentials;
    }

}
