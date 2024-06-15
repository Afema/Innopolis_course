package ru.inno.course.exam3.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.inno.course.exam3.helpers.PropsHelper;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get(PropsHelper.getLoginUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void setLogin() {
        driver.findElement(By.cssSelector("#userName")).sendKeys(PropsHelper.getLogin());
    }

    public void setPassword() {
        driver.findElement(By.cssSelector("#password")).sendKeys(PropsHelper.getPass());
    }

    public void clickLogin() {
        driver.findElement(By.cssSelector("#login")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void authorize(String log, String pass) {
        openLoginPage();
        setLogin();
        setPassword();
        clickLogin();
    }
}
