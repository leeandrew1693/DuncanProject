package inputPack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
//timport org.apache.poi.hssf.usermodel.HSSFSheet;

//import poi-3.15.HSSFWorkbook;

public class ReadExcel {
	
	private XSSFWorkbook database;  //this is the excel file I'll be reading
	private XSSFSheet sheet;	    //this is for the current sheet I'm reading
	private ArrayList<String> colNames;     //this keeps track of the column names
	private Iterator<Row> rowIterator;     //iterator for every row in current sheet
	private Iterator<Cell> cellIterator;   //iterator for all cells in current row
	private Row currRow; 			//current row
	private Map<String, String> currRowData; //represents data for current row, with Key
		//being the column name, Value being value at that cell; this map is then sent
		//to the keysheet generating class
	
	//sets what Excel file our workbook will be reading from
	public void setDatabase(String filename)
	{
		try {
			database = new XSSFWorkbook("Database.xlsx");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//sets what sheet we are currently working on
	public void setSheet(int i)
	{
		sheet = database.getSheetAt(0);
	}
	
	//sets the column names in the given sheet, and sets rowIterator
	public void setColumns()
	{
		rowIterator = sheet.iterator();
		
		if(rowIterator.hasNext()) //as long as the file has a row
			currRow = rowIterator.next(); //we'll set it equal to curr
		
		cellIterator = currRow.cellIterator();
				
		//go through every cell in 1st row and get column names
		while(cellIterator.hasNext())
		{
			Cell cell = cellIterator.next();
			colNames.add(cell.getStringCellValue());
			System.out.println(cell.getStringCellValue());
		}
		//all the column names should be set now
	}
	

	public static void main(String[] args) {
		
		ReadExcel readIt = new ReadExcel();
		
		//for present purposes, I am manually inserting the filename, will eventually be args[0]
	//	readIt.setDatabase("Database.xlsx");
	//	readIt.setSheet(0);
		//readIt.setColumns();
	}

}
