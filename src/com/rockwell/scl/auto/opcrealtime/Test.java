package com.rockwell.scl.auto.opcrealtime;

import com.rockwell.mes.commons.base.ifc.services.ServiceFactory;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentProperty;
import com.rockwell.mes.services.s88equipment.ifc.IMESS88Equipment;
import com.rockwell.mes.services.s88equipment.ifc.IS88EquipmentService;
import com.rockwell.mes.services.s88equipment.ifc.automation.IAutomationService;
import com.rockwell.scl.auto.ReadTag;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
//        System.setProperty("com.rockwell.test.username", "ww");
//        System.setProperty("com.rockwell.test.password", "admin");
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.20");
        String equipmentName ="S1-03-136";
        IAutomationService aiService = ServiceFactory.getService(IAutomationService.class);
        IS88EquipmentService is88EquipmentService = ServiceFactory.getService(IS88EquipmentService.class);
        IMESS88Equipment imess88Equipment = is88EquipmentService.loadEquipmentByIdentifier(equipmentName);
        List<IMESEquipmentProperty<?>> imesEquipmentPropertyList = ReadTag.getAllAutomationProperties(imess88Equipment);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < imesEquipmentPropertyList.size(); i++) {
            System.out.println(imesEquipmentPropertyList.get(i).getIdentifier());
            list.add(imesEquipmentPropertyList.get(i).getIdentifier());
        }

//        list.add(imesEquipmentPropertyList.get(1).getIdentifier());

        new OPCTagMonitor(equipmentName,imesEquipmentPropertyList.get(1).getIdentifier());
//        new OPCTagMonitor(equipmentName,list);
    }
}
