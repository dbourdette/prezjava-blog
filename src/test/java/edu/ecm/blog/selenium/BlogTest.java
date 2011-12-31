package edu.ecm.blog.selenium;

import org.junit.Assert;
import org.junit.Test;

import fr.javafreelance.fluentlenium.core.filter.FilterConstructor;
import fr.javafreelance.fluentlenium.core.test.FluentTest;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class BlogTest extends FluentTest {

    @Test
    public void home() {
        goTo("http://localhost:8080/");

        Assert.assertEquals("Mon blog", $(".title").first().getText());
    }

    @Test
    public void post() {
        goTo("http://localhost:8080/admin/post");

        $("#title").text("un titre selenium 2");
        $("#slug").text("un-titre-selenium-2");

        $("form").submit();

        goTo("http://localhost:8080/");

        find("a", FilterConstructor.withText("un titre selenium 2"));
    }
}
