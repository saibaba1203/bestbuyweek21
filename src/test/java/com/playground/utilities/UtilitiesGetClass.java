package com.playground.utilities;

import com.playground.all_steps_package.UtilitiesSteps;
import com.playground.constants.EndPoints;
import com.playground.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UtilitiesGetClass extends TestBase {

    @Steps
    UtilitiesSteps utilitiesSteps;

    @Title("Getting Version from Utilities")
    @Test
    public void getVersion() {
        utilitiesSteps.getVersionFromUtilities().statusCode(200);
    }

    @Title("Getting HealthCheck from Utilities")
    @Test
    public void getHealthCheck() {
        utilitiesSteps.GetHealthCheckFromUtilities().statusCode(200);
    }

}
