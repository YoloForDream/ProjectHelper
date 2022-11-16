package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import com.rockwell.mes.commons.shared.phase.mvc.AbstractPhaseExecutor0200;
import com.rockwell.mes.phase.eqaigetopcvalues.EqAIGetOPCValsLimitValidationManager0100;
import com.rockwell.mes.phase.eqaigetopcvalues.MESRtPhaseDataEqAIGetOPCVals0100;
import com.rockwell.mes.phase.eqaigetopcvalues.MESRtPhaseOutputEqAIGetOPCVals0100;
import com.rockwell.mes.services.s88.ifc.library.IBuildingBlockOutputDescriptor;
import com.rockwell.mes.services.s88.ifc.recipe.IS88ProcessParameterBundle;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentPropertyType;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;

import java.util.List;

/**
 * 
 * Common methods for working with the phase data per data type
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public interface  IDataHandler0100 {

    /**
     * inits tag data from process parameter bundle
     * 
     * @param executor the phase executor
     * @param aiTagData tag data to init
     * @param bundle the bundle to init from
     * @param validationManager the validation manager to be used
     */
    public void initTagData(AbstractPhaseExecutor0200 executor, AITagData0200 aiTagData, IS88ProcessParameterBundle bundle, //
            EqAIGetOPCValsLimitValidationManager0100 validationManager);

    /**
     * sets phase data from the given tag data
     * @param tagData the tag data to init from
     * @param phaseData the phase data to set
     */
    public void setPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData);
    /***
     * @param tagData   the tag data to init from
     * @param phaseData the phase data to set
     * @param precision the precision used to adjusted values
     */
    public void setPhaseDataWithPrecision(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData,Integer precision);
    /**
     * Initialize tag data from phase data.
     * 
     * @param tagData the tag data to init
     * @param phaseData the phase data to init from
     * @param validationManager the validation manager
     */
    public void initTagDataFromPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData,
            final EqAIGetOPCValsLimitValidationManager0100 validationManager);
    /**
     * sets phase output from the given tag data
     * @param bundleNumber the index of the parameter bundle
     * @param tagData the tag data to init from
     * @param phaseOutput the phase data to set
     */
    public void setPhaseOutput(int bundleNumber, GetOPCTagData0100 tagData, MESRtPhaseOutputEqAIGetOPCVals0100 phaseOutput);

    /**
     * creates output descriptor(s) for given name.
     * 
     * Note: uses the bundle number to create output keys - no special characters are allowed in variable names for
     * transitions and expressions
     * @param bundleNumber the index of the parameter bundle
     * @param displayName the parameter bundle name
     * @return list of IBuildingBlockOutputDescriptor for the desired type
     */
    public List<IBuildingBlockOutputDescriptor> createOutputDescriptors(int bundleNumber, String displayName);

    /**
     * Creates a specific tag data object depending on the data type of the automation property
     * 
     * @param model the phase model
     * @param eqPropertyType equipment property type
     * @return tag data object
     */
    public GetOPCTagData0100 createTagData(AbstractPhaseEqAIModel0200 model, IMESEquipmentPropertyType eqPropertyType);
}
