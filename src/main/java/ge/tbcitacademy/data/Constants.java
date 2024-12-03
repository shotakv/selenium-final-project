package ge.tbcitacademy.data;

public class Constants {
    //URLS
    public static final String SWOOP_MAIN_PAGE_URL = "https://swoop.ge/",
            SWOOP_CARTING_PAGE_URL = "https://swoop.ge/category/2058/sporti/kartingi/",
            CARTING_TEXT = "კარტინგი",
            BROWSER_NOT_SUPPORTED_TEXT = "The browser is not supported";

    //Locators
    public static final String PAGE_COUNT_XPATH = "//div[contains(@class,'flex justify-center items-center w-9 h-9')][last()]",
            LIST_OF_OFFER_PRICES_XPATH = "//a[contains(@class,'group flex flex-col')]//h4[@weight='bold']",
            SORTING_ELEMENT_XPATH = "//button[contains(@class,'flex items-center gap-2')]",
            HOLIDAY_CATEGORY_XPATH = "//a[@href='/category/24/dasveneba/']",
            MOUNTAIN_RESORT_XPATH = "//a[@href='/category/2045/dasveneba/mtis-kurortebi/']",
            PAY_IN_FULL_RADIO_BUTTON_ID = "radio-გადახდის ტიპი-1",
            CATEGORY_ELEMENT_XPATH = "//button[contains(@class,'text-sm bg-white border disabled')]",
            SPORTS_ELEMENT_XPATH = "//div[@class='flex flex-col min-w-80 py-6']//a[@href='/category/110/sporti/']",
            CARTING_ELEMENT_XPATH = "//div[@class='flex flex-col min-w-80 p-6 gap-6']//a[@href='/category/2058/sporti/kartingi/']",
            CATEGORY_CHAIN_ELEMENT_XPATH = "//nav[contains(@class,'flex items-center flex-nowrap')]",
            SWOOP_LOGO_ELEMENT_XPATH = "//a[contains(@class,'cursor-pointer flex items-center py-2 pr-3')]",
            MOVIES_CATEGORY_XPATH = "//a[@href='/movies/']",
            CAVEA_EAST_POINT_FILTER_XPATH = "//p[text()='კავეა ისთ ფოინთი']//preceding-sibling::input",
            CAVEA_EAST_POINT_CONTENT_XPATH = "//div[contains(.,'კავეა ისთ ფოინთი') and contains(@class,'flex  justify-start items-start flex-col laptop')]",
            FREE_LEGEND_CHART_XPATH = "//div[@class='w-2.5 h-2.5 rounded-full bg-primary_green-100-value']",
            FREE_SEAT_SVG_COLOR_XPATH = "//div[@class='cursor-pointer ']/*[name()='svg']/*[name()='path'][starts-with(@d,'M30.1463')]";

    //Strings
    public static final String INVALID_EMAIL_TEXT = "invalid@mail@com",
            FAKE_NUMBER_STARTING_TEXT = "592",
            SAMPLE_CODE_TEXT = "1234",
            EXPECT_MAIL_ERROR = "ჩაწერე ელფოსტა",
            SAMPLE_BIRTH_YEAR_TEXT = "2000",
            PRICE_DESCENDING_TEXT = "ფასით კლებადი",
            PRICE_ASCENDING_TEXT = "ფასით ზრდადი",
            PRICE_RANGE_TEXT = "200₾ - 300₾",
            PRICE_RANGE_FROM_TEXT = "დან",
            PRICE_RANGE_TO_TEXT = "მდე",
            COLOR_MISMATCH_TEXT = "Provided colors are not the same";

}
