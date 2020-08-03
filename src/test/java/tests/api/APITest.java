package tests.api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

public class APITest {

    private JSONObject requestEmptyListJSON;
    private String authorisation = "Bearer DutdpmvlZnUPjR9ctZwSi6tUfBxiKoWI1QB8H9oCaBEujKeQv7uJvfrHLMmi4u3v";
    private RequestSpecification baseRequest;
    private String listId;

    @BeforeClass
    public void beforeClass(){
        baseURI = "https://api.whisk.com";
        requestEmptyListJSON = new JSONObject();
        requestEmptyListJSON.put("name","string");
        requestEmptyListJSON.put("primary",false);

        baseRequest = given().
                header("Content-type","application/json").
                header("Authorization", authorisation).
                contentType(ContentType.JSON).
                accept(ContentType.JSON);
    }

    @BeforeMethod
    public void beforeEachMethod(){
        listId = baseRequest.
                body(requestEmptyListJSON.toJSONString()).
        when().
                post("/list/v2").
        then().
                statusCode(200).
        extract().
                path("list.id");
    }

    @Test
    public void checkEmptyListTest(){
        baseRequest.when().
                get("/list/v2/"+listId).
        then().
                statusCode(200).
                assertThat().body(containsString(listId)).
                assertThat().body("content", Matchers.anEmptyMap());
    }

    @Test
    public void deleteEmptyListTest(){
        baseRequest.when().
                delete("/list/v2/"+listId).
        then().
                statusCode(200);

        baseRequest.when().
                get("/list/v2/"+listId).
        then().
                statusCode(400).
                assertThat().body("code",Matchers.equalToIgnoringCase("shoppingList.notFound"));
    }
}
