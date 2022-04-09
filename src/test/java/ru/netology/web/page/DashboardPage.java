package ru.netology.web.page;

import com.codeborne.selenide.*;
import lombok.Data;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class DashboardPage {
    private static final ElementsCollection cardsSelector = $$(".list__item");
    private static final SelenideElement cardSelector1 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static final SelenideElement cardSelector2 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private final SelenideElement buttonSelector = $("button");
    private final SelenideElement updateSelector = $("[data-test-id='action-reload']");
    private static final SelenideElement headingSelector = $("[data-test-id='dashboard']");
    private static int sumFinal;


    public DashboardPage() {
        headingSelector.should(Condition.visible);
    }

    /**
     * Запускаем рандомайзер суммы
     */
    public static void sum(DataHelper.CardInfo info2) {
        DataHelper s = new DataHelper();
        s.sumRandom(info2.getCardNumber());
    }


    /**
     * выполняем нажатие на кнопку пополнения (выбираем карту которую хотим пополнить)
     */
    public TransferPage cardToTransfer(DataHelper.CardInfo info) {
        cardsSelector.findBy(text(info.getCardNumber().substring(12, 16))).$("button").click();
        return new TransferPage();
    }

    /**
     * Методы обрезают ненужный текст и преобразуют нужный нам текст в число
     *
     * @param cardInfo
     */
    public static int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cardsSelector.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);

    }

    public int getCardBalanceRandom(DataHelper.CardInfo cardInfo) {
        var text = cardsSelector.findBy(text(cardInfo.getCardNumber().substring(12, 16))).getText();
        return extractBalance(text);
    }


    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}

