package com.rockwell.mes.phase.eqaigetopcvalues;

/**
 * 
 * common functions for creating output names
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class RtPhaseOutputNameHelper0100 {

    private RtPhaseOutputNameHelper0100() {

    }

    private static final String OUTPUT_BUNDLE_PREFIX = "bundle";

    private static final String VALUE = "Value";

    private static final String UNIT_OF_MEASURE = "Unit of measure";

    private static final String AUTOMATION_GET_SUCCESS = "Automation get successful";



    /**
     * 
     * @param fromName the property name
     * @return value output name
     */
    public static String createValueOutputName(String fromName) {
        return fromName + " " + VALUE;
    }


    /**
     * 
     * @param fromName the property name
     * @return automationGetSuccess output name
     */
    public static String createAutomationGetSuccessOutputName(String fromName) {
        return fromName + " " + AUTOMATION_GET_SUCCESS;
    }

    /**
     * 
     * @param fromName the property name
     * @return unit of measure output name
     */
    public static String createUnitOfMeasureOutputName(String fromName) {
        return fromName + " " + UNIT_OF_MEASURE;
    }

    /**
     * 
     * @param bundleNumber the bundle number
     * @return value output key
     */
    public static String createValueOutputKey(int bundleNumber) {
        return noSpace(OUTPUT_BUNDLE_PREFIX + bundleNumber + VALUE);
    }


    /**
     * 
     * @param bundleNumber the bundle number
     * @return automationGetSuccess output key
     */
    public static String createAutomationGetSuccessOutputKey(int bundleNumber) {
        return noSpace(OUTPUT_BUNDLE_PREFIX + bundleNumber + AUTOMATION_GET_SUCCESS);
    }
    
    /**
     * 
     * @param bundleNumber the bundle number
     * @return unit of measure output key
     */
    public static String createUnitOfMeasureOutputKey(int bundleNumber) {
        return noSpace(OUTPUT_BUNDLE_PREFIX + bundleNumber + UNIT_OF_MEASURE);
    }

    /**
     * the output key does not allow spaces, we need to replace them to match the regex (it is created from display
     * name)
     * 
     * @param str
     * @return strings with "_" instead " "
     */
    private static String noSpace(String str) {
        return str.replaceAll(" ", "_");
    }

}