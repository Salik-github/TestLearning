package com.example.ofcProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class alldata {
    
    @Test
    public void all() throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoentrilycrm.gaipp.com/signin");
        driver.findElement(By.id("user")).sendKeys("info.entrily@gmail.com");
        driver.findElement(By.id("password")).sendKeys("#Entrily@2024!");
        driver.findElement(By.id("submit")).click();
      
        Thread.sleep(5000);

        driver.findElements(By.xpath("//span[text()='Applications']")).get(1).click();
        Thread.sleep(5000);

    

    }
}
