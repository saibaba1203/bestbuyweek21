package com.playground.all_steps_package;

import com.playground.constants.EndPoints;
import com.playground.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    @Step("Creating service with name : {0}")
    public ValidatableResponse crateServices(String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .when()
                .post(EndPoints.GET_SERVICES)
                .then();

    }
    @Step

    public ValidatableResponse getAllServicesInfo(){
      return  SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_SERVICES)
                .then()
                .extract()
                .path("");

    }

    @Step("Getting the  service information with serviceId: {0}")
    public HashMap<String, Object> getSingleServiceInfoById(int id) {

        HashMap<String, Object> servicesMap = SerenityRest.given().log().all()
                .when()
                .pathParam("serviceID", id)
                .get(EndPoints.GET_SINGLE_SERVICES)
                .then()
                .extract()
                .path("");
        return servicesMap;

    }
//service

    @Step ("Updating service information with id:{0}, name :{1}")
    public ValidatableResponse updateService(int id, String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("serviceID", id)
                .body(servicesPojo)
                .when()
                .put(EndPoints.GET_SINGLE_SERVICES)
                .then();
    }

    @Step("Deleting service information with serviceId: {0}")
    public ValidatableResponse deleteService(int id) {

        return SerenityRest.given().log().all()
                .pathParam("serviceID", id)
                .when()
                .delete(EndPoints.GET_SINGLE_SERVICES)
                .then();

    }



    @Step("Getting service information with serviceId: {0}")
    public ValidatableResponse getSingleServiceById(int id) {

        return SerenityRest.given().log().all()
                .pathParam("serviceID", id)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES)
                .then();
    }
}
