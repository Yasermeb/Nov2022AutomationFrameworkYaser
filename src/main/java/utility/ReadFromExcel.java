package utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromExcel {
    /*
    This is a Java class that reads data from an Excel file using the Apache
POI library. The class has three main methods:
getDataFromCell(int row, int column) which takes in a row and a column number
and returns the data in the corresponding cell.
getEntireColumnData(int rowStart, int column) which takes in a starting row
number and a column number and returns a list of all the data in that column.
getCellValueForGivenHeaderAndKey(String header, String key) which takes in a
header and a key and returns the value of the cell that corresponds to the
header and key.
The constructor initializes the file path and the sheet name from which data
is to be read. The class also makes use of the Apache POI library for reading
data from the excel files.
     */

    FileInputStream excelFile;
    /*FileInputStream is a class in Java that is used to read data from a file. When the object of this class is created,
    it takes the file path of the Excel file as an argument, and this path is used to create the FileInputStream object.
    Once the object is created, the read() method can be used to read bytes from the file. */
    XSSFWorkbook workbook;
    /*XSSFWorkbook is a class in the Apache POI library that is used to read and write Microsoft Excel files in the XLSX format.
    The workbook variable in this class is an instance of the XSSFWorkbook class that is used to represent the Excel file as a workbook.
    The workbook object is created by passing the FileInputStream object to its constructor. Once the workbook object is created,
    you can use various methods of the XSSFWorkbook class to read data from the workbook, such as getSheet() and getNumberOfSheets().
     */
    XSSFSheet sheet;
    /*
     XSSFSheet is a class in the Apache POI library that is used to represent a sheet in an XLSX file (Excel file) whose
      name is passed as an argument in the constructor. Once the sheet object is created, you can use various methods of
      the XSSFSheet class to read data from the sheet, such as getRow() and getLastRowNum().
     */
    String filPath;
    /*
    filPath is a variable of type String that represents the file path of the Excel file from which data is to be read.
    The file path is passed as an argument in the constructor of the class and is used to create the FileInputStream object,
    which is used to read data from the file.
     */
    String sheetName;
    /*
    sheetName is a variable of type String that represents the name of the sheet in the Excel file from which data is to be read.
    The sheet name is passed as an argument in the constructor of the class and is used to retrieve the corresponding sheet from the workbook.
    The sheet name is used in the method workbook.getSheet(sheetName) to get the sheet from the workbook.
     */

    public ReadFromExcel(String filPath, String sheetName) {
        this.filPath = filPath;
        this.sheetName = sheetName;
    }

    public String getDataFromCell(int row, int column) {
        String data = null;

        try {
            excelFile = new FileInputStream(filPath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            data = sheet.getRow(row).getCell(column).getStringCellValue();
            excelFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public List<String> getEntireColumnData(int rowStart, int column) {
        List<String> data = new ArrayList<>();
        try {
            excelFile = new FileInputStream(filPath);
            workbook = new XSSFWorkbook(excelFile);
            sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                data.add(sheet.getRow(i).getCell(column).getStringCellValue());
            }
            excelFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public String getCellValueForGivenHeaderAndKey(String header, String key) {
        String data = null;
        int i = 0;
        while (getDataFromCell(0, i) != null) {
            if (getDataFromCell(0, i).equalsIgnoreCase(header)) {
                for (int j = 0; j < getEntireColumnData(1, i).size(); j++) {
                    if (getEntireColumnData(1, i).get(j).equalsIgnoreCase(key)) {
                        data = getEntireColumnData(1, i + 1).get(j);
                    }
                }
            }
            break;
        }
        return data;
    }
}
    /*
    This is a method called "getCellValueForGivenHeaderAndKey" which takes in two parameters as input, a "header"
    and a "key" and returns a string. This method is using a while loop to iterate through the rows of the first
    column of the excel sheet, and check whether the value of the cell equals the given header. If it does, it then uses
    a nested for loop to iterate through the values of a specific column, which appears to be the second one, and check
    whether the value of the cell equals the given key. If it does, it assigns the value of the next cell in the same row
    as the data and returns it.
     */

//    public static void main(String[] args) throws IOException {
//        ReadFromExcel readFromExcel=new ReadFromExcel();
//        System.out.println(readFromExcel.getCellValueForGivenHeaderAndKey("key","selenium search title"));
//    }
//}
