package Base;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    public static void excelWriter(String filePath, String sheetName, int rowNumber, int cellNumber, String data) {
        Workbook workbook = null;
        FileOutputStream fileOut = null;

        try {
            File file = new File(filePath);
            // Check if the file exists
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
            } else {
                workbook = new XSSFWorkbook(); // Create a new workbook if the file doesn't exist
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName); // Create a new sheet if it doesn't exist
            }

            Row row = sheet.createRow(rowNumber);
            Cell cell = row.createCell(cellNumber);
            cell.setCellValue(data); // Set the data in the specified cell

            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut); // Write the changes to the file

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}