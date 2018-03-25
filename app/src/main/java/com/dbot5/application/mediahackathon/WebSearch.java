package com.dbot5.application.mediahackathon;


/**
 * Created by astrode5 on 25/3/18.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WebSearch {
    public String keyword;
    public String pattern_first_half;
    public String pattern_second_half;

    public Document findURL() throws IOException {
        String url = pattern_first_half+keyword+pattern_second_half;
        Document document= Jsoup.connect(url).get();

        return document;
    }
}
