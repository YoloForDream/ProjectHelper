package com.rockwell.scl.partkit.model;

import com.datasweep.compatibility.client.*;
import com.datasweep.compatibility.manager.UnitOfMeasureManager;
import com.datasweep.plantops.common.measuredvalue.IUnitOfMeasure;
import com.rockwell.mes.commons.base.ifc.services.PCContext;


import org.apache.commons.lang3.StringUtils;


import java.util.Vector;


/**
 * @author RWang18
 */
public class PartEntity {

    String partNames ;

    Response response ;
    public void deletePart() {
        PartFilter partFilter = PCContext.getFunctions().createPartFilter();
        Vector parts = PCContext.getFunctions().getFilteredParts(partFilter);
        for (int i = 0; i < parts.size(); i++) {
            if (partNames.equals(parts.get(i).toString())) {
                response = PCContext.getFunctions().deletePart((Part) parts.get(i));
                response.getResult().toString();
            }
        }

    }

    public PartEntity(String partNames) {
        this.partNames = partNames;


    }
    public PartEntity() {


    }


    public void createOrUpdatePartObject(MaterialEntity materialEntityInsert) {
        Part part = PCContext.getFunctions().getPart(materialEntityInsert.getPartNumber());
        if (part == null) {//新增物料
            Part newPart = PCContext.getFunctions().createPart();
            UnitOfMeasureManager uomFilter = PCContext.getServerImpl().getUnitOfMeasureManager();
            IUnitOfMeasure uom;
            try {
                uom = uomFilter.getUnitOfMeasureBySymbol(materialEntityInsert.getUnitOfMeasure());
                UnitOfMeasure localUnitOfMeasure = (UnitOfMeasure) uom;
                newPart.setUDA(localUnitOfMeasure, "X_UnitOfMeasure");
            } catch (Exception e) {
                e.printStackTrace();
            }

            newPart.setPartNumber(materialEntityInsert.getPartNumber());
            /**
             * 使用产品通用名
             * 2022年6月18日16:32:27
             * yxu17
             */
            newPart.setDescription(materialEntityInsert.getGmpName().trim());
            try {
                if(materialEntityInsert.getGmpName().length() <= 50) {
                    newPart.setUDA(materialEntityInsert.getGmpName(), "X_shortDescription");
                }
                newPart.setUDA(MaterialTypeEnum.getMesCodeBySapCode(materialEntityInsert.getMaterialType()), "X_materialType");

                /**
                 * 2022年5月30日14:51:51
                 */
                if (StringUtils.isNotEmpty(materialEntityInsert.getMaterialGroup())&&!materialEntityInsert.getMaterialGroup().equals("NA")) {
                    newPart.setUDA(materialEntityInsert.getMaterialGroup(), "SV_materialGroup");
                }
                //更新货号字段(SV_itemNo)
                String lotNumber = StringUtils.isNotEmpty(materialEntityInsert.getLotNumber().trim())
                        ? materialEntityInsert.getLotNumber() : "NA";
                newPart.setUDA(lotNumber, "SV_itemNo");
            } catch (DatasweepException e) {
                e.printStackTrace();
            }

            newPart.setPartRevision("1");
            newPart.save(PCContext.getFunctions().getDBTime(), "save part,part=" + materialEntityInsert.getPartNumber(), null);

        } else {//更新物料
            UnitOfMeasureManager uomFilter = PCContext.getServerImpl().getUnitOfMeasureManager();
            IUnitOfMeasure uom;
            try {
                uom = uomFilter.getUnitOfMeasureBySymbol(materialEntityInsert.getUnitOfMeasure());
                UnitOfMeasure localUnitOfMeasure = (UnitOfMeasure) uom;
                part.setUDA(localUnitOfMeasure, "X_UnitOfMeasure");
            } catch (Exception e) {
                e.printStackTrace();
            }

            part.setPartNumber(materialEntityInsert.getPartNumber());
            /**
             * 使用产品通用名
             * 2022年6月18日16:32:27
             * yxu17
             */
            part.setDescription(materialEntityInsert.getGmpName().trim());
            try {
                if(materialEntityInsert.getGmpName().length() <= 50) {
                    part.setUDA(materialEntityInsert.getGmpName(), "X_shortDescription");
                }
                part.setUDA(MaterialTypeEnum.getMesCodeBySapCode(materialEntityInsert.getMaterialType()), "X_materialType");
                /**
                 * 2022年5月30日14:51:51
                 */
                if (StringUtils.isNotEmpty(materialEntityInsert.getMaterialGroup())&&!materialEntityInsert.getMaterialGroup().equals("NA")) {
                    part.setUDA(materialEntityInsert.getMaterialGroup().trim(), "SV_materialGroup");
                }

                //更新货号字段(SV_itemNo)
                String lotNumber = StringUtils.isNotEmpty(materialEntityInsert.getLotNumber().trim())
                        ? materialEntityInsert.getLotNumber() : "NA";
                part.setUDA(lotNumber.trim(), "SV_itemNo");
            } catch (DatasweepException e) {
                e.printStackTrace();
            }
            part.setPartRevision("1");
            part.save(PCContext.getFunctions().getDBTime(), "save part,part=" + materialEntityInsert.getPartNumber(), null);
        }

    }

}
