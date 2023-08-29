package autoTest.puzzle;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class datasetAddnew {
		
	//open the window for ADD DATASETS
	public static void openAddnew(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();	
		driver.quit();
	}
	
	//the button of add is initially disable in the window of ADD DATASETS
	public static void addDisable(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		boolean addNew = driver.findElement(By.xpath("//button[@type='submit']")).isEnabled();
		Assert.assertNotEquals(addNew, true);	
		driver.quit();
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseCatagory(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'������ƶ�')]")).click();	
		driver.quit();	
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseEntity(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'�����ƶ�')]")).click();
		driver.quit();
	}
	
	//choose dataset's type in the window of ADD DATASETS
	public static void chooseOCR(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		driver.findElement(By.xpath("//mat-select[@id='mat-select-0']")).click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'OCR��ƶ�')]")).click();
		driver.quit();	
	}
	
	public static void emptyWarn(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("   ");
		String warning = driver.findElement(By.xpath("//stai-error[contains(text(),'�п�J��ƶ��W��')]")).getText();
		Assert.assertEquals(warning, "�п�J��ƶ��W��");
		driver.quit();
	}
	
	public static void least2Warn(String url) {
		ChromeDriver driver = new ChromeDriver();
		driver.get(url);
		driver.findElement(By.xpath("//button[contains(text(),'�s�W��ƶ�')]")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).clear();
		driver.findElement(By.xpath("//input[@formcontrolname='datasetName']")).sendKeys("t");
		String warning = driver.findElement(By.xpath("//stai-error[contains(text(),'��ƶ��W�ٻݬ�2�r�H�W')]")).getText();
		Assert.assertEquals(warning, "��ƶ��W�ٻݬ�2�r�H�W");
		driver.quit();
	}

}
