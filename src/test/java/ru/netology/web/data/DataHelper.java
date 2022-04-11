package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.*;
import ru.netology.web.page.DashboardPage;

import java.util.Locale;

public class DataHelper {


    /**
     * Данные для входа в Л/К
     */
    @Value
    public static class RegistrationInfo {
        private String login;
        private String password;
    }

    public static String loginBank() {
        return "vasya";
    }

    public static String passwordBank() {
        return "qwerty123";
    }

    public static RegistrationInfo getUser() {

        return new RegistrationInfo(loginBank(), passwordBank());
    }


    /**
     * Код верификации
     */
    @Value
    public static class VerificationInfo {
        String code;
    }

    public static String codeBank() {
        return "12345";
    }

    public static VerificationInfo getCode() {

        return new VerificationInfo(codeBank());
    }


    /**
     * счета пополнения и списания
     */

    @Value
    public static class CardInfo {
        private String cardNumber;


    }

    public static CardInfo getNomCard1() {

        return new CardInfo("5559000000000001");
    }

    public static CardInfo getNomCard2() {

        return new CardInfo("5559000000000002");
    }


    /**
     * Метод генерирующий рандомное число (сумму перевода)
     */


    private static final Faker faker = new Faker(new Locale("en"));

    public static int sumRandom(CardInfo z) {
        DashboardPage i = new DashboardPage();
        int x = i.getCardBalanceRandom(z);
        int sumStr = 0;
        if (x >= 10_000) {
            sumStr = faker.number().numberBetween(1, 2000);
        } else if (x > 7000 & x < 10_000) {
            sumStr = faker.number().numberBetween(1, 1000);
        } else if (x > 5000 & x < 7000) {
            sumStr = faker.number().numberBetween(1, 500);
        } else if (x > 3000 & x < 5000) {
            sumStr = faker.number().numberBetween(1, 200);
        } else if (x > 1000 & x < 3000) {
            sumStr = faker.number().numberBetween(1, 50);
        } else if (x > 100 & x < 500) {
            sumStr = faker.number().numberBetween(1, 10);
        } else if (x > 1 & x <= 100) {
            sumStr = faker.number().numberBetween(1, 1);
        }


        return sumStr;
    }


}
