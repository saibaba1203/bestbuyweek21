package com.playground.products;

import com.playground.constants.EndPoints;
import com.playground.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllProducts(){
        SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_PRODUCTS)
                .then()
                .log().all()
                .statusCode(200);
    }

//    @Test
//    public void thisIsATestWithError(){
//        System.out.println("This is an Error " +(5/0) );
//    }
//
//
//
//    @Pending
//    @Test
//    public void thisIsPending(){
//
//    }
//    @Ignore
//    @Test
//    public void thisIsSkippedTest() {
//    }
//    @Manual
//    @Test
//    public void thisIsManual(){
//
//    }
//    @Test
//    public void thisIsCompromised() throws FileNotFoundException {
//        File file = new File("E://file.txt");
//        FileReader fr = new FileReader(file);
//    }

}
