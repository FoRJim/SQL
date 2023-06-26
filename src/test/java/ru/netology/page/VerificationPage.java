package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class VerificationPage {

    private final SelenideElement enterCode = $("[data-test-id=code] input");
    private final SelenideElement enterButtonVerify = $("[data-test-id=action-verify]");

    public VerificationPage() {
        enterCode.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode){
        verify(verificationCode);
        return page(DashboardPage.class);
    }
    public void verify(String verificationCode){
        enterCode.setValue(verificationCode);
        enterButtonVerify.click();
    }
}