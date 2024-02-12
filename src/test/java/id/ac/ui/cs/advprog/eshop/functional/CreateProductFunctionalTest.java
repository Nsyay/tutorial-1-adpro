package id.ac.ui.cs.advprog.eshop.functional;

import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void productListPage_isCorrect(ChromeDriver driver) throws Exception {
        String productListUrl = baseUrl + "/product/list";
        driver.get(productListUrl);

        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);
    }

    @Test
    void createPage_isCorrect(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/create";
        driver.get(createUrl);

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/list";
        driver.get(createUrl);
        driver.findElement(By.id("create")).click();

        String productName = "Croissant";
        String productQuantity = "8";

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        nameInput.sendKeys(productName);
        quantityInput.sendKeys(productQuantity);
        submitButton.click();

        WebElement productListTable = driver.findElement(By.className("table"));
        String pageSource = productListTable.getText();

        assertTrue(pageSource.contains("Croissant"));
        assertTrue(pageSource.contains("8"));
    }
}