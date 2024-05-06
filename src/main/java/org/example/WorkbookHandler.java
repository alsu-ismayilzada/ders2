package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.example.PageManager.loadCurrentPage;
import static org.example.PageManager.saveCurrentPageNumber;


public class WorkbookHandler {
    private static final String CURRENT_PAGE_FILE = "current_page.properties";
    private static final String BASE_URL = "base_url";
    private Workbook wb;
    private String filename;
    public WorkbookHandler(String filename) {
        this.filename = filename;
        initializeWorkbook();
    }


    private void initializeWorkbook() {
        File file = new File(filename);
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                wb = WorkbookFactory.create(inputStream);
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            wb = new XSSFWorkbook();
        }
    }
    public void process() throws IOException {
        Sheet sheet = wb.getSheet("Autos");
        if (sheet == null) {
            sheet = wb.createSheet("Autos");
            createHeaderRow(sheet);
        }
        addData();
        try (OutputStream os = new FileOutputStream(filename)) {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createHeaderRow(Sheet sheet) {
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
    private static void addData() throws IOException {

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(CURRENT_PAGE_FILE));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String baseUrl = prop.getProperty(BASE_URL);
        int currentPage = loadCurrentPage();

        List<Auto> autoAds = new ArrayList<>();
        for (int i = currentPage; i < currentPage + 5; i++) {
            System.out.println("Page "+ i + " is parsing");
            String url = baseUrl + i;
            Document doc = Jsoup.connect(url + i).get();
            Elements adLinks = doc.select(".tz-container .products .products-i__link");

            for (Element adLink : adLinks) {
                String adUrl = adLink.attr("abs:href");
                Document doc1 = Jsoup.connect(adUrl).get();
                Auto auto = parseAutoAd(doc1);
                autoAds.add(auto);
            }
        }
        currentPage += 5;
        saveCurrentPageNumber(currentPage);
        System.out.println("Current Page " + currentPage);
    }
    private static Auto parseAutoAd(Document document) {

        String price = document.select(".product-price__i.product-price__i--bold").text();
        String region = document.select("[for=ad_region] span").text();
        String brand = document.select("[for=ad_make_id] span").text();
        String model = document.select("[for=ad_model] span").text();
        String graduationYear = document.select("[for=ad_reg_year] span").text();
        String banType = document.select("[for=ad_category] span").text();
        String color = document.select("[for=ad_color] span").text();
        String engine = document.select("[for=ad_engine_volume] span").text();
        String mileage = document.select("[for=ad_mileage] span").text();
        String transmission = document.select("[for=ad_transmission] span").text();
        String gear = document.select("[for=ad_gear] span").text();
        String isNew = document.select("[for=ad_new] span").text();
        String seats = document.select("[for=ad_seats_count] span").text();
        String situation = document.select("[for=ad_Vəziyyəti] span").text();
        String market = document.select("[for=ad_market] span").text();

        return new Auto(price, region, brand, model, graduationYear, banType, color, engine, mileage,
                transmission, gear, isNew, seats, situation, market);
    }

}
