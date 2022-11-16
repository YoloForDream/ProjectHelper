package com.rockwell.scl.auto;

import com.datasweep.compatibility.client.WorkFlow;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

public class tt {
    public static void main(String[] args)  {
////        BigDecimal value = new BigDecimal("10.5345");
////        BigDecimal newValue = value.setScale(0, RoundingMode.HALF_UP);
////        System.out.println(newValue);
////        Time time =new Time()
////        Calendar calendar = Calendar.getInstance(); // gets current instance of the calendar
////
////        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
////        System.out.println(formatter.format(calendar.getTime()));
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date = new Date(System.currentTimeMillis());
//
////        System.out.println(formatter.format(date));
//        Calendar cal = Calendar.getInstance();
//        PCContext.getFunctions().getDBTime();
//        //Date转Calendar
//        cal.setTime(date);
//        Time time = new Time(cal);
//        System.out.println(time);
//        PCContext.getFunctions().getMasterRecipe().
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");
//        MasterRecipeFilter masterRecipeFilter = PCContext.getFunctions().createMasterRecipeFilter().forn
        WorkFlow workFlow =  PCContext.getFunctions().getWorkFlowByName("WF2200015621");


    }
}
