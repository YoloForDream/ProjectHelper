package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import java.math.BigDecimal;

import com.datasweep.compatibility.client.MeasuredValue;
import com.rockwell.mes.commons.base.ifc.utility.ObjectFactory;
import com.rockwell.mes.commons.shared.phase.mvc.AbstractPhaseExecutor0200;
import com.rockwell.mes.phase.eqaigetopcvalues.MESRtPhaseDataEqAIGetOPCVals0100;
import com.rockwell.mes.services.s88.ifc.library.IBuildingBlockOutputDescriptor;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;

/**
 * 
 * Common methods for working with the phase data per data type
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public abstract class AbstractDataHandler0100 implements IDataHandler0100 {

    /** the IDataTypes constant for the specific automation data type */
    protected short dataType;

    /**
     * 
     * @param theDataType the IDataTypes constant for the specific automatiion data type
     */
    public AbstractDataHandler0100(short theDataType) {
        dataType = theDataType;
    }

    /**
     * get BigDecimal value from AITagValue
     * 
     * @param value AITagValue to get from
     * @return the BigDecimal value
     */
    public BigDecimal bigDecimalFromAITagValue(AITagValue0200 value) {
        MeasuredValue valueMV = (MeasuredValue) value.getPhaseValue();
        return bigDecimalFromMeasuredValue(valueMV);
    }

    /**
     * get unit of measure from AITagValue
     * 
     * @param value AITagValue to get from
     * @return the unit of measure
     */
    public String uomFromAITagValue(AITagValue0200 value) {
        MeasuredValue valueMV = (MeasuredValue) value.getPhaseValue();
        return uomFromMeasuredValue(valueMV);
    }

    /**
     * get BigDecimal value from MeasuredValue
     * 
     * @param mv MeasuredValue to get from
     * @return the BigDecimal value
     */
    public BigDecimal bigDecimalFromMeasuredValue(MeasuredValue mv) {
        return mv != null ? mv.getValue() : null;
    }

    /**
     * get Unit of measure value from MeasuredValue
     * 
     * @param mv MeasuredValue to get from
     * @return the Uom value
     */
    public String uomFromMeasuredValue(MeasuredValue mv) {
        return mv != null ? mv.getSymbol() : null;
    }

    /**
     * creates output descriptor with given key, name and type
     * 
     * @param key the key for the descriptor
     * @param displayName the display name
     * @param returnType the type for the descriptor
     * @return output descriptor
     */
    public IBuildingBlockOutputDescriptor createOutputDescriptor(final String key, final String displayName, final Class returnType) {
        IBuildingBlockOutputDescriptor descriptor = ObjectFactory.getInstance().createObject(IBuildingBlockOutputDescriptor.class,
                new Class[] { String.class, String.class, Class.class }, new Object[] { key, displayName, returnType });
        return descriptor;
    }

    /**
     * inits common phase data fields from given tag value
     * 
     * @param tagValue tag value to init from
     * @param phaseData phase data to init
     */
    public void setPhaseDataCommons(AITagValue0200 tagValue, MESRtPhaseDataEqAIGetOPCVals0100 phaseData) {
        phaseData.setTimestampValue(tagValue.getTimestamp());
        phaseData.setIsValueOverridden(tagValue.isManuallyOverridden());
        phaseData.setIsLimitViolateSigned(tagValue.isLimitViolationSigned());
        phaseData.setHasValueBeenRead(tagValue.isHasValueBeenRead());
    }

    /**
     * inits common tag value fields from phase data
     * 
     * @param tagValue tag value to init
     * @param phaseData phase data to init from
     */
    public void initTagDataFromPhaseDataCommons(AITagValue0200 tagValue, MESRtPhaseDataEqAIGetOPCVals0100 phaseData) {
        tagValue.setTimestamp(phaseData.getTimestampValue());
        tagValue.setManuallyOverridden(phaseData.getIsValueOverridden());
        tagValue.setLimitViolationSigned(phaseData.getIsLimitViolateSigned());
        tagValue.setHasValueBeenRead(phaseData.getHasValueBeenRead());
    }

}
