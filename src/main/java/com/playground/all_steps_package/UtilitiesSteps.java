package com.playground.all_steps_package;

import com.playground.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UtilitiesSteps {

    @Step
    public ValidatableResponse getVersionFromUtilities() {

        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_UTILITIES_VERSION)
                .then();
    }

    @Step
    public ValidatableResponse GetHealthCheckFromUtilities() {

        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_UTILITIES_HEALTHCHECK)
                .then();
    }
}
