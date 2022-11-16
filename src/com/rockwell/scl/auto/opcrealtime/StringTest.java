package com.rockwell.scl.auto.opcrealtime;

import java.io.UnsupportedEncodingException;

public class StringTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String cnStr = "龙";
        String enStr = "a";
        byte[] cnBytes = cnStr.getBytes("UTF-8");
        byte[] enBytes = enStr.getBytes("UTF-8");

        System.out.println("bytes size of Chinese：" + cnBytes.length);
        System.out.println("bytes size of English：" + enBytes.length);

        //  in java, char takes two bytes, the question is:
        char cnc = '龙'; // will '龙‘ take two or three bytes ?
        char enc = 'a'; // will 'a' take one or two bytes ?
    }
}
