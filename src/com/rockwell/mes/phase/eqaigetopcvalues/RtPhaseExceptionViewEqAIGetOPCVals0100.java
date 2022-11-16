package com.rockwell.mes.phase.eqaigetopcvalues;

import com.rockwell.mes.apps.ebr.ifc.phase.IPhaseExecutor.Status;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIExceptionView0200;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.ui.AITableRowModel0200;
import com.rockwell.mes.shared.ai.ui.OverrideNumericValueOnlyPanel0200;
import com.rockwell.mes.shared.ai.ui.OverrideValuePanel0200;

/**
 * 
 * Exception view for the Get OPC Values phase
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class RtPhaseExceptionViewEqAIGetOPCVals0100 extends AbstractPhaseEqAIExceptionView0200<RtPhaseModelEqAIGetOPCVals0100> {

    /** generated field */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param theModel the phase model
     */
    public RtPhaseExceptionViewEqAIGetOPCVals0100(AbstractPhaseEqAIModel0200 theModel) {
        super(theModel);
    }

    @Override
    protected OverrideValuePanel0200 createOverrideNumericValuePanel(AITableRowModel0200 rowModel, Status status) {
        return new OverrideNumericValueOnlyPanel0200(rowModel, status);
    }

}
