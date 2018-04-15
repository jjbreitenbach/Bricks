package bricks;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import static io.restassured.RestAssured.*;
// import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@WebMvcTest(BricksController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BricksTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void _01_createOrderTest() throws Exception {
        this.mockMvc.perform(post("/order?amount=1000")).andDo(document("createOrder"));
        this.mockMvc.perform(post("/order?amount=2000"));
        this.mockMvc.perform(post("/order?amount=3000"));
        this.mockMvc.perform(get("/order?id=1")).andDo(document("getOrder"));
        this.mockMvc.perform(get("/orders")).andDo(document("getOrderList"));
        this.mockMvc.perform(put("/order?id=1&amount=1111")).andDo(document("updateOrder"));
        this.mockMvc.perform(delete("/order?id=2")).andDo(document("cancelOrder"));
        this.mockMvc.perform(get("/order/fulfill?id=3")).andDo(document("fulfillOrder"));
        this.mockMvc.perform(put("/order?id=2&amount=2222")).andDo(document("updateCancelledOrder"));
        this.mockMvc.perform(put("/order?id=3&amount=3333")).andDo(document("updateShippedOrder"));
    }
}

