package site.nomoreparties.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.EnvConfig;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Create user by method : " + EnvConfig.USER_REG_PATH)
    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URI)
                .body(user)
                .when()
                .post(EnvConfig.USER_REG_PATH)
                .then().log().all();

    }

    @Step("Login user by method : " + EnvConfig.USER_LOGIN_PATH)
    public ValidatableResponse loginUserByCreds(UserCredentials credentials) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URI)
                .body(credentials)
                .when()
                .post(EnvConfig.USER_LOGIN_PATH)
                .then().log().all();
    }

    @Step("Delete user by method : " + EnvConfig.USER_PATH)
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(EnvConfig.BASE_URI)
                .auth().oauth2(accessToken)
                .delete(EnvConfig.USER_PATH)
                .then().log().all();
    }

    @Step("Get user accessToken")
    public String getUserAccessToken(ValidatableResponse createResponse) {
        String accessTokenWithBearer = createResponse
                .extract()
                .path("accessToken");
        return accessTokenWithBearer.replace("Bearer ", "");
    }

    @Step("Get user refreshToken")
    public String getUserRefreshToken(ValidatableResponse createResponse) {
        String refreshToken = createResponse
                .extract()
                .path("refreshToken");
        return refreshToken;
    }

    @Step("Get token an delete")
    public void getUserTokenAndDeleteUser(ValidatableResponse createResponse) {
        String accessToken = getUserAccessToken(createResponse);
        deleteUser(accessToken);
    }

    @Step("Get login response")
    public ValidatableResponse getLoginResponse(User user) {
        UserCredentials userCredentials = UserCredentials.fromUser(user);
        return loginUserByCreds(userCredentials);
    }


}
