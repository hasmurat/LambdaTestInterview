package part2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Automation {

    WebDriver driver;
    HomePageActions homePageActions = new HomePageActions();

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    //This class includes locators of WebPage
    class HomePage{
        public final String BASE_URL = "https://www.lambdatest.com/capabilities-generator/";
        public final By OS_BROWSER_BUTTON = By.xpath("(//button[contains(text(),'OS/Browser Configuration')])[2]");
        public final By WINDOWS_BUTTON = By.xpath("(//button//span[contains(text(),'Windows')])[2]");
        public final By ADVANCED_CONF_BUTTON = By.xpath("//button[contains(text(),'ADVANCED CONFIGURATION')]");
        public final By VIDEO_RECORDER_SLIDE = By.xpath("//div[contains(text(),'Video Recording')]//span");
        public final By CONSOLE = By.cssSelector(".text-code");

    }

    //This class includes needed methods of WebPage
    class HomePageActions extends HomePage{


        HomePageActions navigateToURL (){
            driver.get(BASE_URL);
            return this;
        }
        HomePageActions selectOS(String os){
            driver.findElement(WINDOWS_BUTTON).click();
            driver.findElement(By.xpath("(//span[contains(text(),'"+os+"')])[2]")).click();
            return this;
        }
        HomePageActions selectBrowserType(String browser){
            driver.findElement(By.xpath("(//span[contains(text(),'"+browser+"')])[4]")).click();
            return this;
        }
        HomePageActions selectChromeVersion(String version){
            driver.findElement(By.xpath("(//span[contains(text(),'"+version+"')])[2]")).click();
            return this;
        }
        HomePageActions selectResolution(String resolution){
            driver.findElement(By.xpath("(//span[4][text()='"+resolution+"'])[2]")).click();
            return this;
        }
        HomePageActions clickAdvanceConf(){
            try{
                driver.findElement(OS_BROWSER_BUTTON).click();
                Thread.sleep(2000);
                driver.findElement(ADVANCED_CONF_BUTTON).click();
            }catch (Exception e){
                driver.findElement(ADVANCED_CONF_BUTTON).click();
            }

            return this;
        }
        HomePageActions selectNetworkLog(String bool){
            driver.findElement(By.xpath("//label[@for='network"+bool+"']")).click();
            return this;
        }
        HomePageActions clickVideoRecSlider(){
            driver.findElement(VIDEO_RECORDER_SLIDE).click();
            return this;
        }
        HomePageActions getTextConsole(){
            System.out.println(driver.findElement(CONSOLE).getText());
            return this;
        }

    }


    @Test
    public void test(){
        homePageActions.navigateToURL()
                .selectOS("Windows 10")
                .selectBrowserType("Chrome")
                .selectChromeVersion("99.0")
                .selectResolution("1366x768")
                .clickAdvanceConf()
                .selectNetworkLog("True")
                .clickVideoRecSlider()
                .getTextConsole();
    }


}
