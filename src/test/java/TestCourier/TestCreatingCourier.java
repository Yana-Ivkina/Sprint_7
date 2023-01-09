package TestCourier;

import Courier.CourierGenerator;
import Courier.CreateCourier;
import Url.CurrentUrl;
import Url.TypeUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Step;

public class TestCreatingCourier {
    @Before
    public void setUp() {
        RestAssured.baseURI = CurrentUrl.baseUrl(TypeUrl.Test_Url);
    }

    @Test
    public void successfulCourierCreation() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = sendPostRequestCourier(courier);
        response.then().statusCode(201);
    }

    @Test
    public void errorWhenCreatingIdenticalCouriers() {
        CreateCourier courier = CourierGenerator.courierDefault();
        Response response = sendPostRequestCourier(courier);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    public void allRequiredFieldsWerePassed() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = sendPostRequestCourier(courier);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void returnedCorrectResponseCode() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = sendPostRequestCourier(courier);
        response.then().statusCode(201);
    }

    @Test
    public void correctResponseCodeReturnTrue() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = sendPostRequestCourier(courier);
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    public void returnsErrorIfFieldLoginMissing() {
        CreateCourier courier = new CreateCourier(null, "1234", "saske");
        Response response = sendPostRequestCourier(courier);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    public void errorReturnedWhenCreatingUserWithExistingLogin() {
        CreateCourier courier = CourierGenerator.courierDefault();
        Response response = sendPostRequestCourier(courier);
        response.then().statusCode(409);
    }

    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestCourier(CreateCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        return response;
    }
}
