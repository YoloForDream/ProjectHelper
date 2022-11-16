package com.rockwell.mes.phase.eqaigetopcvalues;

import com.rockwell.mes.clientfw.commons.ifc.view.activities.StandardColumnConfig;
import com.rockwell.mes.clientfw.commons.ifc.view.activities.StandardColumnConfig.LineType;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIModel0200;
import com.rockwell.mes.shared.ai.AbstractPhaseEqAIView0200;
import com.rockwell.mes.shared.ai.TagIDMsgIDMap0200;
import com.rockwell.mes.shared.ai.ui.AITableRowModel0200;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * view for the Get OPC Values phase
 * <p>
 * 
 * @author mkolev, (c) Copyright 2016 Rockwell Automation Technologies, Inc. All Rights Reserved.
 */
public class RtPhaseViewEqAIGetOPCVals0100 extends AbstractPhaseEqAIView0200<RtPhaseModelEqAIGetOPCVals0100> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param theModel the model
     */
    protected RtPhaseViewEqAIGetOPCVals0100(AbstractPhaseEqAIModel0200 theModel) {
        super(theModel);
    }

    @Override
    protected List<StandardColumnConfig> createColumnConfig() {
        List<StandardColumnConfig> columnConfig = new ArrayList<StandardColumnConfig>();
        columnConfig.add(new StandardColumnConfig(AITableRowModel0200.PROPERTY_PROPERTY, WIDTH_PROPERTY_COLUMN, LineType.SINGLE_LINE));
        columnConfig.add(new StandardColumnConfig(AITableRowModel0200.EXPECTED_VALUE_PROPERTY, WIDTH_VALUE_COLUMN_WIDE, LineType.MULTI_LINE,
                SwingConstants.RIGHT));
        columnConfig.add(
                new StandardColumnConfig(AITableRowModel0200.LOW_LIMITS_PROPERTY, WIDTH_LIMITS_COLUMN, LineType.MULTI_LINE, SwingConstants.RIGHT));
        columnConfig
                .add(new StandardColumnConfig(TagIDMsgIDMap0200.VAL_PROPERTY, WIDTH_VALUE_COLUMN_WIDE, LineType.MULTI_LINE, SwingConstants.RIGHT));
        columnConfig.add(
                new StandardColumnConfig(AITableRowModel0200.HIGH_LIMITS_PROPERTY, WIDTH_LIMITS_COLUMN, LineType.MULTI_LINE, SwingConstants.RIGHT));
        columnConfig.add(new StandardColumnConfig(AITableRowModel0200.UOM_PROPERTY, WIDTH_UOM_COLUMN, LineType.SINGLE_LINE));
        columnConfig.add(new StandardColumnConfig(AITableRowModel0200.TIMESTAMP_PROPERTY, WIDTH_TIMESTAMP_COLUMN_SINGLE, LineType.SINGLE_LINE));
        return columnConfig;
    }

}
