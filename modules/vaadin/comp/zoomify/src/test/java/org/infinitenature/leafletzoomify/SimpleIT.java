package org.infinitenature.leafletzoomify;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.TListUi;
import org.vaadin.addonhelpers.automated.*;

import java.util.List;

public class SimpleIT extends AbstractWebDriverCase {

    @Test
    public void checkAllTestsOpenWithoutErrors() throws IOException, AssertionError {
        startBrowser();

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        
        List<TListUi.TestDetails> listTestClasses = TListUi.listTestClasses();
        for (TListUi.TestDetails td : listTestClasses) {
			Class clazz = td.getClazz();
			
			driver.get(BASEURL + clazz.getName() + "?debug");

			new WebDriverWait(driver, 30).until(VaadinConditions.ajaxCallsCompleted());

			try {
				WebElement error = driver.findElement(By.className("v-Notification-error"));
				Assert.fail("Test " + clazz.getName() + " has client side exception");
			} catch (NoSuchElementException e) {
				continue;
			}
			
		}
        
    }

	@Override
	protected void startBrowser() {
		startBrowser(new PhantomJSDriver());
	}

}