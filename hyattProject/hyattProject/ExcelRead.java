package hyattProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.common.*;
import org.apache.poi.sl.usermodel.Sheet;


public class ExcelRead {
	
	private XSSFWorkbook database;  //this is the excel file I'll be reading
	private XSSFSheet sheet;	    //this is for the current sheet I'm reading
	private ArrayList<String> colNames;     //this keeps track of the column names
	private Iterator<Row> rowIterator;     //iterator for every row in current sheet
	private Iterator<Cell> cellIterator;   //iterator for all cells in current row
	private Row currRow; 			//current row
	private Map<String, String> currRowData; //represents data for current row, with Key
		/*being the column name, Value being value at that cell; this map is then sent
		to the keysheet generating class */
	private createKeysheet keySheets;
	
	public ExcelRead()
	{
		/*empty constructor*/
	}
	
	public ExcelRead(String filename)
	{
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(file != null) setDatabase(file);
		
		setSheet(0);
		setColumns();
		
		keySheets = new createKeysheet(colNames);
	}
	
	/*sets what Excel file our workbook will be reading from*/
	public void setDatabase(final FileInputStream filename)
	{
		try {
				database = new XSSFWorkbook(filename);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	/*sets what sheet we are currently working on*/
	public void setSheet(final int i)
	{
		sheet = database.getSheetAt(i);
	}
	
	/*sets the column names in the given sheet, and sets rowIterator*/
	public void setColumns()
	{
		colNames = new ArrayList<String>();
		
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
	
	public void readDataRow()
	{
		while(rowIterator.hasNext())
		{
		
			currRow = rowIterator.next();
			cellIterator = currRow.cellIterator();
		
			Map<String, String> thisRow = new HashMap<String, String>();
			int colNum = 0;
		
			while(cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
			
				thisRow.put(colNames.get(colNum), getStringValue(cell));
				System.out.println(thisRow.get(colNames.get(colNum)));
				colNum += 1;
			}
		
			keySheets.makeKeysheet(thisRow);
			
		}//end outer while loop
		
	}
	
	/*returns the value of the cell as a string*/
	public String getStringValue(final Cell cell)
	{
		String toReturn = "";
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				toReturn = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				toReturn = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				toReturn = String.valueOf(cell.getBooleanCellValue());
				break;
		}
		
		return toReturn;
	}
	
	//Map<String colName, String dataValue
	public static void main(String [] args)
	{
		ExcelRead readIt = new ExcelRead(args[0]);
		
		readIt.readDataRow();
	}

}