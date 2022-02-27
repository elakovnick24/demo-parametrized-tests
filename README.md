![LOGO](https://fs-thb03.getcourse.ru/fileservice/file/thumbnail/h/b635b6cb9478bb87c77e9c070ee6e122.png/s/x50/a/159627/sc/207)

## Homework QA_GURU

### JUnit <img src="https://starchenkov.pro/qa-guru/img/skills/JUnit5.svg" width="40" height="40"  alt="JUnit 5"/></a>

### Description
___

1. Simple test with annotation @ValueSource

``` java
@ValueSource(strings = {"plainaddress", "email.domain.com", "@domain.com", "#@%^%#$@#$@#.com", "email@domain.com (Joe Smith)", "email@domain@domain.com", ".email@domain.com", " " ,"email@111.222.333.44444"})
@ParameterizedTest(name = "Check input invalid data in the email field \"{0}\"")
    void happyPathTests(String testData) {
            $(".form-group:nth-child(1) > .input").click();
            $(".form-group:nth-child(1) > .input").setValue(testData);
            $(".form-group:nth-child(2) > .input").setValue("qwerty");
            $("#id_button_jr_welcome_create_account").shouldNot(enabled);
            }
```
___
2. Simple test with annotation @CsvSource

``` java
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
```
___

3. Simple test with annotation @MethodSource

``` java
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
```
