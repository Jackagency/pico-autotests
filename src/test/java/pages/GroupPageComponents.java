package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GroupPageComponents {

    @Step("Нажимаем кнопку подтвердить и ждем создания группы")
    public void clickGroupSubmitButton() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        groupCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
    }

    @Step("Нажимаем кнопку подтвердить")
    public void clickGroupSubmitButtonWithError() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
    }
    @Step("Переходим на вкладку группы нажимая кнопку Группы")
    public void mainGroupClick() {
        SelenideElement mainToggleGroupButton =  $("[data-id=main-nav_groups]");
        mainToggleGroupButton.shouldBe(visible).click();
    }
    @Step("Нажимаем кнопку Создать")
    public void groupCreateButtonClick() {
        SelenideElement groupCreateButton = $("[data-id=groups__actions_btn-create]");
        groupCreateButton.shouldBe(visible).click();
    }
    @Step("Открываем меню действия с группой")
    public void groupMenuOpen() {
        SelenideElement groupMenuButton = $("[data-id=actions_btn]");
        groupMenuButton.shouldBe(visible).click();
    }
    @Step("Кликаем редактировать")
    public void groupEditButton() {
        SelenideElement groupEditButton = $("[data-id=actions_btn_edit]");
        groupEditButton.shouldBe(visible).click();
    }
    @Step("Кликаем удалить")
    public void groupDeleteButton() {
        SelenideElement groupDeleteButton = $("[data-id=actions_btn_delete]");
        groupDeleteButton.shouldBe(visible).click();
    }
    @Step("Кликаем Подтвердить удаление")
    public void groupDeleteSubmit() {
        SelenideElement groupDeleteSubmitButton = $("[data-id=confirm_submit]");
        groupDeleteSubmitButton.shouldBe(visible).click();
    }
    @Step("Проверяем что удаленной группы нет в списке групп")
    public void emptyTableCheck() {
        SelenideElement emptyTableSign = $(".dx-datagrid-nodata");
        emptyTableSign.shouldBe(visible).shouldHave(text("Нет данных"));
    }

    @Step("Подвтерждаем удаление")
    public void deleteSubmitWindow(){
        SelenideElement deleteSubmitText = $(".simple-modal__text");
        SelenideElement deleteSubmit = $("[data-id=confirm-delete]");
        deleteSubmitText.shouldHave(text("К группе привязано 0 пользователей. Вы действительно хотите удалить группу?"));
        deleteSubmit.click();
        deleteSubmitText.shouldBe(not(visible), Duration.ofMillis(15000));
    }
    @Step("Проверяем наполнение ошибки")
    public void checkErrorMassage(String text){
        SelenideElement errorMassage = $(".toast-text");
        errorMassage.shouldBe(visible).shouldHave(text(text));
    }
    @Step("Проверяем первую строчку в таблице пользователей")
    public void firstLineUsersTableCheck(String surname){
        SelenideElement firstLine = $("#users tr.dx-row.dx-data-row.dx-column-lines td:nth-child(4) div div a");
        $(byText("Все пользователи")).click();
        $(byText("Только связанные")).click();//перекликиваю чек-боксы иначе список не грузится, руками все ок
        firstLine.shouldHave(text(surname));
    }

}
