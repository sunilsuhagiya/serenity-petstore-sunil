package io.swagger.petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.model.PetDataPojo;
import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PetCRUDTestWithSteps extends TestBase {
    static int id = 22;
    static String Name = "Cat";
    static String status = "Available";

    @Steps
    PetSteps petSteps;


    @Title("This method will create new record of Pet")
    @Test
    public void test001() {
        PetDataPojo.CategoryData categoryData = new PetDataPojo.CategoryData( 22,"Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("www.pug.com/photo");
        ArrayList<PetDataPojo.TagData> tagDataList = new ArrayList<>();
        PetDataPojo.TagData tagData = new PetDataPojo.TagData(11, "Black and White spots");
        tagDataList.add(tagData);
        petSteps.createPet(id, categoryData, Name, photoList, tagDataList, status);
    }

    @Title("This method will fetch existing record of Pet")
    @Test
    public void test002() {
        ValidatableResponse response = petSteps.getUserByID(id).statusCode(200);
        String name = response.extract().path("name");
        Assert.assertTrue(name.matches(Name));
    }

    @Title("This method will update an existing record of Pet")
    @Test
    public void test003() {
        status = "not available";
        PetDataPojo.CategoryData categoryData = new PetDataPojo.CategoryData(25, "Dog");
        ArrayList<String> photoList = new ArrayList<>();
        photoList.add("www.pug.com/photo");
        ArrayList<PetDataPojo.TagData> tagDataList = new ArrayList<>();
        PetDataPojo.TagData tagData = new PetDataPojo.TagData(23, "Black and White spots");
        tagDataList.add(tagData);
        ValidatableResponse response = petSteps.updatePetRecord(id, categoryData, Name, photoList, tagDataList, status);
        HashMap<String, ?> update = response.extract().path("");
        Assert.assertThat(update, hasValue(status));

    }

    @Title("This method will delete an existing record of Pet")
    @Test
    public void test004() {
        petSteps.deleteUser(id);
        petSteps.getUserByID(id).statusCode(404);
    }

}
