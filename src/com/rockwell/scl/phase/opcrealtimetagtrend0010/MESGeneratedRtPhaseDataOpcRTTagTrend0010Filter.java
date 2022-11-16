package com.rockwell.scl.phase.opcrealtimetagtrend0010;

/**
 * This file is generated by the PhaseLibManager
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
public abstract class MESGeneratedRtPhaseDataOpcRTTagTrend0010Filter extends MESATObjectFilter  {

    /** Generated attribute definition */
    private static final long serialVersionUID = 1L;

    /** Generated attribute definition */
    private static final String ATDEFINITION_NAME = "SV_PhDatOpcRTTagTrend0010";

    /**
     * Generated method definition
     *
     * @param server The Server object
     */
    public MESGeneratedRtPhaseDataOpcRTTagTrend0010Filter(Server server) {
        super(server, ATDEFINITION_NAME);
    }

    /**
     * Generated method definition
     *
     */
    public MESGeneratedRtPhaseDataOpcRTTagTrend0010Filter() {
        super(PCContext.getServerImpl(), ATDEFINITION_NAME);
    }

    /**
     * Generated method definition
     *
     * @return the list of the objects
     */
    @Override     
    public List<MESRtPhaseDataOpcRTTagTrend0010> getFilteredObjects () {
        return MESATObject.getFilteredMESATObjectList(this, MESRtPhaseDataOpcRTTagTrend0010.class);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forParentEqualTo(IMESRtPhase value) //
            throws DatasweepException {
        String columnName = MESRtPhaseDataOpcRTTagTrend0010.COL_NAME_PARENT;
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameEqualTo(columnName, Long.valueOf(value.getKey()));
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forParentNotEqualTo(IMESRtPhase value) //
            throws DatasweepException {
        String columnName = MESRtPhaseDataOpcRTTagTrend0010.COL_NAME_PARENT;
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameNotEqualTo(columnName, Long.valueOf(value.getKey()));
    }



    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentIdEqualTo(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameEqualTo("SV_equipmentId", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentIdNotEqualTo(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameNotEqualTo("SV_equipmentId", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentIdContaining(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameContaining("SV_equipmentId", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentIdStartingWith(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameStartingWith("SV_equipmentId", value);
    }


    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentPropertyEqualTo(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameEqualTo("SV_equipmentProperty", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentPropertyNotEqualTo(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameNotEqualTo("SV_equipmentProperty", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentPropertyContaining(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameContaining("SV_equipmentProperty", value);
    }

    /**
     * Generated method definition
     *
     * @param value the value to be filtered for
     * @return the filter object
     * @throws DatasweepException the PC exception
     */
    public MESRtPhaseDataOpcRTTagTrend0010Filter forEquipmentPropertyStartingWith(String value) //
            throws DatasweepException {
        return (MESRtPhaseDataOpcRTTagTrend0010Filter) forColumnNameStartingWith("SV_equipmentProperty", value);
    }

}
