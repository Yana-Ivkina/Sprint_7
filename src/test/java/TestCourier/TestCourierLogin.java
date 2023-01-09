package TestCourier;

import Courier.BaseCourier;
import Courier.CourierGenerator;
import Url.CurrentUrl;
import Url.TypeUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Step;

public class TestCourierLogin {
    @Before
    public void setUp() {
        RestAssured.baseURI = CurrentUrl.baseUrl(TypeUrl.Test_Url);
    }

    @Test
    public void courierCanLogIn() {
        BaseCourier courier = new BaseCourier("hugoo", "1234");
        Response response = sendPostRequestLogin(courier);
        response.then().statusCode(200);
    }

    @Test
    public void passingNotAllRequiredFields() {
        BaseCourier courier = new BaseCourier(null, "1234");
        Response response = sendPostRequestLogin(courier);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void errorWhenEnteringWrongData() {
        BaseCourier courier = new BaseCourier("hugoo", "1235");
        Response response = sendPostRequestLogin(courier);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void errorIfRequiredFieldMissing() {
        BaseCourier courier = new BaseCourier(null, "1234");
        Response response = sendPostRequestLogin(courier);
        response.then().statusCode(400);
    }

    @Test
    public void errorWhenEnteringIncorrectData() {
        BaseCourier courier = new BaseCourier(CourierGenerator.courierRandom().getLogin(), CourierGenerator.courierRandom().getPassword());
        Response response = sendPostRequestLogin(courier);
        response.then().statusCode(404);
    }

    @Test
    public void successfulRequestReturnsId() {
        BaseCourier courier = new BaseCourier("hugoo", "1234");
        Response response = sendPostRequestLogin(courier);
        response.then().assertThat().body("id", notNullValue());
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostRequestLogin(BaseCourier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        return response;
    }
}
