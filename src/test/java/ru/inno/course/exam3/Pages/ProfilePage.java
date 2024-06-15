package ru.inno.course.exam3.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.course.exam3.helpers.PropsHelper;

import java.time.Duration;
import java.util.List;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openProfilePage() {
        driver.get(PropsHelper.getProfileUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public String getEmptyData() {
        return driver.findElement(By.cssSelector(".rt-noData")).getText();
    }

    public List<WebElement> getListOfBooks() {
        return driver.findElements(By.cssSelector("div.rt-td img"));
    }

    public void deleteAllBooks() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Delete All Books\"]")));
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        btn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"closeSmallModal-ok\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
    }

    public void setTenRowsOnPage() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.select-wrap.-pageSizeOptions")));
        btn.click();
        driver.findElement(By.cssSelector(".-pageSizeOptions > select > option:nth-child(2)")).click();
    }

    public void refreshPage() {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
