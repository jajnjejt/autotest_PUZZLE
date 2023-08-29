package autoTest.puzzle;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class datasetAddnew {
		
	//open the window for ADD DATASETS
	public static void openAddnew(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();	
		driver.quit();
	}
	
	//the button of add is initially disable in the window of ADD DATASETS
	public static void addDisable(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		boolean addNew = driver.findElement(By.xpath("//button[@type='submit']")).isEnabled();
		Assert.assertNotEquals(addNew, true);	
		driver.quit();
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseCatagory(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'分類資料集')]")).click();	
		driver.quit();	
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseEntity(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'實體資料集')]")).click();
		driver.quit();
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseOCR(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'OCR資料集')]")).click();
		driver.quit();	
	}
	
	public static void emptyWarn(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("   ");
		String warning = driver.findElement(By.xpath("//stai-error[contains(text(),'請輸入資料集名稱')]")).getText();
		Assert.assertEquals(warning, "請輸入資料集名稱");
		driver.quit();
	}
	
	public static void least2Warn(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'新增資料集')]")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("t");
		String warning = driver.findElement(By.xpath("//stai-error[contains(text(),'資料集名稱需為2字以上')]")).getText();
		Assert.assertEquals(warning, "資料集名稱需為2字以上");
		driver.quit();
	}

}
