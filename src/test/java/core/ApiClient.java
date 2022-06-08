package core;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static io.restassured.RestAssured.given;

public class ApiClient {
    private Gson gson = new Gson();
    public static final Logger logger = LoggerFactory.getLogger(ApiClient.class);

    public ApiClient() {
        RestAssured.baseURI = "http://18.157.148.180:8080/";
    }

    public PlayerGetAllResponseDto getAllPlayers(int expectedStatusCode) {
        logger.info("getAllPlayers controller testing");
        PlayerGetAllResponseDto response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiEndpoints.GET_ALL_PLAYERS)
                .then()
                .assertThat().statusCode(expectedStatusCode)
                .extract()
                .as(PlayerGetAllResponseDto.class);
        return gson.fromJson(gson.toJson(response), PlayerGetAllResponseDto.class);
    }

    public PlayerGetByPlayerIdResponseDto getPlayerByPlayerId(PlayerGetByPlayerIdRequestDto playerGetByPlayerIdRequestDto, int expectedStatusCode) {
        logger.info("getPlayerByPlayerId controller testing");
        PlayerGetByPlayerIdResponseDto response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(playerGetByPlayerIdRequestDto)
                .when()
                .post(ApiEndpoints.GET_PLAYER_BY_PLAYER_ID)
                .then()
                .assertThat().statusCode(expectedStatusCode)
                .extract().as(PlayerGetByPlayerIdResponseDto.class);
        return gson.fromJson(gson.toJson(response), PlayerGetByPlayerIdResponseDto.class);
    }

    public PlayerCreateResponseDto createPlayer(String age, String gender, String login, String password, String role, String screenName, String creator, int expectedStatusCode) {
        logger.info("createPlayer controller testing");
        PlayerCreateResponseDto response = given()
                .contentType(ContentType.JSON)
                .param("age", age)
                .param("gender", gender)
                .param("login", login)
                .param("password", password)
                .param("role", role)
                .param("screenName", screenName)
                .when()
                .get(String.format(ApiEndpoints.CREATE_PLAYER, creator))
                .then()
                .assertThat().statusCode(expectedStatusCode)
                .extract()
                .as(PlayerCreateResponseDto.class);
        return gson.fromJson(gson.toJson(response), PlayerCreateResponseDto.class);
    }

    public Response deletePlayer(PlayerDeleteRequestDto playerDeleteRequestDto, String role, int expectedStatusCode) {
        logger.info("deletePlayer controller testing");
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(playerDeleteRequestDto)
                .when()
                .delete(String.format(ApiEndpoints.DELETE_PLAYER, role))
                .then()
                .assertThat().statusCode(expectedStatusCode)
                .extract().response();
    }

    public PlayerUpdateResponseDto updatePlayer(PlayerUpdateRequestDto playerUpdateRequestDto, int id, String role, int expectedStatusCode) {
        logger.info("updatePlayer controller testing");
        PlayerUpdateResponseDto response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(playerUpdateRequestDto)
                .when()
                .patch(String.format(ApiEndpoints.UPDATE_PLAYER, role, id))
                .then()
                .assertThat().statusCode(expectedStatusCode)
                .extract()
                .as(PlayerUpdateResponseDto.class);
        return gson.fromJson(gson.toJson(response), PlayerUpdateResponseDto.class);
    }
}
