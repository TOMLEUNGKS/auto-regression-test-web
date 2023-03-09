package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
    
    private static ArrayList <String> headerList = new ArrayList <String> ();

    private static ArrayList <String> testDataList = new ArrayList <String> ();

    public static void readExcel(String scenarioName) throws IOException {
        System.out.println("Read Excel for case: " + scenarioName);
        XSSFWorkbook excelWBook;
        XSSFSheet excelWSheet;

        DataFormatter dataFormatter = new DataFormatter();

        String path = "resource/test_data.xlsx";

        try {
            FileInputStream excelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheetAt(0);
            headerList.clear();
            testDataList.clear();

            for (Row row: excelWSheet) {
                String cellValue1stCol = dataFormatter.formatCellValue(row.getCell(0));
                String cellValue2ndCol = dataFormatter.formatCellValue(row.getCell(2));

                //set up the array list for header
                if (cellValue1stCol.trim().equals("caseName")) {
                    for (Cell cell:row) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        headerList.add(cellValue);
                    }
                }
                //set up the array list for test data
                else if (cellValue1stCol.trim().equals(scenarioName.trim()) || cellValue2ndCol.trim().equals(scenarioName.trim())) {
                    for (Cell cell:row) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        testDataList.add(cellValue);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int hlsize = headerList.size();
        int dlsize = testDataList.size();
        System.out.println("hlsize: " + hlsize);
        System.out.println("dlsize: " + dlsize);
    }


}
