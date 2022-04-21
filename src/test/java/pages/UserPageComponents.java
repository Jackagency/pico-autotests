package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UserPageComponents {

    //авторизация
    @Step("Проходим авторизацию")
    public void authorizeSupd(String login, String password) {

        SelenideElement loginInput = $("[data-id=login_login]");
        SelenideElement passwordInput = $("[data-id=login_password]");
        SelenideElement submitButton = $("[data-id=login_submit]");

        loginInput.setValue(login).pressEnter();
        passwordInput.setValue(password).pressEnter();
        submitButton.click();
    }

    @Step("Открываем браузер")
    public void openLoginPage() {
        open("login");
    }

    //основание
    @Step("Заполняем поля основания")
    public void reasonForm(String reason, String type, String number, String name, String date) {

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
    }

    @Step("Сверяем информацию о созданном пользователе")
    public void userInfoCheck(String surname, String name, String patronymic, String login, String adlogin, String kadrid, String email, String isactive, String type) {

        SelenideElement userInfoSurname = $("#user_info_surname");
        SelenideElement userInfoName = $("#user_info_name");
        SelenideElement userInfoPatronymic = $("#user_info_patronymic");
        SelenideElement userInfoLogin = $("#user_info_login");
        SelenideElement userInfoAdLogin = $("#user_info_ldap_login");
        SelenideElement userInfoKadrId = $("#user_info_kadr_id");
        SelenideElement userInfoEmail = $("#user_info_email");
        SelenideElement userInfoActive = $("#user_info_active");
        SelenideElement userInfoAccType = $("#user_info_account_type");

        userInfoSurname.shouldHave(text(surname));
        userInfoName.shouldHave(text(name));
        userInfoPatronymic.shouldHave(text(patronymic));
        userInfoLogin.shouldHave(text(login));
        userInfoAdLogin.shouldHave(text(adlogin));
        userInfoKadrId.scrollIntoView(false).shouldHave(text(kadrid));
        userInfoEmail.scrollIntoView(false).shouldHave(text(email));
        userInfoActive.scrollIntoView(false).shouldHave(text(isactive));
        userInfoAccType.scrollIntoView(false).shouldHave(text(type));
    }

    @Step("Нажимаем кнопку Создать")
    public void userCreateButton() {
        SelenideElement userCreateButton = $("#user_create_btn");
        userCreateButton.shouldBe(visible).click();
    }
    @Step("Нажимаем кнопку Подтвредить")
    public void userCreateSubmitButton() {
        SelenideElement userCreateSubmitButton = $("#user_edit_submit");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        userCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        userCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
    }

    @Step("Нажимаем кнопку Подтвердит")
    public void userDeleteSubmitButton() {
        SelenideElement userDeleteSubmitButton = $("[data-id=confirm_submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        userDeleteSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        userDeleteSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
    }
    @Step("Нажимаем кнопку информация о пользователе")
    public void clickInfoButton() {
        SelenideElement userInfoButton = $("[data-id=user_show_info]");
        userInfoButton.scrollIntoView(false).shouldBe(visible).click();
    }
    @Step("Открываем выпадающий список действий с пользователем")
    public UserPageComponents clickActionButton() {
        SelenideElement userActionButton = $("[data-id=actions_btn]");
        userActionButton.scrollIntoView(true).shouldBe(visible).click();
        return this;
    }
    @Step("Выбираем действие Редактировать")
    public void clickUserEditButton() {
        SelenideElement userUserEditButton = $("[data-id=actions_btn_edit]");
        userUserEditButton.shouldBe(visible).click();
    }
    @Step("Выбираем действие удалить")
    public void clickUserDeleteButton() {
        SelenideElement userUserDeleteButton = $("[data-id=actions_btn_delete]");
        userUserDeleteButton.shouldBe(visible).click();
    }
    @Step("Прокрутка таблицы влево")
    public void scrollUserTableTobegin() {
        SelenideElement userSurnameTable = $("#dx-col-101");
        userSurnameTable.scrollIntoView(true).shouldHave(text("Фамилия"));
    }

    @Step("Проверяем что удаленного пользователя нет в списке пользователей")
    public void emptyTableCheck() {
        SelenideElement emptyTableSign = $(".dx-datagrid-nodata");
        emptyTableSign.shouldBe(visible).shouldHave(text("Нет данных"));
    }

    @Step("Выбираем чек-бокс Эксперт")
    public void setExpertCheckbox() {
        SelenideElement expertCheckBox = $("#user_account_type :nth-child(1) .dx-radio-value-container");
        expertCheckBox.shouldBe(visible).click();
    }

    @Step("Выбираем чек-бокс Служебный сервис")
    public void setServiceCheckbox() {
        SelenideElement serviceCheckbox = $("#user_account_type :nth-child(2) > div.dx-radio-value-container");
        serviceCheckbox.shouldBe(visible).click();
    }

    @Step("Выбираем чек-бокс Временный")
    public void setTemporaryCheckbox() {
        SelenideElement temporaryCheckbox = $("#user_temporary");
        temporaryCheckbox.shouldBe(visible).click();
    }

    @Step("Проверяем сообщения о некорректной дате временного пользователя")
    public void wrongTemporaryDateChek() {
        SelenideElement wrongTemporaryDateStart = $("div.mr-2 .dx-invalid-message-content");
        SelenideElement wrongTemporaryDateEnd = $("div:nth-child(2) > div > .dx-invalid > .dx-invalid-message");
        wrongTemporaryDateStart.shouldBe(visible).shouldHave(text("Дата не может быть раньше текущей"));
        wrongTemporaryDateEnd.shouldBe(visible).shouldHave(text("Дата не может быть раньше текущей или раньше «даты от»"));
    }
    @Step("Нажимаем чек-бокс пользователя")
    public void clickUserCheckbox(){
        SelenideElement userCheckbox = $(".dx-select-checkbox", 1); //кликает первый чек-бокс во всем списке
        userCheckbox.shouldBe(visible).click();
    }
    @Step("Нажимаем кнопку Назначить временные права")
    public void clickTemporaryRightsButton(){
        SelenideElement temporaryRightsButton = $x(".//button[contains(.,'Назначить временные права')]");
        temporaryRightsButton.shouldBe(visible).click();
    }
    @Step("Выбираем первый чек-бокс в списке")
    public void clickObjectsCheckbox(){
        SelenideElement objectCheckbox = $(".app-user-access-form__table .dx-datagrid-rowsview table tr:nth-child(1) td.dx-command-select > div");
        objectCheckbox.shouldBe(visible).click();
    }
    @Step("Нажимаем кнопку Применить")
    public void clickSubmitButton(){
        SelenideElement submitButton = $x(".//button[contains(.,'Применить')]");
        SelenideElement successMessage = $(".notices.is-bottom > div");
        submitButton.shouldBe(visible).click();
    }
    @Step("Проверяем первую строчку в таблице групп")
    public void firstLineGroupTableCheck(){
        SelenideElement firstLine = $("#groups tr.dx-row.dx-data-row.dx-column-lines td:nth-child(2)");
        firstLine.shouldHave(text("TEMP_GROUP"));
    }
    @Step("Нажимаем на первую строчку в таблице групп")
    public void firstLineGroupTableClick(){
        SelenideElement firstLine = $("#groups tr.dx-row.dx-data-row.dx-column-lines td:nth-child(2)");
        firstLine.click();
    }

}
