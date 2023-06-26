package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class LoginToYourPersonalAccountTest {
    DashboardPage dashboardPage;
    @AfterAll
    static void teardown () {cleanDatabase();}
    @Test
    void willShowTheEntranceToThePersonalAccount() {
        var loginPage = open("http://localhost:9999/", ru.netology.page.LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode.getCode());
    }
}