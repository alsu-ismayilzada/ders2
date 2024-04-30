package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Properties;

public class Main {
    private static final String CURRENT_PAGE_FILE = "current_page.properties";
    private static final String CURRENT_PAGE = "current_page";
    public static void main(String[] args) {

        Workbook wb = null;
        File file = new File("autos.xls");

        if(file.exists()){
            try(FileInputStream inputStream = new FileInputStream(file)){
                wb = WorkbookFactory.create(inputStream);
            }catch (IOException e){
                System.out.println(e);
            }
        }else{
            wb = new HSSFWorkbook();
        }

        try {

            Sheet sheet = wb.getSheet("Autos");
            if (sheet == null) {

                sheet = wb.createSheet("Autos");

                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Price");
                header.createCell(1).setCellValue("Region");
                header.createCell(2).setCellValue("Brand");
                header.createCell(3).setCellValue("Model");
                header.createCell(4).setCellValue("Graduation Year");
                header.createCell(5).setCellValue("Ban Type");
                header.createCell(6).setCellValue("Color");
                header.createCell(7).setCellValue("Engine");
                header.createCell(8).setCellValue("Mileage");
                header.createCell(9).setCellValue("Transmission");
                header.createCell(10).setCellValue("Gear");
                header.createCell(11).setCellValue("New");
                header.createCell(12).setCellValue("Seats");
                header.createCell(13).setCellValue("Situation");
                header.createCell(14).setCellValue("Market");
            }
            addData(sheet);

            try (OutputStream os = new FileOutputStream("autos.xls")) {
                wb.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addData(Sheet sheet) throws IOException {

        int currentPage = loadCurrentPage();
        for (int i = currentPage; i < currentPage + 5; i++) {
            System.out.println("Page "+ i + " parsed");
            Document doc = Jsoup.connect("https://turbo.az/autos/?page=" + i).get();
            Elements adLinks = doc.select(".tz-container .products .products-i__link");
            for (Element adLink : adLinks) {

                String adUrl = adLink.attr("abs:href");
                Document doc1 = Jsoup.connect(adUrl).get();

                String price = doc1.select(".product-price__i.product-price__i--bold").text();

                Elements divs = doc1.select(".product-properties__column");

                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                newRow.createCell(0).setCellValue(price);
                for (Element div : divs) {

                    Elements elements = div.select(".product-properties__i ");
                    for (Element element : elements) {

                        Element elementLabel = element.select("label").first();
                        String attr = elementLabel.attr("for");
                        String elementSpan = element.select("span").first().text();

                        switch (attr){
                            case "ad_region":
                                newRow.createCell(1).setCellValue(elementSpan);
                                break;
                            case "ad_make_id":
                                newRow.createCell(2).setCellValue(elementSpan);
                                break;
                            case "ad_model":
                                newRow.createCell(3).setCellValue(elementSpan);
                                break;
                            case "ad_reg_year":
                                newRow.createCell(4).setCellValue(elementSpan);
                                break;
                            case "ad_category":
                                newRow.createCell(5).setCellValue(elementSpan);
                                break;
                            case "ad_color":
                                newRow.createCell(6).setCellValue(elementSpan);
                                break;
                            case "ad_engine_volume":
                                newRow.createCell(7).setCellValue(elementSpan);
                                break;
                            case "ad_mileage":
                                newRow.createCell(8).setCellValue(elementSpan);
                                break;
                            case "ad_transmission":
                                newRow.createCell(9).setCellValue(elementSpan);
                                break;
                            case "ad_gear":
                                newRow.createCell(10).setCellValue(elementSpan);
                                break;
                            case "ad_new":
                                newRow.createCell(11).setCellValue(elementSpan);
                                break;
                            case "ad_seats_count":
                                newRow.createCell(12).setCellValue(elementSpan);
                                break;
                            case "ad_Vəziyyəti":
                                newRow.createCell(13).setCellValue(elementSpan);
                                break;
                            case "ad_market":
                                newRow.createCell(14).setCellValue(elementSpan);
                                break;
                            default:
                        }
                    }
                }
            }
        }
        currentPage += 5;
        saveCurrentPageNumber(currentPage);
        System.out.println("Current Page " + currentPage);
    }
    private static int loadCurrentPage() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(CURRENT_PAGE_FILE));
            String currentPageStr = prop.getProperty(CURRENT_PAGE);
            if (currentPageStr != null) {
                return Integer.parseInt(currentPageStr);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static void saveCurrentPageNumber(int pageNumber) {
        Properties prop = new Properties();
        prop.setProperty(CURRENT_PAGE, String.valueOf(pageNumber));
        try {
            prop.store(new FileOutputStream(CURRENT_PAGE_FILE), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}