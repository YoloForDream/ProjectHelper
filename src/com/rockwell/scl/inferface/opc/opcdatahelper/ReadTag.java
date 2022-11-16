package com.rockwell.scl.inferface.opc.opcdatahelper;


import com.rockwell.mes.commons.base.ifc.choicelist.IMESChoiceElement;
import com.rockwell.mes.commons.base.ifc.services.ServiceFactory;
import com.rockwell.mes.services.s88equipment.ifc.*;
import com.rockwell.mes.services.s88equipment.ifc.automation.*;
import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;

import java.util.*;


/**
 * @author RWang18
 */
public class ReadTag extends Thread {
    private final List<String> tags;
    private final String equipmentName;
    private final Map<String, Object> map;
    private final Map<String, Object> sourceMap;
    List<IMESEquipmentPropertyType> imesEquipmentPropertyTypeList;

    @Override
    public void run() {
        try {
            getTagValue();
        } catch (AutomationException e) {
            e.printStackTrace();
        }
    }

    public ReadTag(String equipmentName) {
        this.equipmentName = equipmentName;
        tags = new ArrayList<>();
        imesEquipmentPropertyTypeList = new ArrayList<>();
        map = new HashMap<>();
        sourceMap = new HashMap<>();
    }

    public static List<IMESEquipmentProperty<?>> getAllAutomationProperties(IMESS88Equipment equipment) {
        ArrayList var1 = new ArrayList();
        if (equipment != null) {
            List var2 = equipment.getProperties();
            if (var2 != null && !var2.isEmpty()) {
                Iterator var3 = var2.iterator();
                while (var3.hasNext()) {
                    IMESEquipmentProperty var4 = (IMESEquipmentProperty) var3.next();
                    IMESChoiceElement var5 = var4.getEquipmentPropertyType().getUsage();
                    if (var5 == EnumEqmPropertyTypeUsage.AUTOMATION) {
                        var1.add(var4);
                    }
                }
            }
        }
        return var1;
    }

    public Map<String, Object> getTagValue() throws AutomationException {
        IAutomationService aiService = ServiceFactory.getService(IAutomationService.class);
        IS88EquipmentService is88EquipmentService = ServiceFactory.getService(IS88EquipmentService.class);
        IMESS88Equipment imess88Equipment = is88EquipmentService.loadEquipmentByIdentifier(equipmentName);
        imess88Equipment.refresh();
        List<IMESEquipmentProperty<?>> imesEquipmentPropertyList = getAllAutomationProperties(imess88Equipment);
        for (int i = 0; i < imesEquipmentPropertyList.size(); i++) {
            imesEquipmentPropertyTypeList.add(imesEquipmentPropertyList.get(i).getEquipmentPropertyType());
        }
        List<EquipmentPropertyTag> equipmentPropertyTagList = aiService.loadDefaultPropertyTypeTagIDs(imess88Equipment, imesEquipmentPropertyTypeList);
        tags.add(equipmentPropertyTagList.get(0).getTagID());
        IMultipleTagsResultContainer iMultipleTagsResultContainer = aiService.readMultipleTagValues(imess88Equipment, equipmentPropertyTagList, LiveDataSource.OPCDevice);
        Boolean allValuesOK = iMultipleTagsResultContainer.allValuesOK();
        if (allValuesOK == true) {
            for (int i = 0; i < imesEquipmentPropertyTypeList.size(); i++) {
                ITagResultContainer iTagResultContainer = aiService.readValues(imess88Equipment, imesEquipmentPropertyTypeList.get(i), tags, LiveDataSource.OPCDevice);
                String tagName = imesEquipmentPropertyList.get(i).getIdentifier();
                Set<Map.Entry<String, Object>> entrySet = iTagResultContainer.getResultsAsValues().entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    sourceMap.put(tagName, entry.getValue());
                }
                map.putAll(sourceMap);
            }
        } else {
            List<EquipmentPropertyTag> equipmentPropertyTagBadList = iMultipleTagsResultContainer.getBadEquipmentPropertyTags();
            List<IMESEquipmentPropertyType> imesEquipmentPropertyTypeListBad = new ArrayList<IMESEquipmentPropertyType>();
            for (EquipmentPropertyTag badTag : equipmentPropertyTagBadList) {
                imesEquipmentPropertyTypeListBad.add(badTag.getPropertyType());
            }
            imesEquipmentPropertyTypeList.removeAll(imesEquipmentPropertyTypeListBad);
            for (int i = 0; i < imesEquipmentPropertyTypeList.size(); i++) {
                ITagResultContainer iTagResultContainer = aiService.readValues(imess88Equipment, imesEquipmentPropertyTypeList.get(i), tags, LiveDataSource.OPCDevice);
                String tagName = imesEquipmentPropertyList.get(i).getIdentifier();
                Set<Map.Entry<String, Object>> entrySet = iTagResultContainer.getResultsAsValues().entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    sourceMap.put(tagName, entry.getValue());
                }
                map.putAll(sourceMap);
            }
            for (IMESEquipmentPropertyType imesEquipmentPropertyType : imesEquipmentPropertyTypeListBad) {
                map.put(imesEquipmentPropertyType.getIdentifier(), "bad quality");
            }
        }
        return map;
    }
}