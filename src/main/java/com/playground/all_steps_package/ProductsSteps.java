package com.playground.all_steps_package;

import com.playground.constants.EndPoints;
import com.playground.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {

@Step("Creating product with name: {0},type: {1},price: {2},shipping: {3},upc: {4},description : {5},manufacturer : {6},model : {7},url :{8},image :{9}")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc, String description,
                                             String manufacturer, String model, String url,String image){

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
      return  SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(productsPojo)
                .when()
                .post(EndPoints.GET_PRODUCTS)
                .then().log().all().statusCode(201);
    }
    @Step("Getting the product information with productId : {0}")
    public HashMap<String,Object> getProductInfoById(int id){

        HashMap<String, Object>
                productMap = SerenityRest.given().log().all()
                .when()
                .pathParam("productID",id)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .extract()
                .path("");
        return productMap;
    }
    @Step("Updating product information with productId:{0},name: {1},type: {2},price: {3},shipping: {4},upc: {5},description : {6},manufacturer : {7},model : {8},url :{9},image :{10}")
    public ValidatableResponse updateProduct(int id,String name, String type, int price, int shipping, String upc, String description,
                                             String manufacturer, String model, String url,String image){
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setDescription(description);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);
        return  SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID",id)
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();


    }
    @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct(int id){

    return SerenityRest.given().log().all()
            .pathParam("productID",id)
            .when()
            .delete(EndPoints.DELETE_PRODUCT_BY_ID)
            .then();
    }

    @Step("Getting product information with productId: {0}")
    public ValidatableResponse getProductById(int id){

    return SerenityRest.given().log().all()
            .pathParam("productID",id)
            .when()
            .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
            .then();
    }


    }



