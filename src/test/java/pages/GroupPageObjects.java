package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class GroupPageObjects {
    //locators
    SelenideElement firstLineInTheTable = $(".dx-column-lines", 1);

    SelenideElement groupNameInput = $("#group-edit-form_sign");
    SelenideElement groupDescriptionInput = $("#group-edit-form_desc");

    //actions
    @Step ("Смотрим что в результатах поиска появилась созданная группа")
    public GroupPageObjects newGroupCheck(String groupName) {
        firstLineInTheTable.shouldBe(visible).shouldHave(text(groupName));
        return this;
    }
    @Step("Вводим сигнатуру группы")
    public GroupPageObjects setGroupName (String groupName) {
        groupNameInput.setValue(groupName);
        return this;
    }
    @Step("Вводим описание группы")
    public GroupPageObjects setGroupDescription (String groupDescription) {
        groupDescriptionInput.setValue(groupDescription);
        return this;
    }
    @Step("Очищаем стрые значения в полях")
    public GroupPageObjects clearGroupCreationFields(){
        groupNameInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        groupDescriptionInput.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        return this;
    }

}
