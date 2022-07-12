package com.rockwell.scl.partkit.model;



public class MaterialEntity {

    private String partNumber; //物料编码

    private String materialType; //物料类型（原料，成品，半成品等）uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu8

    private String description; //物料描述

    private String unitOfMeasure; //基本单位

    private String needQualityCheck; //生产收货质检需标注出来

    private String specification; //规格

    private String tradeName; //产成品商品名

    private String dosage; //剂型

    private String packageType; //包装形式

    private String regist; //批准文号

    private String gmpName; //产品通用名

    private String factory; //工厂

    private String updateDate; //主数据记录更新时间

    private String isControl; //是否受控。X:受控    空：不受控

    private String factoryGmpName;
    private String lotNumber;

    private String checkMethod;

    private String materialGroup;

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    /**
     * SCL
     * <p>
     * 备用字段，SAP定义
     */
    @Deprecated
    private String YL4;
    @Deprecated
    private String YL5;
    @Deprecated
    private String YL6;
    @Deprecated
    private String YL7;
    @Deprecated
    private String YL8;
    @Deprecated
    private String YL9;
    @Deprecated
    private String YL10;
    @Deprecated
    private String YL11;
    @Deprecated
    private String YL12;
    @Deprecated
    private String YL13;
    @Deprecated
    private String YL14;
    @Deprecated
    private String YL15;
    @Deprecated
    private String YL16;
    @Deprecated
    private String YL17;
    @Deprecated
    private String YL18;
    @Deprecated
    private String YL19;
    @Deprecated
    private String YL20;

    /**
     * SCL
     * <p>
     * 备用字段，SAP定义
     */

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getNeedQualityCheck() {
        return needQualityCheck;
    }

    public void setNeedQualityCheck(String needQualityCheck) {
        this.needQualityCheck = needQualityCheck;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getRegist() {
        return regist;
    }

    public void setRegist(String regist) {
        this.regist = regist;
    }

    public String getGmpName() {
        return gmpName;
    }

    public void setGmpName(String gmpName) {
        this.gmpName = gmpName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsControl() {
        return isControl;
    }

    public void setIsControl(String isControl) {
        this.isControl = isControl;
    }

    public String getFactoryGmpName() {
        return factoryGmpName;
    }

    public void setFactoryGmpName(String factoryGmpName) {
        this.factoryGmpName = factoryGmpName;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }

    public String getYL4() {
        return YL4;
    }

    public void setYL4(String YL4) {
        this.YL4 = YL4;
    }

    public String getYL5() {
        return YL5;
    }

    public void setYL5(String YL5) {
        this.YL5 = YL5;
    }

    public String getYL6() {
        return YL6;
    }

    public void setYL6(String YL6) {
        this.YL6 = YL6;
    }

    public String getYL7() {
        return YL7;
    }

    public void setYL7(String YL7) {
        this.YL7 = YL7;
    }

    public String getYL8() {
        return YL8;
    }

    public void setYL8(String YL8) {
        this.YL8 = YL8;
    }

    public String getYL9() {
        return YL9;
    }

    public void setYL9(String YL9) {
        this.YL9 = YL9;
    }

    public String getYL10() {
        return YL10;
    }

    public void setYL10(String YL10) {
        this.YL10 = YL10;
    }

    public String getYL11() {
        return YL11;
    }

    public void setYL11(String YL11) {
        this.YL11 = YL11;
    }

    public String getYL12() {
        return YL12;
    }

    public void setYL12(String YL12) {
        this.YL12 = YL12;
    }

    public String getYL13() {
        return YL13;
    }

    public void setYL13(String YL13) {
        this.YL13 = YL13;
    }

    public String getYL14() {
        return YL14;
    }

    public void setYL14(String YL14) {
        this.YL14 = YL14;
    }

    public String getYL15() {
        return YL15;
    }

    public void setYL15(String YL15) {
        this.YL15 = YL15;
    }

    public String getYL16() {
        return YL16;
    }

    public void setYL16(String YL16) {
        this.YL16 = YL16;
    }

    public String getYL17() {
        return YL17;
    }

    public void setYL17(String YL17) {
        this.YL17 = YL17;
    }

    public String getYL18() {
        return YL18;
    }

    public void setYL18(String YL18) {
        this.YL18 = YL18;
    }

    public String getYL19() {
        return YL19;
    }

    public void setYL19(String YL19) {
        this.YL19 = YL19;
    }

    public String getYL20() {
        return YL20;
    }

    public void setYL20(String YL20) {
        this.YL20 = YL20;
    }
}
