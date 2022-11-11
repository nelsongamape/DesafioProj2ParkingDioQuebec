package br.com.rodrigocbarj.cloudparking.controller;

import br.com.rodrigocbarj.cloudparking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void findById() {
    }

    @Test
    void whenCreateThenCheckIfCreated() {
        ParkingCreateDTO parking = new ParkingCreateDTO();
        parking.setLicense("BMW-0235");
        parking.setModel("BMW M235i");
        parking.setColor("PURPLE");
        parking.setState("PE");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(parking)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("BMW-0235"))
                .body("color", Matchers.equalTo("PURPLE"));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}