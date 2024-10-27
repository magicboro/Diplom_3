package site.nomoreparties.stellarburgers.api;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

    private final String email;
    private final String password;
    private final String name;
    static String userKey = RandomStringUtils.randomAlphabetic(5,15);
    static String shortPassword = RandomStringUtils.randomAlphanumeric(5);

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User random() {
        return new User(userKey + "__@yandex.ru", userKey + "__123QweЙцу!@#", userKey);
    }

    public static User shortPassword() {
        return new User(userKey + "__@yandex.ru", shortPassword, userKey);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


}
