package selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class dataDriven {

	@Test
	public void useData() throws IOException {
		ArrayList<String> data = getData("purchase");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
	}
	
	
	public ArrayList<String> getData(String testCase) throws IOException {

		//---ArrayList to store data
		ArrayList<String> data = new ArrayList<String>();
		
		// ---XSSF workbook take file as fileInputStream Object
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//excelFiles//DataSetForApachePOI.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);

		int sheetCount = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("testData")) {// ---Parametrize this
				XSSFSheet sheet = workBook.getSheetAt(i);
				// ---Scanning the entire first row
				Iterator<Row> rows = sheet.iterator(); // -- To get rows(Horizontal)
				Row firstRow = rows.next(); // ---To get to first row that has column titles
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) { // -- To get the column index
					Cell value = cell.next(); // ----Iterate through each cell in first row
					String cellValue = value.getStringCellValue(); //---Columns first cell(title)
					if (cellValue.equalsIgnoreCase("TestCases")) { //---Parametrize this
						column = k;
					}
					k++;
				}
//				System.out.println("Sheet Count: " + sheetCount);
//				System.out.println("Column Count: " + column);

				while (rows.hasNext()) {
					Row row = rows.next();
					String cellTitle = row.getCell(column).getStringCellValue(); //---Getting a particular cooresponding cell with column containing title
					if (cellTitle.equalsIgnoreCase(testCase)) { // ---Parametrize this
						Iterator<Cell> cellValues = row.cellIterator(); //---Iterate through cell in a row
						while(cellValues.hasNext()) {
							Cell cellNext = cellValues.next();
							if(cellNext.getCellType() == CellType.STRING) { //----Checking type String or Numeric
								String cellValue = cellNext.getStringCellValue();
								data.add(cellValue);
							}else {
								double cellValueDouble = cellNext.getNumericCellValue();
								String cellValue = NumberToTextConverter.toText(cellValueDouble); //---Numeric To Text
								data.add(cellValue);
							}
						}
					}
				}
			}
		}
		return data;
	}
}
