package com.playground.all_steps_package;

import com.playground.constants.EndPoints;
import com.playground.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.yecht.Data;

import java.util.HashMap;

public class StoresSteps {

    @Step ("Creating store with name : {0},type : {1}, address : {2},address2 : {3}, city : {4},state : {5},zip : {6},lat : {7},lng : {8},hours : {9}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state,
                                           String zip, long lat, long lng, String hours) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(storesPojo)
                .when()
                .post(EndPoints.GET_STORES)
                .then();

    }

    @Step ("Getting the store information with storeId: {0}")

    public HashMap<String, Object> getStoreInfoById(int id) {

        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", id)
                .get(EndPoints.GET_SINGLE_STORE)
                .then()
                .extract()
                .path("");
        return storeMap;
    }


    @Step ("Creating store with  id :{0},name : {1},type : {2}, address : {3},address2 : {4}, city : {5},state : {6},zip : {7},lat : {8},lng : {9},hours : {10}")
    public ValidatableResponse updateStoreDetails(int id,String name, String type, String address, String address2, String city, String state,
                                                  String zip, long lat, long lng, String hours){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID",id)
                .body(storesPojo)
                .when()
                .put(EndPoints.GET_SINGLE_STORE)
                .then();

    }

    @Step ("Deleting store information with storeId: {0}")
    public ValidatableResponse deleteStore(int id){
        return SerenityRest.given().log().all()
                .pathParam("storeID",id)
                .when()
                .delete(EndPoints.GET_SINGLE_STORE)
                .then();

    }

    @Step("Getting store information with storeId: {0}")
    public ValidatableResponse getStoreInfoAfterDelete(int id){
        return  SerenityRest.given().log().all()
                .pathParam("storeID",id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE)
                .then();

    }
}
