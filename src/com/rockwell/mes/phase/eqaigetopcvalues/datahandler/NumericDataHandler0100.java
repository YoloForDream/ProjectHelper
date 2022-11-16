package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import com.datasweep.compatibility.client.MeasuredValue;
import com.datasweep.plantops.common.constants.IDataTypes;
import com.rockwell.mes.apps.ebr.ifc.phase.IPhaseExecutor.Status;
import com.rockwell.mes.commons.parameter.excptenablenodef.MESParamExcpEnableNDef0100;
import com.rockwell.mes.commons.shared.phase.mvc.AbstractPhaseExecutor0200;
import com.rockwell.mes.parameter.tworangelimitdefinition.MESParamTwoRangeLimit0100;
import com.rockwell.mes.phase.eqaigetopcvalues.*;
import com.rockwell.mes.services.s88.ifc.library.IBuildingBlockOutputDescriptor;
import com.rockwell.mes.services.s88.ifc.recipe.IS88ProcessParameterBundle;
import com.rockwell.mes.services.s88equipment.ifc.AbstractTechnicalEquipmentPropertyType;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentPropertyType;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;
import com.rockwell.mes.shared.ai.tagdata.NumericAITagValue0200;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * numeric bundle data handling
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class NumericDataHandler0100 extends AbstractDataHandler0100 {

    private static NumericDataHandler0100 instance;


    /**
     * 
     * @return instance of NumericDataHandler
     */
    public static NumericDataHandler0100 getInstance() {
        if (instance == null) {
            instance = new NumericDataHandler0100();
        }
        return instance;
    }

    private NumericDataHandler0100() {
        super(IDataTypes.TYPE_DECIMAL);
    }

    @Override
    public void initTagData(AbstractPhaseExecutor0200 executor, AITagData0200 tagData, IS88ProcessParameterBundle bundle, //
            EqAIGetOPCValsLimitValidationManager0100 validationManager) {


        // the name is in the first bundle
        String name = bundle.getProcessParameters().get(0).getIdentifier();
        MESParamExcpEnableNDef0100 lhConfig =
                executor.getProcessParameterData(MESParamExcpEnableNDef0100.class, name + " " + RtPhaseModelEqAIGetOPCVals0100.LH_CONFIGURATION);
        MESParamExcpEnableNDef0100 llhhConfig =
                executor.getProcessParameterData(MESParamExcpEnableNDef0100.class, name + " " + RtPhaseModelEqAIGetOPCVals0100.LLHH_CONFIGURATION);
        MESParamTwoRangeLimit0100 limitDef =
                executor.getProcessParameterData(MESParamTwoRangeLimit0100.class,  name + " " + RtPhaseModelEqAIGetOPCVals0100.LIMIT_DEFINITION);


        // we don't need low and high values for this phase, disable them in all cases
        tagData.getLimitValidationMsgHelper().addTwoLimitCheck(lhConfig.getEnabled(), llhhConfig.getEnabled(), limitDef.getLLimit(),
                limitDef.getHLimit(), limitDef.getLLLimit(), limitDef.getHHLimit());
        
        if (executor.getStatus() == Status.ACTIVE) {
            validationManager.addNumericRangeValidator(llhhConfig, lhConfig, limitDef, tagData);
        }
    }

    @Override
    public void setPhaseDataWithPrecision(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData,Integer precision) {
        AITagValue0200 tagValue = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        setPhaseDataCommons(tagValue, phaseData);
        phaseData.setUom(uomFromAITagValue(tagValue));
        if(bigDecimalFromAITagValue(tagValue)!= null){
            BigDecimal valueAfterAdjustment = precisionAdjustment(bigDecimalFromAITagValue(tagValue),precision);
            phaseData.setNumericValue(valueAfterAdjustment);
        }
        else
        {
            phaseData.setNumericValue(bigDecimalFromAITagValue(tagValue));
        }

    }

    /***
     * @param value the source value
     * @param precision the precision need to be set
     * @return the value after  precision adjustment
     * @author Rey Wei Wang 2022.07.26
     *
     */
    public BigDecimal precisionAdjustment(BigDecimal value,Integer precision){
        BigDecimal newValue = value.setScale(precision, RoundingMode.HALF_UP);
        return newValue;
    }

   @Override
    public void setPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData) {
        AITagValue0200 tagValue = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        setPhaseDataCommons(tagValue, phaseData);
        phaseData.setUom(uomFromAITagValue(tagValue));
        phaseData.setNumericValue(bigDecimalFromAITagValue(tagValue));
    }

    @Override
    public void initTagDataFromPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData,
            final EqAIGetOPCValsLimitValidationManager0100 validationManager) {
        AITagValue0200 tagValue = tagData.getTagValue(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        initTagDataFromPhaseDataCommons(tagValue, phaseData);
        BigDecimal numericValue = phaseData.getNumericValue();
        tagValue.setPhaseValueFromAIValue(numericValue);

        if (validationManager != null) {
            validationManager.addValueForValidation(tagData, numericValue);
        }
    }

    @Override
    public void setPhaseOutput(int bundleNumber, GetOPCTagData0100 tagData, MESRtPhaseOutputEqAIGetOPCVals0100 phaseOutput) {
        AITagValue0200 value = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        phaseOutput.setOutputValue(RtPhaseOutputNameHelper0100.createValueOutputKey(bundleNumber),
                bigDecimalFromMeasuredValue((MeasuredValue) value.getPhaseValue()));

        phaseOutput.setOutputValue(RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputKey(bundleNumber),
                tagData.isAutomationGetSuccessful(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE));

        phaseOutput.setOutputValue(RtPhaseOutputNameHelper0100.createUnitOfMeasureOutputKey(bundleNumber), tagData.getUom());
    }

    @Override
    public List<IBuildingBlockOutputDescriptor> createOutputDescriptors(int bundleNumber, String displayName) {
        List<IBuildingBlockOutputDescriptor> descriptorList = new ArrayList<IBuildingBlockOutputDescriptor>();
        descriptorList.add(createOutputDescriptor(RtPhaseOutputNameHelper0100.createValueOutputKey(bundleNumber),
                RtPhaseOutputNameHelper0100.createValueOutputName(displayName), BigDecimal.class));
        descriptorList.add(createOutputDescriptor(RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputKey(bundleNumber),
                RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputName(displayName), Boolean.class));
        descriptorList.add(createOutputDescriptor(RtPhaseOutputNameHelper0100.createUnitOfMeasureOutputKey(bundleNumber),
                RtPhaseOutputNameHelper0100.createUnitOfMeasureOutputName(displayName), String.class));
        return descriptorList;
    }

    @Override
    public GetOPCTagData0100 createTagData(AbstractPhaseEqAIModel0200 model, IMESEquipmentPropertyType eqPropertyType) {
        GetOPCTagData0100 tagData = new GetOPCTagData0100(model, model.getPhaseInputEquipment(), eqPropertyType, dataType);
        tagData.addTagValue(new NumericAITagValue0200(tagData, AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE, eqPropertyType));
        return tagData;
    }

}
