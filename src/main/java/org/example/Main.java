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
import java.util.Properties;

public class Main {
    private static final String CURRENT_PAGE_FILE = "current_page.properties";
    private static final String FILE_PATH = "file_path";

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(CURRENT_PAGE_FILE));
            String filePath = prop.getProperty(FILE_PATH);
            WorkbookHandler workbookHandler = new WorkbookHandler(filePath);
            workbookHandler.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
