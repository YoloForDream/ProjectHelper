package com.rockwell.scl.auto.heartbeat;

import java.util.List;

/**
 * @author RWang18
 */
public interface ChartMonitor {
    public HeartMonitorModel getModel();
    public void updateData(List<Double>[] data);
}
