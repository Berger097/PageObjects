package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginSelector = $("[data-test-id='login'] input");
    private final SelenideElement passwordSelector = $("[data-test-id='password'] input");
    private final SelenideElement buttonSelector = $("[data-test-id='action-login']");

    public VerificationPage validation(DataHelper.RegistrationInfo info){
        loginSelector.setValue(info.getLogin());
        passwordSelector.setValue(info.getPassword());
        buttonSelector.click();
        return new VerificationPage();
    }


}
