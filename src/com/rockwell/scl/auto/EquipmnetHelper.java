package com.rockwell.scl.auto;

import com.datasweep.compatibility.client.Equipment;
import com.datasweep.compatibility.client.EquipmentFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.services.s88equipment.ifc.IMESS88Equipment;

import java.util.List;

/**
 * @author RWang18
 */
public class EquipmnetHelper {

    public EquipmnetHelper() {

        EquipmentFilter filter= PCContext.getFunctions().createEquipmentFilter().orderByName(true);
        List<Equipment> equipmentList=PCContext.getFunctions().getFilteredEquipment(filter);
        IMESS88Equipment  imess88Equipment= (IMESS88Equipment) equipmentList.get(0);

    }
}
