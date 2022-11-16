package com.rockwell.scl.filter.datahandle;

import com.datasweep.compatibility.client.*;
import com.datasweep.compatibility.manager.UnitOfMeasureManager;
import com.datasweep.compatibility.ui.Time;

import com.datasweep.plantops.common.measuredvalue.IUnitOfMeasure;

import com.rockwell.mes.commons.base.ifc.functional.MeasuredValueUtilities;
import com.rockwell.mes.commons.base.ifc.services.PCContext;

import com.rockwell.scl.filter.at.IMESSVFilterInfo;
import com.rockwell.scl.filter.at.MESSVFilterInfo;
import com.rockwell.scl.filter.at.MESSVFilterInfoFilter;
import com.rockwell.scl.filter.model.FilterEntity;

import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




/**
 * @author RWang18
 */
public class FilterHelper {

    private static final String NULL_VALUE = "NA";
    private static final String TRUE = "是";
    private static final String FALSE = "否";
    private static final String FALSE_TRANSPORT_LONG = "20";
    private static final String TRUE_TRANSPORT_LONG = "10";

    public MeasuredValue covertStringToMeasuredValue(String str) throws Exception {
        UnitOfMeasureManager uomFilter = PCContext.getServerImpl().getUnitOfMeasureManager();
        IUnitOfMeasure uom;
        str = str.replaceAll("\\s+","").replace("\u00a0","");
        String value = str.replaceAll("[^0-9.]","");
        BigDecimal valueBigDecimal = new BigDecimal(value.trim());
        String uomName =  str.replaceAll("[0-9\\s]","").replace(".","");
                uom = uomFilter.getUnitOfMeasureBySymbol(uomName.trim());
                UnitOfMeasure localUnitOfMeasure = (UnitOfMeasure) uom;
        MeasuredValue measuredValue;
        measuredValue = MeasuredValueUtilities.createMV(valueBigDecimal,localUnitOfMeasure);
        return measuredValue;
    }

