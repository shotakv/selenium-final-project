import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.util.BaseTest;
import ge.tbcitacademy.util.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class HolidayPageTests extends BaseTest {
    @Test
    public void descendingOrderTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);

        WebElement holidayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_CATEGORY_XPATH)));
        js.executeScript("arguments[0].click();", holidayElement);
        List<Double> listOfOffers = Helper.collectAllOffers(driver,wait,js);
        listOfOffers.sort(Collections.reverseOrder());

        Double firstSortedPrice = Helper.sortAndQueryFirstOfferPrice(driver,wait,js,Constants.PRICE_DESCENDING_TEXT);
        Assert.assertEquals(firstSortedPrice,listOfOffers.get(0));
    }

    @Test
    public void ascendingOrderTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);

        WebElement holidayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_CATEGORY_XPATH)));
        js.executeScript("arguments[0].click();", holidayElement);
        List<Double> listOfOffers = Helper.collectAllOffers(driver,wait,js);
        Collections.sort(listOfOffers);
        Double firstSortedPrice = Helper.sortAndQueryFirstOfferPrice(driver,wait,js,Constants.PRICE_ASCENDING_TEXT);
        Assert.assertEquals(firstSortedPrice,listOfOffers.get(0));
    }

    @Test
    public void filterTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);

        WebElement holidayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_CATEGORY_XPATH)));
        js.executeScript("arguments[0].click();", holidayElement);
        WebElement mountainResortElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MOUNTAIN_RESORT_XPATH)));
        js.executeScript("arguments[0].click();", mountainResortElement);
        WebElement payInFullRadioButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Constants.PAY_IN_FULL_RADIO_BUTTON_ID))); //first presence
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Constants.PAY_IN_FULL_RADIO_BUTTON_ID))); // then clickable
        try { // this is common place where staleElementException occurred
            js.executeScript("arguments[0].scrollIntoView(true);", payInFullRadioButtonElement);
            js.executeScript("arguments[0].click();", payInFullRadioButtonElement);
        } catch (StaleElementReferenceException e) {
            payInFullRadioButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Constants.PAY_IN_FULL_RADIO_BUTTON_ID)));   // Re-locate the element
            js.executeScript("arguments[0].click();", payInFullRadioButtonElement);
        }
        WebElement payInFullTextElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='სრული გადახდა']")));
        Assert.assertTrue(payInFullTextElement.isDisplayed());
        List<Double> listOfOffers = Helper.collectAllOffers(driver,wait,js);
        Collections.sort(listOfOffers);
        Double firstSortedPrice = Helper.sortAndQueryFirstOfferPrice(driver,wait,js,Constants.PRICE_ASCENDING_TEXT);
        Assert.assertEquals(firstSortedPrice,listOfOffers.get(0));

    }

    @Test
    public void priceRangeTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);

        WebElement holidayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.HOLIDAY_CATEGORY_XPATH)));
        js.executeScript("arguments[0].click();", holidayElement);
        WebElement priceRangeElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='radio' and contains(.,'"+ Constants.PRICE_RANGE_TEXT + "')]")));
        js.executeScript("arguments[0].click();", priceRangeElement);

        WebElement rangeLowLimitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='"+ Constants.PRICE_RANGE_FROM_TEXT + "']//following-sibling::input")));
        WebElement rangeHighLimitElement = driver.findElement(By.xpath("//p[text()='" + Constants.PRICE_RANGE_TO_TEXT + "']//following-sibling::input"));

        String lowLimitString = rangeLowLimitElement.getAttribute("value");

        String highLimitString = rangeHighLimitElement.getAttribute("value");

        int lowLimit = Integer.parseInt(lowLimitString);
        int highLimit = Integer.parseInt(highLimitString);


        List<Double> listOfOffers = Helper.collectAllOffers(driver,wait,js);

        Assert.assertTrue(listOfOffers.stream().allMatch(price -> price >= lowLimit && price <= highLimit));

    }
}
