package com.rockwell.mes.phase.eqaigetopcvalues;

import com.datasweep.compatibility.client.ActivitySetStep;
import com.datasweep.plantops.common.measuredvalue.IMeasuredValue;
import com.rockwell.livedata.FTException;
import com.rockwell.mes.apps.ebr.ifc.phase.IPhaseCompleter;
import com.rockwell.mes.apps.ebr.ifc.phase.ui.PhaseSystemTriggeredExceptionHandler;
import com.rockwell.mes.commons.base.ifc.exceptions.MESException;
import com.rockwell.mes.commons.base.ifc.functional.BigDecimalUtilities;
import com.rockwell.mes.commons.base.ifc.i18n.I18nMessageUtility;
import com.rockwell.mes.commons.base.ifc.services.ServiceFactory;
import com.rockwell.mes.commons.base.ifc.utility.AutoWaitCursor;
import com.rockwell.mes.commons.deviation.ifc.IESignatureExecutor;
import com.rockwell.mes.commons.deviation.ifc.exceptionrecording.IMESExceptionRecord;
import com.rockwell.mes.commons.deviation.ifc.exceptionrecording.IMESExceptionRecord.RiskClass;
import com.rockwell.mes.commons.parameter.plaininstruction.MESParamPlainInstr0100;
import com.rockwell.mes.commons.shared.phase.validation.*;
import com.rockwell.mes.phase.eqaigetopcvalues.datahandler.GetOPCTagData0100;
import com.rockwell.mes.services.s88.ifc.execution.IMESRtPhase;
import com.rockwell.mes.services.s88.ifc.recipe.IMESPhase;
import com.rockwell.mes.services.s88equipment.ifc.automation.EquipmentPropertyTag;
import com.rockwell.mes.services.s88equipment.ifc.automation.IAutomationService;
import com.rockwell.mes.services.s88equipment.ifc.automation.IMultipleTagsResultContainer;
import com.rockwell.mes.services.s88equipment.ifc.automation.LiveDataSource;
import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationException;
import com.rockwell.mes.services.s88equipment.ifc.exceptions.AutomationInvalidTagException;
import com.rockwell.mes.shared.ai.*;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;
import com.rockwell.mes.shared.ai.tagdata.AbstractBooleanAITagData0200;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.math.BigDecimal;
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
public class RtPhaseExecutorEqAIGetOPCVals0100 extends AbstractPhaseEqAIExecutor0200 {

    /** Checkkey for system triggered exception. */
    private static final String CHECKKEY_LIMIT_VIOLATION = "LimitViolation";

    /** */
    public static final String MSGPACK = "PhaseEqmAIGetOPCValues0100";

    private static final String MSG_ID_LIMIT_VIOLATION_ERROR = "LimitViolation_ErrorMsg";

    private static final String MSG_ID_LIMIT_VIOLATION_DETAILS = "LimitViolationDetails_Msg";

    private static final String MSG_ID_EXPECTED_VALUE_VIOLATION_DETAILS = "ExpectedValueViolationDetails_Msg";

    private static final String MSG_ID_CONFIGURATION_VIOLATION = "ConfigurationViolationDetails_Msg";

    private EqAIGetOPCValsLimitValidationManager0100 validationManager = new EqAIGetOPCValsLimitValidationManager0100();

    /**
     * ctor for an ACTIVE phase or a COMPLETED phase in case of resume.
     * 
     * @param inPhaseCompleter the object, which shall be used to complete the phase
     * @param inRtPhase the runtime phase to be executed
     */
    public RtPhaseExecutorEqAIGetOPCVals0100(IPhaseCompleter inPhaseCompleter, IMESRtPhase inRtPhase) {
        super(inPhaseCompleter, inRtPhase);
    }

