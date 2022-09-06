import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.StringAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;


public class createNewStudent {
   private WebDriver driver;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("InternetExplorer")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    @Test
    public void inputForm() {
        Assertions.assertEquals("ToolsQA", driver.getTitle());

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("First Name");
        firstName.getText();

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Last Name");
        lastName.getText();

        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("userEmail@mail.ru");

        WebElement gender = driver.findElement(By.cssSelector("label[class='custom-control-label'][for='gender-radio-2']"));
        gender.click();

        WebElement userNumber = driver.findElement(By.id("userNumber"));
        userNumber.sendKeys("9232491349");

        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        driver.findElement(By.cssSelector("div.react-datepicker__month-dropdown-container.react-datepicker__month-dropdown-container--select > select > option:nth-child(8)")).click();
        driver.findElement(By.cssSelector("option:nth-child(91)")).click();
        driver.findElement(By.cssSelector("div.react-datepicker__day.react-datepicker__day--018.react-datepicker__day--weekend")).click();
        driver.findElement(By.id("dateOfBirthInput"));

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        subjects.sendKeys("Maths");
        driver.findElement(By.id("subjectsInput")).sendKeys(Keys.RETURN);

        WebElement hobbies = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3']"));
        hobbies.click();

        File file = new File("./src/test/img/3184696.png");
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        selectPictureButton.sendKeys(file.getAbsolutePath());

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("currentAddress");
        driver.findElement(By.id("react-select-3-input")).sendKeys("Hary");
        driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.RETURN);
        driver.findElement(By.id("react-select-4-input")).sendKeys("Kar");
        driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.RETURN);

        driver.findElement(By.id("submit")).sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#example-modal-sizes-title-lg")));




        SoftAssertions softAssert = new SoftAssertions();
            softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText()).isEqualTo("firstName");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)")).getText()).isEqualTo("userEmail");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(2)")).getText()).isEqualTo("gender");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2)")).getText()).isEqualTo("userNumber");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(5) > td:nth-child(2)")).getText()).isEqualTo("dateOfBirth");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(6) > td:nth-child(2)")).getText()).isEqualTo("subjects");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(7) > td:nth-child(2)")).getText()).isEqualTo("hobbies");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(2)")).getText()).isEqualTo("selectPictureButton");
        softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(9)> td:nth-child(2)")).getText()).isEqualTo("currentAddress");
       softAssert.assertThat(driver.findElement(By.cssSelector("tr:nth-child(10)> td:nth-child(2)")).getText()).isEqualTo("userEmail");

       softAssert.assertAll();
 }
    @AfterEach
    public void setDown() {
        driver.quit();
    }
}
