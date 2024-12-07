import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.util.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTests extends BaseTest {
    @Test
    public void activeCategoryTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);
        WebElement categoryElement = driver.findElement(By.xpath(Constants.CATEGORY_ELEMENT_XPATH));
        categoryElement.click();
        WebElement sportsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.SPORTS_ELEMENT_XPATH)));
        actions.moveToElement(sportsElement).perform();
        WebElement cartingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CARTING_ELEMENT_XPATH)));
        js.executeScript("arguments[0].click();", cartingElement);
        WebElement categoryChainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CATEGORY_CHAIN_ELEMENT_XPATH))); // we could use By.tagName here but if in the future new nav bar is added, our code becomes irrelevant
        Assert.assertEquals(driver.getCurrentUrl(),Constants.SWOOP_CARTING_PAGE_URL);
        Assert.assertTrue(categoryChainElement.getText().contains(Constants.CARTING_TEXT));
    }

    @Test
    public void logoTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);
        WebElement relaxationElement = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(Constants.HOLIDAY_CATEGORY_XPATH))));
        js.executeScript("arguments[0].click();", relaxationElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='დასვენება']")));
        WebElement swoopLogoElement = driver.findElement(By.xpath(Constants.SWOOP_LOGO_ELEMENT_XPATH));
        js.executeScript("arguments[0].click();", swoopLogoElement);
        wait.until(ExpectedConditions.urlToBe(Constants.SWOOP_MAIN_PAGE_URL));
        Assert.assertEquals(driver.getCurrentUrl(),Constants.SWOOP_MAIN_PAGE_URL);
    }
}
