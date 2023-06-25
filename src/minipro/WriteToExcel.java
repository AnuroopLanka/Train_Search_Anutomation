package minipro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class WriteToExcel { // Class to write into Excel file
	XSSFWorkbook wb; // XSSFWorkbook object
	XSSFSheet sheet; // XSSFSheet object

	File src = new File(System.getProperty("user.dir") + "\\ExcelSheet\\OutputData.xlsx");
	// Path to ErrorData.xlsx

	public WriteToExcel() { // Setup FileInpuStream in ErrorData.xlsx
		try {
			FileInputStream file = new FileInputStream(src); // FileInputStream object

			wb = new XSSFWorkbook(file);
			sheet = wb.getSheetAt(0); // opens sheet(0)/default sheet

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setCellData(int nrow, int ncolumn, String message) { // print data to Excel
		try {
			XSSFRow row = sheet.createRow(nrow); // Create and set to row 0
			XSSFCell cell = row.createCell(ncolumn); // Create and set to cell 0 in column 0
			cell.setCellValue(message);

			FileOutputStream fos = new FileOutputStream(src);

			wb.write(fos); // Write to sheet

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

/*	public void addColumnData(int nrow, int ncolumn, String message) { // print data to Excel
		try {
			XSSFRow row = sheet.getRow(nrow); // set to row 0
			XSSFCell cell = row.createCell(ncolumn); // Create and set to cell 0 in column 0
			cell.setCellValue(message);

			FileOutputStream fos = new FileOutputStream(src);

			wb.write(fos); // Write to sheet

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	*/

	public void closeFileStream() throws IOException {
		wb.close(); // Close Filestream
	}

}