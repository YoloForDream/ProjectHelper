package com.rockwell.scl.auto.opcrealtime;

import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultOPCTagMonitorModel implements OPCTagMonitorModel {
    public DefaultOPCTagMonitorModel(String equipmentId, String equipmentProperty) {
    }
//    private String equipmentId;
//    private String equipmentProperty;
//
//    public DefaultOPCTagMonitorModel(String equipmentId, String equipmentProperty) {
//    }
//
//    public void setEquipmentProperty(String equipmentProperty) {
//        this.equipmentProperty = equipmentProperty;
//    }
//
//    public String getEquipmentId() {
//        return equipmentId;
//    }
//
//    public String getEquipmentProperty() {
//        return equipmentProperty;
//    }
//
//    public void setEquipmentId(String equipmentId) {
//        this.equipmentId = equipmentId;
//    }
//
//    @Override
//    public List<Double>[] getRealTimeData() throws AutomationException {
//        List<Date> xData = new ArrayList<>(10);
//        List<BigDecimal> yData = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//
//                DateTime dt = DateTime.now().toDateTime();
////                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
////                Date date = new Date();
////                 String label = sdf.format(date);
//                xData.add(dt.toDate());
//                ReadOPCHelper readOPCHelper= new ReadOPCHelper("S1-03-136","压力51");
//
////                System.out.println(readOPCHelper.getValue());
//                yData.add(readOPCHelper.getValue());
////                Thread.sleep(5);
//
//        }
//        return new List[]{xData, yData};
//    }
    private static final DateTimeFormatter FORMAT_NINETEEN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public List<Double>[] getRealTimeData(String equipmentId, String equipmentProperty) throws AutomationException {
        List<Date> xData = new ArrayList<>(10);
        List<BigDecimal> yData = new ArrayList<>(10);
        for (int i = 0; i < 4; i++) {

            DateTime dt = DateTime.now().toDateTime(DateTimeZone.getDefault());
//                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//                Date date = new Date();
//                 String label = sdf.format(date);
            xData.add(dt.toDate());
//            ReadOPCHelper readOPCHelper= new ReadOPCHelper("S1-03-136","压力51");
            ReadOPCHelper readOPCHelper= new ReadOPCHelper(equipmentId,equipmentProperty);

//                System.out.println(readOPCHelper.getValue());
            yData.add(readOPCHelper.getValue());
//                Thread.sleep(5);

        }
        return new List[]{xData, yData};
    }


//    public String getCurrenTime() {
//        Date date = new Date();
//        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        return sdf.format(date);
//    }
}
