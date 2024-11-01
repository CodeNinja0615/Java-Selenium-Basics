package selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {

		// ---XSSF workbook take file as fileInputStream Object
		FileInputStream fis = new FileInputStream("C://Users//HP//OneDrive//Desktop//DataSetForApachePOI.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);

		int sheetCount = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("testData")) {// ---Parametrize this
				XSSFSheet sheet = workBook.getSheetAt(i);
				// ---Scanning the entire first row
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0;
				int column = 0;
				while (cell.hasNext()) { //-- To get the column index
					Cell value = cell.next();
					String cellValue = value.getStringCellValue();
					if (cellValue.equalsIgnoreCase("TestCases")) { // ---Parametrize this
						column = k;
					}
					k++;
				}
				System.out.println("Sheet Count: " + sheetCount);
				System.out.println("Column Count: " + column);
			}
		}
	}
}
