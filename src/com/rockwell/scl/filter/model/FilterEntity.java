package com.rockwell.scl.filter.model;

/**
 * @author RWang18
 */
public class FilterEntity {

    private String serialNo;
    private String manufacturer;
    private String filterType;
    private String filterOpenDate;
    private String filterOpenDateExpiration;
    private String filterAperture;
    private String unqualifiedUpLimit;
    private String unqualifiedIntegrityQty;
    private String integrityTestExpiration;
    private String integrityTestDuration;
    private String integrityTestResult;
    private String integrityTestValue;
    private String integrityTestNote;
    private String sterUpLimit;
    private String sterCount;
    private String sterSerialNumber;
    private String sterBatch;
    private String usageCountUpLimit;
    private String currentUsageCount;
    private String continueUsable;
    private String continueUsableNote;
    private String workshop;
    private String productionLine;
    private String batch;
    private String materialName;
    private String operator;
    private String operateType;
    private String processedMaterialNo;
    private String processedMaterialName;
    private String processedBatch;
    private String recipePath;
    private String comments;
    private String hasCollectedAsConsume;
    private String usage;
    private String mountEquipment;
    private String mountLocation;
    private String svCode;

    public String getCurrentUsageCount() {
        return currentUsageCount;
    }

    public void setCurrentUsageCount(String currentUsageCount) {
        this.currentUsageCount = currentUsageCount;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public String getFilterAperture() {
        return filterAperture;
    }

    public String getFilterOpenDate() {
        return filterOpenDate;
    }

    public String getFilterOpenDateExpiration() {
        return filterOpenDateExpiration;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getIntegrityTestDuration() {
        return integrityTestDuration;
    }

    public String getIntegrityTestExpiration() {
        return integrityTestExpiration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public String getContinueUsable() {
        return continueUsable;
    }

    public String getIntegrityTestNote() {
        return integrityTestNote;
    }

    public String getBatch() {
        return batch;
    }

    public String getIntegrityTestResult() {
        return integrityTestResult;
    }

    public String getIntegrityTestValue() {
        return integrityTestValue;
    }

    public String getUnqualifiedIntegrityQty() {
        return unqualifiedIntegrityQty;
    }

    public String getUnqualifiedUpLimit() {
        return unqualifiedUpLimit;
    }

    public void setFilterOpenDate(String filterOpenDate) {
        this.filterOpenDate = filterOpenDate;
    }

    public void setFilterAperture(String filterAperture) {
        this.filterAperture = filterAperture;
    }

    public String getSterCount() {
        return sterCount;
    }

    public void setFilterOpenDateExpiration(String filterOpenDateExpiration) {
        this.filterOpenDateExpiration = filterOpenDateExpiration;
    }

    public String getContinueUsableNote() {
        return continueUsableNote;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public String getSterBatch() {
        return sterBatch;
    }

    public void setIntegrityTestDuration(String integrityTestDuration) {
        this.integrityTestDuration = integrityTestDuration;
    }

    public void setIntegrityTestExpiration(String integrityTestExpiration) {
        this.integrityTestExpiration = integrityTestExpiration;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setIntegrityTestResult(String integrityTestResult) {
        this.integrityTestResult = integrityTestResult;
    }

    public String getSterUpLimit() {
        return sterUpLimit;
    }

    public void setIntegrityTestNote(String integrityTestNote) {
        this.integrityTestNote = integrityTestNote;
    }

    public String getSterSerialNumber() {
        return sterSerialNumber;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUsageCountUpLimit() {
        return usageCountUpLimit;
    }

    public void setIntegrityTestValue(String integrityTestValue) {
        this.integrityTestValue = integrityTestValue;
    }

    public void setUnqualifiedIntegrityQty(String unqualifiedIntegrityQty) {
        this.unqualifiedIntegrityQty = unqualifiedIntegrityQty;
    }

    public void setUnqualifiedUpLimit(String unqualifiedUpLimit) {
        this.unqualifiedUpLimit = unqualifiedUpLimit;
    }

    public String getComments() {
        return comments;
    }

    public void setSterBatch(String sterBatch) {
        this.sterBatch = sterBatch;
    }

    public void setSterCount(String sterCount) {
        this.sterCount = sterCount;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setContinueUsable(String continueUsable) {
        this.continueUsable = continueUsable;
    }

    public void setSterUpLimit(String sterUpLimit) {
        this.sterUpLimit = sterUpLimit;
    }

    public String getMountEquipment() {
        return mountEquipment;
    }

    public void setSterSerialNumber(String sterSerialNumber) {
        this.sterSerialNumber = sterSerialNumber;
    }

    public String getHasCollectedAsConsume() {
        return hasCollectedAsConsume;
    }

    public String getMountLocation() {
        return mountLocation;
    }

    public void setUsageCountUpLimit(String usageCountUpLimit) {
        this.usageCountUpLimit = usageCountUpLimit;
    }

    public String getWorkshop() {
        return workshop;
    }

    public String getOperateType() {
        return operateType;
    }

    public String getProcessedBatch() {
        return processedBatch;
    }

    public String getProcessedMaterialName() {
        return processedMaterialName;
    }

    public String getProcessedMaterialNo() {
        return processedMaterialNo;
    }

    public String getRecipePath() {
        return recipePath;
    }

    public String getSvCode() {
        return svCode;
    }

    public String getUsage() {
        return usage;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setContinueUsableNote(String continueUsableNote) {
        this.continueUsableNote = continueUsableNote;
    }

    public void setHasCollectedAsConsume(String hasCollectedAsConsume) {
        this.hasCollectedAsConsume = hasCollectedAsConsume;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setMountEquipment(String mountEquipment) {
        this.mountEquipment = mountEquipment;
    }

    public void setMountLocation(String mountLocation) {
        this.mountLocation = mountLocation;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setProcessedBatch(String processedBatch) {
        this.processedBatch = processedBatch;
    }

    public void setProcessedMaterialName(String processedMaterialName) {
        this.processedMaterialName = processedMaterialName;
    }

    public void setProcessedMaterialNo(String processedMaterialNo) {
        this.processedMaterialNo = processedMaterialNo;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public void setRecipePath(String recipePath) {
        this.recipePath = recipePath;
    }

    public void setSvCode(String svCode) {
        this.svCode = svCode;
    }



    public void setUsage(String usage) {
        this.usage = usage;
    }

}
