package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {
	

	
	
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	public HashMap<String, Integer> testDataColumnFields = new HashMap<String, Integer>();
	public String testCase_Id;
	public HashMap<String, Integer> testDataRowFields = new HashMap<String, Integer>();
	private  XSSFCell  cell;
	public int rowNum;
	private XSSFRow row;
	
	
	public void getTestData(String testCase, String sheet) {
		this.setTestCase_Id(testCase);
		
		this.loadTestData(sheet);
		
		try {
			rowNum = testDataRowFields.get(testCase);
		} catch(NullPointerException e){
    		System.out.println("Invalid Testcase ID");
    	}
	}

	public TestData(){
	    	
	    }

	public void loadTestData(String sheet) {
		
		try {
			TestData.setExcelFile(sheet.trim());
			//System.out.println("Sheet name ****** " + sheet);
			int rows = ExcelWSheet.getPhysicalNumberOfRows();
			
			for (int i = 0; i < rows; i++) {
				
				int columns = ExcelWSheet.getRow(i).getPhysicalNumberOfCells();
				
				if (i==0) {
					for (int j = 0; j < columns; j++) {
						testDataColumnFields.put(ExcelWSheet.getRow(i).getCell(j).toString().trim(), j);
					}
				} else {
					testDataRowFields.put(ExcelWSheet.getRow(i).getCell(0).toString().trim(), i);
				}
			}
		} catch (Exception e) {
			System.out.println("Error : "+e);	
		}
		
	}


	public static void setExcelFile(String SheetName) {
		
		try {
			FileInputStream ExcelFile = new FileInputStream(System.getProperty("user.dir") 
					+ File.separator + "src/test/java"
					+ File.separator +"Utility" + File.separator
					+ "TestData.xlsx");
			
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				
		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}

	
	public String getFieldValue(String field) {
		
		int colNum = testDataColumnFields.get(field.trim());
		row = ExcelWSheet.getRow(rowNum);
		return row.getCell(colNum).toString();
	}

	public void setTestCase_Id(String testCase) {
		this.testCase_Id = testCase;
	}
	
	public String getTestCase_Id() {
		 return this.testCase_Id;
	 }



}
