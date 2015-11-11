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
import com.thewalkingdevs.api.model.CityBag;
import com.thewalkingdevs.api.model.CityIndices;
import com.thewalkingdevs.api.model.CityPrices;
import com.thewalkingdevs.api.model.Places;

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
        return ApiUtil.cityIndicesEndpoint(city);
    }

    @ApiMethod(
            name = "getCityBag",
            path = "citybag",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public CityBag getCityBag(@Named("city") String city) {
        return ApiUtil.cityBagEndpoint(city);
    }

    @ApiMethod(name = "getPlaces")
    public Places getPlaces() {

        //test implementation, "location" would be passed as parameter into this method
        String location = "42.4183333,-71.1066667";

        ImmigrateDAO immigrateDAO = new ImmigrateDAO();
        String apiResponse = immigrateDAO.getPlaces(location);

        //log response
        LOG.info(apiResponse);

        Places myPlaces = new Places();
        myPlaces.setData(apiResponse);

        return myPlaces;
    }

}
