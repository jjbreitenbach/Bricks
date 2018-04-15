package bricks;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BricksIT {

    @Test
    public void _01_createOrderTest() {
        /**
         * Create a new order
         * @result 200 OK
         *         Body: { "id": 1, "amount": 1000, "status": "NEW" }
         */
        post("/order?amount=1000").then().assertThat().
            statusCode(200).
            body("id", equalTo(1)).
            body("amount", equalTo(1000)).
            body("status", equalTo("NEW"));
        /**
         * Create a new order
         * @result 200 OK
         *         Body: { "id": 2, "amount": 2000, "status": "NEW" }
         */
        post("/order?amount=2000").then().assertThat().
            statusCode(200).
            body("id", equalTo(2)).
            body("amount", equalTo(2000)).
            body("status", equalTo("NEW"));
        /**
         * Create a new order
         * @result 200 OK
         *         Body: { "id": 3, "amount": 3000, "status": "NEW" }
         */
        post("/order?amount=3000").then().assertThat().
            statusCode(200).
            body("id", equalTo(3)).
            body("amount", equalTo(3000)).
            body("status", equalTo("NEW"));
    }

    @Test
    public void _02_listOrderTest() {
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 1, "amount": 1000, "status": "NEW" }
         */
        get("/order?id=1").then().assertThat().
            statusCode(200).
            body("id", equalTo(1)).
            body("amount", equalTo(1000)).
            body("status", equalTo("NEW"));
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 2, "amount": 2000, "status": "NEW" }
         */
        get("/order?id=2").then().assertThat().
            statusCode(200).
            body("id", equalTo(2)).
            body("amount", equalTo(2000)).
            body("status", equalTo("NEW"));
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 3, "amount": 3000, "status": "NEW" }
         */
        get("/order?id=3").then().assertThat().
            statusCode(200).
            body("id", equalTo(3)).
            body("amount", equalTo(3000)).
            body("status", equalTo("NEW"));
        /**
         * Query invalid order detail
         * @result 200 OK
         *         Body: null
         */
        get("/order?id=4").then().assertThat().
            statusCode(200).
            body(isEmptyOrNullString());
    }

    @Test
    public void _03_listOrdersTest() {
        /**
         * Query all orders
         * @result 200 OK
         *         Body: [ 
                           { "id": 1, "amount": 1000, "status": "NEW" },
                           { "id": 2, "amount": 2000, "status": "NEW" },
                           { "id": 3, "amount": 3000, "status": "NEW" }
                         ]
         */
        get("/orders").then().assertThat().
            statusCode(200).
            body("id", hasItems(1, 2, 3)).
            body("amount", hasItems(1000, 2000, 3000)).
            body("status", hasItems("NEW", "NEW", "NEW"));
    }

    @Test
    public void _04_updateOrderTest() {
        /**
         * Update valid order amount
         * @result 200 OK
         *         Body: { "id": 1, "amount": 1111, "status": "NEW" }
         */
        put("/order?id=1&amount=1111").then().assertThat().
            statusCode(200).
            body("id", equalTo(1)).
            body("amount", equalTo(1111)).
            body("status", equalTo("NEW"));
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 1, "amount": 1111, "status": "NEW" }
         */
        get("/order?id=1").then().assertThat().
            statusCode(200).
            body("id", equalTo(1)).
            body("amount", equalTo(1111)).
            body("status", equalTo("NEW"));
    }

    @Test
    public void _05_cancelOrderTest() {
        /**
         * Cancel valid order
         * @result 200 OK
         *         Body: { "id": 2, "amount": 2000, "status": "CANCELLED" }
         */
        delete("/order?id=2").then().assertThat().
            statusCode(200).
            body("id", equalTo(2)).
            body("amount", equalTo(2000)).
            body("status", equalTo("CANCELLED"));
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 2, "amount": 2000, "status": "CANCELLED" }
         */
        get("/order?id=2").then().assertThat().
            statusCode(200).
            body("id", equalTo(2)).
            body("amount", equalTo(2000)).
            body("status", equalTo("CANCELLED"));
    }

    @Test
    public void _06_fulfillOrderTest() {
        /**
         * Fulfill valid order
         * @result 200 OK
         *         Body: { "id": 3, "amount": 3000, "status": "SHIPPED" }
         */
        get("/order/fulfill?id=3").then().assertThat().
            statusCode(200).
            body("id", equalTo(3)).
            body("amount", equalTo(3000)).
            body("status", equalTo("SHIPPED"));
        /**
         * Query valid order detail
         * @result 200 OK
         *         Body: { "id": 3, "amount": 3000, "status": "SHIPPED" }
         */
        get("/order?id=3").then().assertThat().
            statusCode(200).
            body("id", equalTo(3)).
            body("amount", equalTo(3000)).
            body("status", equalTo("SHIPPED"));
        /**
         * Fulfill invalid order
         * @result 400 Bad Request
         *         Body: { "timestamp": <current timestamp>, "status": 400, "error": "Bad Request", "message": "Invalid order reference: 4", "path": "/order" }
         */
        get("/order/fulfill?id=4").then().assertThat().
            statusCode(400).
            body("status", equalTo(400)).
            body("error", equalTo("Bad Request")).
            body("message", equalTo("Invalid order reference: 4"));
    }

    @Test
    public void _07_updateOrderTest() {
        /**
         * Update valid CANCELLED order amount
         * @result 400 Bad Request
         *         Body: { "timestamp": <current timestamp>, "status": 400, "error": "Bad Request", "message": "Unable to update amount on CANCELLED order", "path": "/order" }
         */
        put("/order?id=2&amount=2222").then().assertThat().
            statusCode(400).
            body("status", equalTo(400)).
            body("error", equalTo("Bad Request")).
            body("message", equalTo("Unable to update amount on CANCELLED order"));
        /**
         * Update valid SHIPPED order amount
         * @result 400 Bad Request
         *         Body: { "timestamp": <current timestamp>, "status": 400, "error": "Bad Request", "message": "Unable to update amount on SHIPPED order", "path": "/order" }
         */
        put("/order?id=3&amount=3333").then().assertThat().
            statusCode(400).
            body("status", equalTo(400)).
            body("error", equalTo("Bad Request")).
            body("message", equalTo("Unable to update amount on SHIPPED order"));
    }
}

