package com.rockwell.mes.phase.eqaigetopcvalues;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.plantops.common.constants.IDataTypes;
import com.rockwell.mes.apps.ebr.ifc.phase.IPhaseExecutor.Status;
import com.rockwell.mes.commons.base.ifc.exceptions.MESRuntimeException;
import com.rockwell.mes.commons.base.ifc.objects.BulkDatabaseOperationSupportForMESATObject;
import com.rockwell.mes.commons.parameter.plaininstruction.MESParamPlainInstr0100;
import com.rockwell.mes.phase.eqaigetopcvalues.datahandler.DataHandlerFactory0100;
import com.rockwell.mes.phase.eqaigetopcvalues.datahandler.GetOPCTagData0100;
import com.rockwell.mes.phase.eqaigetopcvalues.datahandler.IDataHandler0100;
import com.rockwell.mes.services.s88.ifc.recipe.IS88ProcessParameterBundle;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentPropertyType;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIExecutor0200;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.IAIPropertyProcessParameter0200;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Model for Get OPC Values phase
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class RtPhaseModelEqAIGetOPCVals0100 extends AbstractPhaseEqAIModel0200<MESRtPhaseDataEqAIGetOPCVals0100, MESRtPhaseOutputEqAIGetOPCVals0100> {

    /** GetOPCValues message pack */
    public static final String MSGPACK = "PhaseEqmAIGetOPCValues0100";

    /** */
    static final String USAGE_IDENTIFIER_NUMERIC_BUNDLE = "Numeric";

    /** */
    static final String USAGE_IDENTIFIER_BOOL_BUNDLE = "Boolean";

    /** */
    static final String USAGE_IDENTIFIER_STRING_BUNDLE = "String";

    /** */
    public static final String EXPECTED_VALUE_CONFIGURATION = "Expected value configuration";

    /** */
    public static final String EXPECTED_VALUE_DEFINITION = "Expected value definition";

    /** */
    public static final String LH_CONFIGURATION = "L-H configuration";

    /** */
    public static final String L_LIMIT_NAME = "L";

    /** */
    public static final String H_LIMIT_NAME = "H";

    /** */
    public static final String LLHH_CONFIGURATION = "LL-HH configuration";

    /** */
    public static final String LL_LIMIT_NAME = "LL";

    /** */
    public static final String HH_LIMIT_NAME = "HH";

    /** */
    public static final String LIMIT_DEFINITION = "Limit definition";


    /**
     * 
     * @param phaseExecutor the executor
     */
    protected RtPhaseModelEqAIGetOPCVals0100(AbstractPhaseEqAIExecutor0200 phaseExecutor) {
        super(phaseExecutor);
        supportedDataTypes.add(IDataTypes.TYPE_DECIMAL);
        supportedDataTypes.add(IDataTypes.TYPE_BOOLEAN);
        supportedDataTypes.add(IDataTypes.TYPE_STRING);
    }

    @Override
    protected void initTagDataFromPhaseData() {
        if (hasRtPhaseData()) {
            List<MESRtPhaseDataEqAIGetOPCVals0100> allRtPhaseData = getAllRtPhaseData();
            EqAIGetOPCValsLimitValidationManager0100 validationManager =
                    (getExecutor().getStatus() == Status.ACTIVE) ? ((RtPhaseExecutorEqAIGetOPCVals0100) getExecutor()).getValidationManager() : null;
            for (MESRtPhaseDataEqAIGetOPCVals0100 phaseData : allRtPhaseData) {
                GetOPCTagData0100 tagData = (GetOPCTagData0100) getTagDataByPropertyName(phaseData.getProperty());
                getHandler(tagData).initTagDataFromPhaseData(tagData, phaseData, validationManager);
            }
        }
    }

    @Override
    public boolean checkOverrideManually(AITagData0200 tagData, Map<String, Object> values) {
        // we want to clear all errors, because if the check fails, we want to see the errors only for this tag data
        // object
        clearAllTagErrors();
        tagData.clearAllErrors();
        return tagData.checkConversions(values);
    }
    /*
     *get the precision from the instruction
     *@ author rey wei wang
     */

    public Integer getPrecisionValue() {
        MESParamPlainInstr0100 mesParamPlainInstr0100 = getRtPhase().getProcessParameterData(MESParamPlainInstr0100.class, "Instruction");
        String regex = "\\[(.*?)]";
        Pattern pattern = Pattern.compile(regex);
        Integer precision= null;
        if (StringUtils.isNotEmpty(mesParamPlainInstr0100.getDataAsString())) {
            Matcher match = pattern.matcher(mesParamPlainInstr0100.getDataAsString());
            while (match.find()) {
                String value = (match.group(1).replaceAll("[^0-9.]",""));
                precision = Integer.valueOf(value);
            }
        }
        return precision;
    }

    @Override
    protected String getMsgpack() {
        return MSGPACK;
    }

    @Override
    public void setPhaseData(boolean saveExplicitly) {
        List<MESRtPhaseDataEqAIGetOPCVals0100> allRtPhaseData;
        if (!hasRtPhaseData()) {
            allRtPhaseData = initializePhaseData();
        } else {
            allRtPhaseData = getAllRtPhaseData();
        }
        for (MESRtPhaseDataEqAIGetOPCVals0100 phaseData : allRtPhaseData) {
            GetOPCTagData0100 tagData = (GetOPCTagData0100) getTagDataByPropertyName(phaseData.getProperty());
            phaseData.setPropertyDataType(Short.toString(tagData.getDataType()));
            phaseData.setBundleName(tagData.getParameterUserDefinedIdentifier());
            Integer precision = getPrecisionValue();
            if(precision != null) {
                if(precision.compareTo(0)>=0&&precision.compareTo(8) <0){
                    getHandler(tagData).setPhaseDataWithPrecision(tagData, phaseData, precision);
                }
                else {
                    getHandler(tagData).setPhaseData(tagData, phaseData);
                }
            }
            else{
                getHandler(tagData).setPhaseData(tagData, phaseData);
            }

        }

        if (saveExplicitly) {
            try {
                BulkDatabaseOperationSupportForMESATObject.saveOptimized(allRtPhaseData);
            } catch (DatasweepException e) {
                LOGGER.error(e.getMessage(), e);
                throw new MESRuntimeException(e);
            }
        }
    }

    /**
     * @return list of phase data entries for all automation properties
     */
    protected List<MESRtPhaseDataEqAIGetOPCVals0100> initializePhaseData() {
        List<MESRtPhaseDataEqAIGetOPCVals0100> allRtPhaseData = new ArrayList<MESRtPhaseDataEqAIGetOPCVals0100>();
        for (AITagData0200 tagData : getTagDataList()) {
            MESRtPhaseDataEqAIGetOPCVals0100 phaseData = (MESRtPhaseDataEqAIGetOPCVals0100) getRtPhase().addRtPhaseData();
            phaseData.setEqIdentifier(getPhaseInputEquipmentIdentifier());
            phaseData.setEqShortDescription(getPhaseInputEquipmentShortDescription());
            phaseData.setProperty(tagData.getProperty());
            phaseData.setIsValueOverridden(false);
            phaseData.setIsLimitViolateSigned(false);
            phaseData.setHasValueBeenRead(false);
            allRtPhaseData.add(phaseData);
        }
        return allRtPhaseData;
    }

    @Override
    public void setPhaseOutput(boolean saveExplicitly) {
        MESRtPhaseOutputEqAIGetOPCVals0100 phaseOutput = getRtPhaseOutput();
        boolean allGetSuccessful = true;
        if (hasRtPhaseData()) {

            List<MESRtPhaseDataEqAIGetOPCVals0100> allRtPhaseData = getAllRtPhaseData();
            for (MESRtPhaseDataEqAIGetOPCVals0100 phaseData : allRtPhaseData) {
                GetOPCTagData0100 tagData = (GetOPCTagData0100) getTagDataByPropertyName(phaseData.getProperty());
                int bundleNumber = getProcessParameterBundleIndexForTagData(tagData);
                for (AITagValue0200 tagValue : tagData.getEnabledTagValues()) {
                    allGetSuccessful = allGetSuccessful && tagData.isAutomationGetSuccessful(tagValue.getTagID());
                }
                getHandler(tagData).setPhaseOutput(bundleNumber, tagData, phaseOutput);
            }
        }
        phaseOutput.setAllGetSuccessful(allGetSuccessful);
        if (saveExplicitly) {
            try {
                phaseOutput.save();
            } catch (DatasweepException e) {
                LOGGER.error(e.getMessage(), e);
                throw new MESRuntimeException(e);
            }
        }
    }

    @Override
    protected AITagData0200 createTagData(IMESEquipmentPropertyType eqPropertyType, IAIPropertyProcessParameter0200 processParameterData,
            IS88ProcessParameterBundle bundle) {
        return getHandler(eqPropertyType.getTechnicalEquipmentPropertyType().getDataType()).createTagData(this, eqPropertyType);
    }

    @Override
    protected void initTagData(AITagData0200 tagData, IAIPropertyProcessParameter0200 processParameterData, IMESEquipmentPropertyType eqPropertyType,
            IS88ProcessParameterBundle processParameter) {
        super.initTagData(tagData, processParameterData, eqPropertyType, processParameter);
        EqAIGetOPCValsLimitValidationManager0100 validationManager = ((RtPhaseExecutorEqAIGetOPCVals0100) getExecutor()).getValidationManager();
        getHandler(tagData).initTagData(executor, tagData, processParameter, validationManager);
    }

    private IDataHandler0100 getHandler(short dataType) {
        return DataHandlerFactory0100.getHandler(dataType);
    }

    private IDataHandler0100 getHandler(AITagData0200 tagData) {
        return getHandler(tagData.getDataType());
    }

    private int getProcessParameterBundleIndexForTagData(AITagData0200 tagData) {
        List<IS88ProcessParameterBundle> dynamicProcessParameterBundlesList = getRtPhase().getDynamicProcessParameterBundlesList();
        for (int i = 0; i < dynamicProcessParameterBundlesList.size(); i++) {
            IS88ProcessParameterBundle processParameter = dynamicProcessParameterBundlesList.get(i);
            if (processParameter.getUserDefinedIdentifier().equals(tagData.getParameterUserDefinedIdentifier())) {
                return i;
            }
        }
        throw new MESRuntimeException("Process parameter not found for identifier: " + tagData.getParameterUserDefinedIdentifier());
    }

}
