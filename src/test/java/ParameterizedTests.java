import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Enabled;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests {


    @BeforeAll
    static void beforeAll() {
        browserSize = "1920x1080";
    }


    @BeforeEach
    void preCondition() {
        open("https://javarush.ru/welcome/save-progress");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"plainaddress", "email.domain.com", "@domain.com", "#@%^%#$@#$@#.com", "email@domain.com (Joe Smith)", "email@domain@domain.com", ".email@domain.com", " " ,"email@111.222.333.44444"})
    @ParameterizedTest(name = "Check input invalid data in the email field \"{0}\"")
    void happyPathTests(String testData) {
        $(".form-group:nth-child(1) > .input").click();
        $(".form-group:nth-child(1) > .input").setValue(testData);
        $(".form-group:nth-child(2) > .input").setValue("qwerty");
        $("#id_button_jr_welcome_create_account").shouldNot(enabled);
    }


    @CsvSource(value = {
            "email@email | qwerty | Произошла неизвестная ошибка, дополнительной информации нет.",
            "EMAIL__________@DDDDDD |123| Произошла неизвестная ошибка, дополнительной информации нет."
    }, delimiter = '|')
    @ParameterizedTest(name = "Check auth with invalid data (cyrillic symbols) \"{0}\"")
    void loginAndPasswordAuthenticationTest(String email, String password, String errorMessage) {
        $(".form-group:nth-child(1) > .input").click();
        $(".form-group:nth-child(1) > .input").setValue(email);
        $(".form-group:nth-child(2) > .input").setValue(password);
        $("#id_button_jr_welcome_create_account").should(enabled).click();
        $(".alert-notification__text").shouldHave(text(errorMessage));
    }

    static Stream<Arguments> argumentsForDataProvider() {
        return Stream.of(
                Arguments.of("s@s", "Произошла неизвестная ошибка, дополнительной информации нет."),
                Arguments.of("@", "Произошла неизвестная ошибка, дополнительной информации нет.")
        );
    }

    @MethodSource(value = "argumentsForDataProvider")
    @ParameterizedTest(name = "Check auth with invalid data (cyrillic symbols) \"{0}\"")
    void validationPhoneAndEmailTest(String email, String errorMessage) {
        $(".form-group:nth-child(1) > .input").click();
        $(".form-group:nth-child(1) > .input").setValue(email);
        $(".form-group:nth-child(2) > .input").setValue("pass123");
        $("#id_button_jr_welcome_create_account").should(enabled).click();
        $(".alert-notification__text").shouldHave(text(errorMessage));
    }

}
