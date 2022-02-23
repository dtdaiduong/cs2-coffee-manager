package com.md2.ulits;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ConvertUtils {
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
//        temp = temp.replaceAll("đ", "d").replaceAll("Đ", "D");
//        temp.replaceAll("Đ", "D");
//        return temp;
        return (temp.replaceAll("đ", "d") ).replaceAll("Đ", "D");
    }
}
