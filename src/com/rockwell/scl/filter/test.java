package com.rockwell.scl.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author RWang18
 */
public class test {
    public static void main(String[] args) throws Exception {

        String regexp = "\'";

        String str ="'SVA07857', 'SVA07874', 'SVA00147', 'SVA00352', 'SVA00484', 'SVA00646', 'SVA02122', 'SVA05103', 'SVA05226', 'SVA05155', 'SVA05391', 'SVA05225', 'SVA05399', 'SVA05404', 'SVA05886', 'SVA05780', 'SVA05975', 'SVA06220', 'SVA06306', 'SVA05254', 'SVA06112', 'SVA00446'";
        System.out.println("替换前:" + str);

        str = str.replaceAll(regexp, "");
        System.out.println(str);
//          String firstname1 = "0.2 μm";
////        firstname1  = firstname1.replaceAll("\\s","");
////          System.out.println(firstname1);
//          firstname1 = firstname1.replaceAll("[0-9\\s]","").replace(".","");
//
//          System.out.println(firstname1);//Prints Sam
    }
}
