package com.rockwell.mes.phase.eqaigetopcvalues;

import com.datasweep.plantops.common.constants.IDataTypes;
import com.rockwell.mes.commons.base.ifc.exceptions.MESRuntimeException;

/**
 * Parameter-Bundle types for the Set OPC Values phase
 * <p>
 * 
 * @author mkolev, (c) Copyright 2014 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public enum ParameterBundleType0100 {

    /** Measured Value Bundle type */
    NUMERIC_TYPE(RtPhaseModelEqAIGetOPCVals0100.USAGE_IDENTIFIER_NUMERIC_BUNDLE, IDataTypes.TYPE_DECIMAL),

    /** Boolean Value Bundle type */
    BOOLEAN_TYPE(RtPhaseModelEqAIGetOPCVals0100.USAGE_IDENTIFIER_BOOL_BUNDLE, IDataTypes.TYPE_BOOLEAN),

    /** String Value Bundle type */
    STRING_TYPE(RtPhaseModelEqAIGetOPCVals0100.USAGE_IDENTIFIER_STRING_BUNDLE, IDataTypes.TYPE_STRING);

//    /** Long Value Bundle type */
//    LONG_TYPE(RtPhaseModelEqAIGetOPCVals0100.PRECISION_SETTING, IDataTypes.TYPE_LONG);

    private String identifier;

    private short dataType;

    /**
     * default constructor
     * 
     * @param theBundleIdentifier bundle identifier
     * @param theDataType bundle IDataTypes type
     */
    ParameterBundleType0100(String theBundleIdentifier, short theDataType) {
        identifier = theBundleIdentifier;
        dataType = theDataType;
    }

    /**
     * @return the bundle identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return the IDataType for bundle identifier
     */
    public short getIDataType() {
        return dataType;
    }

    /**
     * Gets the enum value for the given internal bundle identifier
     * 
     * @param theIdentifier identifier
     * @return corresponding ParameterBundleType
     */
    public static ParameterBundleType0100 fromIdentifier(String theIdentifier) {
        for (ParameterBundleType0100 bundleType : values()) {
            if (bundleType.getIdentifier().equals(theIdentifier)) {
                return bundleType;
            }
        }
        throw new MESRuntimeException("unknown internal bundle identifier " + theIdentifier);
    }
}