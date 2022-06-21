package com.playground.all_steps_package;

import com.playground.constants.EndPoints;
import com.playground.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {


@Step
    public ValidatableResponse createCategories(String name, String id){
    CategoriesPojo categoriesPojo = new CategoriesPojo();
    categoriesPojo.setName(name);
    categoriesPojo.setId(id);

    return SerenityRest.given()
            .contentType(ContentType.JSON)
            .body(categoriesPojo)
            .when()
            .post(EndPoints.GET_CATEGORIES)
            .then();

    }

    @Step("Getting the category information with categoryId: {0}")
    public HashMap<String,Object> getCategoryInfoById(String id){

      HashMap<String, Object> categoryMap = SerenityRest.given().log().all()
                .when()
                .pathParam("categoryID",id)
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then()
                .extract()
                .path("");
      return categoryMap;
    }
    //

    @Step("Updating category information with categoryId : {0} , name : {1}")
    public ValidatableResponse updateCategory(String id, String name){

    CategoriesPojo categoriesPojo = new CategoriesPojo();
    categoriesPojo.setId(id);
    categoriesPojo.setName(name);

    return SerenityRest.given().log().all()
            .header("Content-Type","application/Json")
            .pathParam("categoryID",id)
            .body(categoriesPojo)
            .when()
            .put(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
            .then();

    }

    @Step("Deleting category information with categoryId: {0}")
    public ValidatableResponse deleteCategory(String id){

  return SerenityRest.given().log().all()
            .pathParam("categoryID",id)
            .when()
            .delete(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
            .then();

    }


    @Step("Getting category information with categoryId: {0}")
    public ValidatableResponse getCategoryById(String id){

    return SerenityRest.given().log().all()
            .pathParam("categoryID",id)
            .when()
            .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
            .then();
    }

}
