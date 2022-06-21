package com.playground.categories;

import com.playground.all_steps_package.CategoriesSteps;
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
public class CategoriesCURDWithSteps extends TestBase {

    static String name = "My Unique Gifts Ideas-" + TestUtils.getRandomValue();
    static String id = "abcat01-" + TestUtils.getRandomValue();

    @Steps
    CategoriesSteps categoriesSteps;
    @Title("This will create a new category")
    @Test
    public void test001() {
        ValidatableResponse response = categoriesSteps.createCategories(name, id);
        response.log().all().statusCode(201);
    }

    @Test
    @Title("Verify if the category was added to the application")
    public void test002() {

        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoById(id);
        Assert.assertThat(categoryMap, hasValue(name));
    }


    @Title("Update the category information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        ValidatableResponse response = categoriesSteps.updateCategory(id, name);
        response.log().all().statusCode(200);
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoById(id);
        Assert.assertThat(categoryMap, hasValue(name));
    }

    @Title("Delete the category and verify if the category is deleted!")
    @Test
    public void test004() {

        ValidatableResponse response = categoriesSteps.deleteCategory(id);
        response.log().all().statusCode(200);
        categoriesSteps.getCategoryById(id).statusCode(404);
    }

}
