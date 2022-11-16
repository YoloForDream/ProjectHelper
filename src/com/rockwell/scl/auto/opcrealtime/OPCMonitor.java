package com.rockwell.scl.auto.opcrealtime;

import java.util.List;

public interface OPCMonitor {
    public void updateData(List<Double>[] data,String equipmentProperty);

    public OPCTagMonitorModel getModel();
}
