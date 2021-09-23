package org.melek.tddwithspringframework.cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.melek.tddwithspringframework.cucumber.SpringCucumberBase;
import static org.hamcrest.Matchers.equalTo;

public class BookFeatureSteps  extends SpringCucumberBase {


    @When("I get the books")
    public void i_get_the_books() throws Exception {
        validatableResponse = requestSpecification()
                .when().get("/books").then();
        System.out.println("RESPONSE :"+validatableResponse.extract().asString());
    }
    @Then("it should return all books")
    public void it_should_return_all_books() throws Exception {
        validatableResponse.assertThat().statusCode(equalTo(200));
    }
    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) throws Exception {
        validatableResponse.assertThat().statusCode(equalTo(statusCode));
    }
}
