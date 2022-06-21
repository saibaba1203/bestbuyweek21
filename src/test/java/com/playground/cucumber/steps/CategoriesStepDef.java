package com.playground.cucumber.steps;

import com.playground.all_steps_package.CategoriesSteps;
import com.playground.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoriesStepDef {
    static ValidatableResponse response;
    static String id_1;
    static String name_1;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^I create a new category by providing the information id \"([^\"]*)\" name \"([^\"]*)\"$")
    public void iCreateANewCategoryByProvidingTheInformationIdName(String id, String name) {
        id = id+ TestUtils.getRandomValue();
        response  = categoriesSteps.createCategories(name,id);
        id_1 = response.log().all().extract().path("id");
        name_1 = response.log().all().extract().path("name");

    }

    @And("^I verify that the category has been created$")
    public void iVerifyThatTheCategoryHasBeenCreated() {
        response.statusCode(201);
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoById(id_1);
        Assert.assertThat(categoryMap, hasValue(name_1));

    }

    @And("^I updated a name of the category$")
    public void iUpdatedANameOfTheCategory() {
        name_1 = name_1+"_updated";
        response = categoriesSteps.updateCategory(id_1,name_1);

    }

    @And("^I verify that the category name has been updated$")
    public void iVerifyThatTheCategoryNameHasBeenUpdated() {
        response.statusCode(200);
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoById(id_1);
        Assert.assertThat(categoryMap, hasValue(name_1));
    }

    @And("^I deleted category with same id$")
    public void iDeletedCategoryWithSameId() {
        categoriesSteps.deleteCategory(id_1).statusCode(200) ;
    }

    @Then("^I verify that the category has been deleted$")
    public void iVerifyThatTheCategoryHasBeenDeleted() {
        categoriesSteps.getCategoryById(id_1).statusCode(404);
    }
}
