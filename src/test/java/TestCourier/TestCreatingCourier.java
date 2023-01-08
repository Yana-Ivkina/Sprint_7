package TestCourier;

import Courier.CourierGenerator;
import Courier.CreateCourier;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestCreatingCourier {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void successfulCourierCreation() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().statusCode(201);
    }

    @Test
    public void errorWhenCreatingIdenticalCouriers() {
        CreateCourier courier = CourierGenerator.courierDefault();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    public void allRequiredFieldsWerePassed() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void returnedCorrectResponseCode() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().statusCode(201);
    }

    @Test
    public void correctResponseCodeReturnTrue() {
        CreateCourier courier = CourierGenerator.courierRandom();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    public void returnsErrorIfFieldLoginMissing() {
        CreateCourier courier = new CreateCourier(null, "1234", "saske");
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    public void errorReturnedWhenCreatingUserWithExistingLogin() {
        CreateCourier courier = CourierGenerator.courierDefault();
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        response.then().statusCode(409);
    }

}
