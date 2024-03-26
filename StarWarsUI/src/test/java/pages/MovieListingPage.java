package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class MovieListingPage {

    public MovieListingPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindBy(how = How.XPATH, using = "//tr/th[contains(.,'Title')]")
    WebElement title;

    @FindBy(how = How.XPATH, using = "//tr/th[contains(.,'Episode')]")
    WebElement episode;
    @FindAll(@FindBy(how = How.CSS, using = "td a"))
    List<WebElement> title_names;

    public void Open_Homepage() throws InterruptedException {
        Thread.sleep(5000);
    }

    public void Click_Title_Header(String header) {
        int retry = 0;
        try {
            switch (header) {
                case "Title":
                    this.title.click();
                    break;
                case "Episode":
                    do {
                        this.episode.click();
                        retry += 1;
                    } while (retry < 2);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Element exception " + e);
        }
    }

    public void Verify_Last_Movie_Title(String title) {
        try {
            WebElement last = title_names.getLast();
            System.out.println(last.getText());
            Assert.assertEquals(title, last.getText());
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }
}