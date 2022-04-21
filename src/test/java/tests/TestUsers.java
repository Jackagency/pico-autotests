package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.GroupPageComponents;
import pages.GroupPageObjects;
import pages.UserPageComponents;
import pages.UserPageObjects;
import utils.RandomUtils;

import java.util.Locale;


public class TestUsers extends TestBase {

    UserPageComponents userPageComponents = new UserPageComponents();
    UserPageObjects userPageObjects = new UserPageObjects();
    GroupPageObjects groupPageObjects = new GroupPageObjects();
    GroupPageComponents groupPageComponents = new GroupPageComponents();
    CreationTestData creationTestData = new CreationTestData();
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
    //Объект для выдачи на него прав
    String object = "GOS";
    //Сигнатура временной группы
    String group = "TEMP_GROUP";


    @Test
    @Feature("Тестирование вкладки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User create")
    public void userCreate() {

        String word = String.valueOf(randomUtils.randomString());

        Faker faker = new Faker(new Locale("ru"));
        String username = faker.name().firstName();
        String userSurname = faker.name().lastName();

        userPageComponents.userCreateButton();
        //ввожу данные пользователя
        userPageObjects
                .setUserSurname(String.valueOf(userSurname))
                .setUsetName(String.valueOf(username))
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
                String.valueOf(userSurname),
                String.valueOf(username),
                String.valueOf(word),
                String.valueOf(word),
                word + adlogin,
                kadrid, email, activechek, usertypenone);
    }

    @Test
    @Feature("Тестирование вкладки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User edit")
    public void userEdit() {

        //random word
        String word2 = String.valueOf(randomUtils.randomString());

        //создаю пользователя через апи и ищу его в таблице
        userPageObjects.userSearch(creationTestData.userCreate());
        //Ищу и нажимаю кнопку редактировать
        userPageComponents.clickActionButton().clickUserEditButton();
        //меняю значения на новые
        userPageObjects
                .clearUserCreationFields()
                .setUserSurname(String.valueOf(word2))
                .setUsetName(String.valueOf(word2))
                .setUserPatronymic(String.valueOf(word2))
                .setUserLogin(String.valueOf(word2))
                .setUserAdlogin(word2 + adlogin)
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
                word2 + adlogin,
                kadrid, email, activechek, usertypservice);
    }

    @Test
    @Feature("Тестирование вкладки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User delete")
    public void userDelete() {

        String user = creationTestData.userCreate();

        //создаю пользователя через апи и ищу его в таблице
        userPageObjects.userSearch(user);
        //Ищу и нажимаю кнопку удалить
        userPageComponents.clickActionButton().clickUserDeleteButton();
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //кликаю подтвердить
        userPageComponents.userDeleteSubmitButton();
        //ищу удаленного пользователя
        userPageObjects.userSearch(user);
        //убеждаюсь что список пуст
        userPageComponents.emptyTableCheck();
    }

    @Test
    @Feature("Тестирование вкладки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("User expert rights create")
    public void userExpertCreate() {

        String word = String.valueOf(randomUtils.randomString());


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
    @Feature("Тестирование вкладки пользователи")
    @Story("Создание, редактирование, удаление пользователя")
    @DisplayName("Temporary user create")
    public void temporaryUserCreate() {

        String word = String.valueOf(randomUtils.randomString());


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

    @Test
    @Feature("Тестирование вкладки пользователи")
    @Story("Выдача временных прав пользователю")
    @DisplayName("Temporary right for user create")
    public void temporaryRightCreate() {

        String user = creationTestData.userCreate();

        //создаю пользователя через апи и ищу его в таблице
        userPageObjects.userSearch(user);
        //выбираю чек-бокс пользователя
        userPageComponents.clickUserCheckbox();
        //Нажимаю кнопку Назначить временные права
        userPageComponents.clickTemporaryRightsButton();
        //Ввожу имя объекта в поле поиска
        userPageObjects.temporaryObjectSearch(object);
        //Выбираю первый чек-бокс в списке
        userPageComponents.clickObjectsCheckbox();
        //ввожу основание
        userPageComponents.reasonForm(reason, type, number, name, date);
        //Нажимаю применить временные права и проверяю сообщение об успешнй выдаче прав
        userPageComponents.clickSubmitButton();
        //Проверяю наличие записи о временной группе
        userPageComponents.firstLineGroupTableCheck();
        //Кликаю на временную группу в таблице
        userPageComponents.firstLineGroupTableClick();
        Selenide.switchTo().window(1); //переключаю вкладку
        //Проверяем что группа отображается на вкладке группы
        groupPageObjects.newGroupCheck(group);
        //Проверяем что в группу входит созданные пользователь
        groupPageComponents.firstLineUsersTableCheck(user);
    }
}

