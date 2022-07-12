package com.rockwell.scl.partkit;

import com.datasweep.compatibility.client.DatasweepException;
import com.rockwell.scl.partkit.model.MaterialEntity;
import com.rockwell.scl.partkit.model.PartEntity;

public class AddPart {
    public static void main(String[] args) throws DatasweepException {

        System.setProperty("com.rockwell.test.username", "BJMESADMIN");
        System.setProperty("com.rockwell.test.password", "CNK2sva1!");
        System.setProperty("HOST_ADDRESS", "192.168.59.10");
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setPartNumber("I000027");
        materialEntity.setMaterialType("ZB00");
        materialEntity.setDescription("中硼硅玻璃管制硅化镀膜注射剂瓶");
        materialEntity.setMaterialGroup("");
        materialEntity.setUnitOfMeasure("只");
        materialEntity.setGmpName("4ml 中硼硅玻璃管制硅化镀膜注射剂瓶 2080只/箱 肖特");
        materialEntity.setLotNumber("");
        PartEntity partEntity = new PartEntity();
        partEntity.createOrUpdatePartObject( materialEntity);

    }
}
