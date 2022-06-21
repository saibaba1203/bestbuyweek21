package com.playground.stores;

import com.playground.all_steps_package.StoresSteps;
import com.playground.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUD_WithSteps extends TestBase {

    static String name ="My second name";
    static String type = "xyz";
    static String address="107 my road";
    static String address2="Address 2";
    static String city="london";
    static String state="My state";
    static String zip ="ha3 5qj";
    static long lng = (long) 25.356;
    static long lat = (long) 11.256;
    static String hours = "Mon:10-9;Tue:10-9;Wed:10-9;Thurs:10-9;Fri:10-9;Sat:10-9:Sun:10-8";
    static int id;

@Steps
    StoresSteps storesSteps;
    @Title("This will create a new store")
    @Test
    public void test001(){
     ValidatableResponse response= storesSteps.createStore(name,type,address,address2,city,state,zip,lng,lat,hours);
     response.log().all().statusCode(201);
     id=response.log().all().extract().path("id");
    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002(){
        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoById(id);
        Assert.assertThat(storeMap,hasValue(name));
    }
    @Title("Update the store information and verify the updated information")
    @Test
    public void test003(){
        city =city+"_updated";
        address =address+"_updated";
        address2= address2+"_updated";
        zip =zip+"_updated";

        ValidatableResponse response = storesSteps.updateStoreDetails(id,name,type,address,address2,city,state,zip,lng,lat,hours);
        response.log().all().statusCode(200);

        HashMap<String ,Object> storeMap = storesSteps.getStoreInfoById(id);
        Assert.assertThat(storeMap,hasValue(name));
    }

    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004(){

        storesSteps.deleteStore(id).statusCode(200) ;
        storesSteps.getStoreInfoAfterDelete(id).statusCode(404);

    }
}
