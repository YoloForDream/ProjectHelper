package com.rockwell.mes.phase.eqaigetopcvalues.datahandler;

import com.datasweep.plantops.common.constants.IDataTypes;
import com.rockwell.mes.commons.base.ifc.exceptions.MESRuntimeException;

/**
 * 
 * data handler factory for getting the correct handler per IDataTypes
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class DataHandlerFactory0100 {

    /**
     * get a handler for specific data type
     * 
     * @param dataType the IDataType requested
     * @return data handler
     */
    public static IDataHandler0100 getHandler(short dataType) {
        switch (dataType) {
        case IDataTypes.TYPE_DECIMAL:
            return NumericDataHandler0100.getInstance();
        case IDataTypes.TYPE_BOOLEAN:
            return BooleanDataHandler0100.getInstance();
        case IDataTypes.TYPE_STRING:
            return StringDataHandler0100.getInstance();
        default:
            throw new MESRuntimeException("unexpected internal bundle identifier " + dataType);
        }
    }
}
