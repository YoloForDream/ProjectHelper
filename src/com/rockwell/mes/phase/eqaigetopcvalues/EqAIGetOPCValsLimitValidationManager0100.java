package com.rockwell.mes.phase.eqaigetopcvalues;

import com.rockwell.mes.commons.base.ifc.choicelist.IMESChoiceElement;
import com.rockwell.mes.commons.base.ifc.choicelist.MESChoiceListHelper;
import com.rockwell.mes.commons.deviation.ifc.exceptionrecording.IMESExceptionRecord.RiskClass;
import com.rockwell.mes.commons.parameter.booleanvalue.MESParamBooleanValue0100;
import com.rockwell.mes.commons.parameter.excptenablenodef.MESParamExcpEnableNDef0100;
import com.rockwell.mes.commons.parameter.string.MESParamString0100;
import com.rockwell.mes.commons.shared.phase.validation.*;
import com.rockwell.mes.parameter.tworangelimitdefinition.MESParamTwoRangeLimit0100;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The validation manager for the get opc values phase.
 * <p>
 * 
 * @author bdreyer, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class EqAIGetOPCValsLimitValidationManager0100 extends LimitValidationManager0100 {

    /**
     * @param llhhConfig the config of LL-HH Range (outer range)
     * @param lhConfig the config of L-H Range (inner range)
     * @param limitDef the limit definition
     * @param tagData the corresponding tagData
     */
    public void addNumericRangeValidator(MESParamExcpEnableNDef0100 llhhConfig, MESParamExcpEnableNDef0100 lhConfig, //
            final MESParamTwoRangeLimit0100 limitDef, AITagData0200 tagData) {
        RangesCheckValidator0100 rangesChecker = createRangeCheckValidator(llhhConfig, lhConfig, limitDef, tagData);
        if (rangesChecker != null) {
            addValidator(tagData, rangesChecker);
        }
    }

    private static RangesCheckValidator0100 createRangeCheckValidator(MESParamExcpEnableNDef0100 llhhConfig, MESParamExcpEnableNDef0100 lhConfig,
            final MESParamTwoRangeLimit0100 limitDef, AITagData0200 tagData) {
        if (!llhhConfig.getEnabled() && !lhConfig.getEnabled()) {
            return null;
        }

        List<SingleRangeValidator0100<BigDecimal>> theRanges = new ArrayList<>();
        if (llhhConfig.getEnabled()) {
            RiskClass riskClass = getRiskClass(llhhConfig.getRiskAssessment());
            SingleRangeValidator0100<BigDecimal> llHHRange = new SingleRangeValidator0100<BigDecimal>(RtPhaseModelEqAIGetOPCVals0100.LL_LIMIT_NAME,
                    RtPhaseModelEqAIGetOPCVals0100.HH_LIMIT_NAME,
                            limitDef.getLLLimit(), limitDef.getHHLimit(), riskClass, llhhConfig.getMessage());
            llHHRange.setObjectResponsibleFor(tagData);
            theRanges.add(llHHRange);
        }
        if (lhConfig.getEnabled()) {
            RiskClass riskClass = getRiskClass(lhConfig.getRiskAssessment());
            SingleRangeValidator0100<BigDecimal> lHRange =
                    new SingleRangeValidator0100<BigDecimal>(RtPhaseModelEqAIGetOPCVals0100.L_LIMIT_NAME, RtPhaseModelEqAIGetOPCVals0100.H_LIMIT_NAME,
                            limitDef.getLLimit(), limitDef.getHLimit(), riskClass, lhConfig.getMessage());
            lHRange.setObjectResponsibleFor(tagData);
            theRanges.add(lHRange);
        }
        
        RangesCheckValidator0100 rangesChecker = new RangesCheckValidator0100<BigDecimal>(theRanges);
        rangesChecker.setObjectResponsibleFor(tagData);
        return rangesChecker;
    }
    
    /**
     * get risk class from a long value
     * 
     * @param riskClassLongValue long
     * @return riskclass instance
     */
    public static RiskClass getRiskClass(long riskClassLongValue) {
        IMESChoiceElement ce = MESChoiceListHelper.getChoiceElement(RiskClass.CHOICE_LIST_NAME, riskClassLongValue);
        RiskClass riskClass = RiskClass.valueOf(ce);
        return riskClass;
    }

    /**
     * @param expectedValueConfig the config of the check
     * @param expectedValueDef the definition of the expected value
     * @param tagData the corresponding tagData
     */
    public void addBooleanExcpectedValueValidator(final MESParamExcpEnableNDef0100 expectedValueConfig,
            final MESParamBooleanValue0100 expectedValueDef,
            final AITagData0200 tagData) {
        ExpectedValueValidator0100<Boolean> validator = createExpectedBooleanValidator(expectedValueConfig, expectedValueDef, tagData);
        
        if (validator != null) {
            addValidator(tagData, validator);
        }
    }


    private static ExpectedValueValidator0100<Boolean> createExpectedBooleanValidator(final MESParamExcpEnableNDef0100 expectedValueConfig, //
            final MESParamBooleanValue0100 expectedValueDef, final AITagData0200 tagData) {
        ExpectedValueValidator0100<Boolean> expectedValueValidator = null;

        if (expectedValueConfig.getEnabled()) {
            RiskClass riskClass = getRiskClass(expectedValueConfig.getRiskAssessment());
            expectedValueValidator = new ExpectedValueValidator0100<Boolean>(expectedValueDef.getValue(), riskClass, //
                    expectedValueConfig.getMessage());
            expectedValueValidator.setObjectResponsibleFor(tagData);
        }
        return expectedValueValidator;
    }

    /**
     * @param expectedValueConfig the config of the check
     * @param expectedValueDef the definition of the expected value
     * @param tagData the corresponding tagData
     */
    public void addStringExpectedValidator(final MESParamExcpEnableNDef0100 expectedValueConfig, final MESParamString0100 expectedValueDef,
            final AITagData0200 tagData) {
        ExpectedValueValidator0100<String> validator = createExpectedStringValidator(expectedValueConfig, expectedValueDef, tagData);

        if (validator != null) {
            addValidator(tagData, validator);
        }
    }

    private ExpectedValueValidator0100<String> createExpectedStringValidator(MESParamExcpEnableNDef0100 expectedValueConfig,
            MESParamString0100 expectedValueDef, AITagData0200 tagData) {
        ExpectedValueValidator0100<String> expectedValueValidator = null;

        if (expectedValueConfig.getEnabled()) {
            RiskClass riskClass = getRiskClass(expectedValueConfig.getRiskAssessment());
            expectedValueValidator = new ExpectedValueValidator0100<String>(expectedValueDef.getValue(), riskClass, //
                    expectedValueConfig.getMessage());
            expectedValueValidator.setObjectResponsibleFor(tagData);
        }
        return expectedValueValidator;
    }


    public void addValueForValidation(final AITagData0200 tagData, final Comparable aValue) {
        if (hasValidatorFor(tagData) && (aValue != null)) {
            super.addValueForValidation(tagData, aValue);
        }
    }


    public ILimitViolation0100 validate(final AITagData0200 tagData, final Comparable value) {
        if (hasValidatorFor(tagData) && (value != null)) {
            return getValidator(tagData).validate(value);
        } else {
            return null;
        }
    }
}
