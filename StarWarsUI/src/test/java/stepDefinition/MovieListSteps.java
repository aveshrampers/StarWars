package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utility.BaseApi;

import java.util.List;

public class MovieListSteps extends BaseApi {
    private Response response;

    @Given("^the url$")
    public void getUrl() {
        String url = base_url + "/api/films";
        RequestSpecification requestSpecification = RestAssured.given();
        response = requestSpecification.log().all().contentType("application/json").when().get(url);
    }

    @Then("^assert response count is (.*)$")
    public void assertMovieCount(int count) {
        JsonPath jsonPath = response.jsonPath();
        int value = jsonPath.getInt("count");
        Assert.assertEquals(count, value);
    }

    @Then("^assert the (.*)rd movie is directed by (.*)$")
    public void assertDirector(String sequel, String director) {
        JsonPath jsonPath = response.jsonPath();
        List<String> value = jsonPath.getList("results.url");
        for (String s : value) {
            if (s.contains(sequel)) {
                RequestSpecification requestSpecification = RestAssured.given();
                response = requestSpecification.log().all().contentType("application/json").when().get(s);
                JsonPath jsonPath1 = response.jsonPath();
                String direct = jsonPath1.get("director");
                Assert.assertEquals(direct, director);
            }
        }
    }
    @Then("^assert the (.*)th movie is not producers are not (.*)$")
    public void assertProducer(String sequel, String producer) {
        JsonPath jsonPath = response.jsonPath();
        List<String> value = jsonPath.getList("results.url");
        for (String s : value) {
            if (s.contains(sequel)) {
                RequestSpecification requestSpecification = RestAssured.given();
                response = requestSpecification.log().all().contentType("application/json").when().get(s);
                JsonPath jsonPath1 = response.jsonPath();
                String direct = jsonPath1.get("producer");
                Assert.assertNotEquals(direct, producer);
            }
        }
    }
}
