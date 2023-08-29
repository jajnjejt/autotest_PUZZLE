package autoTest.puzzle;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class web_test {
	

	

	public static void general() throws IOException, GeneralSecurityException {

/***
		//connect to test cases sheets
		String spreadsheetId = "1_QyUBqLfo6K2DthysCqgw9jRJvFn23rfUafYihBI6Jk";
		String range = "Test Cases";
		
		String testID;
		List<List<Object>> testName = new ArrayList<>();
		String testRange;
		
		List<List<Object>> values = new ArrayList<>();
		try {
			values = googleApi.getData(spreadsheetId, range);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(List<Object> value : values) {
			try {
				testID = value.get(3).toString();
				if (testID.isEmpty() | testID.equals("ID")) {
					System.out.println("false");
					continue;
				} else {
					System.out.println("correct");
					testRange = String.format("%s!B3",testID);
					testName = googleApi.getData(spreadsheetId, testRange);
				}
			} catch(IndexOutOfBoundsException e) {
				continue;
			}			
		}
		for(List<Object> name : testName) {
			System.out.println(name);
		}***/
	}
}
