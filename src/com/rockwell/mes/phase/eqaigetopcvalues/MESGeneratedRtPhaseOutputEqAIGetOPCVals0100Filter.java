package com.rockwell.mes.phase.eqaigetopcvalues;

/**
 * This file is generated by the PhaseGenerator
 *
 * Please do not modify this file manually !!
 */
import java.util.List;

import com.datasweep.compatibility.client.DatasweepException;
import com.datasweep.compatibility.client.Server;
import com.rockwell.mes.commons.base.ifc.objects.MESATObject;
import com.rockwell.mes.commons.base.ifc.objects.MESATObjectFilter;
import com.rockwell.mes.commons.base.ifc.services.PCContext;
import com.rockwell.mes.services.s88.ifc.execution.IMESRtPhase;



/**
 * Generated class definition
 */
public abstract class MESGeneratedRtPhaseOutputEqAIGetOPCVals0100Filter extends MESATObjectFilter  {

    /** Generated attribute definition */
    private static final long serialVersionUID = 1L;

    /** Generated attribute definition */
    private static final String ATDEFINITION_NAME = "RS_PhOutEqAIGetOPCVals0100";

    /**
     * Generated method definition
     *
     * @param server The Server object
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100Filter(Server server) {
        super(server, ATDEFINITION_NAME);
    }

    /**
     * Generated method definition
     *
     */
    public MESGeneratedRtPhaseOutputEqAIGetOPCVals0100Filter() {
        super(PCContext.getServerImpl(), ATDEFINITION_NAME);
    }

    /**
     * Generated method definition
     *
     * @return the list of the objects
     */
    @Override
    public List<MESRtPhaseOutputEqAIGetOPCVals0100> getFilteredObjects () {
        return MESATObject.getFilteredMESATObjectList(this, MESRtPhaseOutputEqAIGetOPCVals0100.class);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forParentEqualTo(IMESRtPhase value) //
            throws DatasweepException {
        String columnName = MESRtPhaseOutputEqAIGetOPCVals0100.COL_NAME_PARENT;
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameEqualTo(columnName, Long.valueOf(value.getKey()));
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forParentNotEqualTo(IMESRtPhase value) //
            throws DatasweepException {
        String columnName = MESRtPhaseOutputEqAIGetOPCVals0100.COL_NAME_PARENT;
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameNotEqualTo(columnName, Long.valueOf(value.getKey()));
    }



    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forOutputValuesSerializEqualTo(byte[] value) //
            throws DatasweepException {
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameEqualTo("RS_outputValuesSerializ", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forOutputValuesSerializNotEqualTo(byte[] value) //
            throws DatasweepException {
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameNotEqualTo("RS_outputValuesSerializ", value);
    }


    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forAllGetSuccessfulEqualTo(Boolean value) //
            throws DatasweepException {
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameEqualTo("RS_allGetSuccessful", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseOutputEqAIGetOPCVals0100Filter forAllGetSuccessfulNotEqualTo(Boolean value) //
            throws DatasweepException {
        return (MESRtPhaseOutputEqAIGetOPCVals0100Filter) forColumnNameNotEqualTo("RS_allGetSuccessful", value);
    }

}
