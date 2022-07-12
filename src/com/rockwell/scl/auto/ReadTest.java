package com.rockwell.scl.auto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;



/**
 * @author RWang18
 */
public class ReadTest {
    public static void main(String[] args) throws Exception {
//
//        System.setProperty("com.rockwell.test.username", "ww");
//        System.setProperty("com.rockwell.test.password", "admin");
//        System.setProperty("HOST_ADDRESS", "192.168.59.20");
////        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
////        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
////        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        ReadTag readTag = new ReadTag("S1-07-073");
//        Map<String, Object> res = readTag.getTagValue();
//        Set<Map.Entry<String,Object>> entrySet =  res.entrySet();
//        for(Map.Entry<String,Object> entry:entrySet){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        System.exit(0);


            BigDecimal foo,foo1;

            foo= BigDecimal.valueOf(0.631f);
            foo1= BigDecimal.valueOf(0.631d);
            System.out.println(foo);
            System.out.println(foo1);


    }
}

