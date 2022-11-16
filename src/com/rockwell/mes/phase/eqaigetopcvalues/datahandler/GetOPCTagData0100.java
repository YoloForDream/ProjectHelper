package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import com.rockwell.livedata.FTException;
import com.rockwell.livedata.TagData;
import com.rockwell.mes.commons.base.ifc.exceptions.MESRuntimeException;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.commons.shared.phase.validation.LimitValidationManager0100;
import com.rockwell.mes.services.s88equipment.ifc.IMESEquipmentPropertyType;
import com.rockwell.mes.services.s88equipment.ifc.IMESS88Equipment;
import com.rockwell.mes.services.s88equipment.ifc.automation.EquipmentPropertyTag;
import com.rockwell.mes.services.s88equipment.ifc.automation.IMultipleTagsResultContainer;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.CategorizedMESException0200;
import com.rockwell.mes.shared.ai.CategoryMsgID0200;
import com.rockwell.mes.shared.ai.ErrorMsgID0200;
import com.rockwell.mes.shared.ai.tagdata.AITagData0200;
import com.rockwell.mes.shared.ai.tagdata.AITagValue0200;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


/**
 * 
 * Extending AITagData to have phase specific functionality
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class GetOPCTagData0100 extends AITagData0200 {



    /**
     * @param argModel reference to model
     * @param argEquipment the identified equipment entity
     * @param argEqPropertyType the equipment property type
     * @param theDataType the IDataTypes for the tag data
     */
    public GetOPCTagData0100(AbstractPhaseEqAIModel0200 argModel, IMESS88Equipment argEquipment, IMESEquipmentPropertyType argEqPropertyType,
            short theDataType) {
        super(argModel, argEquipment, argEqPropertyType, theDataType);
    }

    /**
     * Adds all tag values that should be read to the list that will be passed to the automation service.
     * 
     * @param valuesForReading list of values that will be get from the automation server
     * @return if all enabled tag values could be added successfully
     */
    public boolean prepareForReading(List<EquipmentPropertyTag> valuesForReading) {
        // clean any previous errors
        clearAllErrors();
        if (eqProperty != null) {
            for (AITagValue0200 value : getEnabledTagValues()) {
                if (value.hasTagPath()) {
                    if (value.isManuallyOverridden()) {
                        // if the value is manually overridden, we don't request it for reading anymore
                        value.setWarning(new CategorizedMESException0200(CategoryMsgID0200.VALUES_OVERRIDDEN_WARNING_CATEGORY,
                                ErrorMsgID0200.READ_PRECHECK__NO_GET_AFTER_OVERRIDE__ERROR_MSG, new Object[] { getProperty() }));
                    } else if (!value.isHasValueBeenRead()) {
                        // only add values that has been not yet read and that are not manually overriden
                        EquipmentPropertyTag helperObject = new EquipmentPropertyTag(eqPropertyType, value.getTagID());
                        valuesForReading.add(helperObject);
                    }
                } else {
                    value.setError(CategoryMsgID0200.IRREPARABLE_EXECUTION_ERROR_CATEGORY, ErrorMsgID0200.READ_PRECHECK__NO_PATH__ERROR_MSG);
                    setAutomationSet(AutomationSet.FAILURE);
                }
            }
            return !hasTagSpecificErrors();
        } else {
            addError(new CategorizedMESException0200(CategoryMsgID0200.IRREPARABLE_EXECUTION_ERROR_CATEGORY,
                    ErrorMsgID0200.WRITE__PROPERTY_NOT_FOUND__ERROR_MSG, new Object[] { eqPropertyType.getIdentifier() }));
            return false;
        }
    }

    /**
     * Process the read results returned from the automation service
     * 
     * @param results map of write results
     * @param validationsManager the validations manager that shall validate the given values
     * @param precision the precision used to calculate the new value.
     * @return were all tags get successfully
     * @throws FTException FTException from tag data
     */
    public boolean handleAutomationGetResult(IMultipleTagsResultContainer results, LimitValidationManager0100 validationsManager,Integer precision) //
            throws FTException {
        List<AITagValue0200> tagValuesToSearch = new ArrayList(getEnabledTagValues());
        for (Entry<EquipmentPropertyTag, TagData> result : results.getResultsPerEquipmentPropertyTags().entrySet()) {
            EquipmentPropertyTag tag = result.getKey();
            TagData data = result.getValue();
            if (this.eqProperty.getIdentifier().equals(tag.getPropertyType().getIdentifier())) {
                AITagValue0200 tagValue = getTagValue(tag.getTagID());
                if (data.getError() != null) {
                    setTagValueError(data, tagValue);
                } else {
                    setTagValueSuccess(data,tagValue,precision);
                    if (validationsManager.hasValidatorFor(this)) {
                        validationsManager.addValueForValidation(this, tagValue.getComparablePhaseValue());
                    }
                }
                tagValuesToSearch.remove(tagValue);
                if (tagValuesToSearch.isEmpty()) {
                    break;
                }
            }
        }
        if (!tagValuesToSearch.isEmpty()) {
            throw new MESRuntimeException("Automation service did not return read result for " + tagValuesToSearch.size() + " tag values of the "
                    + getProperty() + " equipment property.");
        }
        return results.allValuesOK();
    }


    /**
     * Process the read results returned from the automation service
     *
     * @param results map of write results
     * @param validationsManager the validations manager that shall validate the given values
     * @return were all tags get successfully
     * @throws FTException FTException from tag data
     */
    public boolean handleAutomationGetResult(IMultipleTagsResultContainer results, LimitValidationManager0100 validationsManager) //
            throws FTException {
        List<AITagValue0200> tagValuesToSearch = new ArrayList(getEnabledTagValues());
        for (Entry<EquipmentPropertyTag, TagData> result : results.getResultsPerEquipmentPropertyTags().entrySet()) {
            EquipmentPropertyTag tag = result.getKey();
            TagData data = result.getValue();
            if (this.eqProperty.getIdentifier().equals(tag.getPropertyType().getIdentifier())) {
                AITagValue0200 tagValue = getTagValue(tag.getTagID());
                if (data.getError() != null) {
                    setTagValueError(data, tagValue);
                } else {
                    setTagValueSuccess(data,tagValue);
                    if (validationsManager.hasValidatorFor(this)) {
                        validationsManager.addValueForValidation(this, tagValue.getComparablePhaseValue());
                    }
                }
                tagValuesToSearch.remove(tagValue);
                if (tagValuesToSearch.isEmpty()) {
                    break;
                }
            }
        }
        if (!tagValuesToSearch.isEmpty()) {
            throw new MESRuntimeException("Automation service did not return read result for " + tagValuesToSearch.size() + " tag values of the "
                    + getProperty() + " equipment property.");
        }
        return results.allValuesOK();
    }



    private void setTagValueError(TagData data, AITagValue0200 tagValue) {
        tagValue.setError(new CategorizedMESException0200(CategoryMsgID0200.IRREPARABLE_EXECUTION_ERROR_CATEGORY, data.getError().getMessage()));
        tagValue.setPhaseValue(null);
        tagValue.setInvalidValue(true);
        tagValue.setHasValueBeenRead(false);
    }
    /***
     * @param value the source value
     * @param precision the precision need to be set
     * @return the value after precision adjustment
     * @author Rey Wei Wang 2022.07.26
     *
     */
    public BigDecimal precisionAdjustment(BigDecimal value, Integer precision){
        BigDecimal newValue = value.setScale(precision, RoundingMode.HALF_UP);
        return newValue;
    }
    private void setTagValueSuccess(TagData data, AITagValue0200 tagValue,Integer precision) throws FTException {
        tagValue.setInvalidValue(false);

        /***
         Here the value obtained from the equipment is adjusted, this value will be used to display on the UI and ensure
         that the same result is maintained as in the database.
         * @author Rey Wei Wang 2022.07.26
         */
        Object value = data.getValue();
        if( value instanceof String ) {
            /**
             * if the value is a string do nothing
             */
            tagValue.setPhaseValueFromAIValue(data.getValue());
        }
        else if( value instanceof Boolean ) {
            /**
             * if the value is a bool do nothing
             */
            tagValue.setPhaseValueFromAIValue(data.getValue());
        }
        else {
            if(!value.equals(null)) {
                /**
                 * The oldValue ,the value read from the equipment,the source value.
                 */
                BigDecimal oldValue = new BigDecimal(data.getValue().toString());
                /**
                 * The newValue ,the value adjusted with the precision configured in th phase.
                 */
                BigDecimal newValue = precisionAdjustment(oldValue,precision);
                /**
                 * The newValue will be used to display  in grid.
                 */
                tagValue.setPhaseValueFromAIValue(newValue);
            }
            else {
                tagValue.setPhaseValueFromAIValue(data.getValue());
            }
        }
        tagValue.setHasValueBeenRead(true);

//        tagValue.setTimestamp(convertDateToTime(data.getTimestamp()));
        System.out.println(PCContext.getFunctions().getDBTime());
        System.out.println(convertDateToTime(data.getTimestamp()));
        tagValue.setTimestamp(PCContext.getFunctions().getDBTime());
    }

    private void setTagValueSuccess(TagData data, AITagValue0200 tagValue) throws FTException {
        tagValue.setInvalidValue(false);
        tagValue.setPhaseValueFromAIValue(data.getValue());
        tagValue.setHasValueBeenRead(true);
//        tagValue.setTimestamp(convertDateToTime(data.getTimestamp()));
        tagValue.setTimestamp(PCContext.getFunctions().getDBTime());
    }

    /**
     * @param tagID tag identifier
     * @return if tag value was read successfully from the automation integration layer
     */
    public boolean isAutomationGetSuccessful(String tagID) {
        AITagValue0200 tagValue = getTagValueNullExcept(tagID);
        // we are interested whether the tag value was successfully read from the automation layer; true if:
        // 1. the value is not enabled; it does not concern us
        // 2. the value is set and it is not overwritten manually
        return !tagValue.isEnabled() || (tagValue.getPhaseValue() != null && !tagValue.isManuallyOverridden());
    }

    /**
     * @return true if a value has to be read from the Automation Layer, false if there is no need to read the value
     */
    public boolean hasValuesToBeRead() {
        if (eqProperty != null) {
            for (AITagValue0200 value : getEnabledTagValues()) {
                boolean valueMustBeRead = value.hasTagPath() && !value.isManuallyOverridden() && !value.isHasValueBeenRead();
                if (valueMustBeRead) {
                    return true;
                }
            }
        }
        return false;
    }

}
