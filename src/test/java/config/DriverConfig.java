package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverConfig {
    public static void configure() {
        Configuration.browser = Project.webConfig.browserName();
        Configuration.browserVersion = Project.webConfig.browserVersion();
        Configuration.browserSize = Project.webConfig.browserSize();
        Configuration.baseUrl = Project.webConfig.getBaseUrl();


        DesiredCapabilities capabilities = new DesiredCapabilities();


        Configuration.browserCapabilities = capabilities;

    }
}