    public void createOrUpdateFilter(FilterEntity entity) throws Exception {
        MESSVFilterInfoFilter messvFilterInfoFilter = new MESSVFilterInfoFilter();
        messvFilterInfoFilter.forSerialNoEqualTo(entity.getSerialNo());
        List<IMESSVFilterInfo>  filterInfos;
        filterInfos = messvFilterInfoFilter.getFilteredObjects();
        if (filterInfos.size() > 0) {
            IMESSVFilterInfo info = filterInfos.get(0);
            if(!Objects.equals(entity.getSerialNo(), NULL_VALUE))
            {
                info.setSerialNo(entity.getSerialNo());
            }
            if(!Objects.equals(entity.getManufacturer(), NULL_VALUE)){
                
                info.setManufacturer(entity.getManufacturer());
            }
            if(!Objects.equals(entity.getFilterOpenDate(), NULL_VALUE)){
                info.setFilterType(entity.getFilterType());
            }
            if(!Objects.equals(entity.getCurrentUsageCount(), NULL_VALUE)){
                info.setCurrentUsageCount(entity.getCurrentUsageCount());
            }
            if(!Objects.equals(entity.getFilterOpenDate(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getFilterOpenDate());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setFilterOpenDate(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getFilterOpenDateExpiration(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getFilterOpenDateExpiration());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setFilterOpenDateExpiration(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getFilterAperture(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getFilterAperture());
                info.setFilterAperture(measuredValue);
            }
            if(!Objects.equals(entity.getUnqualifiedUpLimit(), NULL_VALUE)){
                info.setUnqualifiedUpLimit(Long.valueOf(entity.getUnqualifiedUpLimit()));
            }
            if(!Objects.equals(entity.getUnqualifiedIntegrityQty(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getUnqualifiedIntegrityQty())).longValue();
                info.setUnqualifiedIntegrityQty(count);
            }
            if(!Objects.equals(entity.getIntegrityTestExpiration(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getIntegrityTestExpiration());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setIntegrityTestExpiration(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getIntegrityTestDuration(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getIntegrityTestDuration());
                info.setIntegrityTestDuration(measuredValue);
            }
            if(!Objects.equals(entity.getIntegrityTestResult(), NULL_VALUE)) {
                info.setIntegrityTestResult(entity.getIntegrityTestResult());
            }
            if(!Objects.equals(entity.getIntegrityTestValue(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getIntegrityTestValue());
                info.setIntegrityTestValue(measuredValue);
            }
            if(!Objects.equals(entity.getIntegrityTestNote(), NULL_VALUE)){
                info.setIntegrityTestNote(entity.getIntegrityTestNote());
            }
            if(!Objects.equals(entity.getSterUpLimit(), NULL_VALUE)){
                info.setSterUpLimit(Long.valueOf(entity.getSterUpLimit()));
            }
            if(!Objects.equals(entity.getSterCount(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getSterCount())).longValue();
                info.setSterCount(count);
            }
            if(!Objects.equals(entity.getSterSerialNumber(), NULL_VALUE)){
                info.setSterSerialNumber(entity.getSterSerialNumber());
            }
            if(!Objects.equals(entity.getSterBatch(), NULL_VALUE)) {
                info.setSterBatch(entity.getSterBatch());
            }
            if(!Objects.equals(entity.getUsageCountUpLimit(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getUsageCountUpLimit())).longValue();
                info.setUsageCountUpLimit(Long.valueOf(count));
            }
            if(!Objects.equals(entity.getContinueUsable(), NULL_VALUE)){
                if(entity.getContinueUsable().equals(TRUE)){
                    info.setContinueUsable(Long.valueOf(TRUE_TRANSPORT_LONG));
                }
                else if(entity.getContinueUsable().equals(FALSE)) {
                    info.setContinueUsable(Long.valueOf(FALSE_TRANSPORT_LONG));
                }
            }
            if(!Objects.equals(entity.getContinueUsableNote(), NULL_VALUE)){
                info.setContinueUsableNote(entity.getContinueUsableNote());
            }
            if(!Objects.equals(entity.getWorkshop(), NULL_VALUE)){
                info.setWorkshop(entity.getWorkshop());
            }
           if(!Objects.equals(entity.getProductionLine(), NULL_VALUE)){
               info.setProductionLine(entity.getProductionLine());
           }
           if(!Objects.equals(entity.getBatch(), NULL_VALUE)) {
               info.setBatch(entity.getBatch());
           }
           if(!Objects.equals(entity.getMaterialName(), NULL_VALUE)) {
               info.setMaterialName(entity.getMaterialName());
           }
           if(!Objects.equals(entity.getOperator(), NULL_VALUE)){
               info.setOperator(entity.getOperator());
           }
           if(!Objects.equals(entity.getOperateType(), NULL_VALUE)){
                info.setOperateType(entity.getOperateType());
            }
            else {
                info.setOperateType("40");
            }
            if(!Objects.equals(entity.getProcessedMaterialNo(), NULL_VALUE)){
                info.setProcessedMaterialNo(entity.getProcessedMaterialNo());
            }
            if(!Objects.equals(entity.getMaterialName(), NULL_VALUE)){
                info.setMaterialName(entity.getMaterialName());
            }
            if(!Objects.equals(entity.getRecipePath(), NULL_VALUE)){
                info.setRecipePath(entity.getRecipePath());
            }
            if(!Objects.equals(entity.getComments(), NULL_VALUE)){
               info.setComments(entity.getComments());
            }
            if(!Objects.equals(entity.getHasCollectedAsConsume(), NULL_VALUE)){
                info.setHasCollectedAsConsume(Long.valueOf(entity.getHasCollectedAsConsume()));
            }
            if(!Objects.equals(entity.getUsage(), NULL_VALUE)){
                info.setUsage(entity.getUsage());
            }
            if(!Objects.equals(entity.getMountEquipment(), NULL_VALUE)){
                info.setMountEquipment(entity.getMountEquipment());
            }
            if(!Objects.equals(entity.getMountLocation(), NULL_VALUE)){
                info.setMountLocation(entity.getMountLocation());
            }
            if(!Objects.equals(entity.getSvCode(), NULL_VALUE)){
                info.setSvCode(entity.getSvCode());
            }
            info.save();
        }else {
            MESSVFilterInfo info = new MESSVFilterInfo();
            if(!Objects.equals(entity.getSerialNo(), NULL_VALUE))
            {
                info.setSerialNo(entity.getSerialNo());
            }
            if(!Objects.equals(entity.getManufacturer(), NULL_VALUE)){

                info.setManufacturer(entity.getManufacturer());
            }
            if(!Objects.equals(entity.getFilterOpenDate(), NULL_VALUE)){
                info.setFilterType(entity.getFilterType());
            }
            if(!Objects.equals(entity.getFilterOpenDate(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getFilterOpenDate());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setFilterOpenDate(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getCurrentUsageCount(), NULL_VALUE)){
                info.setCurrentUsageCount(entity.getCurrentUsageCount());
            }
            if(!Objects.equals(entity.getFilterOpenDateExpiration(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getFilterOpenDateExpiration());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setFilterOpenDateExpiration(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getFilterAperture(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getFilterAperture());
                info.setFilterAperture(measuredValue);
            }
            if(!Objects.equals(entity.getUnqualifiedUpLimit(), NULL_VALUE)){
                info.setUnqualifiedUpLimit(Long.valueOf(entity.getUnqualifiedUpLimit()));
            }
            if(!Objects.equals(entity.getUnqualifiedIntegrityQty(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getUnqualifiedIntegrityQty())).longValue();
                info.setUnqualifiedIntegrityQty(count);
            }
            if(!Objects.equals(entity.getIntegrityTestExpiration(), NULL_VALUE)){
                SimpleDateFormat sdf= new SimpleDateFormat("M/dd/yyyy");
                try {
                    Date date =sdf.parse(entity.getIntegrityTestExpiration());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Time sysDate = new Time(calendar);
                    info.setIntegrityTestExpiration(sysDate);
                }catch (ParseException exception){
                    exception.printStackTrace();
                }
            }
            if(!Objects.equals(entity.getIntegrityTestDuration(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getIntegrityTestDuration());
                info.setIntegrityTestDuration(measuredValue);
            }
            if(!Objects.equals(entity.getIntegrityTestResult(), NULL_VALUE)) {
                info.setIntegrityTestResult(entity.getIntegrityTestResult());
            }
            if(!Objects.equals(entity.getIntegrityTestValue(), NULL_VALUE)){
                MeasuredValue measuredValue =covertStringToMeasuredValue(entity.getIntegrityTestValue());
                info.setIntegrityTestValue(measuredValue);
            }
            if(!Objects.equals(entity.getIntegrityTestNote(), NULL_VALUE)){
                info.setIntegrityTestNote(entity.getIntegrityTestNote());
            }
            if(!Objects.equals(entity.getSterUpLimit(), NULL_VALUE)){
                info.setSterUpLimit(Long.valueOf(entity.getSterUpLimit()));
            }
            if(!Objects.equals(entity.getSterCount(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getSterCount())).longValue();
                info.setSterCount(count);
            }
            if(!Objects.equals(entity.getSterSerialNumber(), NULL_VALUE)){
                info.setSterSerialNumber(entity.getSterSerialNumber());
            }
            if(!Objects.equals(entity.getSterBatch(), NULL_VALUE)) {
                info.setSterBatch(entity.getSterBatch());
            }
            if(!Objects.equals(entity.getUsageCountUpLimit(), NULL_VALUE)){
                Long count = new Double(Double.parseDouble(entity.getUsageCountUpLimit())).longValue();
                info.setUsageCountUpLimit(Long.valueOf(count));
            }
            if(!Objects.equals(entity.getContinueUsable(), NULL_VALUE)){
                if(entity.getContinueUsable().equals(TRUE)){
                    info.setContinueUsable(Long.valueOf(TRUE_TRANSPORT_LONG));
                }
                else if(entity.getContinueUsable().equals(FALSE)) {
                    info.setContinueUsable(Long.valueOf(FALSE_TRANSPORT_LONG));
                }
            }
            if(!Objects.equals(entity.getWorkshop(), NULL_VALUE)){
                info.setWorkshop(entity.getWorkshop());
            }
            if(!Objects.equals(entity.getProductionLine(), NULL_VALUE)){
                info.setProductionLine(entity.getProductionLine());
            }
            if(!Objects.equals(entity.getBatch(), NULL_VALUE)) {
                info.setBatch(entity.getBatch());
            }
            if(!Objects.equals(entity.getMaterialName(), NULL_VALUE)) {
                info.setMaterialName(entity.getMaterialName());
            }
            if(!Objects.equals(entity.getOperator(), NULL_VALUE)){

                info.setOperator(entity.getOperator());
            }
            if(!Objects.equals(entity.getOperateType(), NULL_VALUE)){
                info.setOperateType(entity.getOperateType());
            }
            else{
                info.setOperateType("40");
            }
            if(!Objects.equals(entity.getProcessedMaterialNo(), NULL_VALUE)){
                info.setProcessedMaterialNo(entity.getProcessedMaterialNo());
            }
            if(!Objects.equals(entity.getMaterialName(), NULL_VALUE)){
                info.setMaterialName(entity.getMaterialName());
            }
            if(!Objects.equals(entity.getRecipePath(), NULL_VALUE)){
                info.setRecipePath(entity.getRecipePath());
            }
            if(!Objects.equals(entity.getComments(), NULL_VALUE)){
                info.setComments(entity.getComments());
            }
            if(!Objects.equals(entity.getHasCollectedAsConsume(), NULL_VALUE)){
                info.setHasCollectedAsConsume(Long.valueOf(entity.getHasCollectedAsConsume()));
            }
            if(!Objects.equals(entity.getUsage(), NULL_VALUE)){
                info.setUsage(entity.getUsage());
            }
            if(!Objects.equals(entity.getMountEquipment(), NULL_VALUE)){
                info.setMountEquipment(entity.getMountEquipment());
            }
            if(!Objects.equals(entity.getMountLocation(), NULL_VALUE)){
                info.setMountLocation(entity.getMountLocation());
            }
            if(!Objects.equals(entity.getSvCode(), NULL_VALUE)){
                info.setSvCode(entity.getSvCode());
            }
            info.save();
        }
    }
}
