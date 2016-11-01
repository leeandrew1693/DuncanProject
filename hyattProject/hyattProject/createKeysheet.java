package hyattProject;

import java.util.ArrayList;
import java.util.Map;

public class createKeysheet {
	
	/*FUNCTIONALITY:
	 * insert elements into template keysheet
	 * store it on disk
	 * 
	 */

	
	/*SEND HERE: 
	 * where you want file location stored
	 * Map<String, String> of columns and values stored
	 * 
	 */
	
	private ArrayList<String> columnNames; //I want the column names so I can have easier lookup
	
	public createKeysheet(ArrayList<String> col)
	{
		columnNames = col;
	}
	
	/* will return true or false if keysheet was created succesfully */
	public boolean makeKeysheet(Map<String, String> dataRow) 
	{
		//this is simply a test to see if this works
		System.out.println(dataRow.get(columnNames.get(0)));
		
		
		return true;
	}
	
	public static void main(String [] args)
	{
		//createKeysheet ck = new createKeysheet();
	}

}
