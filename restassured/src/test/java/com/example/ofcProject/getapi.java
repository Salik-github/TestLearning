package com.example.ofcProject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class getapi {

    String FilePath = "src\\test\\java\\com\\example\\resources\\lIVEdata.xlsx";

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    int rowIndex = 1;
    ConcurrentMap<String, Double> timestamp = new ConcurrentHashMap<>();
    WebDriver driver;

    String student  ="//span[text()='Students']";
    String taskxpath = "//span[text()='Tasks']";
    String courseSearch = "//span[text()='Course Search']";
    String applicatoin = "//span[text()='Applications']";
    String university = "//span[text()='Universities']";
    String course = "//span[text()='Courses']";
    String Employee = "//span[text()='Employees']";
    String Associate = "//span[text()='Associates']";
    String AllTask ="//label[text()=' All Task ']";
    String CourseSearchAll = "//button[contains(@class,'border-radius-circle')]";
    String listTask ="//label[@title='List']";
    String commission = "//span[text()=' Commission']";
    String latestactivity  ="//a[text()='Latest Activities']";
  

    @Test
    public void method() throws InterruptedException, IOException {
        // Create directories for the file path

        // Initialize workbook and sheet
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Dyn");

        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set up DevTools
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        createExcelHeader();
        // Capture Request Start Time
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String requestID = request.getRequestId().toString();
            double requestTime = System.currentTimeMillis();
            timestamp.put(requestID, requestTime);
        });

        // Capture Response and Calculate Time
        devTools.addListener(Network.responseReceived(), response -> {
            String responseID = response.getRequestId().toString();
            String url = response.getResponse().getUrl();
            int statusCode = response.getResponse().getStatus();
            double responseTime = System.currentTimeMillis();

            if (url.contains("crm.entrily.com/api")) {
                if (timestamp.containsKey(responseID)) {
                    double requestStartTime = timestamp.get(responseID);
                    double timeTaken = responseTime - requestStartTime;
                    writeToExcel(url, statusCode, timeTaken);
                    System.out.println(url + " : " + statusCode + " : " + timeTaken);
                }
            }
        });

        // Navigate and Perform Actions
       // driver.get("https://demoentrilycrm.gaipp.com/signin");
        driver.get("https://crm.entrily.com/signin");
        driver.findElement(By.id("user")).sendKeys("admission@entrily.com");
        driver.findElement(By.id("password")).sendKeys("xIt7JneyTItfki4AVZQr9Q==");
        driver.findElement(By.id("submit")).click();

        Thread.sleep(9000);
        // Wait for the "Students" element and click
        // List<WebElement> elements = driver.findElements(By.xpath(""));
        // new WebDriverWait(driver, Duration.ofSeconds(10))
        //         .until(ExpectedConditions.elementToBeClickable(elements.get(1)));
        // elements.get(1).click();

        setelement();

        // Save and Close Workbook
        saveWorkbook();
        driver.quit();
    }
    public void datafilters() throws InterruptedException
    {
        WebElement dateFilter = driver.findElement(By.id("dropdownMenuButton4"));
        dateFilter.click();
        WebElement AllFilter  = driver.findElement(By.xpath("//a[text()='All']"));
        AllFilter.click();
        Thread.sleep(10000);
    }
    public void selectElement(WebElement sElement)
    {
        Select selE = new Select(sElement);
        selE.selectByIndex(0);
    }
    public void setelement() throws InterruptedException {
        // WebElement dateFilter = driver.findElement(By.id("dropdownMenuButton4"));
        // WebElement AllFilter  = driver.findElement(By.xpath("//a[text()='All']"));
    
        WebElement dateFilter = driver.findElement(By.id("drop-down-menu"));
        dateFilter.click();
        WebElement AllFilter  = driver.findElement(By.xpath("//a[text()='All']"));
        AllFilter.click();
        Thread.sleep(20000);
       
        getSingleElement(latestactivity);
        getElement(student);
        datafilters() ;
        getElement(taskxpath);
        getSingleElement(AllTask);
        getSingleElement(listTask);
        getElement(courseSearch);
        getElement(CourseSearchAll);
        getElement(applicatoin);
        datafilters() ;
       

        getElement(university);
        getElement(Employee);
        getElement(Associate);
        getElement(course);
        getElement(commission);
    }

    public void clickElement(WebElement clickElement) {
        clickElement.click();
    }
    public void getSingleElement(String xpatht) 
    {
      WebElement e = driver.findElement(By.xpath(xpatht));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(e));
        clickElement(e);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void getElement(String xpatht) throws InterruptedException {

        List<WebElement> e = driver.findElements(By.xpath(xpatht));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(e.get(1)));
        clickElement(e.get(1));
        Thread.sleep(20000);

    }

    public void createExcelHeader() {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("URL");
        row.createCell(1).setCellValue("status");
        row.createCell(2).setCellValue("time in Ms");
        row.createCell(3).setCellValue("Loader");
    }

    public synchronized void writeToExcel(String url, int status, double time) {
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(url);
        row.createCell(1).setCellValue(status);
        row.createCell(2).setCellValue(time);
        if (time > 2500) {
            row.createCell(3).setCellValue("More Time");
        }

    }

    public void saveWorkbook() throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(FilePath)) {
            workbook.write(fileOut);
            System.out.println("Data saved to Excel successfully." + Paths.get(FilePath).toAbsolutePath());
        } catch (Exception e) {

            System.out.println(e);
        }
        workbook.close();
    }
}
