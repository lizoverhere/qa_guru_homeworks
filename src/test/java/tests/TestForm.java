package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.value;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selectors.byText;


public class TestForm {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
        //Заполнение формы со всеми полями
    void registrationFormTestWithAllFields() {
        open("/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Bogdanova");
        $("#userEmail").setValue("lizabogdanova@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("8960123456");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__day.react-datepicker__day--012").click();
        $(".react-datepicker").shouldNotBe(visible);
        $("#subjectsInput").setValue("Maths");
        $("#subjectsInput").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).scrollIntoView(true).click();
        $("#uploadPicture").uploadFromClasspath("cat.jpg");
        $("#currentAddress").setValue("Moscow, Lenina avenue, 45");
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body").shouldHave(text("Elizaveta Bogdanova"));
        $(".modal-body").shouldHave(text("lizabogdanova@gmail.com"));
        $(".modal-body").shouldHave(text("Female"));
        $(".modal-body").shouldHave(text("8960123456"));
        $(".modal-body").shouldHave(text("12 May,2000"));
        $(".modal-body").shouldHave(text("Maths"));
        $(".modal-body").shouldHave(text("Reading"));
        $(".modal-body").shouldHave(text("cat.jpg"));
        $(".modal-body").shouldHave(text("Moscow, Lenina avenue, 45"));
        $(".modal-body").shouldHave(text("NCR Delhi"));
    }

    @Test
        //Заполнение формы с обязательными полями
    void requiredFields() {
        open("/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Bogdanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("8960123456");
        $("#submit").scrollIntoView(true).click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }


    @Test
        //Негативный сценарий: номер телефона менее 10 цифр
    void fiveDigitPhoneNumber() {
        open("/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Bogdanova");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("55555");
        $("#submit").scrollIntoView(true).click();
        $("#userNumber:invalid").shouldBe(visible);
    }


    @Test
        //Негативный сценарий: форма без фамилии
    void withoutLastname() {
        open("/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#submit").scrollIntoView(true).click();
        $("#lastName:invalid").shouldBe(visible);
    }

    @Test
        //Негативный сценарий: форма без имени
    void withoutFirstName() {
        open("/automation-practice-form");
        $("#lastName").setValue("Bogdanova");
        $("#submit").scrollIntoView(true).click();
        $("#firstName:invalid").shouldBe(visible);
    }

    @Test
        //Негативный сценарий: неверный формат почты
    void uncorrectEmail() {
        open("/automation-practice-form");
        $("#firstName").setValue("Elizaveta");
        $("#lastName").setValue("Bogdanova");
        $("#userEmail").setValue("qwert");
        $("#submit").scrollIntoView(true).click();
        $("#userEmail:invalid").shouldBe(visible);
    }

    @Test
        //Простая форма, успешный сценарий
    void successSimpleForm() {
        open("/text-box");
        $("#userName").setValue("Bogdanova Elizaveta");
        $("#userEmail").setValue("lizabogdanova880@gmail.com");
        $("#currentAddress").setValue("Moscow, Lenina avenue, 45");
        $("#permanentAddress").click();
        $("#permanentAddress").setValue("Moscow, Udaltsova street, 3");
        $("#submit").click();

        $("#name").shouldHave(text("Bogdanova Elizaveta"));
        $("#email").shouldHave(text("lizabogdanova880@gmail.com"));
        $("#currentAddress").shouldHave(value("Moscow, Lenina avenue, 45"));
        $("#permanentAddress").shouldHave(value("Moscow, Udaltsova street, 3"));
    }

    @Test
        //Простая форма, негативный сценарий,
    void uncorrectSimpleForm() {
        open("/text-box");
        $("#userName").setValue("testik");
        $("#userEmail").setValue("444");
        $("#submit").click();
        $("#userEmail").shouldHave(cssClass("field-error"));
    }
}


