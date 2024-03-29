package com.rockwell.mes.phase.eqaigetopcvalues;

/**
 * This file is generated by the PhaseGenerator
 *
 * Please do not modify this file manually !!
 */

import com.datasweep.compatibility.client.ATRow;
import com.datasweep.compatibility.client.Response;
import com.rockwell.mes.services.s88.ifc.processdata.MESRtPhaseData;

import com.rockwell.mes.commons.base.ifc.utility.StringUtilsEx;
import java.math.BigDecimal;
import com.datasweep.compatibility.client.MeasuredValue;
import com.rockwell.mes.commons.base.ifc.functional.MeasuredValueUtilities;
import com.datasweep.compatibility.ui.Time;

 /**
 * Generated class definition
 * <br/>Application table: RS_PhDatEqAIGetOPCVals0100
 */
public abstract class MESGeneratedRtPhaseDataEqAIGetOPCVals0100
 extends MESRtPhaseData {

    /** Generated attribute definition */
    protected static final String ATDEFINITION_NAME = "RS_PhDatEqAIGetOPCVals0100";


    @Override
    public String getATDefinitionName() {
        return ATDEFINITION_NAME;
    }

    /**
     * Generated constructor
     *
     * @param key The key of the ATRow to load.
     */
    public MESGeneratedRtPhaseDataEqAIGetOPCVals0100(long key) {
        super(key);
    }

    /**
     * Generated copy constructor
     *
     * @param source the source to copy.
     */
    public MESGeneratedRtPhaseDataEqAIGetOPCVals0100(MESGeneratedRtPhaseDataEqAIGetOPCVals0100 source) {
        super(source);
    }

    /**
     * Generated constructor
     *
     * @param baseATRow The ATRow to wrap.
     */
    public MESGeneratedRtPhaseDataEqAIGetOPCVals0100(ATRow baseATRow) {
        super(baseATRow);
    }

    /**
     * Generated constructor
     */
    public MESGeneratedRtPhaseDataEqAIGetOPCVals0100() {
        super();
    }

    @Override
    protected void synchronizeAfterATRowRefresh() {
        super.synchronizeAfterATRowRefresh();
    }    
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getEqIdentifier() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_eqIdentifier"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setEqIdentifier(String value) {
        String oldValue = this.getEqIdentifier();
        this.dgtATRow.setValue("RS_eqIdentifier", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("eqIdentifier", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getEqShortDescription() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_eqShortDescription"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setEqShortDescription(String value) {
        String oldValue = this.getEqShortDescription();
        this.dgtATRow.setValue("RS_eqShortDescription", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("eqShortDescription", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getProperty() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_property"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setProperty(String value) {
        String oldValue = this.getProperty();
        this.dgtATRow.setValue("RS_property", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("property", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getBundleName() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_bundleName"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setBundleName(String value) {
        String oldValue = this.getBundleName();
        this.dgtATRow.setValue("RS_bundleName", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("bundleName", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public BigDecimal getNumericValue() {
        MeasuredValue mv = (MeasuredValue) this.dgtATRow.getValue("RS_numericValue");
        return mv == null ? null : mv.getValue();
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setNumericValue(BigDecimal value) {
        BigDecimal oldValue = this.getNumericValue();
        MeasuredValue mv = value == null ? null : MeasuredValueUtilities.createMV(value, "");
        this.dgtATRow.setValue("RS_numericValue", mv);
        pcs.firePropertyChange("numericValue", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Boolean getBooleanValue() {
        return (Boolean) this.dgtATRow.getValue("RS_booleanValue");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setBooleanValue(Boolean value) {
        Boolean oldValue = this.getBooleanValue();
        this.dgtATRow.setValue("RS_booleanValue", value);
        pcs.firePropertyChange("booleanValue", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getStringValue() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_stringValue"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setStringValue(String value) {
        String oldValue = this.getStringValue();
        this.dgtATRow.setValue("RS_stringValue", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("stringValue", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getUom() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_uom"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setUom(String value) {
        String oldValue = this.getUom();
        this.dgtATRow.setValue("RS_uom", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("uom", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public String getPropertyDataType() {
        return StringUtilsEx.decodeStringForUI((String) this.dgtATRow.getValue("RS_propertyDataType"));
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setPropertyDataType(String value) {
        String oldValue = this.getPropertyDataType();
        this.dgtATRow.setValue("RS_propertyDataType", StringUtilsEx.encodeStringForDB(value));
        pcs.firePropertyChange("propertyDataType", oldValue, value);
    }
    
    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Time getTimestampValue() {
        return (Time) this.dgtATRow.getValue("RS_timestampValue");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setTimestampValue(Time value) {
        Time oldValue = this.getTimestampValue();
        this.dgtATRow.setValue("RS_timestampValue", value);
        pcs.firePropertyChange("timestampValue", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Boolean getIsValueOverridden() {
        return (Boolean) this.dgtATRow.getValue("RS_isValueOverridden");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setIsValueOverridden(Boolean value) {
        Boolean oldValue = this.getIsValueOverridden();
        this.dgtATRow.setValue("RS_isValueOverridden", value);
        pcs.firePropertyChange("isValueOverridden", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Boolean getIsLimitViolateSigned() {
        return (Boolean) this.dgtATRow.getValue("RS_isLimitViolateSigned");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setIsLimitViolateSigned(Boolean value) {
        Boolean oldValue = this.getIsLimitViolateSigned();
        this.dgtATRow.setValue("RS_isLimitViolateSigned", value);
        pcs.firePropertyChange("isLimitViolateSigned", oldValue, value);
    }

    /**
     * Generated method definition
     *
     * @return the requested value
     */
    public Boolean getHasValueBeenRead() {
        return (Boolean) this.dgtATRow.getValue("RS_hasValueBeenRead");
    }

    /**
     * Generated method definition
     *
     * @param value The new value to be assigned
     */
    public void setHasValueBeenRead(Boolean value) {
        Boolean oldValue = this.getHasValueBeenRead();
        this.dgtATRow.setValue("RS_hasValueBeenRead", value);
        pcs.firePropertyChange("hasValueBeenRead", oldValue, value);
    }

    @Override
    protected Response prepareATRowForSave() {
        // Check if transient references are valid and store the corresponding keys in the ATRow:
        Response res = super.prepareATRowForSave();

        return res;
    }

}
