package com.rockwell.scl.partkit;

import java.math.BigDecimal;
import java.text.ParseException;

public class T {
    public static void main(String[] args) throws ParseException {
        String str = "1,000";
        str   =   str.replace( ",", "");
        BigDecimal a = new BigDecimal(str);
        System.out.println(a);
    }
}
