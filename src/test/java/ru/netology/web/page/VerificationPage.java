package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeSelector = $("[data-test-id='code'] input");
    private SelenideElement buttonSelector = $("[data-test-id='action-verify']");

    public VerificationPage() {
        codeSelector.shouldBe(Condition.visible);
    }

    public DashboardPage verification(DataHelper.VerificationInfo info) {
        codeSelector.setValue(info.getCode());
        buttonSelector.click();
        return new DashboardPage();
    }
}
