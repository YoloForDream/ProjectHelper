package com.rockwell.scl.phase.opcrealtimetagtrend0010.opchelper;

import java.util.List;

public interface OPCMonitor {
    public void updateData(List<Double>[] data,String equipmentProperty);
    public OPCTagMonitorModel getModel();
}
