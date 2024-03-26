package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MovieDescriptionPage;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class MovieDescriptionSteps {
    WebDriver driver;
    MovieDescriptionPage movieDescriptionPage;

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

        movieDescriptionPage = new MovieDescriptionPage(driver);
    }

    @Given("^I am on the movie listing page$")
    public void Navigate() throws InterruptedException {
        Thread.sleep(5000);
    }

    @When("^I navigate to the movie details page for \"([^\"]*)\"$")
    public void iNavigateToTheMovieDetailsPageFor(String arg0) throws InterruptedException {
        Thread.sleep(20000);
        this.movieDescriptionPage.Navigate_To_Title_Description(arg0);
    }

    @Then("^I should see \"([^\"]*)\" listed$")
    public void iShouldSeeListed(String arg0) {
        this.movieDescriptionPage.Assert_Species(arg0);
    }

    @Then("^I should not see the planet \"([^\"]*)\" listed$")
    public void iShouldNotSeeThePlanetListed(String arg0) {
        this.movieDescriptionPage.Assert_Planet(arg0);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()){
            Allure.addAttachment("Failed screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driver.quit();
    }
}
