package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class MoneyTransferTest {

    @Test
    public void transferOfFundsToAccountOne() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var info = DataHelper.getUser();
        var verificationPage = loginPage.validation(info);

        var verificationInfo = DataHelper.getCode();
        var dashboardPage = verificationPage.verification(verificationInfo);

        var dashboardInfo = DataHelper.getNomCard1();
        var dashboardInfo2 = DataHelper.getNomCard2();
        var transferPage = dashboardPage.cardToTransfer(dashboardInfo, dashboardInfo2);



        var transferInfo = DataHelper.getNomCard2();
        var transferInfo2 = DataHelper.getSumTransferInfo();
         var dashboardPage1 =  transferPage.transfer(transferInfo, transferInfo2);






    }

}
