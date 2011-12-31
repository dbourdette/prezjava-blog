package edu.ecm.blog.selenium;

import javax.annotation.Nullable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class GoogleTest {
    private WebDriver webDriver;

    @Before
    public void init() {
        webDriver = new FirefoxDriver();
    }

    @After
    public void close() {
        webDriver.quit();
    }
    
    @Test
    public void search() throws InterruptedException {
        // naviguer vers google.fr
        webDriver.navigate().to("http://google.fr");

        // remplir le champs de recherche
        WebElement input = webDriver.findElement(By.name("q"));

        input.sendKeys("selenium");

        // post du formulaire contenant l'input
        input.submit();

        // on attends le chargement de la page
        WebElement link = new WebDriverWait(webDriver, 5).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(@Nullable WebDriver input) {
                // on doit trouver le lien vers le site
                return webDriver.findElement(By.partialLinkText("Web Browser Automation"));
            }
        });

        Assert.assertEquals("http://seleniumhq.org/", link.getAttribute("href"));
    }
}
