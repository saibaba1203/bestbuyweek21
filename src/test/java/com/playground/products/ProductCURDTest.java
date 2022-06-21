package com.playground.products;



import com.playground.all_steps_package.ProductsSteps;
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

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {

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
    static int id;
    //This will create a new product.
    @Steps
    ProductsSteps productsSteps;
    @Title("This will create a new product")
    @Test
    public void test001(){


        ValidatableResponse response= productsSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
                response.log().all().statusCode(201);
                id=response.log().all().extract().path("id");
        }

    @Title("Verify if the product is added to the application")
    @Test
    public void test002(){

        HashMap<String, Object> productMap = productsSteps.getProductInfoById(id);

        Assert.assertThat(productMap,hasValue(name));
        id = (int) productMap.get("id");
        System.out.println(id);

    }

    @Title("Update the product information and verify the updated information")
    @Test
    public void test003(){
        name= name+"_Updated";
        ValidatableResponse response= productsSteps.updateProduct(id,name,type,price,shipping,upc,description,manufacturer,model,url,image);
       response.log().all().statusCode(200);

        HashMap<String,Object> updatedProductMap =productsSteps.getProductInfoById(id);
        Assert.assertThat(updatedProductMap,hasValue(name));

    }
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004(){
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.getProductById(id).statusCode(404);
    }
}
