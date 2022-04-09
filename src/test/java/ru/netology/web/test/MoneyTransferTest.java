package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class MoneyTransferTest {

    @Test
    public void transferOfFundsToAccountOne() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var info = DataHelper.getUser();
        var verificationPage = loginPage.validation(info); //вводим логин и пароль
        var verificationInfo = DataHelper.getCode();
        var dashboardPage = verificationPage.verification(verificationInfo); //вводим код
        //данные и выбор карты пополнения
        var cardInfo1 = DataHelper.getNomCard1();
        var cardInfo2 = DataHelper.getNomCard2();
        DashboardPage.sum(cardInfo2); //Запуск рандомайзера суммы
        int amount = DataHelper.getSumTransferInfo().getSumTransfer();
        var expectedBalanceCard1 = DashboardPage.getCardBalance(cardInfo1) + amount;
        var expectedBalanceCard2 = DashboardPage.getCardBalance(cardInfo2) - amount;
        var transferPage = dashboardPage.cardToTransfer(cardInfo1);
        //ввод суммы, вобор карты списания, проверки
        var sumTransferInfo = DataHelper.getSumTransferInfo();
        var dashboardPage1 = transferPage.transfer(cardInfo2, sumTransferInfo);
        var actualBalanceCard1 = DashboardPage.getCardBalance(cardInfo1);
        var actualBalanceCard2 = DashboardPage.getCardBalance(cardInfo2);
        Assertions.assertEquals(expectedBalanceCard1, actualBalanceCard1);
        Assertions.assertEquals(expectedBalanceCard2, actualBalanceCard2);

    }

    @Test
    public void transferOfFundsToAccountTwo() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var info = DataHelper.getUser();
        var verificationPage = loginPage.validation(info); //вводим логин и пароль
        var verificationInfo = DataHelper.getCode();
        var dashboardPage = verificationPage.verification(verificationInfo); //вводим код
        //данные и выбор карты пополнения
        var cardInfo1 = DataHelper.getNomCard1();
        var cardInfo2 = DataHelper.getNomCard2();
        DashboardPage.sum(cardInfo1); //Запуск рандомайзера суммы
        int amount = DataHelper.getSumTransferInfo().getSumTransfer();
        var expectedBalanceCard1 = DashboardPage.getCardBalance(cardInfo1) - amount;
        var expectedBalanceCard2 = DashboardPage.getCardBalance(cardInfo2) + amount;
        var transferPage = dashboardPage.cardToTransfer(cardInfo2);
        //ввод суммы, вобор карты списания, проверки
        var sumTransferInfo = DataHelper.getSumTransferInfo();
         transferPage.transfer(cardInfo1, sumTransferInfo);
        var actualBalanceCard1 = DashboardPage.getCardBalance(cardInfo1);
        var actualBalanceCard2 = DashboardPage.getCardBalance(cardInfo2);
        Assertions.assertEquals(expectedBalanceCard1, actualBalanceCard1);
        Assertions.assertEquals(expectedBalanceCard2, actualBalanceCard2);

    }

    @Test
    public void TransferAmountMoreThanLimit () {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var info = DataHelper.getUser();
        var verificationPage = loginPage.validation(info); //вводим логин и пароль
        var verificationInfo = DataHelper.getCode();
        var dashboardPage = verificationPage.verification(verificationInfo); //вводим код
        //данные и выбор карты пополнения
        var cardInfo1 = DataHelper.getNomCard1();
        var cardInfo2 = DataHelper.getNomCard2();
      //  int amount = DataHelper.getSumTransferInfo().getSumTransfer();
        var expectedBalanceCard1 = DashboardPage.getCardBalance(cardInfo1);
        var expectedBalanceCard2 = DashboardPage.getCardBalance(cardInfo2);
        var transferPage = dashboardPage.cardToTransfer(cardInfo1);
        //ввод суммы, вобор карты списания, проверки
        var sumTransferInfo = DataHelper.getSumTransferInfoStock();
         transferPage.transfer(cardInfo2, sumTransferInfo);
        var actualBalanceCard1 = DashboardPage.getCardBalance(cardInfo1);
        var actualBalanceCard2 = DashboardPage.getCardBalance(cardInfo2);
        Assertions.assertEquals(expectedBalanceCard1, actualBalanceCard1);
        Assertions.assertEquals(expectedBalanceCard2, actualBalanceCard2);

    }



}
