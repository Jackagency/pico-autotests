package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:properties/${runType}.properties"})
public interface WebDriverConfig extends Config {

    @Key("browserName")
    @DefaultValue("Chrome")
    String browserName();

    @Key("browserVersion")
    String browserVersion();

    @Key("browserSize")
    String browserSize();

    @Key("baseUrl")//читаем значение
    @DefaultValue("http://192.168.151.19:8080/")//обрабатываем дефолтное
    String getBaseUrl();//конвертируем результат

}
