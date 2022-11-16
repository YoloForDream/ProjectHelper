package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import java.util.ArrayList;
import java.util.List;

import com.datasweep.plantops.common.constants.IDataTypes;
import com.rockwell.mes.apps.ebr.ifc.phase.IPhaseExecutor.Status;
import com.rockwell.mes.commons.parameter.booleanvalue.MESParamBooleanValue0100;
import com.rockwell.mes.commons.parameter.excptenablenodef.MESParamExcpEnableNDef0100;
import com.rockwell.mes.commons.shared.phase.mvc.AbstractPhaseExecutor0200;
import com.rockwell.mes.phase.eqaigetopcvalues.EqAIGetOPCValsLimitValidationManager0100;
import com.rockwell.mes.phase.eqaigetopcvalues.MESRtPhaseDataEqAIGetOPCVals0100;
import com.rockwell.mes.phase.eqaigetopcvalues.MESRtPhaseOutputEqAIGetOPCVals0100;
import com.rockwell.mes.phase.eqaigetopcvalues.RtPhaseModelEqAIGetOPCVals0100;
import com.rockwell.mes.phase.eqaigetopcvalues.RtPhaseOutputNameHelper0100;
import com.rockwell.mes.services.s88.ifc.library.IBuildingBlockOutputDescriptor;
import com.rockwell.mes.services.s88.ifc.recipe.IS88ProcessParameterBundle;
import com.rockwell.mes.services.s88equipment.ifc.AbstractTechnicalEquipmentPropertyType;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentPropertyType;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;
import com.rockwell.mes.shared.ai.tagdata.BooleanAITagValue0200;

/**
 * 
 * boolean bundle data handling
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class BooleanDataHandler0100 extends AbstractDataHandler0100 {

    private static BooleanDataHandler0100 instance;

    /**
     * 
     * @return instance of BooleanDataHandler
     */
    public static BooleanDataHandler0100 getInstance() {
        if (instance == null) {
            instance = new BooleanDataHandler0100();
        }
        return instance;
    }

    private BooleanDataHandler0100() {
        super(IDataTypes.TYPE_BOOLEAN);
    }

    @Override
    public void initTagData(AbstractPhaseExecutor0200 executor, AITagData0200 tagData, IS88ProcessParameterBundle bundle, //
            EqAIGetOPCValsLimitValidationManager0100 validationManager) {
        // the name is in the first bundle
        String name = bundle.getProcessParameters().get(0).getIdentifier();
        MESParamExcpEnableNDef0100 expectedValueConfig = executor.getProcessParameterData(MESParamExcpEnableNDef0100.class,
                name + " " + RtPhaseModelEqAIGetOPCVals0100.EXPECTED_VALUE_CONFIGURATION);
        MESParamBooleanValue0100 expectedValueDef = executor.getProcessParameterData(MESParamBooleanValue0100.class,
                name + " " + RtPhaseModelEqAIGetOPCVals0100.EXPECTED_VALUE_DEFINITION);
        tagData.getLimitValidationMsgHelper().addExpectedValue(expectedValueConfig.getEnabled(), expectedValueDef.getValue());

        if (executor.getStatus() == Status.ACTIVE) {
            validationManager.addBooleanExcpectedValueValidator(expectedValueConfig, expectedValueDef, tagData);
        }

    }

    @Override
    public void setPhaseDataWithPrecision(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData, Integer precision) {
        AITagValue0200<Boolean> tagValue = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        setPhaseDataCommons(tagValue, phaseData);
        phaseData.setBooleanValue(tagValue.getPhaseValue());
    }

    @Override
    public void setPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData) {
        AITagValue0200<Boolean> tagValue = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        setPhaseDataCommons(tagValue, phaseData);
        phaseData.setBooleanValue(tagValue.getPhaseValue());
    }

    @Override
    public void initTagDataFromPhaseData(GetOPCTagData0100 tagData, MESRtPhaseDataEqAIGetOPCVals0100 phaseData, //
            final EqAIGetOPCValsLimitValidationManager0100 validationManager) {
        AITagValue0200 tagValue = tagData.getTagValue(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        initTagDataFromPhaseDataCommons(tagValue, phaseData);
        Boolean booleanValue = phaseData.getBooleanValue();
        tagValue.setPhaseValueFromAIValue(booleanValue);

        if (validationManager != null) {
            validationManager.addValueForValidation(tagData, booleanValue);
        }
    }

    @Override
    public void setPhaseOutput(int bundleNumber, GetOPCTagData0100 tagData, MESRtPhaseOutputEqAIGetOPCVals0100 phaseOutput) {
        AITagValue0200 value = tagData.getTagValueNullExcept(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE);
        phaseOutput.setOutputValue(RtPhaseOutputNameHelper0100.createValueOutputKey(bundleNumber),
                value.getPhaseValue());
        phaseOutput.setOutputValue(RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputKey(bundleNumber),
                tagData.isAutomationGetSuccessful(AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE));
    }

    @Override
    public List<IBuildingBlockOutputDescriptor> createOutputDescriptors(int bundleNumber, String displayName) {
        List<IBuildingBlockOutputDescriptor> descriptorList = new ArrayList<IBuildingBlockOutputDescriptor>();
        descriptorList.add(createOutputDescriptor(RtPhaseOutputNameHelper0100.createValueOutputKey(bundleNumber),
                RtPhaseOutputNameHelper0100.createValueOutputName(displayName), Boolean.class));
        descriptorList.add(createOutputDescriptor(RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputKey(bundleNumber),
                RtPhaseOutputNameHelper0100.createAutomationGetSuccessOutputName(displayName), Boolean.class));
        return descriptorList;
    }

    @Override
    public GetOPCTagData0100 createTagData(AbstractPhaseEqAIModel0200 model, IMESEquipmentPropertyType eqPropertyType) {
        GetOPCTagData0100 tagData = new GetOPCTagData0100(model, model.getPhaseInputEquipment(), eqPropertyType, dataType);
        tagData.addTagValue(new BooleanAITagValue0200(tagData, AbstractTechnicalEquipmentPropertyType.TAGDEFINITION_VALUE));
        return tagData;
    }

}
