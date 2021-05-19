package com.fetch.tests;

import com.fetch.utils.BrowserFactory;
import com.fetch.utils.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

    public class fetchAssessment {

        Logger logger = LoggerFactory.getLogger(fetchAssessment.class);
        private WebDriver driver;


        @BeforeMethod
        public void setup() {
            driver = BrowserFactory.getDriver("chrome");
            driver.manage().window().maximize();
            driver.get(ConfigurationReader.getProperty("url"));

        }

        @Test(description = "Verify page title")
        public void test1() {
            String expected = "React App";
            String actual = driver.getTitle();
            Assert.assertEquals(actual, expected);
        }

        @Test(description = "positive test")
        public void test2() throws InterruptedException {
            logger.info("Test started!!!");
            List<WebElement> coinButtons = driver.findElements(By.xpath("//button[@class='square']"));
            WebElement left1 = driver.findElement(By.id("left_0"));
            WebElement right1 = driver.findElement(By.id("right_0"));

            for (int i = 0; i < coinButtons.size(); i++) {

                int coin1 = i;
                int coin2 = i + 1;

                waitT(500L);
                left1.sendKeys((coin1 + ""));
                waitT(500L);
                right1.sendKeys((coin2 + ""));
                waitT(500L);

                WebElement weigh = driver.findElement(By.id("weigh"));
                weigh.click();
                waitT(2000L);

                WebElement icon = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/button[1]"));
                WebElement numberLeft = driver.findElement(By.xpath("//button[@id='coin_" + i + "']"));
                WebElement numberRight = driver.findElement(By.xpath("//button[@id='coin_" + (i + 1) + "']"));

                List<WebElement> list = driver.findElements(By.tagName("li"));
                if (icon.getText().contains(">")) {
                    logger.info("FAKE GOLD BAR : {}", coin2);

                    numberRight.click();
                    waitT(1000L);

                    String text = driver.switchTo().alert().getText();
                    logger.info(text);
                    driver.switchTo().alert().accept();
                    logger.info((i + 1) + ". " + list.get(i).getText());
                    waitT(8000L);
                    break;
                } else if (icon.getText().contains("<")) {
                    logger.info("FAKE GOLD BAR :" + coin1);

                    numberLeft.click();
                    waitT(1000L);

                    String text = driver.switchTo().alert().getText();
                    logger.info(text);
                    driver.switchTo().alert().accept();
                    logger.info((i + 1) + ". " + list.get(i).getText());
                    waitT(8000L);
                    break;
                }
                WebElement reset = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
                reset.click();
                waitT(1000L);

                logger.info((i + 1) + ". " + list.get(i).getText());
            }
        }

        @Test(description = "negative test")
        public void test3() throws InterruptedException {
            logger.info("Test started!!!");
            List<WebElement> coinButtons = driver.findElements(By.xpath("//button[@class='square']"));
            WebElement left1 = driver.findElement(By.id("left_0"));
            WebElement right1 = driver.findElement(By.id("right_0"));

            for (int i = 0; i < coinButtons.size(); i++) {

                int coin1 = i;
                int coin2 = i + 1;

                waitT(500L);
                left1.sendKeys((coin1 + ""));
                waitT(500L);
                right1.sendKeys((coin2 + ""));
                waitT(500L);

                WebElement weigh = driver.findElement(By.id("weigh"));
                weigh.click();
                waitT(2000L);

                WebElement icon = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[2]/button[1]"));
                WebElement numberLeft = driver.findElement(By.xpath("//button[@id='coin_" + i + "']"));
                WebElement numberRight = driver.findElement(By.xpath("//button[@id='coin_" + (i + 1) + "']"));

                List<WebElement> list = driver.findElements(By.tagName("li"));
                if (icon.getText().contains(">")) {
                    logger.info("FAKE GOLD BAR : {}", coin2);

                    numberLeft.click();
                    waitT(1000L);

                    String text = driver.switchTo().alert().getText();
                    logger.info(text);
                    driver.switchTo().alert().accept();
                    logger.info((i + 1) + ". " + list.get(i).getText());
                    waitT(8000L);
                    break;
                } else if (icon.getText().contains("<")) {
                    logger.info("FAKE GOLD BAR :" + coin1);

                    numberRight.click();
                    waitT(1000L);

                    String text = driver.switchTo().alert().getText();
                    logger.info(text);
                    String actual= text;
                    String expected="Oops! Try Again!";
                    Assert.assertEquals(actual,expected);
                    driver.switchTo().alert().accept();
                    logger.info((i + 1) + ". " + list.get(i).getText());
                    waitT(8000L);
                    break;
                }
                WebElement reset = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
                reset.click();
                waitT(1000L);

                logger.info((i + 1) + ". " + list.get(i).getText());
            }


        }


        public void waitT(Long milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @AfterMethod
        public void teardown() {
            driver.quit();
        }



    }



