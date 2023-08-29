package autoTest.puzzle;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class googleApi{
	
	private static final String APPLICATION_NAME = "TEST";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	//connect google API with credentials
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		
		InputStream in = googleApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(SCOPES);
		return credential;
	}

	//get data from specific spreadsheet
	public static List<List<Object>> getData(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
			return null;
		} else {
			return values;
		}
	}
	
	//batch get data from specific spreadsheet
	public BatchGetValuesResponse batchGetData(String spreadsheetId, List<String> ranges) throws IOException, GeneralSecurityException{
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		
		BatchGetValuesResponse result = null;
		try {
			result = service.spreadsheets().values().batchGet(spreadsheetId).setRanges(ranges).execute();
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			if (error.getCode() == 404) {
				System.out.printf("Spreadsheet not found with id '%s' .\n", spreadsheetId);
		} else {
			throw e;
			}
		}
		return result;
	}
	
	//update values in specific spreadsheet
	public UpdateValuesResponse updateValues(String spreadsheetId, String range, String valueInputOption, List<List<Object>> values)
            		throws IOException, GeneralSecurityException {	
		
	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

	    Sheets service = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		
		UpdateValuesResponse result = null;
		try {
			ValueRange body = new ValueRange().setValues(values);
			result = service.spreadsheets().values().update(spreadsheetId, range, body).setValueInputOption(valueInputOption).execute();
			System.out.printf("%d cells updated.", result.getUpdatedCells());
		} catch (GoogleJsonResponseException e) {
			GoogleJsonError error = e.getDetails();
			if (error.getCode() == 404) {
				System.out.printf("Spreadsheet not found with id '%s'.\n", spreadsheetId);
		} else {
			throw e;
			}
		}
		return result;
	}
	
	//get specific sheet ID
	public Integer getSheetById(String spreadsheetId, String sheetName) throws GeneralSecurityException, IOException {
		
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

	    Sheets service = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
	    Spreadsheet spreadsheet = service.spreadsheets().get(spreadsheetId).execute();
	    Integer sheetID = 0;
	    try {
		    for (int numID=0; numID<spreadsheet.getSheets().size(); numID++) {
			    Sheet sheet = spreadsheet.getSheets().get(numID);
			    String title = sheet.getProperties().getTitle();
			    if (title.contentEquals(sheetName)) {
			    	sheetID = sheet.getProperties().getSheetId();			    	
			    } else {
			    	System.out.printf("The name %s doesn't match%n", title);
			    }
		    }
		 } catch (Exception e) {
			 System.out.println(e);
		    }    
	    return sheetID;
	}

	//merge vertical cells in specific sheet
	public BatchUpdateSpreadsheetResponse mergeVerticalCells(String spreadsheetId, String sheetName, Integer col, Integer row_start, Integer row_end)
		      throws IOException, Exception {

	    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

	    Sheets service = new Sheets.Builder(new NetHttpTransport(), JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
	    
		GridRange ranges = new GridRange()
		        .setSheetId(getSheetById(spreadsheetId, sheetName))
		        .setStartRowIndex(col)
		        .setEndRowIndex(col)
		        .setStartColumnIndex(row_start)
		        .setEndColumnIndex(row_end);
		
		List<Request> requests = new ArrayList<>();
		requests.clear();
	    requests.add(new Request().setMergeCells(new MergeCellsRequest().setRange(ranges).setMergeType("MERGE_COLUMNS")));

		BatchUpdateSpreadsheetResponse result = null;
		try {
			BatchUpdateSpreadsheetRequest requestbody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
			result = service.spreadsheets().batchUpdate(spreadsheetId, requestbody).execute();
			System.out.printf("%d cells updated.", result.getReplies().size());
			} catch (GoogleJsonResponseException e) {
				GoogleJsonError error = e.getDetails();
				if (error.getCode() == 404) {
					System.out.printf("Spreadsheet not found with id '%s'.\n", spreadsheetId);
				} else {
					throw e;
					}
		    	}
		return result;
	}
	


}

