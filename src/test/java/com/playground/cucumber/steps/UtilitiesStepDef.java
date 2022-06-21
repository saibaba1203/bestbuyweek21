package com.playground.cucumber.steps;

import com.playground.all_steps_package.UtilitiesSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class UtilitiesStepDef {

    static ValidatableResponse response;
    @Steps
    UtilitiesSteps utilitiesSteps;

    @When("^I am getting version from utilities$")
    public void iAmGettingVersionFromUtilities() {
        response = utilitiesSteps.getVersionFromUtilities();
    }

    @And("^I verify that I have accessed it$")
    public void iVerifyThatIHaveAccessedIt() {
        response.statusCode(200);

    }

    @And("^I am getting HealthCheck from Utilities$")
    public void iAmGettingHealthCheckFromUtilities() {
        response = utilitiesSteps.GetHealthCheckFromUtilities();

    }

    @Then("^I am verifying that I have successfully getting that$")
    public void iAmVerifyingThatIHaveSuccessfullyGettingThat() {
        response.statusCode(200);
    }
}
