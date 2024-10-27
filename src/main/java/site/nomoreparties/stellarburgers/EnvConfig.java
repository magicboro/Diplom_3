package site.nomoreparties.stellarburgers;

public class EnvConfig {

    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    //API routes
    public static final String API_PREFIX = "/api";
    public static final String USER_AUTH_PATH = API_PREFIX + "/auth";
    public static final String USER_REG_PATH = USER_AUTH_PATH + "/register";
    public static final String USER_LOGIN_PATH = USER_AUTH_PATH + "/login";
    public static final String USER_PATH = USER_AUTH_PATH + "/user";
    public static final String ORDERS_PATH = API_PREFIX + "/orders";

    //Front routes
    public static final String MAIN_PAGE_PATH = BASE_URI + "/";
    public static final String REGISTRATION_PAGE_PATH = BASE_URI + "/register";
    public static final String LOGIN_PAGE_PATH = BASE_URI + "/login";
    public static final String PROFILE_PAGE_PATH = BASE_URI + "/account/profile";
    public static final String FORGOT_PASS_PAGE_PATH = BASE_URI + "/forgot-password";
    public static final int DEFAULT_TIMEOUT = 5;
}


