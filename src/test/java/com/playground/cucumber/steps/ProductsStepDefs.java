package com.playground.cucumber.steps;

import com.playground.all_steps_package.ProductsSteps;
import com.playground.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductsStepDefs {
    static ValidatableResponse response;
    static int id;
    static String name ="Duracell - D Batteries (4-Pack)"+ TestUtils.getRandomValue();
    static String type ="HardGood";
    static int price = 4;
    static int shipping=0;
    static String upc ="039800013200";
    static String description="Alkaline batteries; 1.5V";
    static String manufacturer="Energizer";
    static String model="E90BP-2";
    static String url="http://www.bestbuy.com/site/energizer-n-cell-e90-batteries-2-pack/333179.p?id=1185268509951&skuId=333179&cmp=RMXCC";
    static String image="http://img.bbystatic.com/BestBuy_US/images/products/3331/333179_sa.jpg";
    // String name, String type, int price, int shipping, String upc, String description,
    //  String manufacturer, String model, String url,String image
    @Steps
    ProductsSteps productsSteps;
    @When("^I create a new product by providing information name type price shipping upc description manufacturer model url image$")
    public void iCreateANewProductByProvidingInformationNameTypePriceShippingUpcDescriptionManufacturerModelUrlImage() {
        response = productsSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
        id= response.log().all().extract().path("id");
    }

    @And("^I verify that the product has been created$")
    public void iVerifyThatTheProductHasBeenCreated() {
        response.statusCode(201);
        HashMap<String, Object> productMap = productsSteps.getProductInfoById(id);
        Assert.assertThat(productMap,hasValue(name));

    }

    @And("^I update a name of the product$")
    public void iUpdateANameOfTheProduct() {
        name = name+"_updated";
        response= productsSteps.updateProduct(id,name,type,price,shipping,upc,description,manufacturer,model,url,image);

    }

    @And("^I verify that the product has been updated$")
    public void iVerifyThatTheProductHasBeenUpdated() {
        response.statusCode(200);
        HashMap<String,Object> updatedProductMap =productsSteps.getProductInfoById(id);
        Assert.assertThat(updatedProductMap,hasValue(name));

    }

    @And("^I delete the product with same id$")
    public void iDeleteTheProductWithSameId() {
        productsSteps.deleteProduct(id).statusCode(200);

    }

    @Then("^I verify that the product has been deleted$")
    public void iVerifyThatTheProductHasBeenDeleted() {
        productsSteps.getProductById(id).statusCode(404);
    }
}
