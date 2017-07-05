package excel;

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ExcelUtils {
    public static List readXLSXFile(File file) throws IOException {
        InputStream excelFile = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(excelFile);
        XSSFRow row = null;
        List<Map> datas = new ArrayList<>();
        XSSFSheet sheet = wb.getSheetAt(1);
        XSSFRow firstRow = sheet.getRow(0);
        Map<String, Object> data = null;
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
                if("施引专利".equals(header)){
                    data.put(header, Arrays.asList(cellValue.split("\\|")));
                }else{
                    data.put(header, cellValue);
                }
            }
            datas.add(data);
        }
        excelFile.close();
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
    public static void processFile() throws IOException {
        File file = new File("/Users/hbowang/project/java/src/main/java/1205.xlsx");
        List list = ExcelUtils.readXLSXFile(file);

        Iterator iterator = list.iterator();
        String[] iRow = null;
        String[] jRow = null;
        Map<String,Double> resultMap = new HashMap<>();
        int i = 0;
        while (iterator.hasNext()){
            Map map = (Map)iterator.next();
            String pubNumber = (String)map.get("公开号");
            List list1 = (List<String>)map.get("施引专利");
            if(i == 0){
                iRow = new String[list.size()];
                jRow = new String[list.size()];
            }
            if(list1 != null) {
                Collections.sort(list1);
            }
            String number1 = (String)map.get("施引专利计数");
            Iterator otherIter = list.iterator();
            iRow[i] = pubNumber;
            int j = 0;
            while (otherIter.hasNext()){
                Map otherMap = (Map)otherIter.next();
                String otherNumber = (String)otherMap.get("公开号");
                jRow[j] = otherNumber;
                if(pubNumber.equals(otherNumber)){
                    j++;
                    continue;
                }

                List list2 = (List<String>)otherMap.get("施引专利");
                if(list2 != null) {
                    Collections.sort(list2);
                }
                String number2 = (String)otherMap.get("施引专利计数");
                int sum = calculate(list1, list2);
                double subResult = 0;
                if(!number1.trim().equals("0") && !number2.trim().equals("0")){
                    subResult = (double) sum / Math.sqrt(Double.valueOf(number1)*Double.valueOf(number2));
                }
                if(sum != 0 && subResult != 0.0) {
                    resultMap.put(pubNumber + "_" + otherNumber, subResult);
                }
                j++;
            }
            i++;
        }
        System.out.println(iRow.length);
        System.out.println(jRow.length);
        System.out.printf("---------");
        byte[] bytes = writeXLSXFile(resultMap,iRow,jRow);
        Files.write(Paths.get("excel_result.xlsx"), bytes);
    }
    public static void main(String[] args) throws IOException {
        ExcelUtils.processFile();
    }
    private static   byte[] writeXLSXFile(Map<String,Double> datas,String[] i ,String [] j) throws IOException{
        if(!datas.isEmpty()){
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            XSSFSheet sheet = xssfWorkbook.createSheet();
            XSSFRow firstRow = sheet.createRow(0);
            XSSFCell rowCell;
            rowCell = firstRow.createCell(0);
            rowCell.setCellValue("公开号1");
            rowCell = firstRow.createCell(1);
            rowCell.setCellValue("公开号2");
            rowCell = firstRow.createCell(2);
            rowCell.setCellValue("结果");

            setCellValue(datas, sheet);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xssfWorkbook.write(byteArrayOutputStream);
            xssfWorkbook.close();
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }
    private void setHeaderForXlsx(String[] keys, XSSFRow fristRow) {
        XSSFCell rowCell;
        for (int index = 0; index < keys.length; index++) {
            rowCell = fristRow.createCell(index);
            if(index == 0){
                rowCell.setCellValue("");
            }else {
                rowCell.setCellValue(keys[index]);
            }
        }
    }
    private static   void setCellValue(Map<String,Double> datas, XSSFSheet sheet) {
        XSSFRow row;
        XSSFCell rowCell;
        Iterator<String> iterator = datas.keySet().iterator();
        int i = 1;
        while (iterator.hasNext()){
            row = sheet.createRow(i++);
            String key = iterator.next();
            String[] keys = key.split("_");
            for (int index = 0; index < 2; index++) {
                rowCell = row.createCell(index);
                rowCell.setCellValue(keys[index]);
            }
            rowCell = row.createCell(2);
            rowCell.setCellValue(datas.get(key));
        }
    }
    private static int calculate(List<String> list0,List<String> list1){
        int sum = 0;
        for(String str0 : list0){
            for(String str1 : list1){
                if(str0.trim().equals(str1.trim())){
                    sum++;
                }
            }
        }
        return sum;
    }
}
