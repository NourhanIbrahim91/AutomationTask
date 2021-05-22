package cucumber.examples;

import com.google.common.io.Files;
import dataProvider.ConfigFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyStepdefs {
    ConfigFileReader configFileReader;
    WebDriver driver ;

    @Before()
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Given("I am in the search page of google images")
    public void iAmInTheSearchPageOfGoogleImages() {
        driver.get("https://www.google.pl/imghp?hl=pl&ogbl");
        driver.manage().window().maximize();
        driver.findElement(By.id("L2AGLb")).click();
    }
    @When("I enter {string}")
    public void iEnter(String keyword) {
       keyword="flower image";
      driver.findElement(By.xpath("//*[@id=\"sbtc\"]/div/div[2]/input")).sendKeys(keyword);
      driver.findElement(By.className("Tg7LZd")).click();
    }

    @Then("I should visit the specified result in the configuration file")
    public void iShouldVisitTheSpecifiedResultInTheConfigurationFile() {
        configFileReader= new ConfigFileReader();
        List<WebElement> images=driver.findElements(By.cssSelector("img.rg_i.Q4LuWd"));
        System.out.println(images.size());
        images.get(configFileReader.getImgIndex()).click();
        System.out.println(images.get(configFileReader.getImgIndex()));
        Assert.assertTrue(driver.getTitle().contains("flower"));
    }

    @After()
        public void takeScreenshot(){

            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);

            try{
                Files.move(screenshot, new File("./resources/screenshots/test.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
            driver.quit();
        }


}
