package org.gd.automation.core.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static boolean isUrlValid(String sUrl) {
        try {
            URL url = new URL(sUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return false;
    }

    public static String getFormattedDate(String sFormat, String sDate) throws ParseException {
        return new SimpleDateFormat(sFormat).format(new SimpleDateFormat(sFormat).parse(sDate));

    }

    public static String getTodayFormattedDate(String sFormat) throws ParseException {
        return new SimpleDateFormat(sFormat).format((new Date()));

    }


}
