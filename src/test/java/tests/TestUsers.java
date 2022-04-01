package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UserPageComponents;
import pages.UserPageObjects;
import utils.RandomUtils;


public class TestUsers extends TestBase {

    UserPageComponents userPageComponents = new UserPageComponents();
    UserPageObjects userPageObjects = new UserPageObjects();
    RandomUtils randomUtils = new RandomUtils();

    //поля авторизации
    String login = "admin";
    String password = "123";
    //поля пользователя
    String adlogin = "@kinef.lcl";
    String kadrid = "1112223334";
    String tabel = "1112223334";
    String userpassword = "123";
    String email = "123@ya.ru";
    String activechek = "Активный";
    String usertypenone = "Нет типа";
    String usertypexpert = "Эксперт";
    String usertypservice = "Служебный сервис";
    String wrondDateStart = "17.03.2021";
    String wrondDateEnd = "18.03.2021";
    //поля основания
    String reason = "because";
    String type = "because2";
    String number = "3234";
    String name = "Petrov Ivan Dmitrievich";
    String date = "30.10.2020";


    @Test
    @Feature("Тестирование влкадки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User create")
    public void userCreate() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserAdlogin(word + adlogin)
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(userpassword)
                .setUserEmail(email);
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //кликаю кнопку информации
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                word + adlogin,
                kadrid, email, activechek, usertypenone);

    }

    @Test
    @Feature("Тестирование влкадки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User edit")
    public void userEdit() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        //random word
        String word = String.valueOf(randomUtils.randomString());
        String word2 = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(userpassword)
                .setUserEmail(email);
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //Ищу и нажимаю кнопку редактировать
        userPageComponents.clickActionButton().clickUserEditButton();
        //меняю значения на новые
        userPageObjects
                .clearUserCreationFields()
                .setUserSurname(String.valueOf(word2))
                .setUsetName(String.valueOf(word2))
                .setUserPatronymic(String.valueOf(word2))
                .setUserLogin(String.valueOf(word2))
                .setUserAdlogin(word + adlogin)
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(password)
                .setUserEmail(email);
        //выбираю чек-бокс Служебный сервис
        userPageComponents.setServiceCheckbox();
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        userPageComponents.userCreateSubmitButton();
        //скролю таблицу влево
        userPageComponents.scrollUserTableTobegin();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word2));
        //кликаю по кнопке информация
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word2),
                String.valueOf(word2),
                String.valueOf(word2),
                String.valueOf(word2),
                word + adlogin,
                kadrid, email, activechek, usertypservice);

    }

    @Test
    @Feature("Тестирование влкадки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User delete")
    public void userDelete() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        //random word
        String word = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(userpassword)
                .setUserEmail(email);
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //Ищу и нажимаю кнопку удалить
        userPageComponents.clickActionButton().clickUserDeleteButton();
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        userPageComponents.userDeleteSubmitButton();
        //ищу удаленного пользователя
        userPageObjects.userSearch(String.valueOf(word));
        //убеждаюсь что список пуст
        userPageComponents.emptyTableCheck();
    }

    @Test
    @Feature("Тестирование влкадки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User expert rights create")
    public void userExpertCreate() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserAdlogin(word + adlogin)
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(userpassword)
                .setUserEmail(email);
        //выбираю чек-бокс эксперта
        userPageComponents.setExpertCheckbox();
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //кликаю кнопку информации
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                word + adlogin,
                kadrid, email, activechek, usertypexpert);

    }

    @Test
    @Feature("Тестирование влкадки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("Temporary user create")
    public void temporaryUserCreate() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String word = String.valueOf(randomUtils.randomString());

        userPageComponents.openLoginPage();
        userPageComponents.authorizeSupd(login, password);
        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(word))
                .setUsetName(String.valueOf(word))
                .setUserPatronymic(String.valueOf(word))
                .setUserLogin(String.valueOf(word))
                .setUserAdlogin(word + adlogin)
                .setUserKadrId(kadrid)
                .setUserTabel(tabel)
                .setUserPassword(userpassword)
                .setUserEmail(email);
        //выбираю чек-бокс Временно
        userPageComponents.setTemporaryCheckbox();
        //устанавливаю некорректный период
        userPageObjects.setTemporaryUserPeriod(wrondDateStart, wrondDateEnd);
        //проверяю наличие и наполнения сообщений об ошибке
        userPageComponents.wrongTemporaryDateChek();
        //дважды нажимаю чек-бокс чтобы автоматически подставилась корректная дата
        userPageComponents.setTemporaryCheckbox();
        userPageComponents.setTemporaryCheckbox(); //баг что при автозаполнении данных в полях, не нажимается нопка "Сохранить"
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю создать
        userPageComponents.userCreateSubmitButton();
        //проверяю пользователя в таблице
        userPageObjects.newUserCheck(String.valueOf(word));
        //кликаю кнопку информации
        userPageComponents.clickInfoButton();
        //сверяю данные
        userPageComponents.userInfoCheck(
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                String.valueOf(word),
                word + adlogin,
                kadrid, email, activechek, usertypenone);

    }

}