    /**
     * ctor for a phase for the PREVIEW.
     * 
     * @param inPhase the related phase
     * @param inStep  the related activity set step
     */
    public RtPhaseExecutorEqAIGetOPCVals0100(IMESPhase inPhase, ActivitySetStep inStep) {
        super(inPhase, inStep);
    }

    @Override
    protected String getParameterNameOverrideValue() {
        return PARAMETER_OVERRIDE_RECORDED_VALUE;
    }

    @Override
    protected AbstractPhaseEqAIModel0200 createModel() {
        return new RtPhaseModelEqAIGetOPCVals0100(this);
    }

    @Override
    protected AbstractPhaseEqAIView0200 createView(AbstractPhaseEqAIModel0200 theModel) {
        return new RtPhaseViewEqAIGetOPCVals0100(theModel);
    }

    @Override
    protected AbstractPhaseEqAIExceptionView0200 createExceptionView(AbstractPhaseEqAIModel0200 theModel) {
        return new RtPhaseExceptionViewEqAIGetOPCVals0100(theModel);
    }

    @Override
    protected AbstractPhaseEqAIActionView0200 createActionView(AbstractPhaseEqAIModel0200 theModel) {
        return new RtPhaseActionViewEqAIGetOPCVals0100(theModel);
    }

    /**
     * @return the validation manager
     */
    EqAIGetOPCValsLimitValidationManager0100 getValidationManager() {
        return validationManager;
    }

    @Override
    protected void start() {
        super.start();

        // initialization of model has already injected the stored values from the phase data to the validation manager.
        // Now we have to re-create the violations
        reinitLimitViolationsAtStart();
        markAllViolatedTagData();
    }

    private void reinitLimitViolationsAtStart() {
        // there are no violations at this point of time. But we want to document here that we will start with no
        // violations
        boolean valid = validationManager.validateAllValuesForValidation(LimitValidationManager0100.REMOVE_VIOLATIONS_BEFORE_VALIDATE);

        if (!valid) {
            removeAlreadyHandledViolations();
        }
        disableGetButtonIfNoValuesToBeRead();
    }

    private void removeAlreadyHandledViolations() {
        List<AITagData0200> tagDataList = getModel().getTagDataList();

        for (AITagData0200 tagData : tagDataList) {
            for (AITagValue0200 tagValue : tagData.getEnabledTagValues()) {
                if (tagValue.isLimitViolationSigned() || tagValue.isManuallyOverridden()) {
                    validationManager.removeViolation(tagData);
                }
            }
        }
    }

