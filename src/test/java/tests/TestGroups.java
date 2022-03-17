package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.GroupPageComponents;
import pages.GroupPageObjects;
import pages.UserPageComponents;
import utils.RandomUtils;

import java.util.Random;
import java.util.stream.Stream;

import static utils.RandomUtils.getRandomString;

public class TestGroups extends TestBase {

    UserPageComponents userPageComponents = new UserPageComponents();
    GroupPageComponents groupPageComponents = new GroupPageComponents();
    GroupPageObjects groupPageObjects = new GroupPageObjects();
    RandomUtils randomUtils = new RandomUtils();


    //поля авторизации
    String login = "admin";
    String password = "123";
    //поля основания
    String reason = "because";
    String type = "because2";
    String number = "3234";
    String name = "Petrov Ivan Dmitrievich";
    String date = "30.10.2020";
    //тексты ошибок
    String samegrouperror = "Ошибка запроса. Группа с данной сигнатурой уже существует";


    static Stream<Arguments> mixedGroupCreateProvider() {
        Faker faker = new Faker();

        return Stream.of(
                Arguments.of(faker.funnyName().name(), faker.hobbit().location()),
                Arguments.of(getRandomString(30), getRandomString(30))
        );
    }

    @MethodSource(value = "mixedGroupCreateProvider")
    @ParameterizedTest(name = "Создание группы с названием содержащим {0}")
    void mixedGroupCreate(String groupName, String groupDescription) {

        SelenideLogger.addListener("allure", new AllureSelenide());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        //перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(groupName)
                .setGroupDescription(groupDescription);
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю в поиске созданную группу
        groupPageObjects.newGroupCheck(groupName);
    }

    @Test
    @DisplayName("Редактирование группы")
    void groupEdit() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());
        String word2 = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        //перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю в поиске созданную группу
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //открываю выбор действия с группой
        groupPageComponents.groupMenuOpen();
        //кликаю редактировать
        groupPageComponents.groupEditButton();
        //очищаю старые поля сигнатура и описание
        groupPageObjects.clearGroupCreationFields();
        //вношу новые значения
        groupPageObjects
                .setGroupName(String.valueOf(word2))
                .setGroupDescription(String.valueOf(word2));
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю что измененная группа есть в списке
        groupPageObjects.newGroupCheck(String.valueOf(word2));
    }

    @Test
    @DisplayName("Удаление группы")
    void groupDelete() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        //перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю в поиске созданную группу
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //открываю выбор действия с группой
        groupPageComponents.groupMenuOpen();
        //Выбираю Удалить
        groupPageComponents.groupDeleteButton();
        //Заполняю поля основания удаления
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю Подтвердить
        groupPageComponents.groupDeleteSubmit();
        //Подтверждаю удаление
        groupPageComponents.deleteSubmitWindow();
        //Проверяю что таблица пуста
        groupPageComponents.emptyTableCheck();

    }

    @Test
    @DisplayName("Создание группы с неуникальной сигнатурой")
    void notUniqueSign() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());


        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        //перехожу на основную вкладку Группы
        groupPageComponents.mainGroupClick();
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButton();
        //проверяю в поиске созданную группу
        groupPageObjects.newGroupCheck(String.valueOf(word));
        //кликаю "Создать"
        groupPageComponents.groupCreateButtonClick();
        //заполняю поля имя и описание группы
        groupPageObjects
                .setGroupName(String.valueOf(word))
                .setGroupDescription(String.valueOf(word));
        //Заполняю поля основания
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        groupPageComponents.clickGroupSubmitButtonWithError();
        //проверяю появление и наполнение ошибки
        groupPageComponents.checkErrorMassage(samegrouperror);


    }


}

