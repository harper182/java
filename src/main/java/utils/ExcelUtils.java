package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelUtils {
    public static List readXLSXFile(File file) throws IOException {
        InputStream excelFile = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(excelFile);
        XSSFRow row = null;
        List<Map> datas = new ArrayList<>();
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow firstRow = sheet.getRow(0);
        Map<String, String> data = null;
        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            row = sheet.getRow(rowIndex);
            data = new HashMap<>();
            String header = null;
            String cellValue = null;
            for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                header = firstRow.getCell(cellIndex).getStringCellValue();
                XSSFCell cell = row.getCell(cellIndex);
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    cellValue = cellValue.contains(".") ? cellValue.substring(0,cellValue.indexOf(".")) : cellValue;
                }else {
                    cellValue = row.getCell(cellIndex).getStringCellValue();
                }
                data.put(header, cellValue);
            }
            datas.add(data);
        }
        return datas;
    }
    public static byte[] writeXLSXFile(List<Map> datas) throws IOException{
        if(!datas.isEmpty()){
            String[] keys = (String[]) datas.get(0).keySet().toArray(new String[]{});
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            CellStyle style = getCellBoldStyle(xssfWorkbook);
            setHeaderForBrocadeXlsx(keys, row, style);
            setCellValue(datas, keys, sheet);
            for (int index = 0; index < keys.length; index++) {
                sheet.autoSizeColumn(index);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xssfWorkbook.write(byteArrayOutputStream);
            xssfWorkbook.close();
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    private static void setCellValue(List<Map> datas, String[] keys, XSSFSheet sheet) {
        XSSFRow row;
        XSSFCell rowCell;
        for (int index = 0; index < datas.size(); index++) {
            row = sheet.createRow(index+1);
            Map data = datas.get(index);
            for (int col = 0; col < keys.length; col++) {
                rowCell = row.createCell(col);
                Object object = data.get(keys[col]);
                if(object == null){
                    continue;
                }
                if(object instanceof Integer){
                    rowCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    rowCell.setCellValue((Integer)object);
                }else{
                    rowCell.setCellType(Cell.CELL_TYPE_STRING);
                    rowCell.setCellValue(object.toString());
                }
            }
        }
    }

    private static void setHeaderForBrocadeXlsx(String[] keys, XSSFRow row, CellStyle style) {
        XSSFCell rowCell;
        for (int index = 0; index < keys.length; index++) {
            rowCell = row.createCell(index);
            rowCell.setCellValue(keys[index]);
            rowCell.setCellStyle(style);
        }
    }

    private static CellStyle getCellBoldStyle(XSSFWorkbook xssfWorkbook) {
        CellStyle style = xssfWorkbook.createCellStyle();
        Font font = xssfWorkbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        return style;
    }

    public static List readXLSFile(File file) throws IOException {
        List<Map> datas = new ArrayList<>();
        InputStream excelFile = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(excelFile);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow firstRow = sheet.getRow(0);
        Map<String,String> data = null;
        HSSFRow row = null;
        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            row = sheet.getRow(rowIndex);
            if(row == null){
                continue;
            }
            data = new HashMap<>();
            String header = null;
            String cellValue = null;
            for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++) {
                header = firstRow.getCell(cellIndex).getStringCellValue();
                HSSFCell cell = row.getCell(cellIndex);
                if(cell == null){
                    data.put(header,null);
                    continue;
                }
                if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    cellValue = String.valueOf((int)cell.getNumericCellValue());
                }else {
                    cellValue = row.getCell(cellIndex).getStringCellValue();
                }
                data.put(header, cellValue);
            }
            datas.add(data);
        }
        return datas;
    }
}
