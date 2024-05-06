package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PageManager {
    private static final String CURRENT_PAGE_FILE = "current_page.properties";
    private static final String CURRENT_PAGE = "current_page";

    public static int loadCurrentPage() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(CURRENT_PAGE_FILE));
            String currentPageStr = prop.getProperty(CURRENT_PAGE);
            if (currentPageStr != null) {
                return Integer.parseInt(currentPageStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void saveCurrentPageNumber(int pageNumber) {
        Properties prop = new Properties();
        prop.setProperty(CURRENT_PAGE, String.valueOf(pageNumber));
        try {
            prop.store(new FileOutputStream(CURRENT_PAGE_FILE), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
