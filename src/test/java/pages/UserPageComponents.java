package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserPageComponents {

    //авторизация
    @Step("Проходим авторизацию")
    public UserPageComponents authorizeSupd(String login, String password) {

        SelenideElement loginInput = $("[data-id=login_login]");
        SelenideElement passwordInput = $("[data-id=login_password]");
        SelenideElement submitButton = $("[data-id=login_submit]");

        loginInput.setValue(login).pressEnter();
        passwordInput.setValue(password).pressEnter();
        submitButton.click();

        return this;

    }

    @Step("Открываем браузер")
    public UserPageComponents openLoginPage() {
        open("login");
        return this;
    }

    //основание
    @Step("Заполняем поля основания")
    public UserPageComponents reasonForm(String reason, String type, String number, String name, String date) {

        SelenideElement userReasonInput = $("[data-id=reason_form_reason]");
        SelenideElement userReasonTypeInput = $("[data-id=reason_form_type]");
        SelenideElement userReasonNumberInput = $("[data-id=reason_form_number]");
        SelenideElement userAgreementNameInput = $("[data-id=reason_form_fullname_agreement]");
        SelenideElement userAgreementDate = $("#reason_form_date_agreement");

        userReasonInput.scrollIntoView(false).setValue(reason);
        userReasonTypeInput.scrollIntoView(false).setValue(type);
        userReasonNumberInput.scrollIntoView(false).setValue(number);
        userAgreementNameInput.scrollIntoView(false).setValue(name);
        userAgreementDate.scrollIntoView(false).setValue(date);

        return this;

    }

    @Step("Сверяем информацию о созданном пользователе")
    public UserPageComponents userInfoCheck(String surname, String name, String patronymic, String login, String kadrid, String email, String isactive, String type) {

        SelenideElement userInfoSurname = $("#user_info_surname");
        SelenideElement userInfoName = $("#user_info_name");
        SelenideElement userInfoPatronymic = $("#user_info_patronymic");
        SelenideElement userInfoLogin = $("#user_info_login");
        SelenideElement userInfoKadrId = $("#user_info_kadr_id");
        SelenideElement userInfoEmail = $("#user_info_email");
        SelenideElement userInfoActive = $("#user_info_active");
        SelenideElement userInfoAccType = $("#user_info_account_type");

        userInfoSurname.shouldHave(text(surname));
        userInfoName.shouldHave(text(name));
        userInfoPatronymic.shouldHave(text(patronymic));
        userInfoLogin.shouldHave(text(login));
        userInfoKadrId.scrollIntoView(false).shouldHave(text(kadrid));
        userInfoEmail.scrollIntoView(false).shouldHave(text(email));
        userInfoActive.scrollIntoView(false).shouldHave(text(isactive));
        userInfoAccType.scrollIntoView(false).shouldHave(text(type));

        return this;

    }

    @Step("Нажимаем кнопку Создать")
    public UserPageComponents userCreateButton() {
        SelenideElement userCreateButton = $("#user_create_btn");
        userCreateButton.shouldBe(visible).click();
        return this;
    }
    @Step("Нажимаем кнопку Подтвредить")
    public UserPageComponents userCreateSubmitButton() {
        SelenideElement userCreateSubmitButton = $("#user_edit_submit");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        userCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        userCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));

        return this;
    }

    @Step("Нажимаем кнопку Подтвердит")
    public UserPageComponents userDeleteSubmitButton() {
        SelenideElement userDeleteSubmitButton = $("[data-id=confirm_submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        userDeleteSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        userDeleteSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
        return this;
    }
    @Step("Нажимаем кнопку информация о пользователе")
    public UserPageComponents clickInfoButton() {
        SelenideElement userInfoButton = $("[data-id=user_show_info]");
        userInfoButton.scrollIntoView(false).shouldBe(visible).click();
        return this;
    }
    @Step("Открываем выпадающий список действий с пользователем")
    public UserPageComponents clickActionButton() {
        SelenideElement userActionButton = $("[data-id=actions_btn]");
        userActionButton.scrollIntoView(false).shouldBe(visible).click();
        return this;
    }
    @Step("Выбираем действие Редактировать")
    public UserPageComponents clickUserEditButton() {
        SelenideElement userUserEditButton = $("[data-id=actions_btn_edit]");
        userUserEditButton.shouldBe(visible).click();
        return this;
    }
    @Step("Выбираем действие удалить")
    public UserPageComponents clickUserDeleteButton() {
        SelenideElement userUserDeleteButton = $("[data-id=actions_btn_delete]");
        userUserDeleteButton.shouldBe(visible).click();
        return this;
    }
    @Step("Прокрутка таблицы влево")
    public UserPageComponents scrollUserTableTobegin() {
        SelenideElement userSurnameTable = $("#dx-col-101");
        userSurnameTable.scrollIntoView(true).shouldHave(text("Фамилия"));
        return this;
    }

    @Step("Проверяем что удаленного пользователя нет в списке пользователей")
    public UserPageComponents emptyTableCheck() {
        SelenideElement emptyTableSign = $(".dx-datagrid-nodata");
        emptyTableSign.shouldBe(visible).shouldHave(text("Нет данных"));
        return this;
    }


}
