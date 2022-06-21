package com.playground.services;

import com.playground.all_steps_package.ServicesSteps;
import com.playground.testbase.TestBase;
import com.playground.utils.TestUtils;
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
public class ServiceCURDWithSteps extends TestBase {

    static String name="Magnolia Home Theater"+TestUtils.getRandomValue();
    static int id;


    @Steps
    ServicesSteps servicesSteps;

    @Title("This will create a new service")
    @Test
    public void test001(){
        ValidatableResponse response = servicesSteps.crateServices( name);
        response.log().all().statusCode(201);
        id =response.log().all().extract().path("id");
    }


    @Title("Verify if the service was added to the application")
    @Test
    public void test002(){

        HashMap<String,Object> servicesMap = servicesSteps.getSingleServiceInfoById(id);
        Assert.assertThat(servicesMap,hasValue(name));
    }
    @Title("Update the service information and verify the updated information")
    @Test
    public void test003(){

        name= name+"_updated";
        ValidatableResponse response =servicesSteps.updateService(id,name);
        response.log().all().statusCode(200);

        HashMap<String ,Object>updatedServiceMap =servicesSteps.getSingleServiceInfoById(id);
        Assert.assertThat(updatedServiceMap,hasValue(name));

    }
    @Title("Delete the service and verify if the service is deleted!")
    @Test
    public void test004(){

        servicesSteps.deleteService(id).statusCode(200);
      servicesSteps.getSingleServiceById(id).statusCode(404);
    }



}
