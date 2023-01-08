package TestCourier;

import Courier.BaseCourier;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class TestCourierLogin {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void courierCanLogIn() {
        BaseCourier courier = new BaseCourier("hugoo", "1234");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().statusCode(200);
    }

    @Test
    public void passingNotAllRequiredFields() {
        BaseCourier courier = new BaseCourier(null, "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void errorWhenEnteringWrongData() {
        BaseCourier courier = new BaseCourier("hugoo", "1235");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void errorIfRequiredFieldMissing() {
        BaseCourier courier = new BaseCourier(null, "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().statusCode(400);
    }

    @Test
    public void errorWhenEnteringIncorrectData() {
        BaseCourier courier = new BaseCourier("hugooFIRST", "1235");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().statusCode(404);
    }

    @Test
    public void successfulRequestReturnsId() {
        BaseCourier courier = new BaseCourier("hugoo", "1234");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue());
    }
}
