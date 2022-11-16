package com.rockwell.mes.phase.eqaigetopcvalues.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.datasweep.plantops.common.constants.IDataTypes;
import com.datasweep.plantops.common.utility.DataConverter;
import com.rockwell.mes.commons.base.ifc.exceptions.MESRuntimeException;
import com.rockwell.mes.commons.base.ifc.functional.BigDecimalUtilities;
import com.rockwell.mes.commons.base.ifc.i18n.I18nMessageUtility;
import com.rockwell.mes.commons.base.ifc.utility.ObjectFactory;
import com.rockwell.mes.services.batchreport.ifc.IBatchProductionRecordDocumentWrapper.IProcessDataWrapper;
import com.rockwell.mes.services.s88equipment.ifc.EnumEqmPropertyLiveDataTypeNumeric;
import com.rockwell.mes.services.batchreport.ifc.IMESB2MMLJRDataSource;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIExecutor0200;
import com.rockwell.mes.shared.ai.ui.CommonReportScripletEqAIPhases0200;
import com.rockwell.mes.shared.ai.ui.NumericStringConversionHelper0200;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * Scriptlet to support creation of the phase specific sub report.
 * <p>
 * 
 * @author ikoleva, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class ReportScriptletEqAIGetOPCValues0100 extends CommonReportScripletEqAIPhases0200 {

    /**
     * convert the phase data to suitable JRDataSource
     * 
     * @param data phase data collection
     * @return converted datasource
     */
    public JRDataSource getDataSource(Collection<IProcessDataWrapper> data) {
        List<IProcessDataWrapper> dataList = new ArrayList<>();
        dataList.addAll(data);
        // we need the data in the order as in the RD. The BatchDocumentWrapper's method getProcessDataOfPhase return
        // the data in descending order by default so we reverse the dataList
        Collections.reverse(dataList);
        final Object[] beanCollection = new Object[] { dataList };
        IMESB2MMLJRDataSource ds =
                ObjectFactory.getInstance().createObject(IMESB2MMLJRDataSource.class, new Class[] { Collection.class }, beanCollection);
        return ds;
    }

    /**
     * Get the specified field from the data source. Also apply formatting, the report design is much lighter and easy
     * to read since you can just call $P{REPORT_SCRIPTLET}.getField($P{REPORT_DATA_SOURCE}, "fieldName") for each field
     * you need
     * 
     * @param ds data source
     * @param fieldID field name
     * @return the localized value
     */
    public String getField(JRDataSource ds, String fieldID) {

        // first check if we look for a value
        // for the boolean value we need the localized string
        short propertyDataType = getPropertyDataType(ds);
        if ("value".equals(fieldID)) {
            // value is enabled - return the actual value
            switch (propertyDataType) {
            case IDataTypes.TYPE_DECIMAL:
                return defaultString(formatNumberString(getProcessValue(ds, "numericValue")));
            case IDataTypes.TYPE_BOOLEAN:
                // booleans are localized
                return defaultString(getBooleanString(getProcessValue(ds, "booleanValue"), true));
            case IDataTypes.TYPE_STRING:
                return defaultString(getProcessValue(ds, "stringValue"));
            default:
                throw new MESRuntimeException("unexpected internal bundle identifier " + propertyDataType);
            }
        }
        return defaultString(getProcessValue(ds, fieldID));
    }

    private String defaultString(String value) {
        return StringUtils.isEmpty(value) ? getNAString() : value;
    }

    /**
     * Get the timestamp field from the data source and apply formatting
     * 
     * @param ds data source
     * @param dateFormat the date format that should be used
     * @return the localized value
     */
    public String getTimestampField(JRDataSource ds, String dateFormat) {
        if ("true".equals(getProcessValue(ds, "isValueOverridden"))) {
            // we use a special value for manually overridden properties
            return I18nMessageUtility.getLocalizedMessage(AbstractPhaseEqAIExecutor0200.MSGPACK, "ManualTimestamp_Msg");
        }
        String fieldID = "timestampValue";
        String timeStamp = getProcessValue(ds, fieldID);
        if (StringUtils.isEmpty(timeStamp)) {
            return getNAString();
        }
        return MessageFormat.format("{0, date, " + dateFormat + "}", getDate(timeStamp));
    }

    /**
     * @param ds data source
     * @return if the specified data source supports limits
     */
    public boolean supportsLimits(JRDataSource ds) {
        return getPropertyDataType(ds) == IDataTypes.TYPE_DECIMAL;
    }

    /**
     * @param ds data source
     * @return if the specified data source supports expected value
     */
    public boolean supportsExpectedValue(JRDataSource ds) {
        return getPropertyDataType(ds) == IDataTypes.TYPE_BOOLEAN || getPropertyDataType(ds) == IDataTypes.TYPE_STRING;
    }

    private String formatNumberString(String stringValue) {
        try {
            // Use default Locale to convert non localized string value to BigDecimal value
            BigDecimal value = BigDecimalUtilities.fromString(stringValue, getDefaultDecimalFormat(), true);
            return NumericStringConversionHelper0200.convertBigDecimalToString(value, EnumEqmPropertyLiveDataTypeNumeric.DOUBLE, StringUtils.EMPTY);
        } catch (ParseException e) {
            LOGGER.error("Parse error", e);
        }
        return StringUtils.EMPTY;
    }

    private static DecimalFormat getDefaultDecimalFormat() {
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        df.setParseBigDecimal(true);
        df.setMaximumFractionDigits(DataConverter.BD_MAX_SCALE);
        return df;
    }

    /**
     * @param ds data source
     * @param expectedValue expected value
     * @return formatted expected value
     */
    public String formatExpectedValue(JRDataSource ds, String expectedValue) {
        if (getPropertyDataType(ds) == IDataTypes.TYPE_BOOLEAN) {
            return getBooleanString(expectedValue, true);
        } else {
            return expectedValue;
        }
    }

    /**
     * @param first LL limit
     * @param second L limit
     * @param firstEnabled whether LLHH check is enabled
     * @param secondEnabled whether LH check is enabled
     * @return formatted string
     */
    public String formatLimits(String first, String second, boolean firstEnabled, boolean secondEnabled) {
        StringBuilder builder = new StringBuilder();
        if (firstEnabled && StringUtils.isNotEmpty(first)) {
            builder.append(formatNumberString(first));
        } else {
            builder.append("---");
        }

        builder.append("|");
        if (secondEnabled && StringUtils.isNotEmpty(second)) {
            builder.append(formatNumberString(second));
        } else {
            builder.append("---");
        }
        return builder.toString();
    }

    private String getBooleanString(String phaseValue, boolean nullToNAString) {
        if ("".equals(phaseValue)) {
            // the case when the value is not enabled for boolean bundle (unlikely to ever happen but it doesn't hurt to
            // check)
            if (nullToNAString) {
                return getNAString();
            } else {
                // if null, return no message
                return I18nMessageUtility.getI18NValue(false);
            }
        } else if ("true".equals(phaseValue)) {
            return I18nMessageUtility.getI18NValue(true);
        } else {
            return I18nMessageUtility.getI18NValue(false);
        }
    }

    private short getPropertyDataType(JRDataSource ds) {
        String propertyDataTypeStr = ((IProcessDataWrapper) ((IMESB2MMLJRDataSource) ds).getCurrentBean()).getProcessValue("propertyDataType");
        if (StringUtils.isNotEmpty(propertyDataTypeStr)) {
            return Short.parseShort(propertyDataTypeStr);
        } else {
            return IDataTypes.TYPE_NODATATYPE;
        }
    }

    private String getProcessValue(JRDataSource ds, String fieldID) {
        return ((IProcessDataWrapper) ((IMESB2MMLJRDataSource) ds).getCurrentBean()).getProcessValue(fieldID);
    }
}
