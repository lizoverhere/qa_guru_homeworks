package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import java.io.File;
import static com.codeborne.selenide.Condition.text;

public class TestForm {
    @Test
    void registrationFormTest() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Bogdanova");
        $("#userEmail").setValue("lizabogdanova@gmail.com");
        $("#gender-radio-2").click();
        $("#userNumber").setValue("8960123456");
        $("#dateOfBirthInput").setValue("12 May 2000");
        $("#subjectsInput").setValue("Maths");
        $("#subjectsInput").pressEnter();
        $("label[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFile(new File("/Users/lizzie/Desktop/cat.jpg"));
        $("#currentAddress").setValue("Moscow, Lenina avenue, 45");
        $("#state input").setValue("NCR");
        $("#state Input").pressEnter();
        $("#city input").setValue("Delhi");
        $("#city Input").pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text("Elizaveta Bogdanova"));
        $(".modal-body").shouldHave(text("lizabogdanova@gmail.com"));
        $(".modal-body").shouldHave(text("Female"));
        $(".modal-body").shouldHave(text("8960123456"));

    }
}
