package Utilities;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {


    XSSFWorkbook ExcelWBook;

    XSSFSheet ExcelWSheet;
    public ExcelUtils(String filePath)
    {
        try {
            File srcFile= new File(filePath);
            FileInputStream fis=new FileInputStream(srcFile);
            ExcelWBook=new XSSFWorkbook(fis);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getData(int SheetNo,int row,int cloumn )
    {
        ExcelWSheet=ExcelWBook.getSheetAt(SheetNo);
        return ExcelWSheet.getRow(row).getCell(cloumn).getStringCellValue();
    }

    public int getRowCount(int sheetIndex)
    {
        int row=ExcelWBook.getSheetAt(sheetIndex).getLastRowNum();
        row=row+1;
        return row;
    }
}