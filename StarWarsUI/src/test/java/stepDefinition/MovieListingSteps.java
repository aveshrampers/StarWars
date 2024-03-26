package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MovieListingPage;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class MovieListingSteps {
    WebDriver driver;
    MovieListingPage movieListingPage;

    @Before
    public void launchBrowser() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-dev-shm-usage",
                "--remote-debugging-pipe",
                "--headless",
                "--no-sandbox",
                "--allow-insecure-localhost"
        );
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        try {
            driver.get("http://localhost:3000/");
        } catch (Exception e) {
            System.out.println("Driver error " + e);
        }

        movieListingPage = new MovieListingPage(driver);
    }

    @When("^I click the \"([^\"]*)\" header$")
    public void Click_On_Header(String header) {
        this.movieListingPage.Click_Title_Header(header);
    }

    @Then("^the last movie in the list should be \"([^\"]*)\"$")
    public void Verify_Last_Movie(String title) {
        this.movieListingPage.Verify_Last_Movie_Title(title);
    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()){
            Allure.addAttachment("Failed screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driver.quit();
    }
}