    @Override
    protected boolean doExecute() throws MESException {
        boolean success = true;
        // gather values from all tag data objects
        List<EquipmentPropertyTag> valuesForReading = new ArrayList();
        List<GetOPCTagData0100> tagDataList = getModel().getTagDataList();
        for (GetOPCTagData0100 tagData : tagDataList) {
            if (!tagData.prepareForReading(valuesForReading)) {
                success = false;
            }
        }
        if (success && !valuesForReading.isEmpty()) {
            try (AutoWaitCursor waitCursor = new AutoWaitCursor(getView())) {
                final IAutomationService service = ServiceFactory.getService(IAutomationService.class);
                IMultipleTagsResultContainer result =
                        service.readMultipleTagValues(getModel().getPhaseInputEquipment(), valuesForReading, LiveDataSource.OPCDevice);
                success = handleAutomationGetResult(tagDataList, valuesForReading, result, validationManager);
                disableGetButtonIfNoValuesToBeRead();
            } catch (AutomationInvalidTagException e) {
                getModel().handleAutomationInvalidTagException(e);
                success = false;
            } catch (AutomationException e) {
                getModel().handleAutomationError(e);
                throw e;
            } catch (FTException e) {
                throw new MESException(e);
            }
        }
        // save state after writing as phase data, so it can be restored after restarting PEC
        getModel().setPhaseData(true);
        return success;
    }
    /**
     * get the precision setting from instruction
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
    /**
     * Process the read results returned from the automation service
     * 
     * @param tagDataList tag data list to handle
     * @param valuesForReading the list of the requested tags
     * @param result map of write results
     * @param validationsManager the validations manager that shall validate the given values
     * 
     * @return were all tags get successfully
     * 
     * @throws FTException FTException from tag data
     */
    public boolean handleAutomationGetResult(List<GetOPCTagData0100> tagDataList, List<EquipmentPropertyTag> valuesForReading,
            final IMultipleTagsResultContainer result, final LimitValidationManager0100 validationsManager) throws FTException {
        boolean success = true;
        Integer precision = getPrecisionValue();
        if (precision == null) {
            // let each tag data handle the results
            for (GetOPCTagData0100 tagData : tagDataList) {
                // first check if the tag data is in the requested values
                for (EquipmentPropertyTag requestedValue : valuesForReading) {
                    if (tagData.getProperty().equals(requestedValue.getPropertyType().getIdentifier())
                            && !tagData.handleAutomationGetResult(result, validationsManager)) {
                        success = false;
                    }
                }
            }
            return success;
        }else{
            // let each tag data handle the results
            for (GetOPCTagData0100 tagData : tagDataList) {
                // first check if the tag data is in the requested values
                for (EquipmentPropertyTag requestedValue : valuesForReading) {
                    if (tagData.getProperty().equals(requestedValue.getPropertyType().getIdentifier())
                            && !tagData.handleAutomationGetResult(result, validationsManager,precision)) {
                        success = false;
                    }
                }
            }
        }

        return success;
    }

    private void disableGetButtonIfNoValuesToBeRead() {
        if (getModel().hasInitErrors()) {
            return;
        }
        if (!hasValuesToBeRead()) {
            getView().disableExecuteButton();
        }
    }

    /**
     * @return true if there are stil values to be read from the automation layer, otherwise false
     */
    private boolean hasValuesToBeRead() {
        List<GetOPCTagData0100> tagDataList = getModel().getTagDataList();
        for (GetOPCTagData0100 tagData : tagDataList) {
            if (tagData.hasValuesToBeRead()) {
                return true;
            }
        }
        return false;
    }

    /** */
    private static class MessageSupporterTagData implements ICreateDetailedViolationMessageSupporter0100 {
        @Override
        public Object[] getAdditionalArguments(Object relatedObject) {
            if (relatedObject != null) {
                AITagData0200 tagData = (AITagData0200) relatedObject;
                String uom = tagData.getUom();
                return new Object[] { tagData.getProperty(), (uom == null) ? "" : uom };
            }
            return new Object[] {};
        }
        @Override
        public String getMessagePack() {
            return MSGPACK;
        }

        @Override
        public String getValueAsString(final Object value) {
            if (value instanceof Boolean) {
                return ((Boolean) value) ? AbstractBooleanAITagData0200.TRUE_STRING : AbstractBooleanAITagData0200.FALSE_STRING;
            } else if (value instanceof BigDecimal) {
                return BigDecimalUtilities.toStringAsDecimal((BigDecimal) value);
            }

            return value.toString();
        }

        @Override
        public String getMessageIdForViolation(ILimitViolation0100 violation) {
            if (violation instanceof RangeLimitViolation0100) {
                return MSG_ID_LIMIT_VIOLATION_DETAILS;
            } else if (violation instanceof ExpectedValueViolation0100) {
                return MSG_ID_EXPECTED_VALUE_VIOLATION_DETAILS;
            } else if (violation instanceof ConfigurationViolation0100) {
                return MSG_ID_CONFIGURATION_VIOLATION;
            }

            return null;
        }
    }
    
    /**
     *
     */
    @Override
    protected boolean handleValidationErrors() {
        boolean noLimitViolations = validationManager.validateAllValuesForValidation(LimitValidationManager0100.PRESERVE_VIOLATIONS);
        markAllViolatedTagData();
        if (noLimitViolations) {
            removeIsExceptionRequested(CHECKKEY_LIMIT_VIOLATION);
        } else {
            setExceptionRequested(CHECKKEY_LIMIT_VIOLATION);
            recordSystemTriggeredLimitViolationException();
        }
        return noLimitViolations;
    }

