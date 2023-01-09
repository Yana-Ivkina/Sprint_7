package TestOrder;

import Order.OrderList;
import Url.CurrentUrl;
import Url.TypeUrl;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestOrderList {

    @Before
    public void setUp() {
        RestAssured.baseURI = CurrentUrl.baseUrl(TypeUrl.Test_Url);
    }

    @Test
    public void inBodyAllOrder() {
        OrderList orderList = given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders")
                .body().as(OrderList.class);
    }
}
