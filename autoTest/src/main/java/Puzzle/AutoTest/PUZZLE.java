package Puzzle.AutoTest;

import autoTest.puzzle.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class PUZZLE {
	
	//PUZZLE URL
	String url = "http://192.168.118.90/";
/*	
    @Test
    public void DA001() {
    	datasetAddnew.openAddnew(url);  		  
    }
    
    @Test
    public void DA043() {
    	datasetAddnew.addDisable(url);
    }
    
    @Test
    public void DA044() {
    	datasetAddnew.chooseCatagory(url);;
    }
    
    @Test
    public void DA045() {
    	datasetAddnew.chooseEntity(url);;
    }
    
    @Test
    public void DA046() {
    	datasetAddnew.chooseOCR(url);;
    }
    
    @Test
    public void DA003() {
    	datasetAddnew.emptyWarn(url);
    }
*/
	@Test
	public void DA004() {
		datasetAddnew.least2Warn(url);
	}
    
    @BeforeClass
    public void beforeClass() {
	    System.out.println("in beforeclass");
    }

    @AfterClass
    public void afterClass() {
  	    System.out.println("in afterclass");
    }

}
