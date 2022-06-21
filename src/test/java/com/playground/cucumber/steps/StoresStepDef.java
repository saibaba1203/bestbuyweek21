package com.playground.cucumber.steps;

import com.playground.all_steps_package.StoresSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StoresStepDef {
    static ValidatableResponse response;
    static int id;
    static String name1="My Name";
    static String type1= "xyz";
    static String address_1="107 my road";
    static String address_2="Address 2";
    static String city1="london";
    static String state1="My state";
    static  String zip1="ha3 5qj";
    static long lng1= (long) 25.356;
    static long lat1= (long) 11.256;
    static String hours1="Mon:10-9;Tue:10-9;Wed:10-9;Thurs:10-9;Fri:10-9;Sat:10-9:Sun:10-8";


    //id,name,type,address,address2,city,state,zip,lng,lat,hours)
    @Steps
    StoresSteps storesSteps;

    @When("^I create a new store by providing the information name type address address_ city state zip lat lng hours$")
    public void iCreateANewStoreByProvidingTheInformationNameTypeAddressAddress_CityStateZipLatLngHours() {
        response = storesSteps.createStore(name1,type1,address_1,address_2,city1,state1,zip1,lat1,lng1,hours1);
        id= response.log().all().extract().path("id");
    }

//    @When("^I create a new store by providing the information name \"([^\"]*)\" type \"([^\"]*)\" address \"([^\"]*)\" address(\\d+) \"([^\"]*)\" city \"([^\"]*)\" state \"([^\"]*)\" zip \"([^\"]*)\" lat \"([^\"]*)\" lng \"([^\"]*)\" hours \"([^\"]*)\"$")
//    public void iCreateANewStoreByProvidingTheInformationNameTypeAddressAddressCityStateZipLatLngHours(String name, String type, String address, String address2, String city, String state, String zip, String lat, String lng, String hours)  {
//
//        response = storesSteps.createStore(name,type,address,address2,city,state,zip, Long.parseLong(lat), Long.parseLong(lng),hours);
//        id= response.log().all().extract().path("id");
//        name1 = name;
//        type1=type;
//        address_1 =address;
//        address_2=address2;
//        city1 =city;
//        state1 = state;
//        zip1= zip;
//        lng1= Long.parseLong(lng);
//        lat1= Long.parseLong(lat);
//        hours1= hours;
//
//    }

    @And("^I verify that the sore has been created$")
    public void iVerifyThatTheSoreHasBeenCreated() {
        response.statusCode(201);
        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoById(id);
        Assert.assertThat(storeMap,hasValue(name1));
    }


    @And("^I update a address of the store created$")
    public void iUpdateAAddressOfTheStoreCreated() {

        city1 =city1+"_updated";
        address_1 =address_1+"_updated";
        address_2= address_2+"_updated";
        zip1 =zip1+"_updated";
        response= storesSteps.updateStoreDetails(id,name1,type1,address_1,address_2,city1,state1,zip1,lat1,lng1,hours1);


    }
    @And("^I verify that the store name hs been updated$")
    public void iVerifyThatTheStoreNameHsBeenUpdated() {
        response.statusCode(200);
        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoById(id);
        Assert.assertThat(storeMap,hasValue(name1));
    }


    @And("^I delete store has been deleted with same id$")
    public void iDeleteStoreHasBeenDeletedWithSameId() {

        storesSteps.deleteStore(id).statusCode(200);
    }

    @Then("^I verify that the store has been deleted$")
    public void iVerifyThatTheStoreHasBeenDeleted() {
        storesSteps.getStoreInfoAfterDelete(id).statusCode(404);
    }

}
