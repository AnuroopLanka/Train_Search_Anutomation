package minipro;



import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel {
	
	static XSSFSheet sheet1;
	static XSSFWorkbook ExcelWBook;
	
	//Setting up path to read excel sheet
	public static void setExcelFile() throws Exception {
		//Path of Excel sheet

	File src =  new File(System.getProperty("user.dir") + "\\ExcelSheet\\IRCTC.xlsx");                                
		//Create an object of FileInputStream class to read excel file
		FileInputStream fi = new FileInputStream(src);           
		ExcelWBook = new XSSFWorkbook(fi);
	}

	//Method to get data from Excel sheet
	public static String getCellData(int i, int j) {
		 //Getting the sheetS
		sheet1 = ExcelWBook.getSheetAt(0);                              
		//Getting the value of the "ith" row and "jth" column
		String data = sheet1.getRow(i).getCell(j).getStringCellValue();
		 //returning the value
		return data;                                                   
	
	}
	


}
