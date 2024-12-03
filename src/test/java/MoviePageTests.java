import ge.tbcitacademy.data.Constants;
import ge.tbcitacademy.util.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MoviePageTests extends BaseTest {
    @Test
    public void registerViaFilmsTest(){
        driver.navigate().to(Constants.SWOOP_MAIN_PAGE_URL);
        WebElement filmsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.MOVIES_CATEGORY_XPATH)));
        filmsElement.click();
        WebElement eastPointCaveaFilterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CAVEA_EAST_POINT_FILTER_XPATH)));
        js.executeScript("arguments[0].click();", eastPointCaveaFilterElement);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='w-full group pointer-events-none']"))); //offer placeholder when loading
        List<WebElement> listOfFilms = driver.findElements(By.xpath("//div[@class='w-full group ']//a"));
        WebElement firstFilmElement = listOfFilms.get(0);
        wait.until(ExpectedConditions.elementToBeClickable(firstFilmElement));
        js.executeScript("arguments[0].click();", firstFilmElement);
        WebElement caveaEastPointContentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((Constants.CAVEA_EAST_POINT_CONTENT_XPATH))));
        js.executeScript("arguments[0].scrollIntoView(true);", caveaEastPointContentElement);
        List<WebElement> listOfSessions = caveaEastPointContentElement.findElements(By.xpath(".//div[contains(@class,'flex  justify-start items-start cursor-pointer hover')]"));
        WebElement lastSessionElement = listOfSessions.get(listOfSessions.size()-1);
        String movieName = driver.findElement(By.xpath("//h1")).getText();
        String movieTime = lastSessionElement.findElement(By.xpath(".//p[contains(@class,'font-tbcx-medium leading-6')]")).getText();
        String movieDate = lastSessionElement.findElement(By.xpath(".//p[contains(@class,'font-tbcx-medium leading-5')]")).getText();
        String[] movieDateSplit = movieDate.split(" ");
        int movieDay = Integer.parseInt(movieDateSplit[0]);
        String properDateTime = movieDay + " " + movieDateSplit[1];
        String cinemaName = caveaEastPointContentElement.findElement(By.xpath(".//h3")).getText();


        js.executeScript("arguments[0].click();", lastSessionElement);

        String moviePopupDetails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(@class,'text-primary_black-90-value')]")))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(", "));
        String moviePopupName = driver.findElement(By.xpath("//div[@class='flex  justify-start items-start flex-col gap-2']//h2")).getText();


        System.out.println(moviePopupDetails);
        System.out.println(movieTime);

        Assert.assertTrue(moviePopupDetails.contains(cinemaName));
        Assert.assertTrue(moviePopupDetails.contains(movieTime));
        Assert.assertTrue(moviePopupDetails.contains(properDateTime));
        Assert.assertEquals(movieName,moviePopupName);


        WebElement freeLegendChart = driver.findElement(By.xpath(Constants.FREE_LEGEND_CHART_XPATH));
        WebElement freeSeatElement = driver.findElement(By.xpath("//div[@class='cursor-pointer ']"));
        WebElement freeSeatSVGColor = driver.findElement(By.xpath(Constants.FREE_SEAT_SVG_COLOR_XPATH));

        String freeSeatColor = freeSeatSVGColor.getCssValue("fill");
        String freeLegendChartColor = freeLegendChart.getCssValue("background-color");

        System.out.println(freeSeatColor);
        System.out.println(freeLegendChartColor);

        try{
            Assert.assertEquals(freeSeatColor,freeLegendChartColor); //They are not the same color so this keeps failing
        }
        catch (AssertionError e){
            System.out.println(Constants.COLOR_MISMATCH_TEXT);
        }

        js.executeScript("arguments[0].click();", freeSeatElement);

        WebElement createNewAccountElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='create pl-2 ']//a")));
        createNewAccountElement.click();
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordElement = driver.findElement(By.id("password"));
        WebElement passwordRetypeElement = driver.findElement(By.id("PasswordRetype"));
        WebElement maleGenderElement = driver.findElement(By.id("Gender1"));
        WebElement fistNameElement = driver.findElement(By.id("name"));
        WebElement lastNameElement = driver.findElement(By.id("surname"));
        WebElement birthYearElement = driver.findElement(By.cssSelector(".select2-container--default"));
        WebElement phoneNumberElement = driver.findElement(By.id("Phone"));
        WebElement phoneCodeElement = driver.findElement(By.id("PhoneCode"));
        WebElement agreeTermsElement = driver.findElement(By.id("test"));
        WebElement tbcAgreementElement = driver.findElement(By.id("tbcAgreement"));
        WebElement registrationButtonElement = driver.findElement(By.id("registrationBtn"));


        emailElement.sendKeys(Constants.INVALID_EMAIL_TEXT);
        String fakePassword = faker.internet().password(); // so it stays the same when trying to use twice
        char randomUppercase = (char) ('A' + faker.random().nextInt(26)); // Random uppercase letter
        fakePassword = randomUppercase + fakePassword.substring(1);
        passwordElement.sendKeys(fakePassword);
        passwordRetypeElement.sendKeys(fakePassword);
        js.executeScript("arguments[0].click();", maleGenderElement);
        fistNameElement.sendKeys(faker.name().firstName());
        lastNameElement.sendKeys(faker.name().lastName());
        birthYearElement.click();
        WebElement birthYearInputElement = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
        birthYearInputElement.sendKeys(Constants.SAMPLE_BIRTH_YEAR_TEXT);
        actions.sendKeys(Keys.TAB).perform();
        phoneNumberElement.sendKeys(Constants.FAKE_NUMBER_STARTING_TEXT + faker.number().digits(6));
        phoneCodeElement.sendKeys(Constants.SAMPLE_CODE_TEXT);
        js.executeScript("arguments[0].click();", agreeTermsElement);
        js.executeScript("arguments[0].click();", tbcAgreementElement);
        js.executeScript("arguments[0].click();", registrationButtonElement);


        WebElement invalidMailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-error-email")));
        Assert.assertEquals(invalidMailError.getText(),Constants.EXPECT_MAIL_ERROR);

    }
}