    private void recordSystemTriggeredLimitViolationException() {
        if (!validationManager.hasViolations()) {
            throw new IllegalStateException("Inconsistence state. There are no violations although exception is triggered.");
        }
        LimitViolationExceptionTextBuilder0100 excTextBuilder = new LimitViolationExceptionTextBuilder0100(validationManager.getViolations());
        final RiskClass riskClass = excTextBuilder.getHighestRiskClass();
        final String detailMsg = excTextBuilder.getCombinedLimitViolationsExceptionMessage(new MessageSupporterTagData());

        final String message = I18nMessageUtility.getLocalizedMessage(MSGPACK, MSG_ID_LIMIT_VIOLATION_ERROR);
        // Ensure that dialog will be displayed after all possibly outstanding events are processed.
        // Otherwise the dialog is visible while e.g. the overall status is not yet painted
        // In this case the limit violation is rendered in the grid, so invokeLater is needed here
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PhaseSystemTriggeredExceptionHandler.recordException(RtPhaseExecutorEqAIGetOPCVals0100.this, message, message + detailMsg, riskClass,
                        CHECKKEY_LIMIT_VIOLATION);
            }
        });
    }

    @Override
    protected void exceptionTransactionCallback(String checkKey, IMESExceptionRecord exceptionRecord, IESignatureExecutor sigExecutor) {
        // if custom exception is signed, the checkKey is null
        if (StringUtils.isEmpty(checkKey)) {
            return;
        }
        super.exceptionTransactionCallback(checkKey, exceptionRecord, sigExecutor);

        if (checkKey.startsWith(OVERRIDE_VALUES_EVENT)) {
            AITagData0200 tagData = getOverrideValuePanel(checkKey).getTagData();
            // for overridden values we don't want to have a system triggered exception
            validationManager.removeViolation(tagData);
            if (!validationManager.hasViolations()) {
                removeIsExceptionRequested(CHECKKEY_LIMIT_VIOLATION);
            }
            markAllViolatedTagData();

        } else if (checkKey.equals(CHECKKEY_LIMIT_VIOLATION)) {
            markAllViolatedTagDataAsException();
            markAllViolatedTagData();
            removeIsExceptionRequested(CHECKKEY_LIMIT_VIOLATION);
            // either have a var for true, or better a method with a better name
            getModel().setPhaseData(true);
        }
    }

    @Override
    public void exceptionSigned(String checkKey) {
        super.exceptionSigned(checkKey);
        if (checkKey.startsWith(OVERRIDE_VALUES_EVENT)) {
            disableGetButtonIfNoValuesToBeRead();
        } else if (checkKey.equals(CHECKKEY_LIMIT_VIOLATION)) {
            validationManager.clearViolations();
        }
    }

    /**
     * Mark the Tag Data corresponding the limit violation that has been signed
     */
    private void markAllViolatedTagData() {
        List<GetOPCTagData0100> tagDataList = getModel().getTagDataList();
        // reset the violation flag
        for (GetOPCTagData0100 tagData : tagDataList) {
            tagData.setHasViolation(false);
        }
        List<ILimitViolation0100> theViolations = validationManager.getViolations();
        for (ILimitViolation0100 violation : theViolations) {
            AITagData0200 tagData = (AITagData0200) violation.getValidator().getObjectResponsibleFor();
            if (!tagData.get1TagValue().isLimitViolationSigned()) {
                tagData.setHasViolation(true);
            }
        }
    }

    /**
     * Mark the Tag Data corresponding the limit violation that has been signed
     */
    private void markAllViolatedTagDataAsException() {
        // move to executor
        List<ILimitViolation0100> theViolations = validationManager.getViolations();

        for (ILimitViolation0100 violation : theViolations) {
            AITagData0200 tagData = (AITagData0200) violation.getValidator().getObjectResponsibleFor();
            tagData.get1TagValue().setLimitViolationSigned(true);
            tagData.setHasViolation(false);
        }
    }

    @Override
    public boolean checkBeforeExecute() {
        List<MESException> checkErrors = getModel().getCheckErrors();
        List<AITagData0200> tagDataList = getModel().getTagDataList();
        checkErrors.clear();
        boolean success = true;

        if (tagDataList == null || tagDataList.size() == 0) {
            checkErrors.add(new CategorizedMESException0200(CategoryMsgID0200.BUSINESS_LOGIC_ERROR_CATEGORY,
                    ErrorMsgID0200.INIT__NO_BUNDLE_DEFINED__ERROR_MSG));
            success = false;
        }

        // save error state as phase data, so that it can be restored after restarting PEC
        getModel().setPhaseData(true);
        return success;
    }

    @Override
    protected boolean performPhaseCompletionCheck() {
        // Override and thus disable method from AbstractPhaseEqAIExecutor0200. Seem to have no benefit but to make it
        // obscure.
        if (isExceptionRequested(CHECKKEY_LIMIT_VIOLATION) && !isExceptionSigned(CHECKKEY_LIMIT_VIOLATION)) {
            recordSystemTriggeredLimitViolationException();
            return false;
        }

        List checkErrors = getModel().getCheckErrors();
        checkErrors.clear();
        boolean success = true;
        List<AITagData0200> tagDataList = getModel().getTagDataList();
        for (AITagData0200 tagData : tagDataList) {
            for (AITagValue0200 tagValue : tagData.getEnabledTagValues()) {
                if (tagValue.getTimestamp() == null && !tagValue.isManuallyOverridden()) {
                    checkErrors.add(new CategorizedMESException0200(CategoryMsgID0200.REPARABLE_CONFIRM_ERROR_CATEGORY,
                            ErrorMsgID0200.READ_POSTCHECK__NO_VALUE__ERROR_MSG, new Object[] { tagData.getProperty() + "." + tagValue.getTagID() }));
                    success = false;
                }
            }
        }

        if (!success) {
            showErrors(checkErrors);
        }
        return success;
    }


    @Override
    protected Pair<RiskClass, String> validateOverridenValue(final AITagData0200 tagData, Map<String, Object> overrideValues) {
        Object[] values = overrideValues.values().toArray();
        Object value = values[0];

        ILimitViolation0100 violation = validationManager.validate(tagData, getComparable(value));
        if (violation == null) {
            return null;
        }

        final String message = I18nMessageUtility.getLocalizedMessage(MSGPACK, MSG_ID_LIMIT_VIOLATION_ERROR);
        String exceptionText = message + violation.getExceptionText(new MessageSupporterTagData());
        return new ImmutablePair<RiskClass, String>(violation.getRiskClass(), exceptionText);
    }


    /**
     * - * @param aValue The object must implement Comparable or be a IMeasuredValue - * @return the corresponding
     * Comparable -
     */
    private static Comparable getComparable(final Object aValue) {
        if (aValue instanceof Comparable) {
            return (Comparable) aValue;
        }
        if (aValue instanceof IMeasuredValue) {
            return ((IMeasuredValue) aValue).getValue();
        }
        throw new IllegalArgumentException("Value for Validation must implement Comparable or be a IMeasuredValue.");
    }

    @Override
    protected void appendTagDataErrorDetails(StringBuilder sb, AITagData0200 tagData) {
        // nothing to add here
    }

    @Override
    protected void appendTagValueErrorDetails(StringBuilder sb, AITagValue0200 tagValue) {
        appendErrorData(sb, "--timestamp", tagValue.getTimestamp());

    }

}
