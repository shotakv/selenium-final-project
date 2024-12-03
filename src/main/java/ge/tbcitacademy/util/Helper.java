package ge.tbcitacademy.util;

import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Double> collectAllOffers(WebDriver driver, WebDriverWait wait, JavascriptExecutor js){
        List<Double> listOfOffers = new ArrayList<>();
        int pageCount = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.PAGE_COUNT_XPATH))).getText());
        for (int i = 1; i <= pageCount; i++) {
            List<WebElement> tempList = driver.findElements(By.xpath(Constants.LIST_OF_OFFER_PRICES_XPATH));
            tempList.forEach(webElement -> {
                String tempString = webElement.getText();
                listOfOffers.add(Double.parseDouble(tempString.substring(0, tempString.length() - 1)));
            });

            WebElement nextPageElement = driver.findElement(By.xpath("//img[@alt='right arrow']"));

            js.executeScript("arguments[0].click();", nextPageElement);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='right arrow']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'group flex flex-col')]"))); // waiting for the first offer to be clickable

        }
        return listOfOffers;
    }

    public static Double sortAndQueryFirstOfferPrice(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, String sortingMethod){
        WebElement sortingElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORTING_ELEMENT_XPATH)));
        sortingElement.click();
        WebElement sortPriceDescendingElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='cursor-pointer' and contains(.,'"+ sortingMethod + "')]")));
        sortPriceDescendingElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='right arrow']")));
        List<WebElement> sortedListOfOffers = driver.findElements(By.xpath("//a[contains(@class,'group flex flex-col')]//h4[@weight='bold']"));
        String firstSortedOfferPriceElement = sortedListOfOffers.get(0).getText();
        return Double.parseDouble(firstSortedOfferPriceElement.substring(0, firstSortedOfferPriceElement.length() - 1));
    }
}
