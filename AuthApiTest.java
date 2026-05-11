package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PayloadBuilder;
import utils.TokenUtils;

import static io.restassured.RestAssured.given;

public class AuthApiTest extends BaseTest {

    String endpoint = "/api/v1/sandbox/auth";

    @Test
    public void validateSuccessfulAuthentication() {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(PayloadBuilder.validPayload())
                .when()
                .post(endpoint);

        // Validate Status Code
        Assert.assertEquals(response.statusCode(), 200);

        // Print Response Body
        response.prettyPrint();

        // Fetch Response Fields
        String token = response.jsonPath().getString("auth_token");
        String message = response.jsonPath().getString("message");
        String status = response.jsonPath().getString("status");

        // Validate Response Body
        Assert.assertNotNull(token, "Auth token is null");
        Assert.assertFalse(token.isEmpty(), "Auth token is empty");

        Assert.assertEquals(status, "success");
        Assert.assertEquals(message, "Authentication successful");
    }

    @Test
    public void validateReplayAttack() throws InterruptedException {

        String payload = PayloadBuilder.validPayload();

        // First Request
        given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(endpoint);

        Thread.sleep(100);

        // Second Request with Same Payload
        Response secondRequest = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(endpoint);

        secondRequest.prettyPrint();

        // Validate Status Code
        Assert.assertTrue(
                secondRequest.statusCode() == 401 ||
                secondRequest.statusCode() == 409,
                "Replay attack validation failed"
        );

        // Validate Error Response
        String errorMessage =
                secondRequest.jsonPath().getString("message");

        Assert.assertNotNull(errorMessage);
    }

    @Test
    public void validateTokenTampering() {

        // Generate Valid Token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(PayloadBuilder.validPayload())
                .when()
                .post(endpoint);

        String validToken =
                response.jsonPath().getString("auth_token");

        // Tamper Token
        String tamperedToken =
                TokenUtils.tamperToken(validToken);

        // Send Request with Tampered Token
        Response tamperedResponse = given()
                .header("Authorization",
                        "Bearer " + tamperedToken)
                .header("Content-Type", "application/json")
                .body(PayloadBuilder.validPayload())
                .when()
                .post(endpoint);

        tamperedResponse.prettyPrint();

        // Validate Status Code
        Assert.assertEquals(
                tamperedResponse.statusCode(),
                401
        );

        // Validate Response Body
        String errorMessage =
                tamperedResponse.jsonPath().getString("message");

        Assert.assertEquals(
                errorMessage,
                "Invalid or tampered token"
        );
    }
}