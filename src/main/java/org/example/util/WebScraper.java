package org.example.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebScraper {
    public static Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
