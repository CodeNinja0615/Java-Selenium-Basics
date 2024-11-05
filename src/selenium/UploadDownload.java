package selenium;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadDownload {

	@Test
	public void test() throws IOException, InterruptedException {
		String filePath = "C://Users//HP//Downloads//download.xlsx";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		// ---Download
		driver.findElement(By.id("downloadButton")).click();
		// ---Edit Excel
		editExcel(filePath, "Papaya", 15);
		// ---Upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys(filePath);
		// ---Wait for success toast to appear and dissapppear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		By by = By.xpath("//div[@class='Toastify__toast-body']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		String toastMsg = driver.findElement(by).getText();
		Assert.assertTrue(toastMsg.contains("Successfully"));
		System.out.println(toastMsg);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		// ---Verify updated excel data in web table
		String fruitName = "Papaya";
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		WebElement priceOfFruit = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']"));
		Assert.assertFalse(priceOfFruit.getText().equals("187"));
		System.out.println(priceOfFruit.getText());

		driver.quit();
	}

	public void editExcel(String filePath, String fruitName, int newPrice) throws IOException, InterruptedException {
		Thread.sleep(5000);
//		String price = null;
		// ---XSSF workbook take file as fileInputStream Object
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fis);

		int sheetCount = workBook.getNumberOfSheets();
		for (int i = 0; i < sheetCount; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = workBook.getSheetAt(i);
				// ---Scanning the entire first row
				Iterator<Row> rows = sheet.iterator(); // -- To get rows(Horizontal)
				Row firstRow = rows.next(); // ---To get to first row that has column titles
				Iterator<Cell> cell = firstRow.cellIterator();
				int k = 0, l = 0, fruitColumn = 0, priceColumn = 0;
				while (cell.hasNext()) { // -- To get the column index
					Cell value = cell.next(); // ----Iterate through each cell in first row
					String cellValue = value.getStringCellValue(); // ---Columns first cell(title)
					if (cellValue.equalsIgnoreCase("fruit_name")) {
						fruitColumn = k;
					} else if (cellValue.equalsIgnoreCase("price")) {
						priceColumn = l;
					}
					l++;
					k++;
				}

				while (rows.hasNext()) {
					Row row = rows.next();
					String cellTitle = row.getCell(fruitColumn).getStringCellValue(); // ---Getting a particular
																						// cooresponding cell with
																						// column containing title
					if (cellTitle.equalsIgnoreCase(fruitName)) {
//						double cellValueDouble = row.getCell(priceColumn).getNumericCellValue();
//						price = NumberToTextConverter.toText(cellValueDouble);
						row.getCell(priceColumn).setCellValue(newPrice);
						try (FileOutputStream fos = new FileOutputStream(filePath)) {
							workBook.write(fos);
						}
					}
				}
			}
		}
		workBook.close();
	}

}
