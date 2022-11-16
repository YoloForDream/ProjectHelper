package com.rockwell.scl.auto;

import com.rockwell.mes.commons.base.ifc.services.PCContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("com.rockwell.test.username", "ww");
        System.setProperty("com.rockwell.test.password", "admin");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmssSSS");

        for(int i=0;i<100;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("i="+i+":   "+sdf.format(new Date()));
            System.out.println("DBTime"+i+":   "+sdf.format(PCContext.getFunctions().getDBTime().getCalendar().getTime()));

        }
    }

}
