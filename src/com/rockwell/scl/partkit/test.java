package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.*;
import com.rockwell.jms.JmsMessageEvent;
import com.rockwell.livedata.TagData;
import com.rockwell.mes.commons.base.ifc.choicelist.IMESChoiceElement;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.commons.base.ifc.services.ServiceFactory;
import com.rockwell.mes.commons.livedata.ifc.IMESTagGroup;
import com.rockwell.mes.commons.livedata.ifc.IMESTagGroupDefinition;
import com.rockwell.mes.services.s88equipment.ifc.*;
import com.rockwell.mes.services.s88equipment.ifc.automation.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.ecs.html.S;

import javax.jms.JMSException;
import java.io.Serializable;
import java.util.*;


public class test {
    public static List<IMESEquipmentProperty<?>> getAllAutomationProperties(IMESS88Equipment equipment) {
        ArrayList var1 = new ArrayList();
        if (equipment != null) {
            List var2 = equipment.getProperties();
            if (var2 != null && !var2.isEmpty()) {
                Iterator var3 = var2.iterator();

                while(var3.hasNext()) {
                    IMESEquipmentProperty var4 = (IMESEquipmentProperty)var3.next();
                    IMESChoiceElement var5 = var4.getEquipmentPropertyType().getUsage();
                    if (var5 == EnumEqmPropertyTypeUsage.AUTOMATION) {
                        var1.add(var4);
                    }
                }
            }
        }

        return var1;
    }
    public static void main(String[] args) throws Exception {
        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.5");
        IAutomationService aiService = (IAutomationService) ServiceFactory.getService(IAutomationService.class);
        IMESEquipmentProperty eqmProperty;
        IMESEquipmentPropertyType imesEquipmentPropertyType;
        List<IMESEquipmentPropertyType> imesEquipmentPropertyTypeList = new ArrayList<>();
        List<EquipmentPropertyTag> equipmentPropertyTagList = new ArrayList<>();


        //        IMESS88EquipmentFilter imess88EquipmentFilter;

//        EquipmentFilter equipmentFilter  =   PCContext.getFunctions().createEquipmentFilter();
//        Vector equipmentList = PCContext.getFunctions().getFilteredEquipment((EquipmentFilter) imess88EquipmentFilter);
//        System.out.println(equipmentList.size());
        IS88EquipmentService is88EquipmentService =(IS88EquipmentService) ServiceFactory.getService(IS88EquipmentService.class);

        IMESS88Equipment imess88Equipment;
        imess88Equipment = is88EquipmentService.loadEquipmentByIdentifier("S1-07-147");
        imess88Equipment.refresh();
        List<IMESEquipmentProperty<?>> imesEquipmentPropertyList = getAllAutomationProperties(imess88Equipment);
        for(int i =0;i<imesEquipmentPropertyList.size();i++){
//            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType());
//            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagGroupDefinition());
//            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagGroupDefinition().getTagGroups());
//            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagDefinitions().get(0));
            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getIdentifier());
            imesEquipmentPropertyTypeList.add(imesEquipmentPropertyList.get(i).getEquipmentPropertyType());
        }
        imesEquipmentPropertyTypeList.get(0).getIdentifier();
        equipmentPropertyTagList = aiService.loadDefaultPropertyTypeTagIDs(imess88Equipment,imesEquipmentPropertyTypeList);
//        System.out.println(equipmentPropertyTagList.get(0).getTagID());
        List<String> tags = new ArrayList<>();

        tags.add(equipmentPropertyTagList.get(0).getTagID());
        ITagResultContainer var8 = aiService.readValues(imess88Equipment, imesEquipmentPropertyTypeList.get(0), tags, LiveDataSource.OPCDevice);

        var8.getResultsAsValues();

        Set<Map.Entry<String,Object>> entrySet =  var8.getResultsAsValues().entrySet();
        for(Map.Entry<String,Object> entry:entrySet){

            System.out.println(entry.getKey()+":"+entry.getValue());

        }
        ITagResultContainer var9 = aiService.readValues(imess88Equipment, imesEquipmentPropertyTypeList.get(1), tags, LiveDataSource.OPCDevice);

        var9.getResultsAsValues();

        Set<Map.Entry<String,Object>> entrySet1 =  var9.getResultsAsValues().entrySet();
        for(Map.Entry<String,Object> entry:entrySet1){

            System.out.println(entry.getKey()+":"+entry.getValue());

        }

//        imesEquipmentPropertyType = imesEquipmentPropertyList.get(0).getEquipmentPropertyType();
//        imesEquipmentPropertyTypeList.add( imesEquipmentPropertyType);
//        List<EquipmentPropertyTag> equipmentPropertyTagList = aiService.loadDefaultPropertyTypeTagIDs(imess88Equipment,imesEquipmentPropertyTypeList);
//        for(int i =0;i< equipmentPropertyTagList.size();i++){
//            System.out.println(equipmentPropertyTagList.get(i));
//        }
//        List<IMESTagGroupDefinition> imesTagGroupDefinitions = new ArrayList<>();
//        List<IMESTagGroup> imesTagGroups = new ArrayList<>();
//        List<String> tags = new ArrayList<>();
//        tags.add("1-3C-15");
//        ITagResultContainer var8 = aiService.readValues(imess88Equipment,imesEquipmentPropertyType, tags, LiveDataSource.OPCDevice);
//        var8.getResultsAsValues();
//        Set<Map.Entry<String,Object>> entrySet =  var8.getResultsAsValues().entrySet();
//        for(Map.Entry<String,Object> entry:entrySet){
//
//            System.out.println(entry.getKey()+":"+entry.getValue());
//
//        }
//        System.out.println(imesEquipmentPropertyList.size());
//        for(int i =0;i<imesEquipmentPropertyList.size();i++){
//            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType());
//            imesTagGroupDefinitions.add( imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagGroupDefinition());
////            System.out.println(imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagGroupDefinition());
////            List<IMESTagGroup> imesTagGroups = imesEquipmentPropertyList.get(i).getEquipmentPropertyType().getTagGroupDefinition().getTagGroups();
////            for(int j =0;j<imesTagGroups.size();i++){
////                System.out.println(imesTagGroups.get(j).getIdentifier());
////            }
//        }
//        if(imesTagGroupDefinitions.size()>0){
//            for(int j = 0;j<imesTagGroupDefinitions.size();j++){
//               imesTagGroups.addAll(imesTagGroupDefinitions.get(j).getTagGroups());
//            }
//        }
//        for(int k =0;k< imesTagGroups.size();k++){
//            System.out.println(imesTagGroups.get(k).getIdentifier());
//        }

    }
}
