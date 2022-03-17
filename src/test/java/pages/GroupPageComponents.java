package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GroupPageComponents {

    @Step("Нажимаем кнопку подтвердить и ждем создания группы")
    public GroupPageComponents clickGroupSubmitButton() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        SelenideElement toolbarText = $("div.dx-toolbar-before div.dx-item-content div");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        groupCreateSubmitButton.shouldBe(not(visible), Duration.ofMillis(15000));
        toolbarText.shouldBe(Condition.not(Condition.visible), Duration.ofMillis(15000));
        return this;
    }
    @Step("Нажимаем кнопку подтвердить")
    public GroupPageComponents clickGroupSubmitButtonWithError() {
        SelenideElement groupCreateSubmitButton = $("[data-id=group-edit-form_btn-submit]");
        groupCreateSubmitButton.scrollIntoView(false).shouldBe(visible).click();
        return this;
    }
    @Step("Переходим на вкладку группы нажимая кнопку Группы")
    public GroupPageComponents mainGroupClick() {
        SelenideElement mainToggleGroupButton =  $("[data-id=main-nav_groups]");
        mainToggleGroupButton.shouldBe(visible).click();
        return this;
    }
    @Step("Нажимаем кнопку Создать")
    public GroupPageComponents groupCreateButtonClick() {
        SelenideElement groupCreateButton = $("[data-id=groups__actions_btn-create]");
        groupCreateButton.shouldBe(visible).click();
        return this;
    }
    @Step("Открываем меню действия с группой")
    public GroupPageComponents groupMenuOpen() {
        SelenideElement groupMenuButton = $("[data-id=actions_btn]");
        groupMenuButton.shouldBe(visible).click();
        return this;
    }
    @Step("Кликаем редактировать")
    public GroupPageComponents groupEditButton() {
        SelenideElement groupEditButton = $("[data-id=actions_btn_edit]");
        groupEditButton.shouldBe(visible).click();
        return this;
    }
    @Step("Кликаем удалить")
    public GroupPageComponents groupDeleteButton() {
        SelenideElement groupDeleteButton = $("[data-id=actions_btn_delete]");
        groupDeleteButton.shouldBe(visible).click();
        return this;
    }
    @Step("Кликаем Подтвердить удаление")
    public GroupPageComponents groupDeleteSubmit() {
        SelenideElement groupDeleteSubmitButton = $("[data-id=confirm_submit]");
        groupDeleteSubmitButton.shouldBe(visible).click();
        return this;
    }
    @Step("Проверяем что удаленной группы нет в списке групп")
    public GroupPageComponents emptyTableCheck() {
        SelenideElement emptyTableSign = $(".dx-datagrid-nodata");
        emptyTableSign.shouldBe(visible).shouldHave(text("Нет данных"));
        return this;
    }

    @Step("Подвтерждаем удаление")
    public GroupPageComponents deleteSubmitWindow(){
        SelenideElement deleteSubmitText = $(".simple-modal__text");
        SelenideElement deleteSubmit = $("[data-id=confirm-delete]");
        deleteSubmitText.shouldHave(text("К группе привязано 0 пользователей. Вы действительно хотите удалить группу?"));
        deleteSubmit.click();
        deleteSubmitText.shouldBe(not(visible), Duration.ofMillis(15000));
        return this;
    }
    @Step("Проверяем наполнение ошибки")
    public GroupPageComponents checkErrorMassage(String text){
        SelenideElement errorMassage = $(".toast-text");
        errorMassage.shouldBe(visible).shouldHave(text(text));
        return this;
    }
}
