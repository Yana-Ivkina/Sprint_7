package TestOrder;

import Order.OrderList;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestOrderList {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void inBodyAllOrder() {
        OrderList orderList = given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders")
                .body().as(OrderList.class);
    }
}
