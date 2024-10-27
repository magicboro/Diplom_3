package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

public class ProfileTest {


    @Test
    @DisplayName("переход по клику на «Личный кабинет».")
    public void userSuccessfulLoginTest1() {
        /*
        1. Регистрируем пользователя по АПИ
        2. Входим через апи
        2. Перейти на главную https://stellarburgers.nomoreparties.site/
        2. Нажимаем на кнопку "Личный кабинет"
        3. Проверяем URL https://stellarburgers.nomoreparties.site/account/profile
        4. Проверяем, что видны креды в полях или форма
        5. Удаляем пользователя
         */

    }

    @Test
    @DisplayName("переход по клику на «Конструктор»")
    public void userSuccessfulLoginTest2() {
        /*
        1. Регистрируем пользователя по АПИ
        2. Входим через апи
        2. Перейти на страницу ЛК https://stellarburgers.nomoreparties.site/account/profile
        2. Нажимаем на кнопку "Конструктор"
        3. Проверяем URL https://stellarburgers.nomoreparties.site/
        4. Проверяем, что отображается конструктор
        5. Удаляем пользователя
         */

    }

    @Test
    @DisplayName("переход по клику на логотип Stellar Burgers.")
    public void userSuccessfulLoginTest3() {
        /*
        1. Регистрируем пользователя по АПИ
        2. Входим через апи
        2. Перейти на страницу ЛК https://stellarburgers.nomoreparties.site/account/profile
        2. Нажимаем на лого "Stellar Burgers"
        3. Проверяем URL https://stellarburgers.nomoreparties.site/
        4. Проверяем, что отображается конструктор
        5. Удаляем пользователя
         */

    }

    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете.")
    public void userSuccessfulLoginTest4() {
        /*
        1. Регистрируем пользователя по АПИ
        2. Входим через апи
        2. Перейти на страницу ЛК https://stellarburgers.nomoreparties.site/account/profile
        2. Проверяем, что есть кнопка "Выход"
        2. Нажимаем на кнопку "Выход"
        3. Проверяем URL https://stellarburgers.nomoreparties.site/login
        4. Проверяем, что видна форма логина? Проверить, что нет авторизации?
        5. Удаляем пользователя

         */

    }



}
