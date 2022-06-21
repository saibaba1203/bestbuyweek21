package com.playground.cucumber.steps;

import com.playground.all_steps_package.ServicesSteps;
import com.playground.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyServicesStepsDef {
    static ValidatableResponse response;
    static int id;
    String name1;
    @Steps
    ServicesSteps servicesSteps;

    @When("^I create a new service by providing the information name \"([^\"]*)\"$")
    public void iCreateANewServiceByProvidingTheInformationName(String name) {
        name1 = name + TestUtils.getRandomValue();
        response = servicesSteps.crateServices(name1);
        id = response.log().all().extract().path("id");

    }

    @And("^I verify that the service has been created$")
    public void iVerifyThatTheServiceHasBeenCreated() {
        response.statusCode(201);
        HashMap<String, Object> servicesMap = servicesSteps.getSingleServiceInfoById(id);
        Assert.assertThat(servicesMap, hasValue(name1));
    }

    @And("^I update a name of the service$")
    public void iUpdateANameOfTheService() {
        name1 =name1+"_Updated";
        response =servicesSteps.updateService(id,name1);
    }
    @And("^I verify that the service has been updated$")
    public void iVerifyThatTheServiceHasBeenUpdated() {
        response.statusCode(200);
        HashMap<String ,Object>updatedServiceMap =servicesSteps.getSingleServiceInfoById(id);
        Assert.assertThat(updatedServiceMap,hasValue(name1));

    }


    @And("^I delete the service with same id$")
    public void iDeleteTheServiceWithSameId() {
        servicesSteps.deleteService(id).statusCode(200);
    }




    @Then("^I verify that the service has been deleted$")
    public void iVerifyThatTheServiceHasBeenDeleted() {
        servicesSteps.getSingleServiceById(id).statusCode(404);
    }
}
